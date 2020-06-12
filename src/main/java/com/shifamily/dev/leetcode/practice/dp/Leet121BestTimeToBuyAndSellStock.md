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
保留2个变量，最小价格和最高利润。
从0开始循环，记录最小价格，同时计算利润，prf = price[i] - minPrice，从而记录最高值
 


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
