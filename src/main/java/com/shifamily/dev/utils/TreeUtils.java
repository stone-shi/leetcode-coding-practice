package com.shifamily.dev.utils;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TreeUtils {



    public static TreeNode createBST(int[] val){

        if (val == null || val.length == 0)
            return null;

        TreeNode root = insertNodeToBST(null, val[0]);
        for (int i = 1; i < val.length; i++)
            insertNodeToBST(root, val[i]);

        return root;

    }

    public static TreeNode insertNodeToBST(TreeNode root, int val){
        if (root == null)
            return new TreeNode(val);

        if (val <= root.val){
            if (root.left == null)
                return root.left = new TreeNode(val);
            else
                return insertNodeToBST(root.left, val);
        }else{
            if (root.right == null)
                return root.right = new TreeNode(val);
            else
                return insertNodeToBST(root.right, val);
        }

    }

    public static TreeNode createTreeFromString(String tree){
        if (tree == null || tree.length() <= 2 || tree.charAt(0) != '[' || tree.charAt(tree.length() - 1) != ']')
            return null;

        String[] treeArray = tree.substring(1, tree.length() - 1).split(",");
        if (treeArray.length == 0 || treeArray[1].equalsIgnoreCase("null"))
            return null;

        Queue<TreeNode> bfs = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(treeArray[0]));
        bfs.offer(root);
        int i = 1;
        while (i < treeArray.length && !bfs.isEmpty()) {
            TreeNode node = bfs.poll();
            if (i < treeArray.length &&  !treeArray[i].equalsIgnoreCase("null")){
                node.left = new TreeNode(Integer.valueOf(treeArray[i]));
                bfs.offer(node.left);
            }
            i++;
            if (i < treeArray.length && !treeArray[i].equalsIgnoreCase("null")){
                node.right = new TreeNode(Integer.valueOf(treeArray[i]));
                bfs.offer(node.right);
            }
            i++;
        }

        return root;
    }

    public static TreeNode createRandomTree(int numOfNodes){

        Random random = new Random();
        TreeNode root = new TreeNode(random.nextInt());

        Queue<TreeNode>  bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);

        for (int i = 0; i < numOfNodes; i++) {

            TreeNode node = bfsQueue.poll();
            int rndNum = random.nextInt();
            if (Math.abs(rndNum) < 100)
                node.left = null;
            else {
                node.left = new TreeNode(rndNum);
                bfsQueue.offer(node.left);
            }

            rndNum = random.nextInt();
            if (Math.abs(rndNum) < 100)
                node.right = null;
            else {
                node.right = new TreeNode(rndNum);
                bfsQueue.offer(node.right);
            }

        }
        return root;
    }

    public static Node copyToNode(TreeNode root){
        if (root == null)
            return null;

        Node newRoot = new Node(root.val);
        Queue<TreeNode> bfs = new LinkedList<>();
        Queue<Node> bfsNew = new LinkedList<>();
        bfs.offer(root);
        bfsNew.offer(newRoot);

        while (!bfs.isEmpty()){
            TreeNode orig  = bfs.poll();
            Node newNode = bfsNew.poll();

            if (orig.left != null){
                Node node = new Node(orig.left.val);
                newNode.left = node;
                bfs.offer(orig.left);
                bfsNew.offer(node);
            }

            if (orig.right != null){
                Node node = new Node(orig.right.val);
                newNode.right = node;
                bfs.offer(orig.right);
                bfsNew.offer(node);
            }

        }
        return newRoot;
    }

    public static TreeNode duplicateTree(TreeNode root){
        if (root == null)
            return null;

        TreeNode newRoot = new TreeNode(root.val);
        Queue<TreeNode> bfs = new LinkedList<>();
        Queue<TreeNode> bfsNew = new LinkedList<>();
        bfs.offer(root);
        bfsNew.offer(newRoot);

        while (!bfs.isEmpty()){
            TreeNode orig  = bfs.poll();
            TreeNode newNode = bfsNew.poll();

            if (orig.left != null){
                TreeNode node = new TreeNode(orig.left.val);
                newNode.left = node;
                bfs.offer(orig.left);
                bfsNew.offer(node);
            }

            if (orig.right != null){
                TreeNode node = new TreeNode(orig.right.val);
                newNode.right = node;
                bfs.offer(orig.right);
                bfsNew.offer(node);
            }

        }
        return newRoot;

    }

    public static TreeNode findNodeWithValue(TreeNode root, int val){

        if (root == null || root.val == val)
            return root;

        TreeNode node = findNodeWithValue(root.left, val);
        if (node != null)
            return node;

        node = findNodeWithValue(root.right, val);
        return node;

    }



}
