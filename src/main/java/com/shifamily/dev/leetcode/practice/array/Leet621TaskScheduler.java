package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
621. Task Scheduler
Medium

1636

277

Favorite

Share
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class Leet621TaskScheduler extends BasicStudy {

    public Leet621TaskScheduler() {
        char[] case1P1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int case1P2 = 2;
        Integer answer1 = 8;

        Object[] p1 = new Object[2];
        p1[0] = case1P1;
        p1[1] = case1P2;

        addParameterAndAnswer(p1, answer1, false);

    }

    @CaseRunner
    public int leastInterval(char[] tasks, int n) {

        //统计task出现次数
        int[] charCount = new int[26];
        for ( char c : tasks)
            charCount[c - 'A']++;

        // comparator 设成  Collections.reverseOrder() 是为了最大值在前 (max heap)
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        //把次数入queue，从最大次数开始排列，这里不记录是什么task，是因为题目不要求写出实际排列
        for (int ct : charCount) {
            if (ct > 0)
                queue.offer(ct);
        }
        //记录执行次数
        int time = 0;
        while (!queue.isEmpty()){
            //记录这个执行block里面的等待list
            List<Integer> waitingList = new LinkedList<>();
            for (int i = 0; i <= n ; i++) { //每个任务要等待 n
                if (!queue.isEmpty()) {
                    //提取一个任务执行，执行次数-1
                    int taskCt = queue.poll() - 1;
                    time++; //记录执行了这个任务
                    if (taskCt > 0) //如果这个任务还需要执行，放到等待list
                        waitingList.add(taskCt);
                }else{
                    //这里表示现有的执行队列里已经没有任务了，但是有等待列表，那CPU必须轮空（执行次数++)，如果等待列表都没了，说明执行完成了，CPU不需要轮空
                    if (!waitingList.isEmpty())
                        time++;
                }
            }
            //把执行列表里的数据放回执行队列
            for (Integer task: waitingList)
                queue.offer(task);
        }
        return time;
    }
}
