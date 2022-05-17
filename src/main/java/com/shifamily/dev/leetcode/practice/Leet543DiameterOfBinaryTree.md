# 原题
543 Diameter of Binary Tree
Easy

2771

177

Add to List

Share
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

Accepted
312,951
Submissions
651,673
# 解法
求高度。求得过程中计算并保存最大的直径。

最大的直径是 depth(l) + depth(r) + 1 - 1

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java

```
