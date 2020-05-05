package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;
import jdk.nashorn.internal.ir.CallNode;

import java.util.Random;

/**
 https://leetcode.com/problems/trapping-rain-water/

 42. Trapping Rain Water
 Hard

 6232

 110

 Add to List

 Share
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 it is able to trap after raining.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
 (blue section) are being trapped. Thanks Marcos for contributing this image!

 Example:

 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6

 */
public class Leet42TrappingRainWater extends BasicStudy {
    public Leet42TrappingRainWater() {
        int[][] caseP1 = {{2,0,2}, { 0,1,0,2,1,0,1,3,2,1,2,1 } };
        int[] answer = {2, 6};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
        int[] caseRandomP1 = new int[1024 * 32];
        Random random = new Random();
        for (int i = 0; i < caseRandomP1.length; i++)
            caseRandomP1[i] = random.nextInt(Integer.MAX_VALUE);
        int ans = trapDp(caseRandomP1);
        Object[] p = new Object[1];
        p[0] = caseRandomP1;

        addParameterAndAnswer(p, ans, false);


    }

    @CaseRunner
    //Brute force
    public int trapBf(int[] height) {

        int trapVol = 0;
        for (int i = 1; i < height.length - 1; i++) {

            int maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                maxLeft = Math.max(maxLeft, height[j]);

            int maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j < height.length ; j++)
                maxRight = Math.max(maxRight, height[j]);

            int holding = (Math.min(maxLeft, maxRight) - height[i]);

            if (holding > 0)
                trapVol += holding;

        }

        return trapVol;


    }

    @CaseRunner
    //DP
    public int trapDp(int[] height) {
        int len = height.length;
        int trapVol = 0;

        int[] dpLeftMax = new int[len];
        int[] dpRightMax = new int[len];
        for (int i = 1; i < len - 1 ; i++) {
            dpLeftMax[i] = Math.max(dpLeftMax[i - 1], height[i - 1]);
            dpRightMax[len - i - 1] = Math.max(dpRightMax[len - i], height[len - i ]);
        }
        for (int i = 1; i < len - 1 ; i++) {

            int trap = Math.min(dpLeftMax[i], dpRightMax[i]) - height[i];
            if (trap > 0)
                trapVol += trap;
        }


        return trapVol;
    }

    @CaseRunner
    public int trapDp1pass(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trapVol = 0;

        while (left <= right){

            if (height[left] <= height[right]){

                if (leftMax > height[left]){
                    trapVol +=  leftMax - height[left];
                }else
                    leftMax = height[left];

                left++;
            }else{
                if (rightMax > height[right]){
                    trapVol += rightMax - height[right];
                }else
                    rightMax = height[right];
                right--;
            }
        }
        return trapVol;


    }


}
