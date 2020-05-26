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
public class TraversalInorder extends BasicStudy {
    public TraversalInorder() {
        String[] casesP1 = {"[1,2,3,4,null,null,5,null,6,null,null,7,8]"};
        Integer[][] answers = {{4, 7, 6, 8, 2, 1, 3, 5}};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], true);
        }

        TreeNode randRoot = TreeUtils.createRandomTree(1000000);
        Object[] p = new Object[1];
        p[0] = randRoot;
        Integer[] ans = inOrderRecursiveRunner(randRoot);
        addParameterAndAnswer(p, ans, true);

    }

    @CaseRunner
    public Integer[] inOrderRecursiveRunner(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inOrderRecursive(root, res);
        Integer[] result = new Integer[res.size()];
        return res.toArray(result);
    }

    private void inOrderRecursive(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        inOrderRecursive(root.left, res);
        res.add(root.val);
        inOrderRecursive(root.right, res);
    }

    @CaseRunner
    public Integer[] inOrderIterative(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()){
            if (node == null){
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }else {
                stack.push(node);
                node = node.left;
            }
        }



        Integer[] result = new Integer[res.size()];
        return res.toArray(result);

    }


}