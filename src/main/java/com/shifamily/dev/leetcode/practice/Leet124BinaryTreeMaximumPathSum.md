# 原题

Leetcode 124
124 Binary Tree Maximum Path Sum
Hard


Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
# 解法

递归：

对于某个结点，他的最大Sum是 左子树最大Sum + 右子树最大Sum + 自己的值，如果某个子树最大Sum是负的，不计入，因为可以skip这个子树。

子树的最大Sum值等于 自己的值 + Max(左子子树,右子子树)，因为根据题意子节点只能选一个方向，不能反复。


## 复杂度
时间复杂度 O(N) - 每个结点1次
空间复杂度 O(lgN) - 递归调用的栈


## 代码
```Java
   public int maxPathSum(TreeNode root) {

        m = Integer.MIN_VALUE;
        maxNum(root);
        return m;
    }

    int m = Integer.MIN_VALUE;
    private int maxNum(TreeNode root){

        if (root == null)
            return 0;

        int leftNum = Math.max( maxNum(root.left), 0);
        int rightNum = Math.max( maxNum(root.right), 0);

        int val =  leftNum + rightNum + root.val;

        if (val > m)
            m = val;

        return root.val + Math.max(leftNum, rightNum);

    }
```
