package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Stack;

/*
20. Valid Parentheses
Easy

3985

192

Add to List

Share
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */
public class Leet20ValidParentheses extends BasicStudy {

    public Leet20ValidParentheses() {
        String[] caseP1 = {"()",  "()[]{}", "(]", "([)]", "{[]}"};
        boolean[] answer = {true, true, false, false, true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public boolean isValid(String s){

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> matching = new HashMap<>();
        matching.put(']', '[');
        matching.put(')', '(');
        matching.put('}', '{');

        char[] ca = s.toCharArray();
        for (char c: ca) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (c == ')' || c == '}' || c == ']') {
                if (!stack.empty()) {
                    if (!stack.pop().equals( matching.get(c)))
                        return false;
                } else
                    return false;
            }
        }
        return stack.isEmpty();


    }
}
