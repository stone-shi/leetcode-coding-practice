# 原题
366 Find Leaves of Binary Tree
Medium

852

13

Add to List

Share
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []         
Accepted
# 解法
dfs同时计算当前node的高度，然后用高度作为index加入结果集。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, res);
        return res;
    }

    private int dfs(TreeNode root, List<List<Integer>> res){

        if (root == null)
            return 0;
        int height = 1 + Math.max(dfs(root.left, res), dfs(root.right, res));
        int s = res.size();
        for (int i = s; i < height ; i++)
            res.add(new LinkedList<>());

        List<Integer> layer = res.get(height - 1);
        layer.add(root.val);
        return height;
    }
```
