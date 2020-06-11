package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.Interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
57. Insert Interval
Hard

1232

143

Add to List

Share
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class Leet57InsertInterval extends BasicStudy {

    public Leet57InsertInterval() {
        int[][][] caseP1 = { {{1,3},{6,9}}, {{1,2}, {3,5}, {6,7}, {8,10}, {12,16}} };
        int[][] caseP2 = {{2,5}, {4,8}};
        int[][][] answer = {{{1,5},{6,9}}, {{1,2},{3,10},{12,16}}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }
    }



    /* 2nd try 6/10/2020 */
    @CaseRunner
    public int[][] insert2nd(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();
        int i = 0;
        boolean newIntAdded = false;
        while ( i < intervals.length){
            if (intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
            }else if ( intervals[i][0] > newInterval[1]){
                if (!newIntAdded) {
                    res.add(newInterval);
                    newIntAdded = true;
                }
                res.add(intervals[i]);
            }else{
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }

/*
    private List<Interval> intArrayToIntervalList(int[] itv){
        List<Interval> intervals = new LinkedList<>();
        int i = 0;
        while (i < itv.length){
            Interval iv = new Interval(itv[i], itv[i + 1]);
            intervals.add(iv);
            i += 2;
        }
        return intervals;
    }

    private Integer[] IntervalListToArray(List<Interval> intervals){
        Integer[] re = new Integer[intervals.size() * 2];
        int i = 0;
        for (Interval iv: intervals) {
            re[i++] = iv.start;
            re[i++] = iv.end;
        }
        return re;
    }

    @CaseRunner
    public Integer[] runIt(int[] it1, int[] it2){
        List<Interval> intervals1 = intArrayToIntervalList(it1);
        List<Interval> res = insert(intervals1, new Interval(it2[0], it2[1]));
        return IntervalListToArray(res);
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new LinkedList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start ){
            result.add(intervals.get(i));
            i++;
        }
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newStart = Math.min(intervals.get(i).start, newStart);
            newEnd = Math.max(intervals.get(i).end, newEnd);
            i++;
        }
        result.add(new Interval(newStart, newEnd));

        while (i < intervals.size())
            result.add(intervals.get(i++));


        return result;


    }

 */

}
