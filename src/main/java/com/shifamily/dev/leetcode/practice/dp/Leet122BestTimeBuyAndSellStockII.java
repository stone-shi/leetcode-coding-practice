package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 122. Best Time to Buy and Sell Stock II
 Easy

 2287

 1737

 Add to List

 Share
 Say you have an array prices for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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


 Constraints:

 1 <= prices.length <= 3 * 10 ^ 4
 0 <= prices[i] <= 10 ^ 4
 Accepted
 592,996
 Submissions
 1,053,885
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Amazon
 |
 11

 Microsoft
 |
 3

 Facebook
 |
 3

 Bloomberg
 |
 3

 Google
 |
 2

 Apple
 |
 2
 Best Time to Buy and Sell Stock
 Easy
 Best Time to Buy and Sell Stock III
 Hard
 Best Time to Buy and Sell Stock IV
 Hard
 Best Time to Buy and Sell Stock with Cooldown
 Medium
 Best Time to Buy and Sell Stock with Transaction Fee
 Medium
 */
public class Leet122BestTimeBuyAndSellStockII extends BasicStudy {
    public Leet122BestTimeBuyAndSellStockII() {
        int[][] caseP1 = {{7,1,5,3,6,4}, {1,2,3,4,5}, {7,6,4,3,1}};
        int[] answer = {7, 4, 0};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i]);
        }

    }

    @CaseRunner
    public int maxProfit(int[] prices) {

        int dp0 = 0;
        int dp1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }

        return dp0;
    }
}
