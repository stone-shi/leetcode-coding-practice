# 原题
122 Best Time to Buy and Sell Stock II
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
# 解法
这类问题的通解
https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti

设dp[i][k][0/1]为第i天 （从第一天），还有K次可买情况下 持有1/不持有0 情况下的最大盈利。

转移方程:

dp[i][k][0] = max ( dp[i-1][k][0], dp[i-1][k][1] + price[i])
第i天还有k次可买不持有 = max ( 前一天就不持有， 前一天持有，今天卖了  )

dp[i][k][1] = max ( dp[i-1][k][1], dp[i-1][k - 1][0] - price[i])
第i天还有k次可买持有 = max ( 前一天就持有， 前一天不持有，今天买了  )

Base Case:

dp[0][k][0] = 0， 第一天之前（day 0？）显然没有股票可以买卖，结果是0
dp[0][k][1] = 负无穷，因为不可能。

这题中，k是无穷大，可以不track。

dp[i][0]  = max ( dp[i-1][0], dp[i-1][1] + price[i])
dp[i][1]  = max ( dp[i-1][1], dp[i-1][0] - price[i])

当前状态只和上一个状态相关，dp数组可以简化为一个变量。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)


## 代码
```Java
   public int maxProfit(int[] prices) {

        int dp0 = 0;
        int dp1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }

        return dp0;
    }
```
