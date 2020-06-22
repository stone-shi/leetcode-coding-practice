# 原题

LeetCode 238

238 Product of Array Except Self
Medium

2261

189

Favorite

Share
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
# 解法

除了i自己的乘积其实可以看成i的左边乘积乘以i的右边乘积。
product of array except a[i] = a[0, i) * a(i, last]

这样就可以用第一次循环，计算 i 左边所有数字的乘积：
```Java
        result[0] = 1;
        for (int i = 1; i < nums.length; i++)
            result[i] = result[ i -1] * nums[i-1];

```
第二次循环，计算 i 右边所有数字的乘积。  
第三次循环把 i 左乘积和右乘积乘起来。  
我们可以利用现有的result数组，把第二次和第三次循环结合起来。



## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];

        //第一个循环，计算i的左边所有的乘积，当i = 0的时候，乘积初始化为1, 然后每个i左边所有乘积就是 result[i-1] * num[i - 1]
        result[0] = 1;
        for (int i = 1; i < nums.length; i++)
            result[i] = result[ i -1] * nums[i-1];

        //第二个循环，计算i的右边所有数字的乘积，这里，因为最终的结果是要 i左边×i右边的，所以直接用了 result数组。
        int right = 1;
        for (int i = nums.length -2; i >=0 ; i--) {
            //i的右边乘积
            right = right * nums[i + 1];
            //这里直接用了result，其实就是左边*右边
            result[i] = result[i] * right;
        }

        return result;
    }

```
