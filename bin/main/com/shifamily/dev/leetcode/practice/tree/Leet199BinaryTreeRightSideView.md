# 原题
199 Binary Tree Right Side View
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
# 解法

BFS
BFS的时候要把每层最右边的加到结果集。
分层做法是循环的时候，先取出当前queue的size，这就是当前层包含的所有node的数字。如果offer的时候是从left到right，那当循环i = size - 1的时候，就是当前层的最右边。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
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

```
