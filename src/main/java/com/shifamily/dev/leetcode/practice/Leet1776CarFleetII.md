# Leetcode #1776 Car Fleet II

## 原题

[1776 Car Fleet II](https://leetcode.com/problems/car-fleet-ii/)

**<span style="color:red">Hard</span>** 611 16

There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [position~i~, speed~i~] represents:

position~i~ is the distance between the i*th* car and the beginning of the road in **meters**. It is guaranteed that position~i~ < position~i+1~.
speedi is the initial speed of the i*th* car in **meters per second**.
For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where `answer[i]` is the time, in seconds, at which the i^th^ car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10^-5^ of the actual answers are accepted.

**Example 1:**

> `Input: cars = [[1,2],[2,1],[4,3],[7,2]]`
`Output: [1.00000,-1.00000,3.00000,-1.00000]`
**Explanation:** After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.

**Example 2:**

> `Input: cars = [[3,4],[5,4],[6,3],[9,1]]`
`Output: [2.00000,1.00000,1.50000,-1.00000]`
 
**Constraints:**

* `1 <= cars.length <= 105`
* `1 <= positioni, speedi <= 106`
* `positioni < positioni + 1`

## 解法

stack

从数组最后往前找，建立一个stack，如果当前车车速比栈顶慢（永远追不上）**或者** 当前车追上栈顶车的时间比栈顶车追上的时间慢（栈顶车在当前车追上前就collide了，不考虑了）就把栈顶pop。

然后栈中还有的话，计算当前的追上时间，然后当前车入栈.

## 复杂度

时间：O(N)
空间：O(N)

## 代码

```Java
      int n = cars.length;
        double[] res = new double[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            int s = cars[i][1];
            int p = cars[i][0];
            res[i] = -1.0;

            while (stack.size() > 0) {
                int j = stack.peek();
                int s1 = cars[j][1];
                int p1 = cars[j][0];
                if (s <= s1 || (p1 - p) * 1.0 / (s - s1) > res[j] && res[j] > 0) {
                    stack.pop();
                } else
                    break;
            }
            if (stack.size() > 0) {
                int j = stack.peek();
                res[i] = (cars[j][0] - p) * 1.0 / (s - cars[j][1]);
            }
            stack.push(i);
        }
        return res;

```