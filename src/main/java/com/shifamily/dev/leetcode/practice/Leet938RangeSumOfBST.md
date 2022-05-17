# 原题
938 Range Sum of BST
Easy

968

181

Add to List

Share
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 

Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
# 解法

BST in order with range check


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)

## 代码
```Java
   public int rangeSumBST(TreeNode root, int L, int R) {
        sum = 0;
        inOrderRang(root, L, R);
        return sum;

    }

    int sum;
    private void inOrderRang(TreeNode root, int l, int r){
        if (root == null)
            return;

        if (root.val > l)
            inOrderRang(root.left, l, r);

        if (root.val >= l && root.val <= r)
            sum += root.val;

        if (root.val < r)
            inOrderRang(root.right, l, r);

    }

```
