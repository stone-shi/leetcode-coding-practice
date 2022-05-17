package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 270. Closest Binary Search Tree Value
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
 */
public class Leet270ClosestBinarySearchTreeValue extends BasicStudy {
    public Leet270ClosestBinarySearchTreeValue() {
        String[] casesP1 = { "[1500000000,1400000000]",
        "[0]", "[1]",  "[1,null,8]","[4,2,5,1,3]"};
        double[] casesP2 = {-1500000000.0, 2147483648.0, 4.4, 3.0, 3.714286};
        int[] answers = {1400000000, 0, 1, 1, 4};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            p[1] = casesP2[i];
            addParameterAndAnswer(p, answers[i], true);
        }

    }

    @CaseRunner
    public int closestValueBinarySearch(TreeNode root, double target) {

        if (root == null)
            return 0;

        double closest = Math.abs(root.val - target);
        Integer closeValue = root.val;
        while (root != null){
            double diff = Math.abs(root.val - target);
            if (diff < closest){
                closest = diff;
                closeValue = root.val;
            }

            if (target < root.val)
                root = root.left;
            else
                root = root.right;


        }
        return closeValue;


    }


    @CaseRunner
    public int closestValue(TreeNode root, double target) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode node = root;
        Integer less = null;
        Integer greater = null;
        while (node != null ||  ! stack.isEmpty()){
            if (node == null){
                node = stack.pop();
                if (node.val < target)
                    less = node.val;
                else{
                    greater = node.val;
                    break;
                }
               node = node.right;
            }else{
                stack.push(node);
                node = node.left;
            }
        }
        if (less == null )
            return greater;
        if (greater == null)
            return less;

        if (Math.abs(less - target) > Math.abs(greater - target))
            return greater;
        else
            return less;


    }


}
