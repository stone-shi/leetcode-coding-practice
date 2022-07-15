# Leetcode #202. Happy Number

## 原题

[202 Happy Number](https://leetcode.com/problems/happy-number/)

**<span style="color:green">Easy</span>**

Write an algorithm to determine if a number n is happy.

A **happy number** is a number defined by the following process:

* Starting with any positive integer, replace the number by the sum of the squares of its digits.
* Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
* Those numbers for which this process ends in 1 are happy.

Return true if n is a happy number, and false if not. 

**Example 1:**

> `Input: n = 19`
`Output: true`
**Explanation:**
1<sup>2</sup> + 9<sup>2</sup> = 82
8<sup>2</sup> + 2<sup>2</sup> = 68
6<sup>2</sup> + 8<sup>2</sup> = 100
1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1

**Example 2:**

> `Input: n = 2`
`Output: false`

**Constraints:**
- 1 <= n <= 2^31^ - 1

## 解法

主要算法是 loop detection，可以用 Floyd’s Cycle Finding Algorithm。slow point, fast point.


## 复杂度

## 代码

```Java
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = getSumNumber(slow);
            fast = getSumNumber(fast);
            fast = getSumNumber(fast);
        } while (slow != fast && slow != 1);

        return slow == 1;
    }

    private int getSumNumber(int n) {
        int res = 0;
        while (n != 0) {
            int dig = n % 10;
            res += dig * dig;
            n = n / 10;
        }
        return res;
    }

```