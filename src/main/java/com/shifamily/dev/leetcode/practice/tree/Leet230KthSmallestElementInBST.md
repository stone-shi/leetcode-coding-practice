# 原题
230. Kth Smallest Element in a BST
Medium

2403

61

Add to List

Share
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 

Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
Accepted
386,075
Submissions
651,301
Seen this question in a real interview before?

Yes

No
Contributor
shtian
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
7

Facebook
|
6

Oracle
|
4

Apple
|
3

TripleByte
|
2
Binary Tree Inorder Traversal
Medium
Second Minimum Node In a Binary Tree
Easy
Try to utilize the property of a BST.
# 解法
只要用in order遍历即可。迭代方式似乎简单点，直接返回。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public int kthSmallest(TreeNode root, int k) {

        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()){
            if (node != null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                if (--k == 0)
                    return node.val;
                node = node.right;
            }
        }

        return -1;
    }
```
