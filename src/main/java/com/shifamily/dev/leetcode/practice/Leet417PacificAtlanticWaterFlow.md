# Leetcode #417 Pacific Atlantic Water Flow

## 原题

[417 Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

**<span style="color:blue">Medium</span>** 3761 844

There is an `m x n` rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an `m x n` integer matrix heights where `heights[r][c]` represents the height above sea level of the cell at coordinate `(r, c)`.

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where `result[i] = [ri, ci]` denotes that rain water can flow from cell `(ri, ci)` to both the Pacific and Atlantic oceans.

 

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg)

> `Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]`
`Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]`

**Example 2:**

> `Input: heights = [[2,1],[1,2]]`
`Output: [[0,0],[0,1],[1,0],[1,1]]`
 
**Constraints:**

* `m == heights.length`
* `n == heights[r].length`
* `1 <= m, n <= 200`
* `0 <= heights[r][c] <= 105`

## 解法

可以dfs或者bfs，从边缘倒过来找路径。先找 Parcific的，然后找Atlantic然后看重叠的。

## 复杂度

O(m x n)

## 代码

```Java
   public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int row = heights.length;
        if (row <= 0)
            return res;
        int col = heights[0].length;

        int[][] dp = new int[row][col];
        // from pacific row
        for (int i = 0; i < row; i++)
            dfs(heights, row, col, i, 0, dp, res, false);
        // from pacific col 
        for (int i = 0; i < col; i++)
            dfs(heights, row, col, 0, i, dp, res, false);
        // from atlantic row
        for (int i = 0; i < row; i++)
            dfs(heights, row, col, i, col - 1, dp, res, true);
        // from atlantic col
        for (int i = 0; i < col; i++)
            dfs(heights, row, col, row - 1, i, dp, res, true);

        return res;
    }

    // dfs and check result
    private void dfs(int[][] heights, int row, int col, int m, int n, int[][] dp, List<List<Integer>> res,
            boolean fromAtlantic) {
        if ((dp[m][n] == 1) && fromAtlantic) {
            List<Integer> r = new ArrayList<>();
            r.add(m);
            r.add(n);
            res.add(r);
        }
        if ((dp[m][n] == 1 && !fromAtlantic) || dp[m][n] == 2)
            return;

        // mark pacific visited as 1, atlantic as 2
        dp[m][n] = fromAtlantic ? 2 : 1;
        int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int[] d : dir) {
            int nextM = m + d[0];
            int nextN = n + d[1];

            if (nextM < 0 || nextM >= row || nextN < 0 || nextN >= col)
                continue;

            if (heights[m][n] > heights[nextM][nextN])
                continue;

            dfs(heights, row, col, nextM, nextN, dp, res, fromAtlantic);
        }
    }

```