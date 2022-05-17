package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Map;

/*
560. Subarray Sum Equals K
Medium
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class Leet560SubarraySumEqualsK extends BasicStudy {

    public Leet560SubarraySumEqualsK() {
        int [] case1P1 = {1,1,1};
        int case1P2 = 2;

        Object[] p1 = new Object[2];
        p1[0] = case1P1;
        p1[1] = case1P2;

        addParameterAndAnswer(p1, 2, false);

    }

    /*second try*/
    @CaseRunner
    public int subarraySum2nd(int[] nums, int k) {
        Map<Integer, Integer> mapSum = new HashMap<>();
        int sum = 0;
        int res = 0;
        mapSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (mapSum.containsKey(sum - k))
                res += mapSum.get(sum - k);
            mapSum.put(sum, mapSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    @CaseRunner
    public int subarraySum(int[] nums, int k) {

        //保存预先计算的和，key=和， value = 次数 - 因为同一个和可能不同的子数组
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        
        int sum = 0;
        sumMap.put(0, 1); //放入0，保证 num[i] = k时候可以有正确返回
        int result = 0;//结果

        for (int i = 0; i < nums.length; i++) {
            //从0-i的和
            sum += nums[i];
            //看看 sum - k有没有在预先计算的和里，有的话返回次数，没有0
            result += sumMap.getOrDefault( sum - k , 0);
            //把这个和放入map，如果已经存在了，就把次数+1，否则就是1 （0+1）
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);

        }
        

        return result;

    }

}
