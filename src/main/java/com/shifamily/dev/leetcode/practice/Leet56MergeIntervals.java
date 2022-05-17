package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.*;

/**
 56. Merge Intervals
 Medium

 3650

 266

 Add to List

 Share
 Given a collection of intervals, merge all overlapping intervals.

 Example 1:

 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 Example 2:

 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class Leet56MergeIntervals extends BasicStudy {
    public Leet56MergeIntervals() {
        int[][][] caseP1 = {{{1,3},{2,6},{8,10},{15,18} }};
        int[][][] answer = {{{1,6},{8,10},{15,18}}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], true);
        }
    }


    @CaseRunner
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new LinkedList<>();

        int[] curr = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (curr[1] >= intervals[i][0]) {//overlap
                curr[0] = Math.min(curr[0], intervals[i][0]);
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }else{
                res.add(curr);
                curr = intervals[i];
            }
        }
        res.add(curr);

        int[][] ret = new int[res.size()][];
        return res.toArray(ret);
    }


}
