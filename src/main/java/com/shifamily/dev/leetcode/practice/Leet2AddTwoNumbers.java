package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;
import com.shifamily.dev.utils.ListNode;

public class Leet2AddTwoNumbers {
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

    @CaseRunner
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }
}
