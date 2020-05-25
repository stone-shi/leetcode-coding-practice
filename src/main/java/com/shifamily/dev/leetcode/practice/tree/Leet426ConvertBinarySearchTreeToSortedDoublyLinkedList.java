package com.shifamily.dev.leetcode.practice.tree;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.ArrayUtils;
import com.shifamily.dev.utils.Node;
import com.shifamily.dev.utils.TreeUtils;

import java.util.Arrays;
import java.util.Stack;

/*
426. Convert Binary Search Tree to Sorted Doubly Linked List
Medium

Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:


We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.


Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.


 */
public class Leet426ConvertBinarySearchTreeToSortedDoublyLinkedList extends BasicStudy {

    private Node createLeet426Answer(int[] a){
        if (a == null || a.length == 0)
            return null;

        Node head = null;
        Node prev = null;
        Node node = null;
        for (int i = 0; i < a.length; i++) {
            node = new Node(a[i]);
            if (head == null)
                head = node;

            if (prev != null){
                prev.right = node;
                node.left = prev;
            }
            prev = node;
        }
        head.left = node;
        node.right = head;
        return head;
    }

    public Leet426ConvertBinarySearchTreeToSortedDoublyLinkedList() {

        int[] caseP1 = {4, 2, 5, 1, 3};
        Node caseNodeP1 = TreeUtils.copyToNode( TreeUtils.createBST(caseP1) );
        int[] answer1 = {1, 2, 3, 4, 5};
        Node ansNode1 = createLeet426Answer(answer1);


        int[] caseP2 = ArrayUtils.createRandomArray(10000);
        Node caseNodeP2 = TreeUtils.copyToNode( TreeUtils.createBST(caseP2) );
        Arrays.sort(caseP2);
        Node ansNode2 = createLeet426Answer(caseP2);

        Object[] p1 = new Object[1];
        p1[0] = caseNodeP1;
        addParameterAndAnswer(p1, ansNode1, false);

        Object[] p2 = new Object[1];
        p2[0] = caseNodeP2;
        addParameterAndAnswer(p2, ansNode2, false);


    }

    //只要做个中序遍历就好，这里用的是迭代方法。 遍历的时候记住第一个值（head），到最后node的时候，head的left是tail，tail的right是head
    @CaseRunner
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
}
