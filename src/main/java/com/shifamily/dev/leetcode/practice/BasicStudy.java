package com.shifamily.dev.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class BasicStudy {

    protected List<Object[]> parametersList = new ArrayList<>();
    protected List<Object> answersList = new ArrayList<>();
    protected List<Boolean> answersOrderMatter = new ArrayList<>();
    protected boolean compareAnswer(Object r, Object a, Boolean orderMatter) {
        if (r == null )
            return a == null;

        if (a == null)
            return false;

        if (r.getClass().isArray()){
            if (a.getClass().isArray()){
                if (!orderMatter) {
                    Arrays.sort((Object[]) r);
                    Arrays.sort((Object[]) a);
                }
                return Arrays.deepEquals((Object[])r, (Object[])a);
            }else
                return false;
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




        return  r.equals(a);
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
