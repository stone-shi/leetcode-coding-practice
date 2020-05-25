package com.shifamily.dev.utils;


public class ListNode {
      public int val;
      public ListNode next;
      public ListNode(int x) { val = x; }

      public static ListNode fromArray(int[] a){
            if (a == null || a.length == 0)
                  return null;

            ListNode root = new ListNode(a[0]);
            ListNode prev = root;
            for (int i = 1; i < a.length ; i++) {
                  ListNode current = new ListNode(a[i]);
                  prev.next = current;
                  prev = current;
            }
            return root;
      }

      @Override
      public boolean equals(Object o){
            if (!o.getClass().equals(ListNode.class))
                  return false;

            ListNode n1 = (ListNode)o;

            ListNode me = this;

            while (n1 != null && me != null){
                  if (n1.val != me.val)
                        return false;
                  n1 = n1.next;
                  me = me.next;
            }
            if (n1 != null || me != null)
                  return false;

            return true;
      }

      @Override
      public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            ListNode h = this;
            while ( h!= null){
                  sb.append(h.val).append(",");
                  h = h.next;
            }
            sb.append("}");

            return sb.toString();
      }
}

