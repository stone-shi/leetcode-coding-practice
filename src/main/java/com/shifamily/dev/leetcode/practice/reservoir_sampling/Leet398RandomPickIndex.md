# 原题
398 Random Pick Index
Medium

442

651

Add to List

Share
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
Accepted
83,004
Submissions
151,361
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
15
Linked List Random Node
Medium
Random Pick with Blacklist
Hard
Random Pick with Weight
Medium
# 解法
Reservoir Sampling
https://zhuanlan.zhihu.com/p/29178293

用  {1,2,3,3,3}， target = 3举例：
idx: 0 1 2 3 4
循环检查到 i = 2，找到第一个3，这时候的count = 1， random.nextInt(1)会返回 [0,1)。这时候把 2 作为result的概率 100%
i=3，又找到，count = 2,  random.nextInt(2)会返回 [0,1,2)，选中0 （也就是结果2被替换）概率 1/2，保留概率1/2。result = 2的概率 1*1/2 = 1/2，result=3的机会 1/2（2被替换）
i=4，又找到，count = 3,  random.nextInt(3)会返回 [0,1,2,3)，选中0 （也就是结果2或者3被替换）概率 1/3。 所以把4作为result概率 1/3，把2作为result概率（2/3机会不被替换）：1 * 1/2 * 2/3 = 1/3。把3作为结果： 1/2 * 2/3 = 1/3。
由此可见。此算法保证 1/count的概率选中每个出现数字。



## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    class Solution {

        int[] nums ;
        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int count = 0;
            Random random = new Random();
            int result = -1;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target)
                    continue;

                if ( random.nextInt(++count) == 0 )
                    result = i;

            }

            return result;
        }
    }

```
