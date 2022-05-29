package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.*;

public class Leet329LongestIncreasingPathMatrix extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 7, 8, 9 }, { 9, 7, 6 }, { 7, 2, 3 } } }).answer(6)
                .description("case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                        { 19, 18, 17, 16, 15, 14, 13, 12, 11, 10 }, { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 },
                        { 39, 38, 37, 36, 35, 34, 33, 32, 31, 30 }, { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 },
                        { 59, 58, 57, 56, 55, 54, 53, 52, 51, 50 }, { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 },
                        { 79, 78, 77, 76, 75, 74, 73, 72, 71, 70 }, { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 },
                        { 99, 98, 97, 96, 95, 94, 93, 92, 91, 90 },
                        { 100, 101, 102, 103, 104, 105, 106, 107, 108, 109 },
                        { 119, 118, 117, 116, 115, 114, 113, 112, 111, 110 },
                        { 120, 121, 122, 123, 124, 125, 126, 127, 128, 129 },
                        { 139, 138, 137, 136, 135, 134, 133, 132, 131, 130 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } } })
                .answer(140)
                .description("case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } } }).answer(4)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } } }).answer(4)
                .description("case b").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1 } } }).answer(1)
                .description("case c").build());
        return cases;
    }

    @CaseRunner
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxcell = new int[m][n];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int s = dfs(matrix, i, j, m, n, maxcell );
                max = Math.max(s, max);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] maxcell) {
        if (maxcell[i][j] != 0)
            return maxcell[i][j];

        int[][] dir = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int step = 1;
        for (int[] d : dir) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            if (nextI < 0 || nextJ < 0 || nextI >= m || nextJ >= n || matrix[nextI][nextJ] <= matrix[i][j] )
                continue;
            int curr = 1 + dfs(matrix, nextI, nextJ, m, n, maxcell);
            step = Math.max(step, curr);
        }
        maxcell[i][j] = step;
        return step;
    }

}
