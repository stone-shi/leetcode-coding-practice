# 原题


297. Serialize and Deserialize Binary Tree
Hard

1481

69

Favorite

Share
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

# 解法

这题可以有很多解法，我写了递归的dfs。 只要遍历所有的Node，写到字符串就实现了序列化。反过来重新建立node就好了。

## 复杂度
时间复杂度 二叉树遍历的递归实现，每个结点只需遍历一次，故时间复杂度为O(n)
空间复杂度 O(N) 


## 代码
```Java
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
```

