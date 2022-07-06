package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1034ColoringABorder extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[][] { { 1, 1 }, { 1, 2 } }, 0, 0, 3 })
                .answer(new int[][] { { 3, 3 }, { 3, 2 } })
                .description("Example 1").build());
        cases.add(
                CaseParameters.builder().parameters(new Object[] { new int[][] { { 1, 2, 2 }, { 2, 3, 2 } }, 0, 1, 3 })
                        .answer(new int[][] { { 1, 3, 3 }, { 2, 3, 3 } })
                        .description("Example 2").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, 1, 1, 2 })
                .answer(new int[][] { { 2, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 } })
                .description("Example 3").build());
        return cases;
    }

    @CaseRunner
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int checkColor = grid[row][col];
        int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { row, col });

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            visited[pos[0]][pos[1]] = true;
            for (int[] d : dir) {
                int[] next = new int[] { pos[0] + d[0], pos[1] + d[1] };
                if (next[0] < 0 || next[1] < 0 || next[0] >= m || next[1] >= n
                        || (!visited[next[0]][next[1]] && grid[next[0]][next[1]] != checkColor)) {
                    grid[pos[0]][pos[1]] = color;
                    continue;
                }
                if (!visited[next[0]][next[1]])
                    q.offer(next);
            }
        }
        return grid;
    }

}
