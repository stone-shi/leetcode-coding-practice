# 原题
189. Rotate Array
Easy

2657

792

Add to List

Share
Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 

Constraints:

1 <= nums.length <= 2 * 10^4
It's guaranteed that nums[i] fits in a 32 bit-signed integer.
k >= 0
Accepted
474,292
Submissions
1,391,043
Seen this question in a real interview before?

Yes

No
Contributor
Freezen
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Microsoft
|
6

Amazon
|
4

Facebook
|
3

Paypal
|
2
The easiest solution would use additional memory and that is perfectly fine.
The actual trick comes when trying to solve this problem without using any additional memory. This means you need to use the original array somehow to move the elements around. Now, we can place each element in its original location and shift all the elements around it to adjust as that would be too costly and most likely will time out on larger input arrays.
One line of thought is based on reversing the array (or parts of it) to obtain the desired result. Think about how reversal might potentially help us out by using an example.
The other line of thought is a tad bit complicated but essentially it builds on the idea of placing each element in its original position while keeping track of the element originally in that position. Basically, at every step, we place an element in its rightful position and keep track of the element already there or the one being overwritten in an additional variable. We can't do this in one linear pass and the idea here is based on cyclic-dependencies between elements.
# 解法
[1,2,3,4,5,6,7], k = 3 将转换为
[5,6,7,1,2,3,4]

把数组reverse一遍：[7,6,5,4,3,2,1]
这样三个就到了前面，
然后把前3 [0,k-1]个反一下：[5,6,7,4,3,2,1]
然后把后4 [k, len -1] 个反一下：[5,6,7,1,2,3,4]
就得到了结果。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)

## 代码
```Java
   public int[] rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0,  nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k , nums.length -1);
        return nums;
    }

    private void reverse(int[] nums, int start, int end){
        while (start < end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
```
