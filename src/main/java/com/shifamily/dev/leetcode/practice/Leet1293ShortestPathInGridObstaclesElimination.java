package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;

import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

/*
1293. Shortest Path in a Grid with Obstacles Elimination
Hard

2223

41

Add to List

Share
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:


Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:


Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
Accepted
99,292
Submissions
228,079
*/

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
                    if (grid[next_m][next_n] == 1 && next_k > 0){
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
