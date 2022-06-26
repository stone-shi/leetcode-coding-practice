package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet56MergeIntervals extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } } })
                .answer(new int[][] { { 1, 6 }, { 8, 10 }, { 15, 18 } })
                .description("case a").build());

        return cases;
    }

    // 3rd try 6/25/2022
    @CaseRunner
    public int[][] merge3(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();

        int currStart = intervals[0][0];
        int currEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (currEnd < intervals[i][0]) {
                res.add(new int[] { currStart, currEnd });
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            }else{
                currEnd = Math.max(currEnd, intervals[i][1]);
            }
        }
        res.add(new int[] { currStart, currEnd });
        int[][] ret = new int[res.size()][];
        return res.toArray(ret);
    }

    // revisit at 5/22/2022
    @CaseRunner
    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();

        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > curr[1]) {
                res.add(curr);
                curr = intervals[i];
            } else {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }
        }
        res.add(curr);
        int[][] ret = new int[res.size()][];
        return res.toArray(ret);
    }

    @CaseRunner
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new LinkedList<>();

        int[] curr = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (curr[1] >= intervals[i][0]) {// overlap
                curr[0] = Math.min(curr[0], intervals[i][0]);
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                res.add(curr);
                curr = intervals[i];
            }
        }
        res.add(curr);

        int[][] ret = new int[res.size()][];
        return res.toArray(ret);
    }

}
