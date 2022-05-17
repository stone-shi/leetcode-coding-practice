package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 *
 123. Best Time to Buy and Sell Stock III
 Hard

 1956

 71

 Add to List

 Share
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [3,3,5,0,0,3,1,4]
 Output: 6
 Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 Example 2:

 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.
 Example 3:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.
 Accepted
 206,124
 Submissions
 559,097
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Two Sigma
 |
 3

 Bloomberg
 |
 2
 Best Time to Buy and Sell Stock
 Easy
 Best Time to Buy and Sell Stock II
 Easy
 Best Time to Buy and Sell Stock IV
 Hard
 Maximum Sum of 3 Non-Overlapping Subarrays
 Hard
 */
public class Leet123BestTimeToBuyAndSellStockIII extends BasicStudy {

    public Leet123BestTimeToBuyAndSellStockIII() {
        int[][] caseP1 = {{3,3,5,0,0,3,1,4}, {1,2,3,4,5}, {7,6,4,3,1}};
        int[] answer = {6, 4, 0};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i]);
        }

    }

    @CaseRunner
    public int maxProfit(int[] prices) {

        int dp20 = 0;
        int dp10 = 0;
        int dp21 = Integer.MIN_VALUE;
        int dp11 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            dp10 = Math.max(dp10, dp11 + prices[i]);
            dp11 = Math.max(dp11, -prices[i]);
            dp20 = Math.max(dp20, dp21 + prices[i]);
            dp21 = Math.max(dp21, dp10 - prices[i]);
        }


        return dp20;

    }
}
