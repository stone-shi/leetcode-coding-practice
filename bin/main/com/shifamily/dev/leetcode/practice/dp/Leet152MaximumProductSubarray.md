# 原题

152 Maximum Product Subarray
Medium

3072

131

Add to List

Share
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

# 解法
DP

当前最大乘积要么是前一个最大乘积乘以本身或者就是本身
如果本身是负数，交换最大最小数


## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)

## 代码
```Java
    public int maxProduct(int[] nums) {

        int r = nums[0];
        int minVal = nums[0];
        int maxVal = nums[0];

        for (int i = 1 ; i < nums.length; i++) {

            if (nums[i] < 0){
                int temp = minVal;
                minVal = maxVal;
                maxVal = temp;
            }

            maxVal =   Math.max( nums[i], maxVal * nums[i]);
            minVal =   Math.min( nums[i], minVal * nums[i]);

            r = Math.max(maxVal, r);

        }

        return r;

    }
```
