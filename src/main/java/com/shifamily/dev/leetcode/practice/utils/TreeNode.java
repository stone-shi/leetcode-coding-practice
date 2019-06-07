package com.shifamily.dev.leetcode.practice.utils;

import apple.laf.JRSUIUtils;

public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int item)
    {
        val = item;
        left = right = null;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof TreeNode)
            return compareNode(this, (TreeNode)o);
        else
            return false;
    }

    private boolean compareNode (TreeNode a, TreeNode b){
        if (a == null)
            return b == null;

        if (b == null)
            return false;

        if (a.val != b.val)
            return false;

        return compareNode(a.left, b.left) && compareNode(a.right, b.right);

    }
}
