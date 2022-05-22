package com.shifamily.dev.leetcode.practice;

import java.util.*;
import java.util.stream.Collectors;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

public class Leet1110DeleteNodesAndReturnForest extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[1,2,3,4,5,6,7]"), new int[] { 3, 5 } })
                .answer(TreeUtils.createForestFromString("[[1,2,null,4],[6],[7]]"))
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[1,2,4,null,3]"), new int[] { 3 } })
                .answer(TreeUtils.createForestFromString("[[1,2,4]]"))
                .description("case b").build());

        return cases;
    }

    // solution 2, dfs - should use this one, much simpler
    @CaseRunner
    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        List<TreeNode> res = new LinkedList<>();
        helper(toDelete, res, root, true);
        return res;
    }

    private TreeNode helper(Set<Integer> toDelete, List<TreeNode> res, TreeNode node, boolean isRoot){
        if (node == null)
            return null;

        boolean delected = toDelete.contains(node.val);
        if (isRoot && !delected)
            res.add(node);
        node.left = helper(toDelete, res, node.left, delected);
        node.right = helper(toDelete, res, node.right, delected);
        return delected ? null : node;
    }

    // solution 1, bfs
    @CaseRunner
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Queue<TreeNode> forest = new LinkedList<>();

        List<TreeNode> res = new ArrayList<>();
        Set<Integer> setToDel = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        forest.offer(root);
        while (!forest.isEmpty()) {
            TreeNode firstNode = forest.poll();
            Queue<TreeNode[]> q = new LinkedList<>();
            q.offer(new TreeNode[] { firstNode, null });
            while (!q.isEmpty() && !setToDel.isEmpty()) {
                TreeNode[] pair = q.poll();
                TreeNode node = pair[0];
                TreeNode parent = pair[1];
                if (setToDel.contains(node.val)) {
                    if (node.left != null)
                        forest.add(node.left);
                    if (node.right != null)
                        forest.add(node.right);
                    if (parent == null) // first node
                        firstNode = null;
                    else {
                        if (parent.left == node)
                            parent.left = null;
                        else
                            parent.right = null;
                    }
                    setToDel.remove(pair[0].val);
                } else {
                    if (node.left != null)
                        q.offer(new TreeNode[] { node.left, node });
                    if (node.right != null)
                        q.offer(new TreeNode[] { node.right, node });
                }
            }
            if (firstNode != null)
                res.add(firstNode);
        }
        return res;
    }
}
