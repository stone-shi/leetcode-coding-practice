package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1776CarFleetII extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 4 }, { 4, 5 }, { 7, 1 }, { 13, 4 }, { 14, 3 }, { 15, 2 },
                        { 16, 5 }, { 19, 1 } } })
                .answer(new Double[] { 2.00000, 0.75000, -1.00000, 1.00000, 1.00000, 4.00000, 0.75000, -1.00 })
                .description("Case 0").build());
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
        int n = cars.length;
        double[] res = new double[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            int s = cars[i][1];
            int p = cars[i][0];
            res[i] = -1.0;

            while (stack.size() > 0) {
                int j = stack.peek();
                int s1 = cars[j][1];
                int p1 = cars[j][0];
                if (s <= s1 || (p1 - p) * 1.0 / (s - s1) > res[j] && res[j] > 0) {
                    stack.pop();
                } else
                    break;
            }
            if (stack.size() > 0) {
                int j = stack.peek();
                res[i] = (cars[j][0] - p) * 1.0 / (s - cars[j][1]);
            }
            stack.push(i);
        }
        return res;
    }

}
