package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

/*
124. Binary Tree Maximum Path Sum
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
 */
public class Leet124BinaryTreeMaximumPathSum extends BasicStudy {

    public Leet124BinaryTreeMaximumPathSum() {

        String[] casesP1 = {"[5,4,8,11,null,13,4,7,2,null,null,null,1]", "[1,2,3]", "[-10,9,20,null,null,15,7]"};
        int[] answers = {48, 6, 42};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], false);
        }


    }

    /* 2nd try - 5/23/2020 */
    int maxGainValue = Integer.MIN_VALUE;
    @CaseRunner
    public int maxPathSum2nd(TreeNode root) {
        maxGainValue = Integer.MIN_VALUE;
        maxGain(root);
        return maxGainValue;
    }

    private int maxGain(TreeNode root){
        if (root == null)
            return 0;
        int leftGain = Math.max(0, maxGain(root.left));
        int rightGain = Math.max(0, maxGain(root.right));
        int val = root.val + leftGain + rightGain;
        if (val > maxGainValue)
            maxGainValue = val;
        return root.val + Math.max( leftGain,  rightGain);
    }


    @CaseRunner
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
}
