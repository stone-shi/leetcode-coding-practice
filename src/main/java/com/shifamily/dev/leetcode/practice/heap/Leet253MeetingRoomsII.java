package com.shifamily.dev.leetcode.practice.heap;


import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
253. Meeting Rooms II
Medium

1329

24

Favorite

Share
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class Leet253MeetingRoomsII  extends BasicStudy {

    public Leet253MeetingRoomsII() {
        int [][] case1 = { {0, 30},{5, 10},{15, 20}};
        int [][] case2 = { {7, 10},{2, 4}};
        Integer a[] = {2, 1};

        Object[][] parameters = new Object[2][1];
        parameters[0][0] = case1;
        parameters[1][0] = case2;

        addParameterAndAnswer(parameters[0], a[0], false);
        addParameterAndAnswer(parameters[1], a[1], false);
    }

    @CaseRunner
    //Mini heap
    public int minMeetingRooms(int[][] intervals) {

        if (intervals == null|| intervals.length == 0)
            return 0;

        //对会议开始的时间排序，会议总是要从先开始的开始处理的，这个是 O（NlgN)
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        //mimi heap
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0][1]);  //放入第一个会议的结束时间
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= queue.peek()) //检查当前需要安排的会议，是否有已经进行的会议结束了
                queue.poll(); //结束的会议出queue
            queue.offer(intervals[i][1]); //开始这个会议
        }
        return queue.size(); //所有的会议安排完了，看看有多少个同时进行的

    }

}
