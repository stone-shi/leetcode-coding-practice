package com.shifamily.dev.leetcode.practice.bfs;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.NestedInteger;

import java.util.*;

/*
364. Nested List Weight Sum II
Medium

446

113

Add to List

Share
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
public class Leet364NestedListWeightSumII extends BasicStudy {

    public Leet364NestedListWeightSumII() {

        String[] caseP1 = {  "[1,[4,[6]]]", "[[1,1],2,[1,1]]" };
        int[] answer = {  17, 8 };

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }

    }

    @CaseRunner
    public int runIt(String ni){
        return depthSumInverse(NestedInteger.fromString(ni).getList().get(0).getList());
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        Deque<List<Integer>> layers = new LinkedList<>();
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);

        while (!queue.isEmpty()){
            List<Integer> newLayer = new LinkedList<>();
            int currentQueueSize = queue.size();
            for (int i = 0; i < currentQueueSize; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger())
                    newLayer.add(ni.getInteger());
                else {
                    for (NestedInteger nie: ni.getList())
                        queue.offer(nie);
                }
            }
            layers.push(newLayer);
        }

        int currentLayerId = 1;
        int sum = 0;
        while (!layers.isEmpty()){
            List<Integer> layer = layers.pop();
            for (Integer i:layer)
                sum += i * currentLayerId;
            currentLayerId++;
        }

        return sum;
    }
}
