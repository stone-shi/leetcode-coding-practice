# Leetcode #803. Bricks Falling When Hit

## 原题

[803 Bricks Falling When Hit](https://leetcode.com/problems/bricks-falling-when-hit/)

**<span style="color:red">Hard</span>** 841 172

You are given an `m x n` binary grid, where each 1 represents a brick and 0 represents an empty space. A brick is stable if:

* It is directly connected to the top of the grid, or
* At least one other brick in its four adjacent cells is stable.

You are also given an array hits, which is a sequence of erasures we want to apply. Each time we want to erase the brick at the location `hits[i] = (rowi, coli)`. The brick on that location (if it exists) will disappear. Some other bricks may no longer be stable because of that erasure and will fall. Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).

Return an array result, where each result[i] is the number of bricks that will fall after the i^th^ erasure is applied.

**Note** that an erasure may refer to a location with no brick, and if it does, no bricks drop.

 

**Example 1:**

> `Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]`
`Output: [2]`
**Explanation:** Starting with the grid:
\[[1,0,0,0],
  [<u>1</u>,1,1,0]]
We erase the underlined brick at (1,0), resulting in the grid:
\[[1,0,0,0],
 [0,<u>1</u>,<u>1</u>,0]]
The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
\[[1,0,0,0],
 [0,0,0,0]]
Hence the result is [2].

**Example 2:**

> `Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]`
`Output: [0,0]`
**Explanation:** Starting with the grid:
\[[1,0,0,0],
 [1,<u>1</u>,0,0]]
We erase the underlined brick at (1,1), resulting in the grid:
\[[1,0,0,0],
 [1,0,0,0]]
All remaining bricks are still stable, so no bricks fall. The grid remains the same:
\[[1,0,0,0],
 [<u>1</u>,0,0,0]]
Next, we erase the underlined brick at (1,0), resulting in the grid:
\[[1,0,0,0],
 [0,0,0,0]]
Once again, all remaining bricks are still stable, so no bricks fall.
Hence the result is [0,0].
 
**Constraints:**

* `m == grid.length`
* `n == grid[i].length`
* `1 <= m, n <= 200`
* `grid[i][j] is 0 or 1.`
* `1 <= hits.length <= 4 * 104`
* `hits[i].length == 2`
* `0 <= xi <= m - 1`
* `0 <= yi <= n - 1`
* All (xi, yi) are unique.

## 解法

[参考](https://leetcode.com/problems/bricks-falling-when-hit/discuss/119829/Python-Solution-by-reversely-adding-hits-bricks-back)
![](https://s3-lc-upload.s3.amazonaws.com/users/luckypants/image_1521450349.png)
![](https://s3-lc-upload.s3.amazonaws.com/users/luckypants/image_1521450376.png)
![](https://s3-lc-upload.s3.amazonaws.com/users/luckypants/image_1521450387.png)
![](https://s3-lc-upload.s3.amazonaws.com/users/luckypants/image_1521450393.png)

- 把所有hits中的brick全部remove，因为有可能本来是0，所以 1->0, 0->-1，方便以后检查。
- dfs把所有链接到 i = 0 row的所有 brick全部mark成2。这是remove所有的hit后的最终链接状态。
- 从hits数组后向前，把 brick全部加回去，并且要计数有多少应为这个动作而加回去brick。从后往前是应为 isConnected检查，最后remove的在之前是连接的。
  - 做某些检查，比如是 -1代表原来是0，没有连接的，说明之前其实已经drop了，直接countine。

## 复杂度

Time O(m x n | k) k -> hits， m x n -> dfs worst case

## 代码

```Java
    @CaseRunner
    public int[] hitBricks2(int[][] grid, int[][] hits) {
        int m = grid.length;
        if (m == 0)
            return null;
        int n = grid[0].length;
        int k = hits.length;

        // remove all hit blocks: original 1 -> 0, 0 -> -1
        for (int[] hit : hits)
            grid[hit[0]][hit[1]]--;

        // dfs marks all connected cell to 2
        for (int i = 0; i < n; i++)
            dfs2(grid, 0, i);

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            int hi = hits[i][0];
            int hj = hits[i][1];
            if (grid[hi][hj] != 0)
                continue;
            grid[hi][hj] = 1;
            if (!isConnected(grid, hi, hj))
                continue;
            res[i] = dfs2(grid, hi, hj) - 1;
        }
        return res;
    }

    private boolean isConnected(int[][] grid, int i, int j) {
        if (i == 0)
            return true;

        for (int[] d : dir) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            if (nextI < 0 || nextJ < 0 || nextI >= grid.length || nextJ >= grid[0].length)
                continue;
            if (grid[nextI][nextJ] == 2)
                return true;
        }

        return false;
    }

    private int dfs2(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1)
            return 0;
        int res = 1;
        grid[i][j] = 2;
        for (int[] d : dir) {
            res += dfs2(grid, i + d[0], j + d[1]);
        }
        return res;
    }
```