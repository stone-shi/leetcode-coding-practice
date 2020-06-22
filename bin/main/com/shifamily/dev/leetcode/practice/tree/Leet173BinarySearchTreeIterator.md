# 原题

173 Binary Search Tree Iterator
Medium

1344

240

Favorite

Share
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:

![Figure 1](https://assets.leetcode.com/uploads/2018/12/25/bst-tree.png)


BSTIterator iterator = new BSTIterator(root);  
iterator.next();    // return 3  
iterator.next();    // return 7  
iterator.hasNext(); // return true  
iterator.next();    // return 9  
iterator.hasNext(); // return true  
iterator.next();    // return 15  
iterator.hasNext(); // return true  
iterator.next();    // return 20   
iterator.hasNext(); // return false  
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

# 解法
和BST 迭代中序遍历一样：所有根入栈，所有左子树入栈。

next(): 从栈顶弹出一个node，如果这个node有右节点，入栈，然后这个右节点的所有左子树入栈。返回node值。

hasNext(): 是否栈空

## 复杂度
时间复杂度 O(N)  
空间复杂度 O(N)


## 代码
```Java
   class BSTIterator {

        Stack<TreeNode> stack ;
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            while (root != null){
                stack.push(root);
                root = root.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode treeNode = stack.pop();
            int res = treeNode.val;

            treeNode = treeNode.right;
            while (treeNode != null){
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            return res;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
```



