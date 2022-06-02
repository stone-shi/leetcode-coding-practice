# Leetcode #15 3Sum

## 原题

[15 3Sum](https://leetcode.com/problems/3sum/)

**<span style="color:blue">Medium</span>**  18137 1743

Given an integer array nums, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j, i != k`, and `j != k, and nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must not contain duplicate triplets.

**Example 1:**

> `Input: nums = [-1,0,1,2,-1,-4]`
`Output: [[-1,-1,2],[-1,0,1]]`

**Example 2:**

> `Input: nums = []`
`Output: []`

**Example 3:**

> `Input: nums = [0]`
`Output: []`

**Constraints:**

* `0 <= nums.length <= 3000`
* `-105 <= nums[i] <= 105`

## 解法

选择第一个数字 `nums[i]`，然后找出 后面2个数字之和 为 `0 - nums[i]`
第一个解法是用了当初 2sum

第二个解法是slide window (比较快)


## 复杂度

O(N^2^)

## 代码

```Java
    // solution 2:
    @CaseRunner
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int target = 0 - nums[i];

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int n = nums[l] + nums[r];
                if (n == target) {
                    List<Integer> tup = new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r]));
                    res.add(tup);
                    while (l < r && nums[l + 1] == nums[l])
                        l++;
                    while (l < r && nums[r - 1] == nums[r])
                        r--;
                    l++;
                    r--;
                } else if (n < target)
                    r--;
                else
                    l++;
            }
        }
        return res;
    }

    // Solution 1: combine two sum - slow ~800ms
    @CaseRunner
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            twoSum(nums, 0 - nums[i], i + 1, nums.length, res);
        }
        List<List<Integer>> r = new ArrayList<>();
        r.addAll(res);
        return r;
    }

    private void twoSum(int[] nums, int target, int start, int end, Set<List<Integer>> res) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (numSet.contains(target - nums[i])) {
                List<Integer> triplets = new ArrayList<>();
                triplets.add(0 - target);
                triplets.add(target - nums[i]);
                triplets.add(nums[i]);
                res.add(triplets);
            } else {
                numSet.add(nums[i]);
            }
        }
    }

```