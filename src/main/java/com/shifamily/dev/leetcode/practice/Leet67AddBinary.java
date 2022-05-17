package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/*
67. Add Binary
Easy

970

189

Favorite

Share
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */
public class Leet67AddBinary extends BasicStudy {

    public Leet67AddBinary() {
        String[] caseP1 = {"11", "1010"};
        String[] caseP2 = {"1", "1011"};
        String[] answer = {"100", "10101"};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public String addBinary(String a, String b) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty())
            return null;

        int n = a.length() - 1;
        int m = b.length() - 1;
        StringBuilder sb = new StringBuilder();

        int carry = 0;
        while (n >= 0 || m >= 0){
            //和要加上上次的进位
            int sum = carry;

            //两数字相加
            if (n >= 0)
                sum += a.charAt(n--) - '0';
            if (m >= 0)
                sum += b.charAt(m--) - '0';

            //计算carry
            carry = sum / 2;
            //计算并且append和，和只有3种情况, 0 (0+0), 1 (0+1)，2（1+1）,模 2后分别为 0， 1， 0，符合进位规则
            sb.append(sum % 2);
        }
        //最后加上进位，如果有的话
        if (carry != 0)
            sb.append(carry);
        //因为循环里用的是append，低位在前，所以这里统一反过来
        sb.reverse();

        return sb.toString();

    }
}
