# Leetcode #

## 原题

[1034 Coloring A Border](https://leetcode.com/problems/coloring-a-border/description/)

**<span style="color:blue">Medium</span>**

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>, and three integers <code>row</code>, <code>col</code>, and <code>color</code>. Each value in the grid represents the color of the grid square at that location.</p>

<p>Two squares belong to the same <strong>connected component</strong> if they have the same color and are next to each other in any of the 4 directions.</p>

<p>The <strong>border of a connected component</strong> is all the squares in the connected component that are either <strong>4-directionally</strong> adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).</p>

<p>You should color the <strong>border</strong> of the <strong>connected component</strong> that contains the square <code>grid[row][col]</code> with <code>color</code>.</p>

<p>Return <em>the final grid</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
<strong>Output:</strong> [[3,3],[3,2]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
<strong>Output:</strong> [[1,3,3],[2,3,3]]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
<strong>Output:</strong> [[2,2,2],[2,1,2],[2,2,2]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
        <li><code>m == grid.length</code></li>
        <li><code>n == grid[i].length</code></li>
        <li><code>1 &lt;= m, n &lt;= 50</code></li>
        <li><code>1 &lt;= grid[i][j], color &lt;= 1000</code></li>
        <li><code>0 &lt;= row &lt; m</code></li>
        <li><code>0 &lt;= col &lt; n</code></li>
</ul>


## 解法

从 row和col开始BFS，把同一 component的格子遍历一遍。检查边界并且color。
## 复杂度

O(M x N)

## 代码

```Java
   public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int checkColor = grid[row][col];
        int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { row, col });

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            visited[pos[0]][pos[1]] = true;
            for (int[] d : dir) {
                int[] next = new int[] { pos[0] + d[0], pos[1] + d[1] };
                if (next[0] < 0 || next[1] < 0 || next[0] >= m || next[1] >= n
                        || (!visited[next[0]][next[1]] && grid[next[0]][next[1]] != checkColor)) {
                    grid[pos[0]][pos[1]] = color;
                    continue;
                }
                if (!visited[next[0]][next[1]])
                    q.offer(next);
            }
        }
        return grid;
    }

```