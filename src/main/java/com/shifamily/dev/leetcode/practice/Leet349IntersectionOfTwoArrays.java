package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashSet;
import java.util.Set;

/**
 349. Intersection of Two Arrays
 Easy

 755

 1170

 Add to List

 Share
 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [9,4]
 Note:

 Each element in the result must be unique.
 The result can be in any order.


 Accepted
 345,934
 Submissions
 564,468
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 30

 Amazon
 |
 4

 Google
 |
 2

 LinkedIn
 |
 2

 Oracle
 |
 2

 ByteDance
 |
 2
 Intersection of Two Arrays II
 Easy
 Intersection of Three Sorted Arrays
 Easy
 */
public class Leet349IntersectionOfTwoArrays extends BasicStudy {
    public Leet349IntersectionOfTwoArrays() {
        int [][] case1P1 = {{1,2,2,1},{4,9,5}};
        int [][] case1P2 = {{2,2},{9,4,9,8,4}};
        Integer [][] ans = {{2}, {9,4}};

        for (int i = 0; i < case1P1.length; i++) {
            Object[] p1 = new Object[2];
            p1[0] = case1P1[i];
            p1[1] = case1P2[i];
            addParameterAndAnswer(p1, ans[i], false);
        }

    }


    @CaseRunner
    public Integer[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> check = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < nums1.length; i++)
            check.add(nums1[i]);

        for (int i = 0; i < nums2.length; i++) {
            if (check.contains(nums2[i]))
                res.add(nums2[i]);
        }

        Integer[] rest = new Integer[res.size()];

        return res.toArray(rest);

    }

}
