# 原题
23 Merge k Sorted Lists
Hard

4216

267

Add to List

Share
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
Accepted
600,199
Submissions
1,536,794
Seen this question in a real interview before?

Yes

No
# 解法

## Divide and Conquer
每两个merge，然后再merge，直到只剩下一个
merge2 是in place的，直接用linked list

## 复杂度
N代表平均列表长度
K是列表数字
时间复杂度 O(NlogK)，两两执行，一共有k个，所以是logk次
空间复杂度 O(1)



### Priority Queue
把k个head加入pq，然后从头取。
每取出一个，如.next不空，把 .next放入pq



### 复杂度
N代表最大列表长度
时间复杂度 O(NklogK)，PQ插入的代价是O(logk)，要执行k次，所以PQ上消耗 klogk，然后最大长度N
空间复杂度 O(1)



## 代码
```Java
    /*
    Divide and Conquer
     */

    public ListNode mergeKListsDC(ListNode[] lists) {

        if (lists == null || lists.length == 0)
            return null;

        Queue<ListNode> q = new LinkedList<>();
        Collections.addAll(q, lists);

        ListNode n1 = null;
        ListNode n2 = null;
        while (!q.isEmpty()){
            n1 = q.poll();
            if (!q.isEmpty())
                n2 = q.poll();
            else
                return n1;
            n1 = merge2ListNode(n1, n2);
            q.offer(n1);
        }


        return n1;

    }
    private ListNode merge2ListNode(ListNode n1, ListNode n2){
        ListNode head = null, prev = null;

        while (n1 != null && n2 != null){
            ListNode pt ;

            if (n1.val < n2.val) {
                pt = n1;
                n1 = n1.next;
            }else{
                pt = n2;
                n2 = n2.next;
            }
            if (head == null){
                head = pt;
                prev = pt;
            }else{
                prev.next = pt;
                prev = pt;
            }
        }
        if (n1 != null)
            prev.next = n1;
        if (n2 != null)
            prev.next = n2;

        return head;
    }



    /*
    Priority queue
     */
    public ListNode mergeKListsPQ(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>(((o1, o2) -> o1.val - o2.val));
        for (ListNode listNode : lists)
            pq.add(listNode);

        ListNode newRoot = null ;
        ListNode prev = null;

        while (!pq.isEmpty()){
            ListNode current = pq.poll();
            ListNode currNode = new ListNode(current.val);
            if (newRoot == null){
                newRoot = currNode;
                prev = currNode;
            }else{
                prev.next = currNode;
                prev = currNode;
            }

            if (current.next != null)
                pq.offer(current.next);
        }

        return newRoot;

    }
```
