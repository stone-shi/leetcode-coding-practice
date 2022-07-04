# Leetcode #2096. Step-By-Step Directions From a Binary Tree Node to Another

## 原题

[2096 Step-By-Step Directions From a Binary Tree Node to Another](https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/)

**<span style="color:blue">Medium</span>** 1150 75

You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

* 'L' means to go from a node to its left child node.
* 'R' means to go from a node to its right child node.
* 'U' means to go from a node to its parent node.

Return the step-by-step directions of the shortest path from node s to node t.

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2021/11/15/eg1.png)

> `Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6`
`Output: "UURL"`
**Explanation:** The shortest path is: 3 → 1 → 5 → 2 → 6.

**Example 2:**
![Example 2](https://assets.leetcode.com/uploads/2021/11/15/eg2.png)

> `Input: root = [2,1], startValue = 2, destValue = 1`
`Output: "L"`
**Explanation:** The shortest path is: 2 → 1.

**Constraints:**

* The number of nodes in the tree is n.
* `2 <= n <= 105`
* `1 <= Node.val <= n`
* All the values in the tree are unique.
* `1 <= startValue, destValue <= n`
* `startValue != destValue`

## 解法

二叉树寻找最低公共祖先

DFS

DFS建立 二个stack 的path，start 和 destination
然后比较path stack，找到公共祖先，
把start的stack，每一个写成U （start总是向上到最低公共祖先）
然后append destination的Path

## 复杂度

## 代码

```Java
    public String getDirections(TreeNode root, int startValue, int destValue) {
        Deque<Integer> stackStart = new LinkedList<>();
        Deque<Integer> stackDest = new LinkedList<>();
        Deque<String> stackPath = new LinkedList<>();

        // dfs find path to start
        boolean bStart = dfs(root, startValue, "", stackStart, null);
        // dfs find path to dest
        boolean bEnd = dfs(root, destValue, "", stackDest, stackPath);

        if (!bStart && !bEnd)
            return null;

        StringBuilder sb = new StringBuilder();
        // find lowest common ancestor (LCA)
        while (!stackStart.isEmpty() && !stackDest.isEmpty()) {
            if (!stackStart.peekLast().equals(stackDest.peekLast()))
                break;
            stackDest.removeLast();
            stackStart.removeLast();
            stackPath.removeLast();
        }

        // start path add U (all up)
        for (int i = 0; i < stackStart.size(); i++)
            sb.append("U");

        // dest path 
        while (!stackPath.isEmpty()) {
            sb.append(stackPath.pollLast());
        }

        return sb.toString();
    }

    private boolean dfs(TreeNode node, int target, String dir, Deque<Integer> p, Deque<String> d) {
        if (node == null)
            return false;
        p.push(node.val);
        if (d != null)
            d.push(dir);
        if (node.val == target)
            return true;

        boolean r = dfs(node.left, target, "L", p, d) || dfs(node.right, target, "R", p, d);
        if (!r) {
            p.pop();
            if (d != null)
                d.pop();
        }
        return r;
    }

```