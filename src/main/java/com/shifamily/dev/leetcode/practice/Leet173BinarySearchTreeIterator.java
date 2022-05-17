package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

import java.util.ArrayDeque;
import java.util.Deque;
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
public class Leet173BinarySearchTreeIterator extends BasicStudy {

    public Leet173BinarySearchTreeIterator() {
        String[] casesP1 = {"[7,3,15,null,null,9,20]"};
        String[] casesP2 = {"nnhnhnhnh"};
        int[][] answers = {{3,7,1,9,1,15,1,20,0}};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[3];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            p[1] = casesP2[i];
            p[2] = answers[i];
            addParameterAndAnswer(p, true, true);
        }

    }

    @CaseRunner
    public boolean BSTIteratorRunner2nd(TreeNode root, String ops, int[] ans){
        BSTIterator2nd bstIterator2nd = new BSTIterator2nd(root);
        char[] op = ops.toCharArray();

        for (int i = 0; i < op.length; i++) {
            int res = -1;
            if (op[i] == 'n')
                res = bstIterator2nd.next();
            else if (op[i] == 'h')
                res = bstIterator2nd.hasNext()?1:0;
            if (ans[i] != res)
                return false;
        }

        return true;
    }

    /* 2nd try 5/25/2020 */
    class BSTIterator2nd {

        Deque<TreeNode> stack = new ArrayDeque<>();

        public BSTIterator2nd(TreeNode root) {

            while (root != null){
                stack.push(root);
                root = root.left;
            }

        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            int ret = node.val;
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }


            return ret;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {

            return !stack.isEmpty();
        }
    }

    @CaseRunner
    public boolean BSTIteratorRunner(TreeNode root, String ops, int[] ans){
        BSTIterator bstIterator = new BSTIterator(root);
        char[] op = ops.toCharArray();

        for (int i = 0; i < op.length; i++) {
            int res = -1;
            if (op[i] == 'n')
                res = bstIterator.next();
            else if (op[i] == 'h')
                res = bstIterator.hasNext()?1:0;
            if (ans[i] != res)
                return false;
        }

        return true;
    }


    /*--------------------------------------------------------*/

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
