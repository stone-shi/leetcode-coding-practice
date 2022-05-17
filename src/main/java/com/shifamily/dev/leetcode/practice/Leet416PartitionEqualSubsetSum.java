package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 416. Partition Equal Subset Sum
 Medium

 2387

 65

 Add to List

 Share
 Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 Note:

 Each of the array element will not exceed 100.
 The array size will not exceed 200.


 Example 1:

 Input: [1, 5, 11, 5]

 Output: true

 Explanation: The array can be partitioned as [1, 5, 5] and [11].


 Example 2:

 Input: [1, 2, 3, 5]

 Output: false

 Explanation: The array cannot be partitioned into equal sum subsets.


 Accepted
 166,929
 Submissions
 386,759
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 3

 Amazon
 |
 2
 Partition to K Equal Sum Subsets
 Medium
 */
public class Leet416PartitionEqualSubsetSum extends BasicStudy {
    public Leet416PartitionEqualSubsetSum() {
        int[][] casesP1 = {{1, 5, 11, 5}, {1, 2, 3, 5}};
        boolean[] ans = {true, false};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = casesP1[i];
            addParameterAndAnswer(p, ans[i]);
        }
    }


    @CaseRunner
    public boolean canPartition(int[] nums) {

        int len = nums.length;
        int sum = 0;
        for (int n : nums)
            sum += n;

        if (sum % 2 == 1)
            return false;
        sum = sum / 2;

        boolean[][] dp = new boolean[len + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 1; i < len + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < len + 1 ; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if ( nums[i - 1] <= j){
                    dp[i][j] = dp[i][j] || dp[i - 1][ j - nums[i - 1] ];
                }
            }
        }

        return dp[len][sum];
    }

    @CaseRunner
    public boolean canPartition1dDp(int[] nums) {

        int len = nums.length;
        int sum = 0;
        for (int n : nums)
            sum += n;

        if (sum % 2 == 1)
            return false;
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num: nums) {
            for (int i = sum ; i > 0; i--) {
                if ( num <= i){
                    dp[i] = dp[i] || dp[ i - num ];
                }
            }
        }

        return dp[sum];
    }
}
