package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.*;

public class Leet954ArrayDoubledPairs extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        // cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 2, 1, 1, 4, 8, 8 } }).answer(false)
        //         .description("Case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 2, 4, 0, 0, 8, 1 } }).answer(true)
                .description("Case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 3, 1, 3, 6 } }).answer(false)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 2, 1, 2, 6 } }).answer(false)
                .description("Example 2").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 4, -2, 2, -4 } }).answer(true)
                .description("Example 3").build());
        return cases;
    }

    @CaseRunner
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);

        Map<Integer, Integer> count = new HashMap<>();
        int z = 0;
        for (int a : arr) {
            if (a != 0) {
                count.put(a, count.getOrDefault(a, 0) + 1);
            }else{
                z++;
            }
        }
        if (z % 2 != 0)
            return false;

        for (int a : arr) {
            int target;
            if (a >= 0)
                target = a * 2;
            else if (a % 2 == 0)
                target = a / 2;
            else
                continue;

            if (count.containsKey(target) && count.containsKey(a)) {
                int t = count.get(target);
                int c = count.get(a);
                t = t - c;
                if (t > 0) {
                    count.put(target, t);
                    count.remove(a);
                } else if (t == 0) {
                    count.remove(target);
                    count.remove(a);
                } else {
                    count.remove(target);
                    count.put(a, -1 * t );
                }
            }
        }
        return count.size() == 0;
    }
}
