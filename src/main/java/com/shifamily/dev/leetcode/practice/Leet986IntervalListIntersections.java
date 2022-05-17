package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 986. Interval List Intersections
 Medium

 1279

 41

 Add to List

 Share
 Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

 Return the intersection of these two interval lists.

 (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)



 Example 1:



 Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]


 Note:

 0 <= A.length < 1000
 0 <= B.length < 1000
 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 Accepted
 110,743
 Submissions
 165,298
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 HolySofa
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 27

 Uber
 |
 4

 Google
 |
 4

 Apple
 |
 2

 Snapchat
 |
 2

 Amazon
 |
 2

 Oracle
 |
 2
 Merge Intervals
 Medium
 Merge Sorted Array
 Easy
 Employee Free Time
 Hard
 */
public class Leet986IntervalListIntersections extends BasicStudy {
    public Leet986IntervalListIntersections() {
        int[][][] caseP1 = {
                {{0,2},{5,10},{13,23},{24,25}},
        };
        int[][][] caseP2 = {
                {{1,5},{8,12},{15,24},{25,26}},
        };

        int[][][] answer = {
                {{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}}
        };

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i]);
        }

    }

    @CaseRunner
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        List<int[]> res = new ArrayList<>();
        while (i < A.length && j < B.length){
            int[] a = A[i];
            int[] b = B[j];

            int overStart = Math.max(a[0], b[0]);
            int overEnd = Math.min(a[1], b[1]);
            if (overEnd >= overStart )
                res.add(new int[]{overStart, overEnd});

            if (a[1] == overEnd) i++;
            if (b[1] == overEnd) j++;

        }
        return res.toArray(new int[res.size()][]);
    }

    /* First try - too complicated
    @CaseRunner
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int a = 0;
        int b = 0;
        List<int[]> res = new LinkedList<>();
        if (A.length == 0 || B.length == 0)
            return new int[0][];

        int[] current = A[a][0] < B[b][0]? A[a++]:B[b++] ;
        while (a < A.length || b < B.length){
            int[] p;
            if (a < A.length && b < B.length)
                p = A[a][0] < B[b][0]? A[a++]:B[b++];
            else if (a < A.length)
                p = A[a++];
            else
                p = B[b++];

            if (p[0] <= current[1]){
                int[] r = new int[2];
                r[0] = Math.max(current[0], p[0]);
                r[1] = Math.min(current[1], p[1]);
                res.add(r);
            }
            if (p[1] > current[1])
                current = p;
        }

        int[][] ret = new int[res.size()][];
        return res.toArray(ret);
    }

     */
}
