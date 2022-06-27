package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.*;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

public class Leet951FlipEquivalentBinaryTrees extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[1,2,3,4,5,6,null,null,null,7,8]"),
                        TreeUtils.createTreeFromString("[1,3,2,null,6,4,5,null,null,null,null,8,7]") })
                .answer(true)
                .description("case a").build());

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[]"), TreeUtils.createTreeFromString("[]") })
                .answer(true)
                .description("case b").build());

        cases.add(CaseParameters.builder()
                .parameters(
                        new Object[] { TreeUtils.createTreeFromString("[]"), TreeUtils.createTreeFromString("[1]") })
                .answer(false)
                .description("case c").build());

        return cases;
    }

    // second try - 2022/06/26
    @CaseRunner
    public boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2 == null;
        if (root2 == null)
            return false;

        if (root1.val != root2.val)
            return false;

        return (flipEquiv2(root1.left, root2.left) || flipEquiv2(root1.left, root2.right))
                && (flipEquiv2(root1.right, root2.right) || flipEquiv2(root1.right, root2.left));
    }

    // solution 2, DFS
    @CaseRunner
    public boolean flipEquivDFS(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private boolean dfs(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            if (n1 == n2)
                return true;
            else
                return false;
        }
        if (n1.val != n2.val)
            return false;

        boolean usedRightNode = false;

        boolean leftOK = dfs(n1.left, n2.left);
        if (!leftOK) {
            if (!dfs(n1.left, n2.right))
                return false;
            usedRightNode = true;
        }
        return dfs(n1.right, usedRightNode ? n2.left : n2.right);
    }

    // solution 1, BFS
    @CaseRunner
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2 == null;
        if (root2 == null)
            return false;

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(root1);
        q2.offer(root2);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();

            if (node1 == null || node2 == null) {
                if (node1 != node2) // only one null
                    return false;
                else
                    continue;
            }

            if (node1.val != node2.val)
                return false;

            boolean addedN2Left = false;
            q1.add(node1.left);
            if ((node1.left == null && node2.left == null)
                    || (node1.left != null && node2.left != null && node2.left.val == node1.left.val)) {
                q2.add(node2.left);
                addedN2Left = true;
            } else
                q2.add(node2.right);

            q1.add(node1.right);
            q2.add(addedN2Left ? node2.right : node2.left);
        }
        return q1.isEmpty() && q2.isEmpty();
    }

}
