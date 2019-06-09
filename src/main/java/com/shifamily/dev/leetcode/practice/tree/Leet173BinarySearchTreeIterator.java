package com.shifamily.dev.leetcode.practice.tree;

import com.shifamily.dev.leetcode.practice.utils.TreeNode;

import java.util.Stack;

/*
173. Binary Search Tree Iterator
Medium

1344

240

Favorite

Share
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.



Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class Leet173BinarySearchTreeIterator {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class BSTIterator {

        Stack<TreeNode> stack ;
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            while (root != null){
                stack.push(root);
                root = root.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode treeNode = stack.pop();
            int res = treeNode.val;

            treeNode = treeNode.right;
            while (treeNode != null){
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            return res;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

}
