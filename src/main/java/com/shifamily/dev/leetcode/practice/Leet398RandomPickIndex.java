package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.*;

/**
 398. Random Pick Index
 Medium

 442

 651

 Add to List

 Share
 Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);
 Accepted
 83,004
 Submissions
 151,361
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 15
 Linked List Random Node
 Medium
 Random Pick with Blacklist
 Hard
 Random Pick with Weight
 Medium
 */
public class Leet398RandomPickIndex extends BasicStudy {
    public Leet398RandomPickIndex() {
        int[][] casesP1 = {{1,2,3,3,3}};
        int[][] casesP2 = {{3, 1}};
        for (int i = 0; i < casesP1.length; i++) {
            Object[] p  = new Object[2];
            p[0] = casesP1[i];
            p[1] = casesP2[i];
            addParameterAndAnswer(p, true);
        }
    }



    private boolean checkCase(int sampleCount , int target, Solution solution, Set<Integer> answer){
        int allowDiff = (int)( 0.05 * sampleCount );
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < sampleCount * answer.size(); i++) {
            int s = solution.pick(target);
            if (!answer.contains(s))
                return false;
            m.put(s, m.getOrDefault(s, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: m.entrySet()){
            if (entry.getValue() < sampleCount - allowDiff || entry.getValue() > sampleCount + allowDiff )
                return false;
        }

        return true;
    }

    @CaseRunner
    public boolean runIt(int[] nums, int[] targets){
        Map<Integer, Set<Integer>> checker = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> lst = checker.getOrDefault(nums[i], new HashSet<>());
            lst.add(i);
            checker.put(nums[i], lst);
        }

        Solution solution = new Solution(nums);
        for (int i = 0; i < targets.length; i++) {
            if (! checkCase(1000, targets[i], solution, checker.get(targets[i])))
                return false;
        }

        return true;

    }




    class Solution {

        int[] nums ;
        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int count = 0;
            Random random = new Random();
            int result = -1;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target)
                    continue;

                if ( random.nextInt(++count) == 0 )
                    result = i;

            }

            return result;
        }
    }

}
