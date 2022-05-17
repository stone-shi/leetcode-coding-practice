# 原题
37 Sudoku Solver
Hard

1682

89

Add to List

Share
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
![](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)
![](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

A sudoku puzzle...
vc==

...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
Accepted
180,717
Submissions
424,780
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
Valid Sudoku
Medium
Unique Paths III
Hard
# 解法

https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/sudoku

Backtracking



## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
  public void solveSudoku(char[][] board) {


        backTrack(board, 0, 0);


    }


 
    boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }
    private boolean backTrack(char[][] board, int i, int j){
        int n = 9;
        int m = 9;

        if (j == m)
            return backTrack(board, i + 1, 0);

        if (i == n)
            return true;

        if (board[i][j] != '.')
            return backTrack(board, i, j + 1);

        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, i, j, ch))
                continue;
            board[i][j] = ch;
            if (backTrack(board, i, j + 1))
                return true;
            board[i][j] = '.';
        }

        return false;


    }
```
