package com.shifamily.dev;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class BasicStudy {

    protected List<CaseParameters> caseParameters = new ArrayList<>();
    protected List<ClassCaseParameters> classCaseParameters = new ArrayList<>();

    protected Object convertReturn(Object r) {
        return r;
    }

    protected List<List<Integer>> convertReturnNeedSortListListTwoNumbers(List<List<Integer>> res) {
        Comparator<List<Integer>> c = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> arg0, List<Integer> arg1) {
                return arg0.get(0).equals(arg1.get(0)) ? arg0.get(1) - arg1.get(1) : arg0.get(0) - arg1.get(0);
            }
        };
        Collections.sort(res, c);
        return res;
    }

    private int[] ListToArrayInt(List r) {
        if (!r.get(0).getClass().getName().equals("Integer"))
            return null;

        int[] res = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            res[i] = (int) r.get(i);
        }
        return res;
    }

    private Object[] listToArray(List r) {
        if (r.isEmpty())
            return new Object[0];
        Object[] res = new Object[r.size()];
        if (r.get(0) instanceof List) {
            for (int i = 0; i < r.size(); i++) {
                List rsub = (List) r.get(i);
                Object[] sub = new Object[rsub.size()];
                res[i] = rsub.toArray(sub);
            }

        } else {
            r.toArray(res);
        }

        return res;
    }

    protected boolean compareArray(Object r, Object a, boolean orderMatter, Comparator comparator) {

        String aClass = a.getClass().getName();
        String rClass = r.getClass().getName();
        Object[] o1;
        Object[] o2;
        if (aClass.equals("[I")) {
            o1 = Arrays.stream((int[]) a).boxed().toArray();
        } else if (aClass.equals("[D")) {
            o1 = Arrays.stream((double[]) a).boxed().toArray();
        } else if (aClass.equals("[[I")) {
            o1 = new Object[((int[][]) a).length];
            int i = 0;
            for (int[] o : (int[][]) a)
                o1[i++] = Arrays.stream(o).boxed().toArray();

        } else {
            o1 = (Object[]) a;
        }

        if (r instanceof List) {
            o2 = listToArray((List) r);
        } else if (rClass.equals("[I")) {
            o2 = Arrays.stream((int[]) r).boxed().toArray();
        } else if (aClass.equals("[D")) {
            o2 = Arrays.stream((double[]) r).boxed().toArray();
        } else {
            o2 = (Object[]) r;
        }

        if (!orderMatter) {
            if (comparator == null) {
                Arrays.sort(o1);
                Arrays.sort(o2);
            } else {
                Arrays.sort(o1, comparator);
                Arrays.sort(o2, comparator);

            }
        }

        return Arrays.deepEquals(o1, o2);
    }

    protected boolean compareAnswer(Object r, Object a, boolean orderMatter, Comparator comparator) {
        if (r == null)
            return a == null;

        if (a == null)
            return false;

        r = convertReturn(r);

        if (a.getClass().isArray()) {
            return compareArray(r, a, orderMatter, comparator);
        }

        return a.equals(r);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers) {
        addParameterAndAnswer(parameters, answers, true, null, null, -1);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers, boolean orderMatter) {
        addParameterAndAnswer(parameters, answers, orderMatter, null, null, -1);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers, boolean orderMatter,
            Comparator comparator) {
        addParameterAndAnswer(parameters, answers, orderMatter, null, null, -1);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers, boolean orderMatter,
            Comparator comparator, String desc, int answerInplace) {
        if (desc == null) {
            desc = this.getClass().getName() + caseParameters.size();
        }
        CaseParameters param = CaseParameters.builder().parameters(parameters).answer(answers)
                .answersOrderMatter(orderMatter).answersComparator(comparator).description(desc)
                .answerInPlaceIndex(answerInplace).build();
        caseParameters.add(param);
    }

    private void prepareLocalCaseData(List<CaseParameters> caseParameters,
            List<ClassCaseParameters> classCaseParameters) {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CaseData.class) && caseParameters != null) {
                log.info("Found @CaseData method: {}", method.getName());
                try {
                    Object res = method.invoke(this);
                    if (res instanceof List) {
                        caseParameters.addAll((List<CaseParameters>) res);
                        log.info("{} Case added", ((List<CaseParameters>) res).size());
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    log.error("Error to invoke runner ", e);
                }
            } else if (method.isAnnotationPresent(ClassCaseData.class) && classCaseParameters != null) {
                log.info("Found @ClassCaseData method: {}", method.getName());
                try {
                    Object res = method.invoke(this);
                    if (res instanceof List) {
                        classCaseParameters.addAll((List<ClassCaseParameters>) res);
                        log.info("{} Case added", ((List<ClassCaseParameters>) res).size());
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    log.error("Error to invoke runner ", e);
                }
            }
        }
    }

    private void runOneCase(Method m, CaseParameters c, RunState runStat) {
        try {
            runStat.setResult("Error");
            final Runtime rt = Runtime.getRuntime();
            for (int k = 0; k < 3; k++)
                rt.gc();
            final long startSize = rt.totalMemory() - rt.freeMemory();
            long startTime = System.nanoTime();
            Object r = m.invoke(this, c.getParameters());
            // in case our answer is some in-place change
            if (c.getAnswerInPlaceIndex() != -1)
                r = c.getParameters()[c.getAnswerInPlaceIndex()];
            runStat.setRunTimeInNs(System.nanoTime() - startTime);
            runStat.setRunMemoryInBytes(rt.totalMemory() - rt.freeMemory() - startSize);
            if (!compareAnswer(r, c.getAnswer(), c.getAnswersOrderMatter(), c.getAnswersComparator())) {
                log.error("Error on case method {}: expected [{}] got [{}]", m.getName(),
                        c.getAnswer(), r);
            } else
                runStat.setResult("Pass");

        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Error to invoke runner ", e);
        }
    }

    private void runOneClassCase(Class<?> clazz, ClassCaseParameters c, RunState runStat) {
        try {
            runStat.setResult("Error");
            final Runtime rt = Runtime.getRuntime();
            for (int k = 0; k < 3; k++)
                rt.gc();
            final long startSize = rt.totalMemory() - rt.freeMemory();
            long startTime = System.nanoTime();

            Method[] methods = clazz.getDeclaredMethods();
            Map<String, Method> methodMap = new HashMap<>();
            for (Method method : methods)
                methodMap.put(method.getName(), method);

            String[] ops = c.getOperations();
            Object[][] opsPara = c.getOperationParameters();
            Class<?>[] parameterType = new Class[opsPara[0].length];
            for (int i = 0; i < opsPara[0].length; i++) {
                parameterType[i] = opsPara[0][i].getClass();
            }

            Object o = clazz.getDeclaredConstructor(parameterType).newInstance(opsPara[0]);
            List<Object> ret = new LinkedList<>();
            ret.add(null);
            for (int i = 1; i < ops.length; i++) {
                Method m = methodMap.get(ops[i]);
                if (m == null) {
                    log.error("Error while looking for method [{}]", ops[i]);
                }
                Object r = m.invoke(o, opsPara[i]);
                ret.add(r);
            }
            runStat.setRunTimeInNs(System.nanoTime() - startTime);
            runStat.setRunMemoryInBytes(rt.totalMemory() - rt.freeMemory() - startSize);

            if (!compareAnswer(ret.toArray(), c.getAnswer(), c.getAnswersOrderMatter(), c.getAnswersComparator())) {
                log.error("Error on case method {}: expected [{}] got [{}]", clazz.getName(),
                        c.getAnswer(), ret);
            } else
                runStat.setResult("Pass");

        } catch (Exception e) {
            log.error("Error to invoke runner ", e);
        }
    }

    private List<RunState> runClassCase() {
        List<RunState> result = new LinkedList<>();
        Class<?>[] classes = this.getClass().getClasses();
        List<Class<?>> runners = new LinkedList<>();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(CaseRunner.class)) {
                log.info("Found @CaseRunner class: {} ", clazz.getName());
                runners.add(clazz);
            }
        }
        for (Class<?> clazz : runners) {
            List<ClassCaseParameters> localCase = new LinkedList<>();
            prepareLocalCaseData(null, localCase);
            localCase.addAll(classCaseParameters);
            int i = 0;
            for (ClassCaseParameters c : localCase) {
                CaseRunner r = clazz.getAnnotation(CaseRunner.class);
                if (!r.value().isEmpty() && !r.value().equals(c.getOperations()[0]))
                    continue;
                RunState runStat = new RunState();
                runStat.setName(this.getClass().getSimpleName() + "." + clazz.getName() + "(): case " + i++ + ' '
                        + c.getDescription());
                runOneClassCase(clazz, c, runStat);
                result.add(runStat);
            }
        }
        return result;
    }

    private List<RunState> runMethodCase() {
        List<RunState> result = new LinkedList<>();

        Method[] methods = this.getClass().getDeclaredMethods();
        List<Method> runners = new LinkedList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CaseRunner.class)) {
                log.info("Found @CaseRunner method: {}", method.getName());
                runners.add(method);
            }
        }

        for (Method m : runners) {
            List<CaseParameters> localCase = new LinkedList<>();
            prepareLocalCaseData(localCase, null);
            localCase.addAll(caseParameters);
            int i = 0;
            for (CaseParameters c : localCase) {
                RunState runStat = new RunState();
                runStat.setName(this.getClass().getSimpleName() + "." + m.getName() + "(): case " + i++ + ' '
                        + c.getDescription());
                runOneCase(m, c, runStat);
                result.add(runStat);
            }
        }

        return result;
    }

    public List<RunState> runCases() {
        List<RunState> result = new LinkedList<>();
        result.addAll(runMethodCase());
        result.addAll(runClassCase());
        return result;
    }
}
