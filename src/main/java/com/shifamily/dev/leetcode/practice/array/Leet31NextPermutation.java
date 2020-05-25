package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;

/**
 31. Next Permutation
 Medium

 3048

 1093

 Add to List

 Share
 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place and use only constant extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1

 Accepted
 349.3K
 Submissions
 1.1M
 */
public class Leet31NextPermutation extends BasicStudy {
    public Leet31NextPermutation() {
        int [][] case1P1 = {{5, 1, 1},{1,2,3}, {3,2,1}, {1,1,5}};
        Integer [][] ans = {{1,1,5}, {1,3,2}, {1,2,3}, {1,5,1}};

        for (int i = 0; i < case1P1.length; i++) {
            Object[] p1 = new Object[1];
            p1[0] = case1P1[i];
            addParameterAndAnswer(p1, ans[i], true);
        }

    }

    @CaseRunner
    public Integer[] runIt(int[] nums){
        int[] res = new int[nums.length];
        System.arraycopy(nums, 0, res, 0, nums.length);
        nextPermutation(res);
        return Arrays.stream(res).boxed().toArray(Integer[]::new);
    }

    public void nextPermutation(int[] nums) {
        int p = nums.length - 1;
        while (p > 0 && nums[p] <= nums[p -1])
            p--;

        if (p > 0){
            int p1 = p ;
            while (p1 < nums.length && nums[p1] > nums[p - 1])
                p1++;
            swap(nums, p - 1, p1 - 1);
        }
        reverse(nums, p );


    }
    private void swap(int[] nums, int n1, int n2){
        int temp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = temp;
    }

    private void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while (start < end)
            swap(nums, start++, end--);
    }


}
