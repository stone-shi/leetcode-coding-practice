# Leetcode #

## 原题

735 Asteroid Collision
Medium

3489

279

Add to List

Share
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 
Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
Accepted
197,506
Submissions
445,252

## 解法

用stack (deque)即可，把数字和stack头部比较，根据题目描述：
如果stack顶是负数（向左走）直接把数字加入
如果stack定时正数，数字也是正数，表示同向，直接加入
如果stack顶是正数，数字是负数，根据绝对值检查碰撞结果。

最后把stack中的数字，反向加入结果即可。


## 复杂度

O(N)

## 代码


```Java
  public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> s = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] == 0)
                continue;

            int income = asteroids[i];
            while (!s.isEmpty()) {
                int last = s.peek();
                if ((last > 0 && income < 0)) {
                    int lastMass = Math.abs(last);
                    int incomeMass = Math.abs(income);
                    if (lastMass == incomeMass) {
                        s.pop();
                        income = 0;
                        break;
                    } else if (lastMass < incomeMass) {
                        s.pop();
                    } else {
                        income = 0;
                        break;
                    }
                } else
                    break;
            }
            if (income != 0)
                s.push(income);
        }
        int[] result = new int[s.size()];
        int i = 0;
        while (!s.isEmpty())
            result[i++] = s.pollLast();
        return result;
    }
```
