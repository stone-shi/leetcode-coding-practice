# Leetcode #1937 Maximum Number of Points with Cost

## 原题

[1937 Maximum Number of Points with Cost](https://leetcode.com/problems/maximum-number-of-points-with-cost/)

**<span style="color:blue">Medium</span>** 1533 89

You are given an `m x n` integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates `(r, c)` will add `points[r][c]` to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

* x for x >= 0.
* -x for x < 0.
 

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2021/07/12/screenshot-2021-07-12-at-13-40-26-diagram-drawio-diagrams-net.png)

> `Input: points = [[1,2,3],[1,5,1],[3,1,1]]`
`Output: 9`
**Explanation:**
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.

**Example 2:**
![Example 2](https://assets.leetcode.com/uploads/2021/07/12/screenshot-2021-07-12-at-13-42-14-diagram-drawio-diagrams-net.png)

> `Input: points = [[1,5],[2,3],[4,2]]`
`Output: 11`
**Explanation:**
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.
 
**Constraints:**

* m == points.length
* n == points[r].length
* 1 <= m, n <= 105
* 1 <= m * n <= 105
* 0 <= points[r][c] <= 105

## 解法

先看solution 2，`O(M * N * K )` 容易理解, 弄个dp记录每个选择的最大值。然后我们要去掉K，看solution1
先忽略当前行
left_dp[i] 代表，如果选择是i的话，上一行能提供的最大值。
right_dp[i]一样。
然后当前行计算 dp。


## 复杂度

O(M * n)

## 代码

```Java
   // solution 1 O (m * n ), good one, but need solution 2 to understand why
    // basically , we need get rid of k loop
    // leftDP[i] meaning when chose i, what's the max points if max from left side
    // rightDP[i] meaning when chose i, what's the max points if max from rhgt side
    @CaseRunner
    public long maxPoints2(int[][] points) {
        int m = points.length, n = points[0].length;
        long [] prev = new long[n];
        for (int i = 0; i < n; i++) prev[i] = points[0][i];
        long[] leftDP = new long[n];
        long[] rightDP = new long[n];

        for (int i = 1; i < m; i++) {
            long[] curr = new long[n];
            leftDP[0] = prev[0];
            for (int k = 1; k < n; k++)
                leftDP[k] = Math.max(leftDP[k - 1] - 1, prev[k]);

            rightDP[n - 1] = prev[n - 1];
            for (int k = n - 2; k >= 0; k--)
                rightDP[k] = Math.max(rightDP[k + 1] - 1, prev[k]);

            for (int j = 0; j < n; j++) {
                curr[j] = Math.max(leftDP[j], rightDP[j]) + points[i][j];
            }
            prev = curr;

        }
        long res = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, prev[i]);
        }

        return res;

    }

    // solution 2 O (m * n * n), will cause time out
    @CaseRunner
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        int[][] dp = new int[m][n];
        dp[0] = points[0];

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.max(dp[i][j], points[i][j] + dp[i - 1][k] - Math.abs(k - j));
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[m - 1][i]);
        }

        return res;
    }

```
