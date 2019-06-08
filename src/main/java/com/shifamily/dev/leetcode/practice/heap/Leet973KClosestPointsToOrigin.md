# 原题
LeetCode 973 
973 K Closest Points to Origin
Medium

416

43

Favorite

Share
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

# 解法Log
到原点距离计算  sqrt(x^2 + y^2)，排序时不必考虑平方根  

## 解法1
最简单的解法，直接对整个points数组排序

### 复杂度
时间复杂度 O(NLogN)
空间复杂度 O(1)


### 代码
```Java
   public int[][] kClosestSort(int[][] points, int K) {
        Arrays.sort(points,  Comparator.comparingInt(a -> a[0] * a[0] + a[1] * a[1]));
        return Arrays.copyOfRange(points, 0, K );
    }
```

## 解法2
这次利用 max heap来排序，heap内最多时候只有 k个point，所以比全排序快

### 复杂度
时间复杂度 O(NLogK)
空间复杂度 O(K)


### 代码
```Java
   public int[][] kClosestHeap(int[][] points, int K) {

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0]*b[0] + b[1]*b[1] - a[0]*a[0] - a[1]*a[1]);

        for (int i = 0; i < points.length ; i++) {
            queue.offer(points[i]);
            if (queue.size() > K)
                queue.poll();
        }

        int[][] res = new int[K][];
        for (int i = 0; i < K; i++)
            res[i] = queue.poll();

        return res;

    }
```
## 解法3
这个是 quick select算法，是选k th 元素最快的算法。

### 复杂度
时间复杂度 平均O(N)，worst O(N^2)
空间复杂度 O(1)


### 代码
```Java
    public int[][] kClosestQuickSelect(int[][] points, int K) {

        int l = 0;
        int r = points.length - 1;

        while (l < r) {
            int mid = helper(points, l, r);
            if (mid < K)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper( int[][]  points, int l, int r){
        int[] pivot = points[l]; //"随机"选择的 pivot point

        while ( l < r){
            //先 r 减小，找到第一个 小于 pivot的
            while (l < r && compare(points[r], pivot) >= 0 ) r--;
            points[l] = points[r];
            //l 增加，找到第一个 大于 Pivot的
            while (l < r && compare(points[l], pivot) <= 0) l++;
            points[r] = points[l];
        }
        points[l] = pivot;
        return l;
    }


    private int compare(int[] a, int[] b){
        return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
    }

```

