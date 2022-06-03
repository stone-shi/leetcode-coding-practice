# Leetcode #562 Longest Line of Consecutive One in Matrix

## 原题

562 Longest Line of Consecutive One in Matrix

Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

**Example:**

```java
Input:

[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]

Output: 3
```

Hint: The number of elements in the given matrix will not exceed 10,000.

## 解法

DP

```java
int val = m[i - 1][j - 1];
dpY[i][j] = val + dpY[i - 1][j];  // 上一行的垂直值
dpD[i][j] = val + dpD[i - 1][j + 1]; // / 上一行的右上方
dpRD[i][j] = val + dpRD[i - 1][j - 1]; // \ 上一行的右下方
```

## 复杂度

O(N^2^)

## 代码

```Java
    public int longestLine(int[][] m) {

        int row = m.length;
        int col = m[0].length;

        int[][] dpY = new int[row + 1][col + 2];
        int[][] dpD = new int[row + 1][col + 2];
        int[][] dpRD = new int[row + 1][col + 2];

        int res = Integer.MIN_VALUE;

        for (int i = 1; i <= row; i++) {
            int countInRow = 0;
            for (int j = 1; j <= col; j++) {
                int val = m[i - 1][j - 1];
                countInRow += val;
                dpY[i][j] = val + dpY[i - 1][j];
                dpD[i][j] = val + dpD[i - 1][j + 1]; // /
                dpRD[i][j] = val + dpRD[i - 1][j - 1]; // \
            }
            res = Math.max(res, countInRow);
        }

        for (int i = 1; i <= col; i++) {
            res = Math.max(res, dpY[row][i]);
            res = Math.max(res, dpD[row][i]);
            res = Math.max(res, dpRD[row][i]);
        }
        return res;
    }
```
