# 原题
42 Trapping Rain Water
Hard

6232

110

Add to List

Share
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
# 解法
暴力解法（Brute force）
根据描述，一个特定index能trap的水就是 这个index找出左边和右边的最高bar，取小值，然后和自身比较。如果大于自身，取其和自身的差，就是可以trap的水。
时间复杂度 O(N^2)
空间复杂度 O(1)

动态规划：
这个暴力算法的问题就是要不断扫描左右，其实可以把左右最高值存下来，成为动态规划解法.
这个解法就是扫描一次当前位置左边最高，和当前位置右边最高，保存在2个数组里：
        int[] dpLeftMax = new int[len];
        int[] dpRightMax = new int[len];
        for (int i = 1; i < len - 1 ; i++) {
            dpLeftMax[i] = Math.max(dpLeftMax[i - 1], height[i - 1]);
            dpRightMax[len - i - N1] = Math.max(dpRightMax[len - i], height[len - i ]);
        }
时间复杂度 O(N)
空间复杂度 O()
        

动态规划- 1pass：主要是空间是O(N)
其实，左边最大和右边最大取的是小值，也就是说，只要从两头定一个指针，left, right。
只要确定height[left] < height[right]，那height[right]是不是right里面的最大值其实没关系。
反之也一样。
这是从上一个dp中改良的方法。而且这是边扫边计算，所以不要保留数组，只要当前就好。
时间复杂度 O(N)
空间复杂度 O(1)


## 复杂度

见上

## 代码
```Java
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

        int left = 1;
        int right = height.length - 2;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int trapVol = 0;

        while (left < right){

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
```
