package com.shifamily.dev.leetcode.practice.search;


import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/*
33. Search in Rotated Sorted Array
Medium

3605

393

Add to List

Share
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
public class Leet33SearchInRotatedSortedArray extends BasicStudy {

    public Leet33SearchInRotatedSortedArray() {
        int[][] caseP1 = {{3,1}, {4,5,6,7,0,1,2},{4,5,6,7,0,1,2} };
        int[] caseP2 = {1, 0, 3};
        int[] answer = {1, 4, -1};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high){
            int mid = low + ( high - low ) / 2 ;
            if (nums[mid] ==  target)
                return mid;
            if (nums[low] <= nums[mid]){ //sorted on low side
                if (nums[mid] > target && nums[low] <= target) //in left part
                    high = mid - 1;
                else
                    low = mid + 1;
            }else { //sorted on high side
                if (nums[mid] < target && nums[high] >= target) //in right part
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }

}
