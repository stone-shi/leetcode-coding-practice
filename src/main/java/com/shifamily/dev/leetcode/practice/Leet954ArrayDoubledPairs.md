# Leetcode #954 Array of Doubled Pairs

## 原题

[954 Array of Doubled Pairs](https://leetcode.com/problems/array-of-doubled-pairs/)

**<span style="color:blue">Medium</span>**

Given an integer array of even length arr, return true if it is possible to reorder arr such that `arr[2 * i + 1] = 2 * arr[2 * i]` for every `0 <= i < len(arr) / 2`, or `false` otherwise.

**Example 1:**

> `Input: arr = [3,1,3,6]`
`Output: false`

**Example 2:**

> `Input: arr = [2,1,2,6]`
`Output: false`

**Example 3:**

> `Input: arr = [4,-2,2,-4]`
`Output: true`
**Explanation:** We can take two groups, `[-2,-4]` and `[2,4]` to form `[-2,-4,2,4]` or `[2,4,-2,-4]`.
 
**Constraints:**

* ``2 <= arr.length <= 3 * 104``
* `arr.length` is even.
* ``-105 <= arr[i] <= 105``

## 解法

先排序，然后计算每个数字的count，然后和 2 * 数字 比较。

## 复杂度

O (N Log N) // from sort

## 代码

```Java
 public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);

        Map<Integer, Integer> count = new HashMap<>();
        int z = 0;
        for (int a : arr) {
            if (a != 0) {
                count.put(a, count.getOrDefault(a, 0) + 1);
            }else{
                z++;
            }
        }
        if (z % 2 != 0)
            return false;

        for (int a : arr) {
            int target;
            if (a >= 0)
                target = a * 2;
            else if (a % 2 == 0)
                target = a / 2;
            else
                continue;

            if (count.containsKey(target) && count.containsKey(a)) {
                int t = count.get(target);
                int c = count.get(a);
                t = t - c;
                if (t > 0) {
                    count.put(target, t);
                    count.remove(a);
                } else if (t == 0) {
                    count.remove(target);
                    count.remove(a);
                } else {
                    count.remove(target);
                    count.put(a, -1 * t );
                }
            }
        }
        return count.size() == 0;
    }

```