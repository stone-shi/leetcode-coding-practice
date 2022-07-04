package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet803BricksFallingWhenHit extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 0, 1 }, { 1, 1, 1 } },
                        new int[][] { { 0, 0 }, { 0, 2 }, { 1, 1 } } })
                .answer(new int[] { 0, 3, 0 })
                .description("Case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 0, 0, 0 }, { 1, 1, 1, 0 } }, new int[][] { { 1, 0 } } })
                .answer(new int[] { 2 })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 0, 0, 0 }, { 1, 1, 0, 0 } },
                        new int[][] { { 1, 1 }, { 1, 0 } } })
                .answer(new int[] { 0, 0 })
                .description("Example 2").build());
        return cases;
    }

    // optimized solution
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

    // this solution is brutal force one and will get TLE on leetcode
    @CaseRunner
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[hits.length];

        for (int k = 0; k < hits.length; k++) {
            int hitI = hits[k][0];
            int hitJ = hits[k][1];
            if (grid[hitI][hitJ] == 0)
                continue;
            grid[hitI][hitJ] = 0;
            boolean[][] reach = new boolean[m][n];
            for (int i = 0; i < n; i++)
                dfs(grid, 0, i, reach);

            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!reach[i][j] && grid[i][j] == 1) {
                        res[k]++;
                        grid[i][j] = 0;
                    }
                }
            }
        }
        return res;
    }

    int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    private void dfs(int[][] grid, int i, int j, boolean[][] reach) {
        if (grid[i][j] == 1)
            reach[i][j] = true;
        else
            return;

        for (int[] d : dir) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            if (nextI < 0 || nextJ < 0 || nextI >= grid.length || nextJ >= grid[0].length || grid[nextI][nextJ] == 0
                    || reach[nextI][nextJ])
                continue;
            dfs(grid, nextI, nextJ, reach);
        }
    }
}
