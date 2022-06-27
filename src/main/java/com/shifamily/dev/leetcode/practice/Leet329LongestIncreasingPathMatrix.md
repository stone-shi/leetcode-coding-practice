# Leetcode #329 Longest Increasing Path in a Matrix

## 原题

[329 Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/)

**<span style="color:red">Hard</span>** 6268 100

Given an `m x n` integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2021/01/05/grid1.jpg)

> `Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]`
`Output: 4`
Explanation: The longest increasing path is `[1, 2, 6, 9]`.

**Example 2:**
![Example 2](https://assets.leetcode.com/uploads/2021/01/27/tmp-grid.jpg)

> `Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]`
`Output: 4`
Explanation: The longest increasing path is `[3, 4, 5, 6]`. Moving diagonally is not allowed.

**Example 3:**

> `Input: matrix = [[1]]`
`Output: 1`

**Constraints:**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 200`
- `0 <= matrix[i][j] <= 231 - 1`

## 解法

dfs + dp
因为有了dp，不需要visited

## 复杂度


## 代码


```Java
   public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxcell = new int[m][n];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int s = dfs(matrix, i, j, m, n, maxcell );
                max = Math.max(s, max);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] maxcell) {
        if (maxcell[i][j] != 0)
            return maxcell[i][j];

        int[][] dir = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int step = 1;
        for (int[] d : dir) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            if (nextI < 0 || nextJ < 0 || nextI >= m || nextJ >= n || matrix[nextI][nextJ] <= matrix[i][j] )
                continue;
            int curr = 1 + dfs(matrix, nextI, nextJ, m, n, maxcell);
            step = Math.max(step, curr);
        }
        maxcell[i][j] = step;
        return step;
    }

```
