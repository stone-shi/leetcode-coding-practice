package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

/*
238. Product of Array Except Self
Medium

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class Leet238ProductOfArrayExceptSelf extends BasicStudy {

    public Leet238ProductOfArrayExceptSelf() {
        int[] case1P1 = {1,2,3,4};
        int[] answer1 = {24,12,8,6};

        Object[] p1 = new Object[1];
        p1[0] = case1P1;

        addParameterAndAnswer(p1, answer1, true);

    }

    //second time
    @CaseRunner
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        int[] leftProd = new int[nums.length];
        leftProd[0] = 1;
        for (int i =1; i < nums.length; i++)
            leftProd[i] = leftProd[i - 1] * nums[i - 1];

        int rightProd = 1;
        for (int i = nums.length - 1 ; i >=0 ; i--) {
            res[i] = rightProd * leftProd[i];
            rightProd = rightProd * nums[i];
        }

        return res;
    }
    /*
    @CaseRunner
    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];

        //第一个循环，计算i的左边所有的乘积，当i = 0的时候，乘积初始化为1, 然后每个i左边所有乘积就是 result[i-1] * num[i - 1]
        result[0] = 1;
        for (int i = 1; i < nums.length; i++)
            result[i] = result[ i -1] * nums[i-1];

        //第二个循环，计算i的右边所有数字的乘积，这里，因为最终的结果是要 i左边×i右边的，所以直接用了 result数组。
        int right = 1;
        for (int i = nums.length -2; i >=0 ; i--) {
            //i的右边乘积
            right = right * nums[i + 1];
            //这里直接用了result，其实就是左边*右边
            result[i] = result[i] * right;
        }

        return result;
    }

     */
}
