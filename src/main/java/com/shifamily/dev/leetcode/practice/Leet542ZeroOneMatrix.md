# Leetcode #542 01 Matrix

## 原题

[542 01 Matrix](https://leetcode.com/problems/01-matrix/)

**<span style="color:blue">Medium</span>** 4721 231

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2021/04/24/01-1-grid.jpg)

> `Input: mat = [[0,0,0],[0,1,0],[0,0,0]]`
`Output: [[0,0,0],[0,1,0],[0,0,0]]`

**Example 2:**
![Example 2](https://assets.leetcode.com/uploads/2021/04/24/01-2-grid.jpg)

> `Input: mat = [[0,0,0],[0,1,0],[1,1,1]]`
`Output: [[0,0,0],[0,1,0],[1,2,1]]`
 
**Constraints:**

* `m == mat.length`
* `n == mat[i].length`
* `1 <= m, n <= 104`
* `1 <= m * n <= 104`
* `mat[i][j] is either 0 or 1.`
* `There is at least one 0 in mat.`

## 解法

BFS, 先把0 enqueue，然后处理周围第一层1，把1 enqueue，然后处理第二层，依次类推

## 复杂度

O(M*N)

## 代码

```Java
   public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        if (m <= 0)
            return null;
        int n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();
        // you can reuse mat too
        // this loop will add zero (first layer in queue) and marked rest -1 (not processed)
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    q.offer(new int[]{i, j});
                else
                    res[i][j] = -1;
            }
        }

        while (!q.isEmpty()){
            // get 
            int[] pos = q.poll();

            // check up, down, left and right, if not processed, enqueue and update result + 1
            for (int[] d : dir) {
                int nextM = d[0] + pos[0];
                int nextN = d[1] + pos[1];
                if (nextM < 0 || nextN < 0 || nextM >= m || nextN >= n )
                    continue;
                if (res[nextM][nextN] == -1){
                    res[nextM][nextN] = res[pos[0]][pos[1]] + 1;
                    q.offer(new int[]{nextM, nextN});
                }
            }

        }


        return res;
    }



```