package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.utils.ListNode;

import java.util.*;

/**
 23. Merge k Sorted Lists
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
 600.2K
 Submissions
 1.5M
 Seen this question in a real interview before?
 */
public class Leet23MergeKSortedLists extends BasicStudy {

    public Leet23MergeKSortedLists() {
        int[][][] caseP1 = {{{1,4,5}, {1,3,4}, {2,6} }};
        int[][] answer = {{1,1,2,3,4,4,5,6}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, ListNode.fromArray(answer[i]), true);
        }

        int k = 1024, n = 1024 * 8;
        Object[] p = new Object[1];
        int[][] p1 = new int[k][n];
        ListNode a = createRandom(n, k, p1);
        p[0] = p1;
        addParameterAndAnswer(p, a, true);


    }
    private ListNode createRandom(int n, int k, int[][] p1){

        int[][] val = new int[k][n];
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++)
                val[i][j] = random.nextInt();
            Arrays.sort(val[i]);
        }

        return runAsPQ(p1);
    }

    @CaseRunner
    public ListNode runAsDC(int[][] data){
        ListNode[] p1 = new ListNode[data.length];
        for (int j = 0; j < data.length ; j++)
            p1[j] = ListNode.fromArray(data[j]);

        return mergeKListsDC(p1);
    }
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

    @CaseRunner
    public ListNode runAsPQ(int[][] data){
        ListNode[] p1 = new ListNode[data.length];
        for (int j = 0; j < data.length ; j++)
            p1[j] = ListNode.fromArray(data[j]);

        return mergeKListsPQ(p1);
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
}
