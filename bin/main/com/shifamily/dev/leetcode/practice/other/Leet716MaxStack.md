# 原题
716 Max Stack
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

# 解法

PriorityQueue 里面存放 linkedList的node。node根据val和position排序。position可以保证后插入的先出queue。
然后用 double linked list存放 stack数据.


## 复杂度
时间复杂度 :
push - O(nlogn) - pq 排序时间
top - O(1)
pop - O(n) - queue.remove时间
popMax - O(1)
peekMax - O(1)


空间复杂度 O(N)


## 代码
```Java
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
```
