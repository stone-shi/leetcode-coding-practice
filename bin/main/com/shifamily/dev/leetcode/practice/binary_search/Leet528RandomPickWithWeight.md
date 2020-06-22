# 原题
528 Random Pick with Weight
Medium

559

1042

Add to List

Share
Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

Accepted
62,008
Submissions
141,638
Seen this question in a real interview before?

Yes

No
Contributor
1337c0d3r
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
12

Google
|
6

Microsoft
|
3

Twitter
|
2

Amazon
|
2
Random Pick Index
Medium
Random Pick with Blacklist
Hard
Random Point in Non-overlapping Rectangles
Medium


 Explaination of question itself:
 for the array of {1, 3, 4, 6}
 the pickIndex() call will have 1/(1+3+4+6) = 1/14 = ~7% chance of picking index 0; 3/14 = 21% change of picking index 1; 4/14 = 29% change of picking index 2; and 6/14 = 43% of picking index 3.


 Given an array w of positive integers sent to Solution(), where w[i] describes the weight of index i. [1,3] would mean index 0 has weight 1, while index 1 has weight 3.
 Write a function pickIndex() which randomly picks an index in proportion to its weight. pickIndex() will be called multiple times

 Example 1:
 w[] = [1]
 expected return values from pickIndex() calls:
 0

 Example 2:
 Input:
 w[] = [1,3]
 expected return values from pickIndex() calls:
 0,1,1,1,0

 Note:
 1 <= w.length <= 10000
 1 <= w[i] <= 10^5
 pickIndex will be called at most 10000 times.
 
 
# 解法

首先利用w[]生成prefixSum数组。每个p[i]代表产生随机数的上届。同时产生totalSum。
用Math.random()产生[0,1)的随机数，然后乘以totalSum就成为在totalSum中的分布。
然后根据prefixSum查上届就可以得出i。

可以用顺序查找O(n)，优化使用二分查找O(log N)


## 复杂度
时间复杂度 O(Log N)
空间复杂度 O(N)


## 代码
```Java

    class Solution {

        int[] prefixSum;
        int totalSum;
        public Solution(int[] w) {

            prefixSum = new int[w.length];
            totalSum = w[0];

            prefixSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                prefixSum[i] = prefixSum[i-1] + w[i];
                totalSum += w[i];
            }

        }


        /* binary */
        public int pickIndex() {

            double r = Math.random() * totalSum;

            int low = 0;
            int high = prefixSum.length - 1;
            int mid = 0;
            while (low < high){

                mid = low + (high - low) / 2;
                if (prefixSum[mid] >= r)
                    high = mid ;
                else
                    low = mid + 1;

            }
            return low;

        }

        public int pickIndexLinear() {

            double r = Math.random() * totalSum;
            for (int i = 0; i < prefixSum.length ; i++) {
                if (r < prefixSum[i])
                    return i;
            }
            return 0;


        }
    }
```
