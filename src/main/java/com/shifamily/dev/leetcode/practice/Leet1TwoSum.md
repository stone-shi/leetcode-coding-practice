# 原题

1 Two Sum
Easy

13289

482

Add to List

Share
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

# 解法

利用HashMap进行查找.

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public Integer[] twoSum(int[] nums, int target){

        HashMap<Integer, Integer> map = new HashMap<>();
        Integer[] res = new Integer[2];
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null ){
                res[0] = idx;
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);

        }
        return null;
    }
```
