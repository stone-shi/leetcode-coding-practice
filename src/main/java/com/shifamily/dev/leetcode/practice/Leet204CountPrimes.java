package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.CaseRunner;

import java.util.*;
import com.shifamily.dev.*;

public class Leet204CountPrimes {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { 10 }).answer(4)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 0 }).answer(0)
                .description("Example 2").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 1 }).answer(0)
                .description("Example 3").build());
        return cases;
    }

    @CaseRunner
    public int countPrimes(int n) {
        return 0;
    }

}
