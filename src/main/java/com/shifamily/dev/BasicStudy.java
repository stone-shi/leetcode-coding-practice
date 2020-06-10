package com.shifamily.dev;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.FREE_MEM;

import javax.management.remote.rmi._RMIConnection_Stub;
import javax.security.auth.login.CredentialException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class BasicStudy {

    protected List<Object[]> parametersList = new ArrayList<>();
    protected List<Object> answersList = new ArrayList<>();
    protected List<Boolean> answersOrderMatter = new ArrayList<>();
    protected List<Comparator> answersComparator = new ArrayList<>();

    protected Object convertReturn(Object r){
        return r;
    }

    private int[] ListToArrayInt(List r){
        if (!r.get(0).getClass().getName().equals("Integer"))
            return null;

        int[] res = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            res[i] = (int)r.get(i);
        }
        return res;
    }

    private Object[] listToArray(List r){
        if (r.isEmpty())
            return new Object[0];
        Object[] res = new Object[r.size()];
        if (r.get(0) instanceof List){
            for (int i = 0; i < r.size(); i++) {
                List rsub = (List)r.get(i);
                Object[] sub = new Object[rsub.size()];
                res[i] = rsub.toArray(sub);
            }

        }else{
            r.toArray(res);
        }

        return res;
    }

    protected boolean compareArray(Object r, Object a, boolean orderMatter, Comparator comparator) {

        String aClass = a.getClass().getName();
        String rClass = r.getClass().getName();
        Object[] o1;
        Object[] o2;
        if (aClass.equals("[I")){
            o1 = Arrays.stream((int[])a).boxed().toArray();
        }else if (aClass.equals("[D")){
            o1 = Arrays.stream((double[])a).boxed().toArray();
        }else{
            o1 = (Object[])a;
        }


        if (r instanceof List){
            o2 = listToArray((List)r);
        }
        else if (rClass.equals("[I")){
            o2 = Arrays.stream((int[])r).boxed().toArray();
        }else if (aClass.equals("[D")){
            o2 = Arrays.stream((double[])r).boxed().toArray();
        }else{
            o2 = (Object[])r;
        }


        if (!orderMatter){
            if (comparator == null) {
                Arrays.sort(o1);
                Arrays.sort(o2);
            }else{
                Arrays.sort(o1, comparator);
                Arrays.sort(o2, comparator);

            }
        }

        return Arrays.deepEquals(o1, o2);


    }

    protected boolean compareAnswer(Object r, Object a, boolean orderMatter, Comparator comparator) {
        if (r == null )
            return a == null;

        if (a == null)
            return false;

        r = convertReturn(r);


        if (a.getClass().isArray()){
            return compareArray(r, a, orderMatter, comparator);
        }


        return  a.equals(r);
    }

    protected void addParameterAndAnswer(Object[] parameters, Object answers) {
        addParameterAndAnswer(parameters, answers, true, null);
    }
    protected void addParameterAndAnswer(Object[] parameters, Object answers, boolean orderMatter) {
        addParameterAndAnswer(parameters, answers, orderMatter, null);
    }
    protected void addParameterAndAnswer(Object[] parameters, Object answers, boolean orderMatter, Comparator comparator) {
        parametersList.add(parameters);
        answersList.add(answers);
        answersOrderMatter.add(orderMatter);
        answersComparator.add(comparator);
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
                    if (!compareAnswer(r, answersList.get(i), answersOrderMatter.get(i), answersComparator.get(i))){
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
