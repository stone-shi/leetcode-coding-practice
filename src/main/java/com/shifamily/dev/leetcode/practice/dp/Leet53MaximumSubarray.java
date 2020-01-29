package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

/*
53. Maximum Subarray
Easy

6118

254

Add to List

Share
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 */

public class Leet53MaximumSubarray extends BasicStudy {

    public Leet53MaximumSubarray() {
        int[][] caseP1 = {{-2,1,-3,4,-1,2,1,-5,4} };
        int[] answer = {6};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length]; //dp[i]代表i为最后元素的最大sub array
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}
