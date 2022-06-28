package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.*;

public class Leet15ThreeSum extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { -1, 0, 1, 2, -1, -4 } })
                .answer(new int[][] { { -1, -1, 2 }, { -1, 0, 1 } })
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 0, 0, 0, 0 } })
                .answer(new int[][] { { 0, 0, 0 } })
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] {} })
                .answer(new int[][] {})
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 0 } })
                .answer(new int[][] {})
                .description("case a").build());
        return cases;
    }

    // second try - 2022/6/27
    public List<List<Integer>> threeSum3(int[] nums) {
        
        
    }

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

}
