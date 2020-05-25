package com.shifamily.dev.leetcode.practice.hashMap;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;


/*

1. Two Sum

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */
public class Leet1TwoSum  extends BasicStudy {

    public Leet1TwoSum() {
        int[][] caseP1 = {{2, 7, 11, 15} };
        int[] caseP2 = {9};
        Integer[][] answer = {{0, 1}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public Integer[] twoSum(int[] nums, int target){

        HashMap<Integer, Integer> map = new HashMap<>();
        Integer[] res = new Integer[2];
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null ){
                res[0] = idx;
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);

        }
        return null;
    }
}
