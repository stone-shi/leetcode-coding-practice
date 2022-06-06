package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet542ZeroOneMatrix extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(
                        new Object[] { new int[][] { { 1, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1, 1, 1, 1, 1 },
                                { 1, 1, 1, 0, 0, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
                                { 0, 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
                                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 1 }, { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                                { 0, 1, 0, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 0, 1, 1, 1, 1 } } })
                .answer(new int[][] { { 2, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1, 1, 2, 2, 1 },
                        { 1, 1, 1, 0, 0, 1, 2, 2, 1, 0 }, { 0, 1, 2, 1, 0, 1, 2, 3, 2, 1 },
                        { 0, 0, 1, 2, 1, 2, 1, 2, 1, 0 }, { 1, 1, 2, 3, 2, 1, 0, 1, 1, 1 },
                        { 0, 1, 2, 3, 2, 1, 1, 0, 0, 1 }, { 1, 2, 1, 2, 1, 0, 0, 1, 1, 2 },
                        { 0, 1, 0, 1, 1, 0, 1, 2, 2, 3 }, { 1, 2, 1, 0, 1, 0, 1, 2, 3, 4 } })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0
                } } })
                .answer(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } } })
                .answer(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 2, 1 } })
                .description("Example 2").build());
        return cases;
    }

    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    @CaseRunner
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        if (m <= 0)
            return null;
        int n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();
        // you can reuse mat too
        // this loop will add zero (first layer in queue) and marked rest -1 (not processed)
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    q.offer(new int[]{i, j});
                else
                    res[i][j] = -1;
            }
        }

        while (!q.isEmpty()){
            // get 
            int[] pos = q.poll();

            // check up, down, left and right, if not processed, enqueue and update result + 1
            for (int[] d : dir) {
                int nextM = d[0] + pos[0];
                int nextN = d[1] + pos[1];
                if (nextM < 0 || nextN < 0 || nextM >= m || nextN >= n )
                    continue;
                if (res[nextM][nextN] == -1){
                    res[nextM][nextN] = res[pos[0]][pos[1]] + 1;
                    q.offer(new int[]{nextM, nextN});
                }
            }

        }


        return res;
    }




}
