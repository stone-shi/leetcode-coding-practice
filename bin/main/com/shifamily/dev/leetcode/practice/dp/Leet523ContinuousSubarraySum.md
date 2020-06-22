# 原题
523 Continuous Subarray Sum
Medium

1230

1715

Add to List

Share
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

 

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 

Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
Accepted
120,913
Submissions
494,522
Seen this question in a real interview before?

Yes

No
Contributor
10000tb
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
28

Amazon
|
2
Subarray Sum Equals K
Medium
# 解法
解法一：
循环计算并保存sum[i], sum[i] 代表 [0,i]的和。
然后双循环，计算 sum[i,j]是否 %k = 0; 其中 sum[i,j] = sum[j] - sum[i] + nums[i]

时间复杂度 O(N^2)
空间复杂度 O(N)

解法二：
大致利用 
(a - b)%k = 0
a%k - b%k = 0
a%k = b%k

也就是一次循环，计算和的模，放入map，下一个循环，查找同样的模是否已经有了，如果有了，是否相差至少2个。
k=6 数组[23,2,6,4,7] 和数组 [23,25,31,35,42]， 模数组[5,1,1,5,0]，也就是在i=3的时候，i=0的模也是5，也就是 (0, 3]之间的和一定是k的倍数。
i = 1，2，3有三个数，符合条件。

## 代码
```Java
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;

```
