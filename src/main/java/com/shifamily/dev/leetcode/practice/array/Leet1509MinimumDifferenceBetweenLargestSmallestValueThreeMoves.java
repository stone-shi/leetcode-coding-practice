package com.shifamily.dev.leetcode.practice.array;

import java.util.ArrayList;
import java.util.List;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.CaseParameters;

/*
1509. Minimum Difference Between Largest and Smallest Value in Three Moves
Medium
1091
148
Add to List
Share
You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
Return the minimum difference between the largest and smallest value of nums after performing at most three moves.

Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1]. 
The difference between the maximum and minimum is 1-0 = 1.
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
*/

public class Leet1509MinimumDifferenceBetweenLargestSmallestValueThreeMoves extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 5, 3, 2, 4 } }).answer(0)
                .build());

        return cases;
    }

    @CaseRunner
    public int minDifference(int[] nums) {
        return 0;

    }

}
