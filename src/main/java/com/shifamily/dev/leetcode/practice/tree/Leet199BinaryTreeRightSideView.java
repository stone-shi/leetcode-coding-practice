package com.shifamily.dev.leetcode.practice.tree;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;
import com.shifamily.dev.leetcode.practice.utils.TreeNode;
import com.shifamily.dev.leetcode.practice.utils.TreeUtils;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 199. Binary Tree Right Side View
 Medium

 1985

 120

 Add to List

 Share
 Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

 Example:

 Input: [1,2,3,null,5,null,4]
 Output: [1, 3, 4]
 Explanation:

 1            <---
 /   \
 2     3         <---
 \     \
 5     4       <---
 Accepted
 267,681
 Submissions
 505,841
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 amrsaqr
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 47

 Amazon
 |
 9

 Microsoft
 |
 6

 ByteDance
 |
 4

 Bloomberg
 |
 3
 */
public class Leet199BinaryTreeRightSideView extends BasicStudy {
    public Leet199BinaryTreeRightSideView() {
        String[] casesP1 = {"[1,2]", "[1,2,3,null,5,null,4]"};
        Integer[][] answers = {{1,2}, {1,3,4}};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = TreeUtils.createTreeFromString(casesP1[i]);
            addParameterAndAnswer(p, answers[i], true);
        }

    }

    @CaseRunner
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
                if (i == sz - 1)
                    res.add(node.val);
            }
        }
        return res;
    }


}
