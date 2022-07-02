package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet562LongestLineConsecutiveOneMatrix extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 1 } } }).answer(3)
                .description("case a").build());
        return cases;
    }


    // second try 2022/6/28
    @CaseRunner
    public int longestLine2(int[][] m) {
        int rows = m.length;
        int cols = m.length;

        int[][] dpV = new int[rows + 1][cols + 1];
        int[][] dpD = new int[rows + 1][cols + 2];
        int[][] dpAD = new int[rows + 1][cols + 1];

        int res = 0;
        for (int i = 1; i <= rows; i++) {
            int ctH = 0;
            for (int j = 1; j <= cols; j++) {
                int v = m[i - 1][j - 1];
                ctH++;
                dpV[i][j] = dpV[i - 1][j] + v;
                dpD[i][j] = dpD[i - 1][j + 1] + v;
                dpAD[i][j] = dpAD[i - 1][j - 1] + v;
            }
            res = Math.max(res, ctH);
        }
        for (int i = 0; i < cols; i++) {
            res = Math.max(res, dpV[rows][i]);
            res = Math.max(res, dpD[rows][i]);
            res = Math.max(res, dpAD[rows][i]);
        }
        return res;
    }

    @CaseRunner
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

}
