# 原题
121 Best Time to Buy and Sell Stock
Easy

4858

216

Add to List

Share
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
Accepted
843,901
Submissions
1,683,774
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
40

Facebook
|
20

Microsoft
|
10

Goldman Sachs
|
10

Bloomberg
|
7

Apple
|
6

Oracle
|
5

Walmart Labs
|
4

Uber
|
3

ByteDance
|
3

Expedia
|
2

Zillow
|
2

Atlassian
|
2

Audible
|
2
Maximum Subarray
Easy
Best Time to Buy and Sell Stock II
Easy
Best Time to Buy and Sell Stock III
Hard
Best Time to Buy and Sell Stock IV
Hard
Best Time to Buy and Sell Stock with Cooldown
Medium
# 解法

解法一
保留2个变量，最小价格和最高利润。
从0开始循环，记录最小价格，同时计算利润，prf = price[i] - minPrice，从而记录最高值
 
解法二：这类问题的通解
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


了解通解后：
对于这题，k=1
Base case:
dp[0][1][0] = 0
dp[0][1][1] = Integer.MIN_VAL

转移方程：
dp[i][1][0] = max ( dp[i-1][1][0], dp[i-1][1][1] + price[i]  )
dp[i][1][1] = max ( dp[i-1][1][1], dp[i-1][0][0] - price[i] ) 

这题只能买一次，所以dp[i-1][0][0] 为0，也就是不持股
去掉K
转移方程：
dp[i][0] = max ( dp[i-1][0], dp[i-1][1] + price[i]  )
dp[i][1] = max ( dp[i-1][1],  -price[i] ) 

另外，dp[i]状态只和dp[i-1]有关，所以空间复杂度可以做到 O(1)

## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)


## 代码
```Java
    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProf = 0;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProf = Math.max(maxProf, prices[i] - minPrice);
        }

        return maxProf;
    }

```
