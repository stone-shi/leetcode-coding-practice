package com.shifamily.dev.alg.practice.binary_tree;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class traversalPreorder extends BasicStudy {
    public traversalPreorder() {
        String[] casesP1 = {"[1,2,3,4,null,null,5,null,6,null,null,7,8]"};
        Integer[][] answers = {{1, 2, 4, 6, 7, 8, 3, 5}};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], true);
        }

        TreeNode randRoot = TreeUtils.createRandomTree(1000000);
        Object[] p = new Object[1];
        p[0] = randRoot;
        Integer[] ans = preOrderRecursiveRunner(randRoot);
        addParameterAndAnswer(p, ans, true);

    }

    @CaseRunner
    public Integer[] preOrderRecursiveRunner(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preOrderRecursive(root, res);
        Integer[] result = new Integer[res.size()];
        return res.toArray(result);
    }

    private void preOrderRecursive(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        preOrderRecursive(root.left, res);
        preOrderRecursive(root.right, res);
    }

    @CaseRunner
    public Integer[] preOrderIterative(TreeNode root) {

        List<Integer> res = new LinkedList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            if (node == null){
                node = stack.pop().right;
            }else {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
        }
        Integer[] result = new Integer[res.size()];
        return res.toArray(result);

    }
}