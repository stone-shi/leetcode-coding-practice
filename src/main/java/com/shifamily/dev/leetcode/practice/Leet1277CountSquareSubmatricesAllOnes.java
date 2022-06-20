package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet1277CountSquareSubmatricesAllOnes extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 } } }).answer(15)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(
                        new Object[] { new int[][] { { 0, 1, 1, 1 }, { 1, 1, 0, 1 }, { 1, 1, 1, 1 }, { 1, 0, 1, 0 } } })
                .answer(13)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 0, 1 }, { 1, 1, 0 }, { 1, 1, 0 } } }).answer(7)
                .description("case b").build());
        return cases;
    }

    @CaseRunner
    public int countSquares3(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == 1){
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j -1], dp[i -1][j -1])) + 1;
                    res += dp[i][j];
                }
            }
        }
        return res;
    }

    // solution 1, reuse dp, save some memory
    @CaseRunner
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rs = 0;

        int dp[][] = new int[2][n + 1];
        int prevDp = 0;
        int currDp = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int curr = matrix[i - 1][j - 1];
                if (curr == 1) {
                    dp[currDp][j] = Math.min(dp[currDp][j - 1], Math.min(dp[prevDp][j], dp[prevDp][j - 1])) + 1;
                    rs += dp[currDp][j];
                } else {
                    dp[currDp][j] = 0;
                }
            }
            prevDp = currDp;
            currDp = currDp == 1 ? 0 : 1;
        }
        return rs;
    }

    // solution 2, do not reuse dp
    @CaseRunner
    public int countSquares2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rs = 0;

        int dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int curr = matrix[i - 1][j - 1];
                if (curr == 1) {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    rs += dp[i][j];
                }
            }
        }
        return rs;
    }
}
