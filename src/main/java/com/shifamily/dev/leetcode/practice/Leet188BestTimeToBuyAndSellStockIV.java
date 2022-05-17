package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 188. Best Time to Buy and Sell Stock IV
 Hard

 1365

 86

 Add to List

 Share
 Say you have an array for which the i-th element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Example 1:

 Input: [2,4,1], k = 2
 Output: 2
 Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 Example 2:

 Input: [3,2,6,5,0,3], k = 2
 Output: 7
 Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 Accepted
 123,907
 Submissions
 446,853
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 Freezen
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Google
 |
 4

 Amazon
 |
 3
 Best Time to Buy and Sell Stock
 Easy
 Best Time to Buy and Sell Stock II
 Easy
 Best Time to Buy and Sell Stock III
 Hard
 */

public class Leet188BestTimeToBuyAndSellStockIV extends BasicStudy {
    public Leet188BestTimeToBuyAndSellStockIV() {
        int[] caseP1 = {1, 1, 2, 2};
        int[][] caseP2 = {{6,1,6,4,3,0,2}, {1,2}, {2,4,1}, {3,2,6,5,0,3}};
        int[] answer = {5, 1, 2, 7};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i]);
        }

    }

    private int maxProfitUnlimited(int[] prices) {

        int dp0 = 0;
        int dp1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }

        return dp0;
    }

    @CaseRunner
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        //optional,
        if (k >= n/2)
            return maxProfitUnlimited(prices);

        int[][] dp = new int[k + 1][2];

        for (int i = 1; i <= k ; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];

    }
}
