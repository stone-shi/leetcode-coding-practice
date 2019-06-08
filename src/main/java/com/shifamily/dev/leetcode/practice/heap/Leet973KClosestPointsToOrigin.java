package com.shifamily.dev.leetcode.practice.heap;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
973. K Closest Points to Origin
Medium

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */
public class Leet973KClosestPointsToOrigin extends BasicStudy {

    public Leet973KClosestPointsToOrigin() {

        int[][] case1P1 = {{1,3},{-2,2}};
        int case1P2 = 1;

        Object[] p1 = new Object[2];
        p1[0] = case1P1;
        p1[1] = case1P2;

        int[][] answer1 = {{-2, 2}};

        addParameterAndAnswer(p1, answer1, false);

    }



    //直接利用排序，然后取前K。到原点距离计算  sqrt(x^2 + y^2)，排序时不必考虑 平方根
    @CaseRunner
    public int[][] kClosestSort(int[][] points, int K) {

        Arrays.sort(points,  Comparator.comparingInt(a -> a[0] * a[0] + a[1] * a[1]));

        return Arrays.copyOfRange(points, 0, K );
    }


    //利用优先队列，始终保持K
    @CaseRunner
    public int[][] kClosestHeap(int[][] points, int K) {

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0]*b[0] + b[1]*b[1] - a[0]*a[0] - a[1]*a[1]);

        for (int i = 0; i < points.length ; i++) {
            queue.offer(points[i]);
            if (queue.size() > K)
                queue.poll();
        }

        int[][] res = new int[K][];
        for (int i = 0; i < K; i++)
            res[i] = queue.poll();

        return res;

    }

    //Quick select算法
    @CaseRunner
    public int[][] kClosestQuickSelect(int[][] points, int K) {

        int l = 0;
        int r = points.length - 1;

        while (l < r) {
            int mid = helper(points, l, r);
            if (mid < K)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper( int[][]  points, int l, int r){
        int[] pivot = points[l]; //"随机"选择的 pivot point

        while ( l < r){
            //先 r 减小，找到第一个 小于 pivot的
            while (l < r && compare(points[r], pivot) >= 0 ) r--;
            points[l] = points[r];
            //l 增加，找到第一个 大于 Pivot的
            while (l < r && compare(points[l], pivot) <= 0) l++;
            points[r] = points[l];
        }
        points[l] = pivot;
        return l;
    }


    private int compare(int[] a, int[] b){
        return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
    }


}
