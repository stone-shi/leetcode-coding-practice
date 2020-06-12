# 原题
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

这题中，k=2, 必须穷举K，当k比较小的时候也可以直接列出。



当前状态只和上一个状态相关，dp数组可以简化为一个变量。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java

```
