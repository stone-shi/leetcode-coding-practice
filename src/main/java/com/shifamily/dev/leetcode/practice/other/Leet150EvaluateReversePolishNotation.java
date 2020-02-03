package com.shifamily.dev.leetcode.practice.other;


import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.util.Deque;
import java.util.LinkedList;

/*
150. Evaluate Reverse Polish Notation
Medium

768

414

Add to List

Share
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
Accepted
 */

public class Leet150EvaluateReversePolishNotation extends BasicStudy {
    public Leet150EvaluateReversePolishNotation() {
        String[][] caseP1 = {
                {"2", "1", "+", "3", "*"},
                {"4", "13", "5", "/", "+"},
                {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
        };
        int[] answer = {9, 6, 22};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int evalRPN(String[] tokens) {

        Deque<String> stack = new LinkedList<>();

        for (String token: tokens
             ) {
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