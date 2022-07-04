package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.CaseRunner;

import java.util.*;
import com.shifamily.dev.*;

public class Leet204CountPrimes extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { 2 }).answer(0)
                .description("Case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 50000 }).answer(5133)
                .description("Case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 10 }).answer(4)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 0 }).answer(0)
                .description("Example 2").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 1 }).answer(0)
                .description("Example 3").build());
        return cases;
    }

    // solution 1 - this can be further optmized to only check sqrt(n)
    @CaseRunner
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;

        boolean[] notPrime = new boolean[n];

        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                for (long j = i; j * i < n; j++) {
                    notPrime[(int) (j * i)] = true;
                }
            }
        }
        return res;
    }

    // solution 2 - only check sqrt(n)
    @CaseRunner
    public int countPrimes2(int n) {
        if (n <= 2)
            return 0;

        boolean[] notPrime = new boolean[n];
        int res = 0;
        long sqn = (long)Math.sqrt(n * 1.0);

        for (int i = 2; i <= sqn; i++ ) {
            if (notPrime[i] == false){
                for (long j = i; j * i < n; j++) {
                    notPrime[(int)(j * i)] = true;
                }
            }
        }

        for (int i = 2; i < notPrime.length; i++) {
            if (notPrime[i] == false)
                res++;
        }
        return res;
    }

}
