package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1776CarFleetII extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 2 }, { 2, 1 }, { 4, 3 }, { 7, 2 } } })
                .answer(new Double[] { 1.00000, -1.00000, 3.00000, -1.00000 })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 3, 4 }, { 5, 4 }, { 6, 3 }, { 9, 1 } } })
                .answer(new Double[] { 2.00000, 1.00000, 1.50000, -1.00000 })
                .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public double[] getCollisionTimes(int[][] cars) {
        return null;

    }

}
