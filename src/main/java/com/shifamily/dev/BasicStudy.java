package com.shifamily.dev;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class BasicStudy {

    protected List<Object[]> parametersList = new ArrayList<>();
    protected List<Object> answersList = new ArrayList<>();
    protected List<Boolean> answersOrderMatter = new ArrayList<>();

    protected boolean compareArray(Object r, Object a, Boolean orderMatter) {

        String rClass = r.getClass().getName();
        String aClass = a.getClass().getName();
        if (!rClass.equals(aClass))
            return false;

        Object[] o1;
        Object[] o2;
        if (aClass.equals("[I")){
            o1 = Arrays.stream((int[])a).boxed().toArray();
            o2 = Arrays.stream((int[])r).boxed().toArray();
        }else if (aClass.equals("[D")){
            o1 = Arrays.stream((double[])a).boxed().toArray();
            o2 = Arrays.stream((double[])r).boxed().toArray();
        }else{
            o1 = (Object[])r;
            o2 = (Object[])a;
        }

        if (!orderMatter){
            Arrays.sort(o1);
            Arrays.sort(o2);
        }

        return Arrays.deepEquals(o1, o2);


    }

    protected boolean compareAnswer(Object r, Object a, Boolean orderMatter) {
        if (r == null )
            return a == null;

        if (a == null)
            return false;

        if (a.getClass().isArray()){
            return compareArray(r, a, orderMatter);
        }

        if (r instanceof List){
            if (!orderMatter)
                Collections.sort((List)r);

            if (a.getClass().isArray()){
                if (!orderMatter)
                    Arrays.sort((Object[])a);
               return r.equals(Arrays.asList((Object[])a));
            }
            if (a instanceof List) {
                if (!orderMatter)
                    Collections.sort((List)a);
                return r.equals(a);
            }
        }

        return  a.equals(r);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers) {
        addParameterAndAnswer(parameters, answers, false);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers, boolean orderMatter) {
        parametersList.add(parameters);
        answersList.add(answers);
        answersOrderMatter.add(orderMatter);
    }

    public  List<RunState> runCases(){

        List<RunState> result = new LinkedList<>();

        Method[] methods = this.getClass().getDeclaredMethods();
        List<Method> runners = new LinkedList<>();
        for (Method method: methods) {
            if (method.isAnnotationPresent(CaseRunner.class)){
                log.info("Found @CaseRunner method: {}", method.getName());
                runners.add(method);
            }
        }

        for (int i = 0; i < parametersList.size(); i++) {
            for (Method m: runners) {
                RunState runStat = new RunState();

                try {
                    runStat.setName(this.getClass().getSimpleName() + "." + m.getName() + "(): case " + i );
                    runStat.setResult("Error");

                    final Runtime rt = Runtime.getRuntime();
                    for (int k = 0; k < 3; k++) rt.gc();
                    final long startSize = rt.totalMemory()-rt.freeMemory();
                    long startTime = System.nanoTime();
                    Object r = m.invoke(this, parametersList.get(i));
                    runStat.setRunTimeInNs(System.nanoTime() - startTime);
                    runStat.setRunMemoryInBytes(rt.totalMemory()-rt.freeMemory()-startSize);
                    if (!compareAnswer(r, answersList.get(i), answersOrderMatter.get(i))){
                        log.error("Error on case {} method {}: expected [{}] got [{}]", i, m.getName(),   answersList.get(i), r);
                    }else
                        runStat.setResult("Pass");

                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("Error to invoke runner ", e);
                }

                result.add(runStat);
            }
        }

        return result;
    }




}
