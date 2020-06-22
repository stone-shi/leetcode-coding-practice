# 原题
934 Shortest Bridge
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


# 解法

先用DFS标记第一个岛。具体是：
找到第一个A[i][j] = 1。然后从这个坐标向dfs四个方向找，然后把找到的所有岛标记为2 也可以用visited数组。
每找到一个坐标，放到Queue里面。

然后用BFS，从Queue取坐标进行level order。每取出一个坐标，向四个方向检查，如果其中一个方向是第二个岛，返回level。
不是的话，mark成2，放入queue作为下一层。

不直接用BFS的原因：如果不用DFS先标记第一个岛的话，BFS的时候，没有办法快速检查是否连接。



## 复杂度
时间复杂度 O(N * M)
空间复杂度 O(N)


## 代码
```Java
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
```
