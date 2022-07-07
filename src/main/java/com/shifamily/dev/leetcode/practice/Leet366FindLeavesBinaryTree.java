package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import com.shifamily.dev.*;

@Slf4j
public class Leet366FindLeavesBinaryTree extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[1,2,3,4,5,null,null]") })
                .answer(new int[][] { { 4, 5, 3 }, { 2 }, { 1 } })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[1,2,3,4]") })
                .answer(new int[][] { { 4, 3 }, { 2 }, { 1 } })
                .description("Example 2").build());
        return cases;
    }

    // second try - 2022/7/6
    @CaseRunner
    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs2(root, res);
        return res;
    }

    private int dfs2(TreeNode node, List<List<Integer>> res) {
        if (node == null)
            return 0;
        int height = 1 + Math.max(dfs(node.left, res), dfs(node.right, res));
        for (int i = res.size(); i < height; i++) 
            res.add(new ArrayList<>());

        res.get(height - 1).add(node.val);
        return height;
    }

    @CaseRunner
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, res);
        return res;
    }

    private int dfs(TreeNode root, List<List<Integer>> res) {

        if (root == null)
            return 0;
        int height = 1 + Math.max(dfs(root.left, res), dfs(root.right, res));
        int s = res.size();
        for (int i = s; i < height; i++)
            res.add(new LinkedList<>());

        List<Integer> layer = res.get(height - 1);
        layer.add(root.val);
        return height;
    }
}
