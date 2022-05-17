# 原题
31 Next Permutation
Medium

3048

1093

Add to List

Share
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Accepted
349.3K
Submissions
1.1M
# 解法
4 3 2 1：已经是最后一个组合，没有下一个了
如下数组：
idx: 0 1 2 3 4 5 6 7 8
val: 1 5 8 4 7 6 5 3 1
从后往前扫描，当idx = 4时，找到 nums[idx] > nums[idx-1]. idx保存在p
然后从idx往后扫描，当idx=7时，找到 nums[p-1] > nums[idx], idx保存在p2
交换 nums[p-1]和nums[p2-1]
得到：
idx: 0 1 2 3 4 5 6 7 8
val: 1 5 8 *5* 7 6 *4* 3 1

然后从P开始的数组，reverse一下。其实就是字典排序最小值，因为扫描的时候已经确定是倒序了，所以只要反一下就好了。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)


## 代码
```Java
   public void nextPermutation(int[] nums) {
        int p = nums.length - 1;
        while (p > 0 && nums[p] <= nums[p -1])
            p--;

        if (p > 0){
            int p1 = p ;
            while (p1 < nums.length && nums[p1] > nums[p - 1])
                p1++;
            swap(nums, p - 1, p1 - 1);
        }
        reverse(nums, p );


    }
    private void swap(int[] nums, int n1, int n2){
        int temp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = temp;
    }

    private void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while (start < end)
            swap(nums, start++, end--);
    }

```
