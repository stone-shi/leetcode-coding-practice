package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet562LongestLineConsecutiveOneMatrix extends BasicStudy{
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 1 } } }).answer(3)
                .description("case a").build());
        return cases;
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
