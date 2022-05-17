package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 37. Sudoku Solver
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


 A sudoku puzzle...


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
 */
public class Leet37SudokuSolver extends BasicStudy {

    public Leet37SudokuSolver() {
        char[][][] caseP1 = {
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
        };
        char[][][] answer = {
                {{'5','3','4','6','7','8','9','1','2'},
                        {'6','7','2','1','9','5','3','4','8'},
                        {'1','9','8','3','4','2','5','6','7'},
                        {'8','5','9','7','6','1','4','2','3'},
                        {'4','2','6','8','5','3','7','9','1'},
                        {'7','1','3','9','2','4','8','5','6'},
                        {'9','6','1','5','3','7','2','8','4'},
                        {'2','8','7','4','1','9','6','3','5'},
                        {'3','4','5','2','8','6','1','7','9'}}
        };

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false, null, null, 0);
        }

    }

    @Override
    protected boolean compareAnswer(Object r, Object a, boolean orderMatter, Comparator comparator) {
        char[][] cr = (char[][])r;
        char[][] ca = (char[][])a;
        return Arrays.deepEquals(cr, ca);
    }




    @CaseRunner
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
}
