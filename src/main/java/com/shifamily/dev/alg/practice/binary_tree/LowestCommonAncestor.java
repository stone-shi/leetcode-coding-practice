package com.shifamily.dev.alg.practice.binary_tree;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

/**

查找两个节点最低祖先节点


如果给定pRoot是NULL，即空树，则返回的公共节点自然就是NULL；

如果给定pRoot与两个节点中任何一个相同，说明，pRoot在就是所要找的两个节点之一，则直接返回pRoot，表明在当前链路中找到至少一个节点；

如果给定pRoot不是两个节点中任何一个，则说明，需要在pRoot的左右子树中重新查找，此时有三种情况：两个节点都在左子树上；两个节点都在右子树上；
一个在左子树，一个在右子树上；具体来说，就是：

        情况一：如果左子树查找出的公共节点是NULL，则表明从左子树根节点开始到左子树的所有叶子节点等所有节点中，没有找到两个节点中的任何一个，
        这就说明，这两个节点不在左子树上，不在左子树，则必定在右子树上；

       情况二：如果右子树查找的公共节点是NULL，说明在右子树中无法找到任何一个节点，
       则两个节点必定在左子树上；

       情况三： 如果左右子树查找的公共节点都不是NULL，说明左右子树中各包含一个节点，则当前节点pRoot就是最低公共节点，返回就可以了。

       三种情况是互斥的， 只能是其中之一。
 */
public class LowestCommonAncestor extends BasicStudy {
    public LowestCommonAncestor() {
        String[] casesP1 = {"[1,2,3,4,5,6,7,8,9,19,11]"};
        int[] casesP2 = {8};
        int[] casesP3 = {5};
        int[] answers = {2};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[3];
            TreeNode root = TreeUtils.createTreeFromString(casesP1[i]);
            p[0] = root;
            p[1] = TreeUtils.findNodeWithValue(root, casesP2[i]);
            p[2] = TreeUtils.findNodeWithValue(root, casesP3[i]);

            TreeNode ans = TreeUtils.findNodeWithValue(root, answers[i]);
            addParameterAndAnswer(p, ans, false);
        }

    }


    @CaseRunner
    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
        if (root == null)
            return null;

        if (root.val == p.val)
            return root;

        if (root.val == q.val)
            return root;

        TreeNode leftLca = lca(root.left, p, q);
        TreeNode rightLca = lca(root.right, p, q);

        if (leftLca != null && rightLca != null)
            return root;

        return leftLca == null? rightLca: leftLca;

    }

}
