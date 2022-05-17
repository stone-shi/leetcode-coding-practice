package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.NestedInteger;

import java.util.List;


/*
339. Nested List Weight Sum
Easy

422

94

Add to List

Share
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class Leet339NestedListWeightSum extends BasicStudy {

    public Leet339NestedListWeightSum() {
        String[] caseP1 = {  "[1,[4,[6]]]", "[[1,1],2,[1,1]]" };
        int[] answer = {  27, 10 };

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int runIt(String ni){
        return depthSum(NestedInteger.fromString(ni).getList().get(0).getList());
    }

    public int depthSum(List<NestedInteger> nestedList) {

        return dfs(nestedList, 1);

    }

    public int dfs( List<NestedInteger> nestedList, int level ){

        int sum = 0;
        for (NestedInteger ni: nestedList
             ) {
            if (ni.isInteger())
                sum = sum +  ni.getInteger() * level;
            else
                sum = sum + dfs(ni.getList(), level + 1);
        }
        return sum;

    }
}
