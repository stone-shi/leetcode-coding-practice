package com.shifamily.dev.leetcode.practice.tree;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal
 * Medium
 * <p>
 * 933
 * <p>
 * 164
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * <p>
 * If two nodes are in the same row and column, the order should be from left to right.
 * <p>
 * Examples 1:
 * <p>
 * Input: [3,9,20,null,null,15,7]
 * <p>
 * 3
 * /\
 * /  \
 * 9  20
 * /\
 * /  \
 * 15   7
 * <p>
 * Output:
 * <p>
 * [
 * [9],
 * [3,15],
 * [20],
 * [7]
 * ]
 * Examples 2:
 * <p>
 * Input: [3,9,8,4,0,1,7]
 * <p>
 * 3
 * /\
 * /  \
 * 9   8
 * /\  /\
 * /  \/  \
 * 4  01   7
 * <p>
 * Output:
 * <p>
 * [
 * [4],
 * [9],
 * [3,0,1],
 * [8],
 * [7]
 * ]
 * Examples 3:
 * <p>
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 * <p>
 * 3
 * /\
 * /  \
 * 9   8
 * /\  /\
 * /  \/  \
 * 4  01   7
 * /\
 * /  \
 * 5   2
 * <p>
 * Output:
 * <p>
 * [
 * [4],
 * [9,5],
 * [3,0,1],
 * [8,2],
 * [7]
 * ]
 * Accepted
 * 110,566
 * Submissions
 * 249,744
 * Seen this question in a real interview before?
 * <p>
 * Yes
 * <p>
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 * <p>
 * Facebook
 * |
 * 14
 * <p>
 * Amazon
 * |
 * 10
 * <p>
 * Databricks
 * |
 * 7
 * <p>
 * Bloomberg
 * |
 * 4
 * <p>
 * Google
 * |
 * 3
 * <p>
 * Reddit
 * |
 * 3
 * <p>
 * Microsoft
 * |
 * 2
 * <p>
 * Wish
 * |
 * 2
 * Binary Tree Level Order Traversal
 */
public class Leet314BinaryTreeVerticalOrderTraversal extends BasicStudy {
    public Leet314BinaryTreeVerticalOrderTraversal() {
        String[] casesP1 = {"[3,9,8,4,0,1,7,null,null,null,2,5]", "[3,9,20,null,null,15,7]", "[3,9,8,4,0,1,7]"};
        Integer[][][] answers = {
                {
                        {4},
                        {9, 5},
                        {3, 0, 1},
                        {8, 2},
                        {7}
                },
                {
                        {9},
                        {3, 15},
                        {20},
                        {7}
                },
                {
                        {4},
                        {9},
                        {3, 0, 1},
                        {8},
                        {7}
                }


        };

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], false);
        }

    }

    @Override
    protected boolean compareAnswer(Object r, Object a, Boolean orderMatter) {
        List<List<Integer>> res = (List<List<Integer>>)r;
        Integer[][] ans = (Integer[][])a;

        if (res.size() != ans.length)
            return false;


        for (int i = 0; i < res.size() ; i++) {
            List<Integer> r1 = res.get(i);
            if (r1.size() != ans[i].length)
                return false;
            for (int j = 0; j < r1.size(); j++) {
                if (!r1.get(j).equals(ans[i][j]))
                    return false;
            }
        }
        return true;
    }

    @CaseRunner
    public List<List<Integer>> verticalOrder(TreeNode root){

        List<List<Integer>> res = new LinkedList<>();
        Map<Integer, List<int[]> > map = new TreeMap<>();
        dfs(root, 0, 0,  map);
        for (Map.Entry<Integer, List<int[]>> e: map.entrySet()){
            List<int[]> val = e.getValue();
            Collections.sort(val, Comparator.comparingInt(o -> o[0]));
            List<Integer> r = new LinkedList<>();
            for (int[] n : val){
                r.add(n[1]);
            }
            res.add(r);
        }

        return res;
    }

    private void dfs(TreeNode root, int col, int row, Map<Integer, List<int[]>> map){
        if (root == null)
            return;

        List<int[]> d = map.getOrDefault(col, new LinkedList<>());
        int[] val = new int[2];
        val[0] = row;
        val[1] = root.val;
        d.add(val);
        map.put(col, d);

        dfs(root.left, col - 1, row + 1, map);
        dfs(root.right, col + 1, row + 1, map);
    }

}
