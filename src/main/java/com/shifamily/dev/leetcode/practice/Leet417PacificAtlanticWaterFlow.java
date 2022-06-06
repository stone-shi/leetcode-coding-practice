package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet417PacificAtlanticWaterFlow extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 },
                        { 6, 7, 1, 4, 5 }, { 5, 1, 1, 2, 4 } } })
                .answer(new int[][] { { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 2 }, { 3, 0 }, { 3, 1 }, { 4, 0 } })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 2, 1 }, { 1, 2 } } })
                .answer(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } })
                .description("Example 2").build());
        return cases;
    }

    @Override
    protected Object convertReturn(Object r) {
        return convertReturnNeedSortListListTwoNumbers((List<List<Integer>>) r);
    }

    @CaseRunner
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
}
