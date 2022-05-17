# 原题
53 Maximum Subarray
Easy

6118

254

Add to List

Share
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
# 解法
DP问题

设dp[i] 为以i为结尾的子串最大的和
可看出dp[0] 即为 nums[0]
dp[i] = Max(dp[i-1], 0) + nums[i] //意思是当求下一个i的最大子串，看dp[i-1]是否大于0，如果大于加上，不大于不加


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length]; //dp[i]代表i为最后元素的最大sub array
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
```
