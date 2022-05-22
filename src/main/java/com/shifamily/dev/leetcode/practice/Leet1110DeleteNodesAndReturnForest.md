# Leetcode #110. Delete Nodes And Return Forest

## 原题

1110 Delete Nodes And Return Forest
Medium

2834

86

Add to List

Share
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.

 

Example 1:
![Example 1](https://assets.leetcode.com/uploads/2019/07/01/screen-shot-2019-07-01-at-53836-pm.png)

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:

Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
Accepted
158,870
Submissions
229,200

## 解法

用dfs就好。注意root检查


## 复杂度

Time O(N)
Space O(1)

## 代码


```Java
    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        List<TreeNode> res = new LinkedList<>();
        helper(toDelete, res, root, true);
        return res;
    }

    private TreeNode helper(Set<Integer> toDelete, List<TreeNode> res, TreeNode node, boolean isRoot){
        if (node == null)
            return null;

        boolean delected = toDelete.contains(node.val);
        if (isRoot && !delected)
            res.add(node);
        node.left = helper(toDelete, res, node.left, delected);
        node.right = helper(toDelete, res, node.right, delected);
        return delected ? null : node;
    }

```
