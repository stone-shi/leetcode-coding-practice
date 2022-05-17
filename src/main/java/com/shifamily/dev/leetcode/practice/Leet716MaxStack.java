package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
716. Max Stack
Easy

570

105

Add to List

Share
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */
@Slf4j
public class Leet716MaxStack extends BasicStudy {
    public Leet716MaxStack() {
        String[][] caseP1 = {
                {"push","push","popMax","push","push","top","push","push","push","pop","popMax","pop","push"},
                {"push","push","push","top","popMax", "top", "peekMax", "pop", "top"}
        };

        int[][] caseP2 = { {-95,1,1,-44,16,16,29,65,-18,-18,65,29,78}, {5,1,5,5,5,1,5,1,5}};
        boolean[] answer = {true, true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public boolean runIt(String[] ops, int[] p1){
        MaxStack maxStack = new MaxStack();
        for (int i = 0; i < ops.length; i++) {
            log.info("MaxStack.{}({})", ops[i], p1[i]);
            int r = Integer.MAX_VALUE;
            if (ops[i].equals("push")){
                maxStack.push(p1[i]);
            }else if (ops[i].equals("pop")){
                r = maxStack.pop();
            }else if (ops[i].equals("top")){
                r = maxStack.top();
            }else if (ops[i].equals("popMax")){
                r = maxStack.popMax();
            }else if (ops[i].equals("peekMax")){
                r = maxStack.peekMax();
            }
            if (r != Integer.MAX_VALUE && r != p1[i]) {
                log.info("--> {} but expected {}", r, p1[i]);
                return false;
            }
        }

        return true;

    }


    public static class MaxStack {

        static class LinkedNode{

            public LinkedNode (int val, int position){
                this.val = val;
                this.position = position;
            }
            public int val;
            public int position;
            public LinkedNode next;
            public LinkedNode prev;
        }

        LinkedNode tail = null;

        PriorityQueue<LinkedNode> queue;
        int pos = 0;

        /** initialize your data structure here. */
        public MaxStack() {
            Comparator<LinkedNode> c = (LinkedNode n1, LinkedNode n2)-> n1.val == n2.val? n2.position - n1.position : n2.val - n1.val;
            queue = new PriorityQueue<>(c);

        }

        public void push(int x) {
            LinkedNode linkedNode = new LinkedNode(x, pos++);
            if (tail != null) {
                tail.next = linkedNode;
                linkedNode.prev = tail;
            }
            tail = linkedNode;
            queue.offer(linkedNode);
        }

        public int pop() {
            LinkedNode linkedNode = tail;
            deleteNode(tail);
            queue.remove(linkedNode);
            return linkedNode.val;
        }

        public int top() {
            return tail.val;
        }

        public int peekMax() {
            return queue.peek().val;
        }

        public int popMax() {
            LinkedNode node = queue.poll();
            deleteNode(node);
            return node.val;
        }

        private void deleteNode(LinkedNode node){
            LinkedNode n = node.next;
            LinkedNode p = node.prev;
            if (n != null)
                n.prev = node.prev;
            else
                tail = p;

            if (p != null)
                p.next = node.next;


        }
    }

}
