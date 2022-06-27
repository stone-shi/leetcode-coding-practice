package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;
import com.shifamily.dev.utils.ListNode;

public class Leet2AddTwoNumbers extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { ListNode.fromArray(new int[] { 2, 4, 3 }),
                        ListNode.fromArray(new int[] { 5, 6, 4 }) })
                .answer(ListNode.fromArray(new int[] { 7, 0, 8 }))
                .description("case a").build());

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { ListNode.fromArray(new int[] { 0 }),
                        ListNode.fromArray(new int[] { 0 }) })
                .answer(ListNode.fromArray(new int[] { 0 }))
                .description("case a").build());

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { ListNode.fromArray(new int[] { 9, 9, 9, 9, 9, 9, 9 }),
                        ListNode.fromArray(new int[] { 9, 9, 9, 9 }) })
                .answer(ListNode.fromArray(new int[] { 8, 9, 9, 9, 0, 0, 0, 1 }))
                .description("case a").build());

        return cases;
    }

    // second try - 2022/06/26
    @CaseRunner
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int up = 0;
        ListNode res = null;
        ListNode prev = null;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + up;
            up = sum / 10;
            sum = sum % 10;
            ListNode r = new ListNode(sum);
            if (res == null) {
                res = r;
                prev = r;
            } else {
                prev.next = r;
                prev = r;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (up != 0){
            prev.next = new ListNode(1);
        }
        return res;
    }

    @CaseRunner
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = null;
        ListNode prev = null;
        int addtion = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int r = v1 + v2 + addtion;
            addtion = r / 10;
            r = r % 10;
            ListNode n = new ListNode(r);
            if (res == null) {
                res = n;
                prev = n;
            } else {
                prev.next = n;
                prev = n;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (addtion != 0) {
            prev.next = new ListNode(addtion);
        }

        return res;
    }
}
