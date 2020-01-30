package com.shifamily.dev.leetcode.practice.tree;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;
import com.shifamily.dev.leetcode.practice.utils.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
297. Serialize and Deserialize Binary Tree
Hard

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

 */
public class Leet297SerializeAndDeserializeBinaryTree extends BasicStudy {

    public Leet297SerializeAndDeserializeBinaryTree() {

        Object[] p1 = new Object[1];
        Object[] p2 = new Object[1];

        TreeNode para1 = TreeUtils.createTreeFromString("[1,2,3,null,null,4,5]");
        TreeNode ans1 = TreeUtils.duplicateTree(para1);

        TreeNode para2 = TreeUtils.createRandomTree(1000);
        TreeNode ans2 = TreeUtils.duplicateTree(para2);

        p1[0] = para1;
        p2[0] = para2;

        addParameterAndAnswer(p1, ans1, false);
        addParameterAndAnswer(p2, ans2, false);

    }

    @CaseRunner
    public TreeNode runIt(TreeNode root){

        Codec codec = new Codec();

        return codec.deserialize(codec.serialize(root));

    }
    /*
    second practise
     */

    public class Codec {



        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfs(sb, root);
            if (sb.length() > 0)
                sb.deleteCharAt(sb.length() -1 );
            return sb.toString();
        }

        private void dfs (StringBuilder sb, TreeNode root){
            if (root == null){
                sb.append("null,");
                return;
            }

            sb.append(root.val).append(",");
            dfs(sb, root.left);
            dfs(sb, root.right);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] treeArray = data.split(",");
            Queue<String> nodeQueue = new LinkedList(Arrays.asList(treeArray));
            return assembleTree(nodeQueue);
        }

        private TreeNode assembleTree( Queue<String> nodeQueue){
            if (nodeQueue.isEmpty())
                return null;

            String val = nodeQueue.poll();
            if (!val.equals("null")) {
                TreeNode root = new TreeNode( Integer.valueOf(val)) ;
                root.left = assembleTree( nodeQueue );
                root.right = assembleTree( nodeQueue );
                return root;
            }else
                return null;

        }

    }

    /*
    First practice, dfs recursive call
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder result = new StringBuilder();
            dfs(root, result);

            if (result.length() > 0)
                result.deleteCharAt(result.length() - 1); //remove ',' 为了显示好看点
            return result.toString();

        }

        //dfs遍历，把node的值写到stringbuilder里面。
        private void dfs(TreeNode root, StringBuilder result){

            if (root == null){
                result.append("null,");
                return;
            }
            result.append(root.val).append(",");
            dfs(root.left, result);
            dfs(root.right, result);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] treeData = data.split(",");
            //字符串放到队列里
            Queue<String> q = new LinkedList<>(Arrays.asList(treeData));
            return assembleTree(q);
        }

        //把解析的字符串反过来建树
        private TreeNode assembleTree(Queue<String> q){
            if (q.isEmpty())
                return null;

            String nodeToProcess = q.poll();
            if (nodeToProcess.equals("null"))
                return null;

            TreeNode root = new TreeNode(Integer.valueOf(nodeToProcess));
            root.left = assembleTree(q);
            root.right = assembleTree(q);

            return root;
        }
    }


     */
}
