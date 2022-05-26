# Leetcode #4 Median of Two Sorted Arrays

## 原题

[4 Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
Hard 16606 2016

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be `O(log (m+n)).`

**Example 1:**

> `Input: nums1 = [1,3], nums2 = [2]`
`Output: 2.00000`
Explanation: merged array = [1,2,3] and median is 2.

**Example 2:**

> `Input: nums1 = [1,2], nums2 = [3,4]`
`Output: 2.50000`
Explanation: `merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.`

**Constraints:**

- nums1.length == m
- nums2.length == n
- 0 <= m <= 1000
- 0 <= n <= 1000
- 1 <= m + n <= 2000
- -10<sup>6</sup> <= nums1[i], nums2[i] <= 10<sup>6</sup>

## 解法

在两个sorted数组中寻找第k个元素
对于两个数组，都 k/2 -1 个元素，如果 a[start + k/2 -1] 小于 b[start + k/2 -1]，表示我们要找的数字不会在 a的分割点 （k/2 -1)左边，反之，不会在 b的左边
然后抛弃不可能的部分，递归呼叫。

核心伪代码

```
if (aMid < bMid) Keep [aRight + bLeft]    
else Keep [bRight + aLeft]
```

## 复杂度

O( log (m+m))

## 代码


```Java
 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;

        l = locateK(nums1, 0, nums2, 0, l);
        r = locateK(nums1, 0, nums2, 0, r);

        return (l + r) / 2.0;
    }

    private int locateK(int[] a, int startA, int[] b, int startB, int k) {
        if (startA >= a.length)
            return b[startB + k - 1];
        if (startB >= b.length)
            return a[startA + k - 1];

        if (k == 1)
            return Math.min(a[startA], b[startB]);

        int na = startA + k / 2 > a.length ? Integer.MAX_VALUE : a[startA + k / 2 - 1];
        int nb = startB + k / 2 > b.length ? Integer.MAX_VALUE : b[startB + k / 2 - 1];

        if (na < nb)
            return locateK(a, startA + k / 2, b, startB, k - k / 2); // a right , b left
        else
            return locateK(a, startA, b, startB + k / 2, k - k / 2); // a left, b right

    }

```
