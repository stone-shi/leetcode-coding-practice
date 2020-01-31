package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

/*
215. Kth Largest Element in an Array
Medium

2850

210

Add to List

Share
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class Leet215KthLargestElementArray extends BasicStudy {

    public Leet215KthLargestElementArray() {
        int[][] caseP1 = {{3,2,1,5,6,4}, {3,2,3,1,2,4,5,5,6} };
        int[] caseP2 = {2, 4};
        int[] answer = {5, 4};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int findKthLargest(int[] nums, int k) {
        return 0;

    }
}
