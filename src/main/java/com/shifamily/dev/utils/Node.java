package com.shifamily.dev.utils;

//Leet426
public class Node  {

    public int val;
    public Node left, right;


    public Node(int item)
    {
        val = item;
        left = right = null;
    }



    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            return compareNode(this, (Node)o);
        }else
            return false;
    }

    private boolean compareNode(Node a, Node b){
        Node head1 = a;
        Node head2 = b;
        while (a != null ){
            if (b == null || a.val != b.val)
                return false;
            a = a.right;
            b = b.right;
            if (a == head1)
                break;
        }
        return (a == head1 && b == head2) || (a == null && b == null);
    }
}
