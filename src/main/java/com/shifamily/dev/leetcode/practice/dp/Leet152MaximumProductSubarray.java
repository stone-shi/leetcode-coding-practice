package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;


/*
152. Maximum Product Subarray
Medium

3072

131

Add to List

Share
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/


public class Leet152MaximumProductSubarray extends BasicStudy {
    public Leet152MaximumProductSubarray() {
        int[][] caseP1 = {{2,3,-2,4}, {-2,0,-1} };
        int[] answer = {6, 0};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }

    }

    @CaseRunner
    public int maxProduct(int[] nums) {

        int r = nums[0];
        int minVal = nums[0];
        int maxVal = nums[0];

        for (int i = 1 ; i < nums.length; i++) {

            if (nums[i] < 0){
                int temp = minVal;
                minVal = maxVal;
                maxVal = temp;
            }

            maxVal =   Math.max( nums[i], maxVal * nums[i]);
            minVal =   Math.min( nums[i], minVal * nums[i]);

            r = Math.max(maxVal, r);

        }

        return r;

    }

}
