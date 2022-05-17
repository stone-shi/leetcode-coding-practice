# 原题
88. Merge Sorted Array
Easy

1611

3487

Add to List

Share
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

# 解法
从最后往前扫描两个数组即可。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i1 = m - 1;
        int i2 = n - 1;
        int current = n + m - 1;

        while (current >= 0 && i2 >= 0){
            if (i1 >= 0 && nums1[i1] > nums2[i2])
                nums1[current--] = nums1[i1--];
            else
                nums1[current--] = nums2[i2--];
        }
    }
```
