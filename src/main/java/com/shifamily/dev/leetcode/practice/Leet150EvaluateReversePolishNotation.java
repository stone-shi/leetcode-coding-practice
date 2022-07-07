package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet150EvaluateReversePolishNotation extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(
                CaseParameters.builder().parameters(new Object[] { new String[] { "2", "1", "+", "3", "*" } }).answer(9)
                        .description("Example 1").build());
        cases.add(
                CaseParameters.builder().parameters(new Object[] { new String[] { "4", "13", "5", "/", "+" } })
                        .answer(6)
                        .description("Example 2").build());
        cases.add(
                CaseParameters.builder()
                        .parameters(new Object[] {
                                new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" } })
                        .answer(22)
                        .description("Example 3").build());
        return cases;
    }

    @CaseRunner
    public int evalRPN2(String[] tokens) {
        Deque<Integer> s = new LinkedList<>();

        for (String t : tokens) {
            if (t.equals("+")) {
                int n1 = s.pop();
                int n2 = s.pop();
                s.push(n1 + n2);

            } else if (t.equals("-")) {
                int n1 = s.pop();
                int n2 = s.pop();
                s.push(n2 + n1);
            } else if (t.equals("*")) {
                int n1 = s.pop();
                int n2 = s.pop();
                s.push(n2 * n1);
            } else if (t.equals("/")) {
                int n1 = s.pop();
                int n2 = s.pop();
                s.push(n2 / n1);
            } else {
                s.push(Integer.valueOf(t));
            }
        }
        return s.pop();
    }

    @CaseRunner
    public int evalRPN(String[] tokens) {

        Deque<String> stack = new LinkedList<>();

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int n2 = Integer.parseInt(stack.pop());
                int n1 = Integer.parseInt(stack.pop());
                int rs = 0;
                switch (token.charAt(0)) {
                    case '+':
                        rs = n1 + n2;
                        break;
                    case '-':
                        rs = n1 - n2;
                        break;
                    case '*':
                        rs = n1 * n2;
                        break;
                    case '/':
                        rs = n1 / n2;
                        break;
                }
                stack.push(Integer.toString(rs));
            } else
                stack.push(token);
        }
        return Integer.parseInt(stack.pop());
    }

}
