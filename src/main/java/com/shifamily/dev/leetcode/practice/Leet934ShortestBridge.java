package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 934. Shortest Bridge
 Medium

 654

 50

 Add to List

 Share
 In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

 Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

 Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)



 Example 1:

 Input: A = [[0,1],[1,0]]
 Output: 1
 Example 2:

 Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 Output: 2
 Example 3:

 Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 Output: 1


 Constraints:

 2 <= A.length == A[0].length <= 100
 A[i][j] == 0 or A[i][j] == 1
 Accepted
 23,641
 Submissions
 50,290
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 ab889977
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 7

 Microsoft
 |
 2


 */
public class Leet934ShortestBridge extends BasicStudy {

    public Leet934ShortestBridge() {
        int[][][] caseP1 = {{{0,1},{1,0}}, {{0,1,0},{0,0,0},{0,0,1}} ,  {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}}};
        int[] answer = {1, 2, 1};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;

        for (int i = 0; i < m; i++) {
            if (found)
                break;
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1){
                    dfs(A, dir, i, j, m, n, queue);
                    found = true;
                    break;
                }
            }
        }

        return bfs(A, dir, m, n, queue);

    }

    private int bfs(int[][] A, int[][] dir,  int m, int n, Queue<int[]> queue){

        int level = 0;
        while (!queue.isEmpty()){
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] pt = queue.poll();
                for (int j = 0; j < dir.length; j++) {
                    int[] pt1 = new int[]{ pt[0] + dir[j][0], pt[1] + dir[j][1] };
                    if ( pt1[0] < 0 || pt1[0] >= m || pt1[1] < 0 || pt1[1] >= n
                            || A[pt1[0]][pt1[1]] == 2 )
                        continue;
                    if (A[pt1[0]][pt1[1]] == 1)
                        return level;
                    A[pt1[0]][pt1[1]] = 2;
                    queue.offer(pt1);
                }
            }
            level++;

        }
        return -1;

    }

    private void dfs(int[][] A, int[][] dir, int i, int j, int m, int n, Queue<int[]> queue){
        if (i < 0 || j < 0 || i >=m || j >= n  || A[i][j] == 0  || A[i][j] == 2)
            return;

        A[i][j] = 2;
        queue.add(new int[]{i, j});
        for (int k = 0; k < dir.length; k++) {
            dfs(A, dir, i + dir[k][0] , j + dir[k][1], m, n, queue);
        }

    }
}
