# 原题
270 Closest Binary Search Tree Value
Easy

683

53

Add to List

Share
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
Accepted
129,109
Submissions
272,870
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
16

Amazon
|
2
Count Complete Tree Nodes
Medium
Closest Binary Search Tree Value II
Hard
Search in a Binary Search Tree
Easy
# 解法

解法一
In order ， 找到 target之前和之后的数字，然后比较哪个差距小。
In order的话用迭代比较简单，因为发现比target大的数字可以立刻终止迭代。
比较前后的话，用差的绝对值。

时间复杂度 O(N)
空间复杂度 O(1)

解法二：

从root开始，如果target比root小，走左边，否则走右边，每次都计算差值，取最小。
因为如果target比root小，走右边只会增加差值。
时间复杂度 O(H)
空间复杂度 O(1)



## 代码
```Java

```
