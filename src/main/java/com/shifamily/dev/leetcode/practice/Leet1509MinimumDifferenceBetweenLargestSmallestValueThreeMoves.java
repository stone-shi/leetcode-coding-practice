package com.shifamily.dev.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.CaseParameters;

public class Leet1509MinimumDifferenceBetweenLargestSmallestValueThreeMoves extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 82, 81, 95, 75, 20 } }).answer(1)
                .build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 6, 6, 0, 1, 1, 4, 6 } }).answer(2)
                .build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 5, 3, 2, 4 } }).answer(0)
                .build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 5, 0, 10, 14 } }).answer(1)
                .build());
        return cases;
    }

    @CaseRunner
    public int minDifferenceSort(int[] nums) {
        int k = 3;
        int len = nums.length;
        if (len <= k + 1) {
            return 0;
        }
        Arrays.sort(nums);
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < k + 1; i++) {
            minVal = Math.min(nums[len - 1 - k + i] - nums[i], minVal);
        }
        return minVal;
    }

    // second try - 2022/06/19
    @CaseRunner
    public int minDifference2(int[] nums) {
        int n = nums.length;
        int k = 3; // change three numbers
        if (n <= k + 1)
            return 0;
        Arrays.sort(nums);

        int m = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++){
            m = Math.min(m, nums[n - 1 - i] - nums[ k - i ]);
        }
        return m; 

    }

    @CaseRunner
    public int minDifferencePQ(int[] nums) {
        int k = 3;
        int len = nums.length;
        if (len <= k + 1)
            return 0;

        PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < len; i++) {
            pqMin.offer(nums[i]);
            pqMax.offer(nums[i]);
            if (i > k){
                pqMin.poll(); 
                pqMax.poll();
            }
        }
        int[] smallNum = new int[k + 1];
        int i = 0;
        while (!pqMax.isEmpty()){
            smallNum[k - i] = pqMax.poll();
            i++;
        }
        i = 0;
        int m = Integer.MAX_VALUE;
        while (!pqMin.isEmpty()){
            m = Math.min( pqMin.poll() - smallNum[i++], m);
        }
        return m;
    }

}
