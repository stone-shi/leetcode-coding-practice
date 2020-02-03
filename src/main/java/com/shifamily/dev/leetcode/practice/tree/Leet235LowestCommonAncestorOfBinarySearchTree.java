package com.shifamily.dev.leetcode.practice.tree;

import apple.laf.JRSUIUtils;
import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;
import com.shifamily.dev.leetcode.practice.utils.TreeNode;
import com.shifamily.dev.leetcode.practice.utils.TreeUtils;

/*
235. Lowest Common Ancestor of a Binary Search Tree
Easy

1573

93

Add to List

Share
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]




Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.
Accepted
 */

public class Leet235LowestCommonAncestorOfBinarySearchTree extends BasicStudy {

    public Leet235LowestCommonAncestorOfBinarySearchTree() {
        String[] casesP1 = {"[6,2,8,0,4,7,9,null,null,3,5]", "[6,2,8,0,4,7,9,null,null,3,5]"};
        int[][] caseP2 = {{2, 8}, {2, 4}};
        int[] answers = { 6, 2};
        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[3];
            TreeNode root = TreeUtils.createTreeFromString(casesP1[i]);
            TreeNode p1 = TreeUtils.findNodeWithValue(root, caseP2[i][0]);
            TreeNode p2 = TreeUtils.findNodeWithValue(root, caseP2[i][1]);
            p[0] = root;
            p[1] = p1;
            p[2] = p2;
            addParameterAndAnswer(p, TreeUtils.findNodeWithValue(root, answers[i]), true);
        }
    }

    @CaseRunner
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (p.val > q.val)
            return lowestCommonAncestor(root, q, p);

        if (root.val == p.val || root.val == q.val || (root.val < q.val && root.val > p.val))
            return root;


        if (root.val < q.val && root.val < p.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return lowestCommonAncestor(root.left, p, q);


    }
}
