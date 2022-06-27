# Leetcode #951 Flip Equivalent Binary Trees

## 原题

[951 Flip Equivalent Binary Trees](https://leetcode.com/problems/flip-equivalent-binary-trees/)
**<span style="color:blue">Medium</span>**

For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2018/11/29/tree_ex.png)
Flipped Trees Diagram

> `Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]`
`Output: true`
Explanation: We flipped at nodes with values 1, 3, and 5.

**Example 2:**
> `Input: root1 = [], root2 = []`
`Output: true`

**Example 3:**
> `Input: root1 = [], root2 = [1]`
`Output: false`

**Constraints:**

- The number of nodes in each tree is in the range [0, 100].
- Each tree will have unique node values in the range [0, 99].

## 解法

DFS OR BFS

## 复杂度

## 代码

```Java
  // solution 2, DFS
    @CaseRunner
    public boolean flipEquivDFS(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private boolean dfs(TreeNode n1, TreeNode n2){
        if (n1 == null || n2 == null){
            if (n1 == n2)
                return true;
            else
                return false;
        }
        if (n1.val != n2.val)
            return false;

        boolean usedRightNode = false;

        boolean leftOK = dfs(n1.left, n2.left);
        if (!leftOK){
            if ( !dfs(n1.left, n2.right))
            return false;
            usedRightNode = true;
        }
        return dfs(n1.right, usedRightNode? n2.left : n2.right);
    }

    // solution 1, BFS
    @CaseRunner
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2 == null;
        if (root2 == null)
            return false;

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(root1);
        q2.offer(root2);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();

            if (node1 == null || node2 == null) {
                if (node1 != node2) // only one null
                    return false;
                else
                    continue;
            }

            if (node1.val != node2.val)
                return false;

            boolean addedN2Left = false;
            q1.add(node1.left);
            if ((node1.left == null && node2.left == null)
                    || (node1.left != null && node2.left != null && node2.left.val == node1.left.val)) {
                q2.add(node2.left);
                addedN2Left = true;
            } else
                q2.add(node2.right);

            q1.add(node1.right);
            q2.add(addedN2Left ? node2.right : node2.left);
        }
        return q1.isEmpty() && q2.isEmpty();
    }
    ```
