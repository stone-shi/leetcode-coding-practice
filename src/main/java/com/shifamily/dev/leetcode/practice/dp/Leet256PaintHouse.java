package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/*
256. Paint House
Easy

593

66

Add to List

Share
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
 */
public class Leet256PaintHouse extends BasicStudy {
    public Leet256PaintHouse() {
        int[][][] caseP1 = { {{17,2,17},{16,16,5},{14,3,19}} };
        int[] answer = {10};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }



    @CaseRunner
    public int minCost(int[][] costs) {

        int n = costs.length;
        int[] miniCosts = new int[3];
        int[] currentCost = new int[3];
        for (int i = 0; i < n; i++) {
            currentCost[0] = costs[i][0] + Math.min(miniCosts[1], miniCosts[2]);
            currentCost[1] = costs[i][1] + Math.min(miniCosts[0], miniCosts[2]);
            currentCost[2] = costs[i][2] + Math.min(miniCosts[0], miniCosts[1]);
            System.arraycopy(currentCost, 0, miniCosts, 0, 3);
        }
        return Math.min(Math.min(miniCosts[0], miniCosts[1]),  miniCosts[2]);

    }

}
