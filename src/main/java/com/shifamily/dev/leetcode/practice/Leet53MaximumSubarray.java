package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet53MaximumSubarray extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 } })
                .answer(6).description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1 } }).answer(1).description("case a")
                .build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 5, 4, -1, 7, 8 } }).answer(23)
                .description("case a").build());
        return cases;
    }

    // 4th at 2022/06/27
    @CaseRunner
    public int maxSubArray4(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // 3rd try at 5/31/2022
    @CaseRunner
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = (dp[i - 1] > 0 ? dp[i - 1] : 0) + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /*
     * 2nd try 6/10/2020
     */
    @CaseRunner
    public int maxSubArray2nd(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            if (maxSum < dp[i])
                maxSum = dp[i];
        }
        return maxSum;
    }

    @CaseRunner
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length]; // dp[i]代表i为最后元素的最大sub array
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}
