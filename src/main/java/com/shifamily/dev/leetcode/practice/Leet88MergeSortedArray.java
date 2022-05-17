package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;

/*
88. Merge Sorted Array
Easy

1611

3487

Add to List

Share
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
 */
public class Leet88MergeSortedArray extends BasicStudy {

    public Leet88MergeSortedArray() {
        int[][] caseP1 = {{1,2,3,0,0,0} };
        int[] caseP2 = {3};
        int[][] caseP3 = {{2,5,6}};
        int[] caseP4 = {3};
        int[][] caseP5 = {{1,2,2,3,5,6}};
        boolean[] answer = {true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[5];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            p[2] = caseP3[i];
            p[3] = caseP4[i];
            p[4] = caseP5[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    /* second try 5/23/2020 */
    public void merge2nd(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        m--;
        n--;
        while (n >= 0){
            if (m >= 0 && nums1[m] > nums2[n])
                nums1[p--] = nums1[m--];
            else
                nums1[p--] = nums2[n--];
        }

    }


    @CaseRunner
    public boolean runIt2(int[] nums1, int m, int[] nums2, int n, int[] answer){
        int[] p1 = Arrays.copyOf(nums1, nums1.length);
        int[] p2 = Arrays.copyOf(nums2, nums2.length);
        merge2nd(p1, m, p2, n);
        return Arrays.equals(p1, answer);
    }

    @CaseRunner
    public boolean runIt(int[] nums1, int m, int[] nums2, int n, int[] answer){
        int[] p1 = Arrays.copyOf(nums1, nums1.length);
        int[] p2 = Arrays.copyOf(nums2, nums2.length);
        merge(p1, m, p2, n);
        return Arrays.equals(p1, answer);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i1 = m - 1;
        int i2 = n - 1;
        int current = n + m - 1;

        while (current >= 0 && i2 >= 0){
            if (i1 >= 0 && nums1[i1] > nums2[i2])
                nums1[current--] = nums1[i1--];
            else
                nums1[current--] = nums2[i2--];
        }
    }
}
