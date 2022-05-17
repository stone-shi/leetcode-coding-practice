package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

/**
 543. Diameter of Binary Tree
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
 */
public class Leet543DiameterOfBinaryTree extends BasicStudy {
    public Leet543DiameterOfBinaryTree() {

        String[] casesP1 = {"[1,2,3,4,5]"};
        int[] answers = {3};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], false);
        }

    }

    @CaseRunner
    public int diameterOfBinaryTree(TreeNode root) {

        diameterMax = 1;
        maxHeight(root);
        return diameterMax ;

    }

    int diameterMax ;

    private int maxHeight(TreeNode root){
        if (root == null)
            return 0;

        int heightLeft = maxHeight(root.left);
        int heightRight = maxHeight(root.right);

        diameterMax = Math.max( heightLeft + heightRight, diameterMax);

        return Math.max(heightLeft, heightRight) + 1;
    }

}
