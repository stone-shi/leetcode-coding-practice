package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1406StoneGameIII extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 2, 3, 7 } }).answer("Bob")
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 2, 3, -9 } }).answer("Alice")
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 2, 3, 6 } }).answer("Tie")
                .description("case a").build());
        return cases;
    }

    @CaseRunner
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int take = 0;
            for (int j = 0; j < 3 && j + i < n; j++) {
                take += stoneValue[j + i];
                dp[i] = Math.max(dp[i], take - dp[j + i + 1]);
            }
        }
        if (dp[0] > 0)
            return "Alice";
        else if (dp[0] < 0)
            return "Bob";
        return "Tie";
    }
}
