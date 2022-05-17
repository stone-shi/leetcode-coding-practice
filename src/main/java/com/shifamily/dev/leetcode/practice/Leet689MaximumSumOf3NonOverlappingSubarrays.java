package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 689. Maximum Sum of 3 Non-Overlapping Subarrays
 Hard

 892

 53

 Add to List

 Share
 In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 Example:

 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.


 Note:

 nums.length will be between 1 and 20000.
 nums[i] will be between 1 and 65535.
 k will be between 1 and floor(nums.length / 3).


 Accepted
 39,899
 Submissions
 87,033
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 1337c0d3r
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 9
 Best Time to Buy and Sell Stock III
 Hard

 */
public class Leet689MaximumSumOf3NonOverlappingSubarrays extends BasicStudy {
    public Leet689MaximumSumOf3NonOverlappingSubarrays() {
        int[][] caseP1 = {{4,3,2,1}, {1,2,1,2,6,7,5,1}};
        int[] caseP2 = {1, 2};
        int[][] answer = {{0, 1, 2}, {0, 3, 5}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }

    }



    @CaseRunner
    public int[] maxSumOfThreeSubarraysDPN(int[] nums, int k) {
        int dp[][] = new int[4][nums.length ];
        int idx[][] =  new int[4][nums.length ];

        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];

        for (int n = 1; n < 4 ; n++) {
            for (int i = k - 1; i < nums.length; i++) {

                int subSum = i - k < 0 ? prefixSum[i]: prefixSum[i] - prefixSum[i - k] + dp[n - 1][i - k];
                if ((i > 0 && subSum > dp[n][i - 1])|| i == 0){
                    dp[n][i] = subSum;
                    idx[n][i] = i - k + 1;
                }else{
                    dp[n][i] = dp[n][i - 1];
                    idx[n][i] = idx[n][i - 1];
                }

            }

        }

        int[] res = new int[3];
        res[2] = idx[3][nums.length - 1];
        res[1] = idx[2][res[2] - 1];
        res[0] = idx[1][res[1] - 1];

        return res;

    }
}
