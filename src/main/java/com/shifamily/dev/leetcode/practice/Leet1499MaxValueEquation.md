# Leetcode #1499 Max Value of Equation

## 原题

[1499 Max Value of Equation](https://leetcode.com/problems/max-value-of-equation/)

**Hard** 830 31

You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where `points[i] = [xi, yi]` such that `xi < xj` for all `1 <= i < j <= points.length`. You are also given an integer k.

Return the maximum value of the equation `yi + yj + |xi - xj|` where `|xi - xj| <= k and 1 <= i < j <= points.length`.

It is guaranteed that there exists at least one pair of points that satisfy the constraint `|xi - xj| <= k`.

**Example 1:**

> `Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1`
`Output: 4`
**Explanation:** The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.

**Example 2:**

> `Input: points = [[0,0],[3,0],[9,2]], k = 3`
`Output: 3`
**Explanation:** Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 
**Constraints:**

* 2 <= points.length <= 105
* points[i].length == 2
* -108 <= xi, yi <= 108
* 0 <= k <= 2 * 108
* xi < xj for all 1 <= i < j <= points.length
* xi form a strictly increasing sequence.

## 解法

看代码注释：
`yi + yj + | xi - xj|`, since x is sorted, `| xi - xj | -> xj - xi`
`yi + yj + | xi - xj|` -> `yi + yj + xj - xi` -> `xj + yj + (yi - xi)`

循环所有的array，代表j 
i 就是 j 之前的所有元素。
对于每一个j的点，我们要找到 最大的 yi - xi

两种做法，可以再循环时候，把 一个pair(yi - xi, xi)放入 priority queue 或者 monotonic stack。两者都可以保证前面的元素 yi - xi最大。
找j对应的最大值前，做 xj - xi <= k检查，不符合的都去掉。


## 复杂度

monotonic solution - O(N)

## 代码

```Java

    // monotonic stack solution
    // yi + yj + | xi - xj|, since x is sorted, | xi - xj | -> xj - xi.
    // yi + yj + | xi - xj| -> yi + yj + xj - xi -> xj + yj + yi - xi
    // given j, we know xj + yj, we need find i has max ( yi - xi)
    // we can use priority queue to sort i O(N log N) OR monotonic stack (O(N))
    // this is a monotonic stack solution
    @CaseRunner
    public int findMaxValueOfEquation(int[][] points, int k) {
        int res = Integer.MIN_VALUE;
        Deque<int[]> q = new LinkedList<>();
        for (int[] point : points) { // go throuh all points (j)
            // remove point not qualify xj - xi > k
            while (!q.isEmpty() && point[0] - q.peekFirst()[1] > k)
                q.pollFirst();

            // if any left, first one is biggest of qualifed (monotonic stack to make sure)
            if (!q.isEmpty()) {
                res = Math.max(res, point[0] + point[1] + q.peekFirst()[0]); //  point[0] + point[1] + q.peekFirst()[0] -> xj + yj + (yi - xi)
            }
            // monotonic stack here
            while (!q.isEmpty() && q.peekLast()[0] < (point[1] - point[0]))
                q.removeLast();

            // enqueue two elements first is (yi - xi), second is xi
            q.offerLast(new int[] { point[1] - point[0], point[0] });
        }
        return res;
    }

```