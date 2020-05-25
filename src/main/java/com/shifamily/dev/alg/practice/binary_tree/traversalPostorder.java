package com.shifamily.dev.alg.practice.binary_tree;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class traversalPostorder extends BasicStudy {
    public traversalPostorder() {
        String[] casesP1 = {"[1,2,3,4,null,null,5,null,6,null,null,7,8]"};
        Integer[][] answers = {{7, 8, 6, 4, 2, 5, 3, 1}};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], true);
        }

        TreeNode randRoot = TreeUtils.createRandomTree(1000000);
        Object[] p = new Object[1];
        p[0] = randRoot;
        Integer[] ans = postOrderRecursiveRunner(randRoot);
        addParameterAndAnswer(p, ans, true);

    }

    @CaseRunner
    public Integer[] postOrderRecursiveRunner(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        postOrderRecursive(root, res);
        Integer[] result = new Integer[res.size()];
        return res.toArray(result);
    }

    private void postOrderRecursive(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        postOrderRecursive(root.left, res);
        postOrderRecursive(root.right, res);
        res.add(root.val);

    }

    @CaseRunner
    public Integer[] preOrderIterative(TreeNode root) {

        List<Integer> res = new LinkedList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()){

            if (node == null){
                node = stack.pop().left;
            }else {
                res.add(node.val);
                stack.push(node);
                node = node.right;
            }
        }

        Collections.reverse(res);

        Integer[] result = new Integer[res.size()];
        return res.toArray(result);

    }


}