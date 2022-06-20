# Leetcode #1277 Count Square Submatrices with All Ones

## 原题

[1277 Count Square Submatrices with All Ones](https://leetcode.com/problems/count-square-submatrices-with-all-ones/)

**<span style="color:blue">Medium</span>** 3303 57

Given a `m * n` matrix of ones and zeros, return how many square submatrices have all ones.

**Example 1:**

> `Input: matrix =`
`[`
  `[0,1,1,1],`
  `[1,1,1,1],`
  `[0,1,1,1]`
`]`
`Output: 15`
**Explanation:**
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.

**Example 2:**
```
Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
```

> **Explanation:**
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 
**Constraints:**

* `1 <= arr.length <= 300`
* `1 <= arr[0].length <= 300`
* `0 <= arr[i][j] <= 1`

## 解法

`dp[i][j]` means the size of biggest square with `A[i][j]` as bottom-right corner.
`dp[i][j]` also means the number of squares with `A[i][j]` as bottom-right corner.

If `A[i][j] == 0`, no possible square.
If `A[i][j] == 1`,
we compare the size of square `dp[i-1][j-1]`, `dp[i-1][j]` and `dp[i][j-1]`.
`min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1` is the maximum size of square that we can find.


## 复杂度

O(N^2^)


## 代码


```Java

    // solution 1, reuse dp, save some memory
    @CaseRunner
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rs = 0;

        int dp[][] = new int[2][n + 1];
        int prevDp = 0;
        int currDp = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int curr = matrix[i - 1][j - 1];
                if (curr == 1) {
                    dp[currDp][j] = Math.min(dp[currDp][j - 1], Math.min(dp[prevDp][j], dp[prevDp][j - 1])) + 1;
                    rs += dp[currDp][j];
                } else {
                    dp[currDp][j] = 0;
                }
            }
            prevDp = currDp;
            currDp = currDp == 1 ? 0 : 1;
        }
        return rs;
    }

    // solution 2, do not reuse dp
    @CaseRunner
    public int countSquares2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rs = 0;

        int dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int curr = matrix[i - 1][j - 1];
                if (curr == 1) {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    rs += dp[i][j];
                }
            }
        }
        return rs;
    }

```
