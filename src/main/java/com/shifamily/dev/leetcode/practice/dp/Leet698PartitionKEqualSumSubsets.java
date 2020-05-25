package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;

/*
698. Partition to K Equal Sum Subsets
Medium

1257

70

Add to List

Share
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */
public class Leet698PartitionKEqualSumSubsets extends BasicStudy {
    public Leet698PartitionKEqualSumSubsets() {
        int[][] caseP1 = {{4, 3, 2, 3, 5, 2, 1} };
        int[] caseP2 = {4};
        boolean[] answer = {true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    private boolean helper(int[] nums, int k, int currentSum, int currentCount, int target, int[] visited, int start){
        if (k == 0)
            return true;

        if (currentSum == target && currentCount > 0)
            return helper(nums, k - 1, 0, 0, target, visited, 0);

        for (int i = start; i < nums.length; i++) {
            if (visited[i] != 1) {
                visited[i] = 1;
                if (helper(nums, k, currentSum + nums[i], currentCount + 1, target, visited, i + 1))
                    return true;
                else{
                    visited[i] = 0;
                }
            }
        }
        return false;

    }

    @CaseRunner
    public boolean canPartitionKSubsets(int[] nums, int k) {

        if (nums == null || nums.length < k)
            return false;

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0)
            return false;

        int[] visited = new int[nums.length];

        return helper(nums, k, 0, 0, sum / k, visited, 0);
    }
}
