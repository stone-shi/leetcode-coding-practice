package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.*;

public class Leet300LongestIncreasingSubsequence extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 2, 6, 8, 3, 4, 5, 1 } })
                .answer(4).description("Case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 10, 9, 2, 5, 3, 7, 101, 18 } })
                .answer(4).description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 0, 1, 0, 3, 2, 3 } }).answer(4)
                .description("Example 2").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 7, 7, 7, 7, 7, 7, 7 } }).answer(1)
                .description("Example 3").build());

        return cases;
    }

    // solution 1, dp time: O(N^2)
    // dp[i] = max increasing subsequence ended at num[i]
    // give num[i], find if num[i] > num[j] true, dp[i] = max( dp[j] + 1, dp[i] ),
    // meaning get max of dp before then + 1 (num[i])
    @CaseRunner
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // solution 2, greedy subarry + binary search, O(N Log N)
    @CaseRunner
    public int lengthOfLIS2(int[] nums) {

        int[] sub = new int[nums.length];
        int subLen = 1;
        sub[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub[subLen - 1]) {
                sub[subLen++] = nums[i];
                continue;
            }
            int m = binarySearchLowBound(sub, subLen, nums[i]);
            if (m <= subLen)
                sub[m] = nums[i];

        }
        return subLen;

    }

    private int binarySearchLowBound(int[] a, int len, int k) {
        int l = 0;
        int r = len;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= k) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    // solution 3, same as 2 but use Java binary search function
    @CaseRunner
    public int lengthOfLIS3(int[] nums) {

        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
                continue;
            }
            int m = Collections.binarySearch(sub, nums[i]);
            if (m >= 0)
                sub.set(m, nums[i]);
            else {
                // m = (-(insertion point) - 1) ref:
                // https://docs.oracle.com/javase/6/docs/api/java/util/Collections.html#binarySearch%28java.util.List,%20T%29
                int insert = -1 * m - 1;
                if (insert < sub.size()) {
                    sub.set(insert, nums[i]);
                }
            }
        }
        return sub.size();

    }

}
