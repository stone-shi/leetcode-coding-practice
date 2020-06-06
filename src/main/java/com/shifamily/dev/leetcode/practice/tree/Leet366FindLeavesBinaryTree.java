package com.shifamily.dev.leetcode.practice.tree;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
366. Find Leaves of Binary Tree
Medium

852

13

Add to List

Share
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
Accepted
 */
@Slf4j
public class Leet366FindLeavesBinaryTree extends BasicStudy {
    public Leet366FindLeavesBinaryTree() {
        String[] casesP1 = {"[1,2,3,4,5,null,null]"};
        int[][][] answers = {{{4, 5, 3}, {2}, {1}}};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], false);
        }
    }

    @Override
    protected boolean compareAnswer(Object r, Object a,  boolean orderMatter, Comparator comparator){
        List<List<Integer>> result = (List<List<Integer>>)r;
        int[][] answer = (int[][])a;

        for (int i = 0; i < result.size(); i++) {
            List<Integer> list = result.get(i);
            for (int j = 0; j < list.size() ; j++) {
                if (!list.get(j).equals(answer[i][j])){
                    log.info("Wrong Answer: expect [{}] at index [{} of {}] but got [{}]", answer[i][j], i, j, list.get(j));
                    return false;
                }
            }
      }


        return true;
    }

    @CaseRunner
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, res);
        return res;
    }

    private int dfs(TreeNode root, List<List<Integer>> res){

        if (root == null)
            return 0;
        int height = 1 + Math.max(dfs(root.left, res), dfs(root.right, res));
        int s = res.size();
        for (int i = s; i < height ; i++)
            res.add(new LinkedList<>());

        List<Integer> layer = res.get(height - 1);
        layer.add(root.val);
        return height;
    }
}
