package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1937MaximumNumberPointsCost extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new int[][] { { 0, 3, 0, 4, 2 }, { 5, 4, 2, 4, 1 }, { 5, 0, 0, 5, 1 }, { 2, 0, 1, 0, 3 } } })
                .answer(15L)
                .description("Case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 2, 3 }, { 1, 5, 1 }, { 3, 1, 1 } } }).answer(9L)
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 5 }, { 2, 3 }, { 4, 2 } } }).answer(11L)
                .description("Example 2").build());

        return cases;
    }

    // second try - 2022/06/27
    public long maxPoints3(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[] prev = new long[n];
        for (int i = 0; i < n; i++) {
           prev[i] = points[0][i]; 
        }

        for (int i = 1; i < m; i++) {
            long[] left = new long[n];
            left[0] = prev[0];
            for (int j = 1; j < n; j++) {
                left[j] = Math.max(left[j - 1] -1, prev[j]);
            }

            long[] right = new long[n];
            right[n - 1] = prev[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, prev[j]);
            }
            
            long[] curr = new long[n];
            for (int j = 0; j < j; j++) {
                curr[j] = Math.max(left[j], right[j]) + points[i][j]; 
            }
            prev = curr;
        }
        long res = 0;
        for (int i = 0; i < prev.length; i++) {
           res = Math.max(res, prev[i]); 
        }
        return res;
    }

    // solution 1 O (m * n ), good one, but need solution 2 to understand why
    // basically , we need get rid of k loop
    // leftDP[i] meaning when chose i, what's the max points if max from left side
    // rightDP[i] meaning when chose i, what's the max points if max from rhgt side
    @CaseRunner
    public long maxPoints2(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] prev = new long[n];
        for (int i = 0; i < n; i++)
            prev[i] = points[0][i];
        long[] leftDP = new long[n];
        long[] rightDP = new long[n];

        for (int i = 1; i < m; i++) {
            long[] curr = new long[n];
            leftDP[0] = prev[0];
            for (int k = 1; k < n; k++)
                leftDP[k] = Math.max(leftDP[k - 1] - 1, prev[k]);

            rightDP[n - 1] = prev[n - 1];
            for (int k = n - 2; k >= 0; k--)
                rightDP[k] = Math.max(rightDP[k + 1] - 1, prev[k]);

            for (int j = 0; j < n; j++) {
                curr[j] = Math.max(leftDP[j], rightDP[j]) + points[i][j];
            }
            prev = curr;

        }
        long res = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, prev[i]);
        }

        return res;

    }

    // solution 2 O (m * n * n), will cause time out
    @CaseRunner
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        int[][] dp = new int[m][n];
        dp[0] = points[0];

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.max(dp[i][j], points[i][j] + dp[i - 1][k] - Math.abs(k - j));
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[m - 1][i]);
        }

        return res;
    }

}
