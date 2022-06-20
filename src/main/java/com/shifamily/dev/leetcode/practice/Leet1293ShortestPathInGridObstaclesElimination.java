package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;

import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet1293ShortestPathInGridObstaclesElimination extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new int[][] { { 0, 1, 0, 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
                                { 0, 0, 0, 1, 0, 0, 1, 0 } },
                        1 })
                .answer(13)
                .description("case 0").build());

        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new int[][] { { 0, 0 }, { 1, 0 }, { 1, 0 }, { 1, 0 }, { 1, 0 }, { 1, 0 }, { 0, 0 }, { 0, 1 },
                                { 0, 1 }, { 0, 1 }, { 0, 0 }, { 1, 0 }, { 1, 0 }, { 0, 0 }
                        }, 4 })
                .answer(14)
                .description("case 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new int[][] { { 0, 0, 0 }, { 1, 1, 0 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 0, 0 } }, 1 })
                .answer(6)
                .description("case 2").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new int[][] { { 0, 1, 1 }, { 1, 1, 1 }, { 1, 0, 0 } }, 1 })
                .answer(-1)
                .description("case 3").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new int[][] {
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                                { 0, 1, 0, 1, 1, 1, 1, 0, 0, 0 },
                                { 0, 1, 0, 0, 0, 0, 0, 0, 1, 0 },
                                { 0, 1, 1, 1, 1, 1, 1, 0, 1, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }
                        }, 1 })
                .answer(20)
                .description("case 4").build());
        return cases;
    }

    // second try - 2022/06/19
    @CaseRunner
    public int shortestPath3(int[][] grid, int k) {
        int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        if (n == 0)
            return 0;

        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, k });
        visited[0][0][k - 1] = true;

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] node = q.poll();
                if (node[0] == m - 1 && node[1] == n - 1)
                    return steps;
                for (int[] d : dir) {
                    int nextM = d[0] + node[0];
                    int nextN = d[1] + node[1];
                    int nextK = node[2];
                    if (nextM < 0 || nextM >= m || nextN < 0 || nextN >= n)
                        continue;
                    if (grid[nextM][nextN] == 1) {
                        if (nextK == 0)
                            continue;
                        else
                            nextK--;
                    }
                    if (visited[nextM][nextN][nextK])
                        continue;
                    visited[nextM][nextN][nextK] = true;
                    q.offer(new int[] { nextM, nextN, nextK });
                }
            }
            steps++;
        }
        return -1;

    }

    static class GridNode {
        int m;
        int n;
        int k;
        Set<String> path = new HashSet<>();

        GridNode(int m, int n, int k, GridNode prev) {
            this.m = m;
            this.n = n;
            this.k = k;
            if (prev != null) {
                String nodeId = String.valueOf(prev.m) + ',' + String.valueOf(prev.n);
                path.addAll(prev.path);
                path.add(nodeId);
            }
        }

        boolean isInPath(int m, int n) {
            String nodeId = String.valueOf(m) + ',' + String.valueOf(n);
            return path.contains(nodeId);
        }
    }

    // solution 1, easy to understand but it use hash set to keep the path and will
    // cause timeout
    @CaseRunner
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // [m, n, k, path node]
        Queue<GridNode> q = new LinkedList<>();
        q.offer(new GridNode(0, 0, k, null));
        int[][] dirToGo = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!q.isEmpty()) {
            GridNode curr = q.poll();
            if (curr.m == m - 1 && curr.n == n - 1) {
                // found
                return curr.path.size();
            }
            for (int[] d : dirToGo) {
                int i = curr.m + d[0];
                int j = curr.n + d[1];
                // check the out of bound AND node already visited in current path
                if (i > m - 1 || j > n - 1 || i < 0 || j < 0 || curr.isInPath(i, j))
                    continue;
                if (grid[i][j] == 0) {
                    q.offer(new GridNode(i, j, curr.k, curr));
                } else {
                    if (curr.k > 0)
                        q.offer(new GridNode(i, j, curr.k - 1, curr));
                }
            }
        }
        return -1;
    }

    // solution 2 - visited just need keep k + 1 states, if k same, no need to
    // record in same level, also added BFS level order inner loop
    @CaseRunner
    public int shortestPath2(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][k + 1];
        // m, n, k
        q.offer(new int[] { 0, 0, k });
        int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int rs = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr[] = q.poll();
                if (curr[0] == m - 1 && curr[1] == n - 1)
                    return rs;
                for (int[] d : dir) {
                    int next_m = curr[0] + d[0];
                    int next_n = curr[1] + d[1];
                    int next_k = curr[2];
                    if (next_m > m - 1 || next_n > n - 1 || next_n < 0 || next_m < 0 || visited[next_m][next_n][next_k])
                        continue;
                    visited[next_m][next_n][next_k] = true;
                    if (grid[next_m][next_n] == 1 && next_k > 0) {
                        next_k--;
                    }
                    if (grid[next_m][next_n] == 0 || next_k != curr[2]) {
                        q.offer(new int[] { next_m, next_n, next_k });
                    }
                }
            }
            rs++;
        }
        return -1;
    }

}
