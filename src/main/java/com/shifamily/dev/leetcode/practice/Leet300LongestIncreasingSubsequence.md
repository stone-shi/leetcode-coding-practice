# Leetcode #300 Longest Increasing Subsequence

## 原题

[300 Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

**Medium** 12122 237

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, `[3,6,2,7]` is a subsequence of the array `[0,3,1,6,2,2,7]`.

**Example 1:**

> `Input: nums = [10,9,2,5,3,7,101,18]`
`Output: 4`
**Explanation:** The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

**Example 2:**

> `Input: nums = [0,1,0,3,2,3]`
`Output: 4`

**Example 3:**

> `Input: nums = [7,7,7,7,7,7,7]`
`Output: 1`

**Constraints:**

* `1 <= nums.length <= 2500`
* `-104 <= nums[i] <= 104`

**Follow up**: Can you come up with an algorithm that runs in `O(n log(n))` time complexity?

## 解法

### Solution 1 dp 

O( N^2^ )

给定 `dp[i]` 为 `num[i]` 结尾的最长的subsequence. 每个dp的初始值1，代表自长1。
`dp[0] = 1`
`dp[1] = num[1] > num[0] ? dp[0] + 1 : dp[0]`，如果一个数比之前的数字大，`num[i] > num[j]`那么 `dp[i] = dp[j] + 1`，然后扫描全部i之前的数字，找出最大的dp。

### Solution 2, greedy sub array + binary search

Solution 3 一样的实现，只是用util里面的BinarySearch

[参考](https://leetcode.com/problems/longest-increasing-subsequence/discuss/1326308/C%2B%2BPython-DP-Binary-Search-BIT-Solutions-Picture-explain-O(NlogN))

Let's construct the idea from following example.
Consider the example nums = [2, 6, 8, 3, 4, 5, 1], let's try to build the increasing subsequences starting with an empty one: sub1 = [].

* Let pick the first element, sub1 = [2].
* 6 is greater than previous number, sub1 = [2, 6]
* 8 is greater than previous number, sub1 = [2, 6, 8]
* 3 is less than previous number, we can't extend the subsequence sub1, but we must keep 3 because in the future there may have the longest subsequence start with [2, 3], sub1 = [2, 6, 8], sub2 = [2, 3].
* With 4, we can't extend sub1, but we can extend sub2, so sub1 = [2, 6, 8], sub2 = [2, 3, 4].
* With 5, we can't extend sub1, but we can extend sub2, so sub1 = [2, 6, 8], sub2 = [2, 3, 4, 5].
* With 1, we can't extend neighter sub1 nor sub2, but we need to keep 1, so sub1 = [2, 6, 8], sub2 = [2, 3, 4, 5], sub3 = [1].
* Finally, length of longest increase subsequence = len(sub2) = 4.

In the above steps, we need to keep different sub arrays (sub1, sub2..., subk) which causes poor performance. But we notice that we can just keep one sub array, when new number x is not greater than the last element of the subsequence sub, we do binary search to find the smallest element >= x in sub, and replace with number x.
Let's run that example nums = [2, 6, 8, 3, 4, 5, 1] again:

* Let pick the first element, sub = [2].
* 6 is greater than previous number, sub = [2, 6]
* 8 is greater than previous number, sub = [2, 6, 8]
* 3 is less than previous number, so we can't extend the subsequence sub. We need to find the smallest number >= 3 in sub, it's 6. Then we overwrite it, now sub = [2, 3, 8].
* 4 is less than previous number, so we can't extend the subsequence sub. We overwrite 8 by 4, so sub = [2, 3, 4].
* 5 is greater than previous number, sub = [2, 3, 4, 5].
* 1 is less than previous number, so we can't extend the subsequence sub. We overwrite 2 by 1, so sub = [1, 3, 4, 5].
* Finally, length of longest increase subsequence = len(sub) = 4.

![image](https://assets.leetcode.com/users/images/737a284c-b0f7-4dea-aa0f-068e768e65f5_1625856915.051443.jpeg)

## 代码

```Java
    // solution 1, dp - O(N^2)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    // solution 2, greedy subarry + binary search, O(N Log N)
    @CaseRunner
    public int lengthOfLIS2(int[] nums) {

        int[] sub = new int[nums.length];
        int subLen = 1;
        sub[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub[subLen - 1]) {
                sub[subLen++] = nums[i];
                continue;
            }
            int m = binarySearchLowBound(sub, subLen, nums[i]);
            if (m <= subLen)
                sub[m] = nums[i];

        }
        return subLen;

    }

    private int binarySearchLowBound(int[] a, int len, int k) {
        int l = 0;
        int r = len;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= k) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

        // solution 3, same as 2 but use Java binary search function
    @CaseRunner
    public int lengthOfLIS3(int[] nums) {

        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
                continue;
            }
            int m = Collections.binarySearch(sub, nums[i]);
            if (m >= 0)
                sub.set(m, nums[i]);
            else {
                // m = (-(insertion point) - 1) ref:
                // https://docs.oracle.com/javase/6/docs/api/java/util/Collections.html#binarySearch%28java.util.List,%20T%29
                int insert = -1 * m - 1;
                if (insert < sub.size()) {
                    sub.set(insert, nums[i]);
                }
            }
        }
        return sub.size();

    }

```