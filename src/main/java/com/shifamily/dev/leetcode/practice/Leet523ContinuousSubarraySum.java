package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
523. Continuous Subarray Sum
Medium

1230

1715

Add to List

Share
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.



Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
Accepted
120,913
Submissions
494,522
Seen this question in a real interview before?

Yes

No
Contributor
10000tb
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
28

Amazon
|
2
Subarray Sum Equals K
Medium
 */
@Slf4j
public class Leet523ContinuousSubarraySum extends BasicStudy {
    public Leet523ContinuousSubarraySum() {
        int[][] caseP1 = { {1,2,12}, {15,0,0,3},  {0,1,0}, {0,0}, {1, 0}, {0,1,0},{23, 2, 6, 4, 7}, {0,0}, {0}, {23, 2, 6, 4, 7}, { 23, 2, 4, 6, 7 } , {1,2,4,5,6}};
        int[] caseP2 = {6, 4, -1,-1, 2, 0, 0, 0, 0, 6, 6, 70};
        boolean[] answer = {false, true, true, true, false, false, false, true, false, true, true, false};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }

    }

    /* 2nd try 6/9/2020 */
    @CaseRunner
    public boolean checkSubarraySum2nd(int[] nums, int k) {
        if (nums.length <= 1)
            return false;

        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0]; 
        for (int i = 1; i < nums.length; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i ; j++) {
                int sum = prefixSum[i] - prefixSum[j] + nums[j];
                if ( (k == 0 && sum == 0 )  || (k != 0 && sum % k == 0))
                    return true;
            }
        }
        return false;



    }

    /* 2nd try 6/9/2020 */
    @CaseRunner
    public boolean checkSubarraySum2ndHashMap(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (k != 0) sum %= k ;
            if (map.containsKey(sum)){
                if (i - map.get(sum) > 1)
                    return true;
            }else
                map.put(sum, i);
        }
        return false;

    }

    @CaseRunner
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length <= 1)
            return false;

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int sumij = sum[i] - sum[j] + nums[j];
                if ((k == 0 && sumij == 0) || (k!= 0 && sumij % k == 0)) {
                    //log.info("Found sum[{},{}]", j, i);
                    return true;
                }
            }
        }

        return false;
    }

    @CaseRunner
    public boolean checkSubarraySumHashMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }

}
