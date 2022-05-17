# 原题


426. Convert Binary Search Tree to Sorted Doubly Linked List
Medium

Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

![Figure 1](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)

We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.

 
![Figure 2](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)


 
Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

![Figure 3](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturnbst.png)

# 解法

因为是BST而且题目要求顺序输出，所以用中序遍历。可以用各种方法，这个解用了迭代。基本如下：  
所有左节点入栈  
循环直到栈空  
出栈 - 做处理。这里是把 prev的right (next)指到出栈节点，然后出栈节点left (previous)指到prev，prev赋值出栈节点。
如果有右节点，右节点和其所有的左节点入栈。  

因为这个要求循环，所以记住head和tail节点，head的left指向tail，tail的right指向head。完成题目要求。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public Node treeToDoublyList(Node root) {

        if (root == null)
            return null;

        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null){
            stack.push(node);
            node = node.left;
        }

        Node head = stack.peek();
        Node prev = null;

        while (! stack.isEmpty()){
            node = stack.pop();
            Node n = node.right;
            while (n != null){
                stack.push(n);
                n = n.left;
            }
            if (prev != null) {
                prev.right = node;
                node.left = prev;
            }
            prev = node;

        }
        head.left = node;
        node.right = head;

        return head;
    }
```

