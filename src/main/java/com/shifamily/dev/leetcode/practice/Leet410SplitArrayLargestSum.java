package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;

/*
410. Split Array Largest Sum
Hard

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous
subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class Leet410SplitArrayLargestSum extends BasicStudy {

    public Leet410SplitArrayLargestSum() {
        int[][] casesP1 = {{7,2,5,10,8}};
        int[] casesP2 = {2};
        int[] ans = {18};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = casesP1[i];
            p[1] = casesP2[i];
            addParameterAndAnswer(p, ans[i]);
        }
    }


    @CaseRunner
    public int splitArray(int[] nums, int m) {

        int n = nums.length;
        int[] sum = new int[n + 1];
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        dp[0][0] = 0;

        for (int i = 1; i <= n ; i++)
            sum[i] = sum[i -1] + nums[i - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = i - 1; k < j; k++) {

                    int val = Math.max( dp[i - 1][k], sum[j] - sum[k] );
                    dp[i][j] = Math.min(dp[i][j], val);

                }
            }
        }

        return dp[m][n];
    }
}
