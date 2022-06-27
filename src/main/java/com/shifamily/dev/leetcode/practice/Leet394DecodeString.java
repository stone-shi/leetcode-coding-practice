package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.*;;

public class Leet394DecodeString extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        // cases.add(CaseParameters.builder().parameters(new Object[] {
        // "3[z]2[2[y]pq4[2[jk]e1[f]]]ef" })
        // .answer("zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef")
        // .description("case 0").build());

        cases.add(CaseParameters.builder().parameters(new Object[] { "3[a]2[bc]" }).answer("aaabcbc")
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "3[a2[c]]" }).answer("accaccacc")
                .description("case b").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "2[abc]3[cd]ef" }).answer("abcabccdcdcdef")
                .description("case c").build());

        return cases;
    }

    // second try - 2022/06/25
    @CaseRunner
    public String decodeString3(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> stackCt = new LinkedList<>();
        Deque<StringBuilder> stackSb = new LinkedList<>();

        int i = 0;
        int numStart = -1;
        int count = 1;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (numStart == -1)
                    numStart = i;
            } else if (c == '[') {
                stackCt.push(count);
                stackSb.push(sb);
                sb = new StringBuilder();
                count = Integer.valueOf(s.substring(numStart, i));
                numStart = -1;
            } else if (c == ']') {
                StringBuilder expended = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    expended.append(sb);
                }
                count = stackCt.pop();
                sb = stackSb.pop().append(expended);
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    // solution 2 - iteration / stack
    @CaseRunner
    public String decodeString2(String s) {
        Deque<StringBuilder> s1 = new LinkedList<>();
        Deque<Integer> s2 = new LinkedList<>();
        StringBuilder ret = new StringBuilder();

        StringBuilder part = new StringBuilder();

        int i = 0;
        int count = 0;
        boolean isPatern = false;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (!isPatern) {
                    count = count * 10 + c - '0';
                } else {
                    s1.push(part);
                    s2.push(count);
                    isPatern = false;
                    count = c - '0';
                    part = new StringBuilder();
                }
            } else if (c == '[') {
                isPatern = true;
            } else if (c == ']') {
                StringBuilder currentPart = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    currentPart.append(part);
                }
                if (!s1.isEmpty()) {
                    isPatern = true;
                    count = s2.pop();
                    part = s1.pop();
                    part.append(currentPart);
                } else {
                    isPatern = false;
                    count = 0;
                    part = new StringBuilder();
                    ret.append(currentPart);
                }
            } else {
                if (!isPatern)
                    ret.append(c);
                else
                    part.append(c);
            }

            i++;
        }
        return ret.toString();

    }

    // solution 1 - recursion solution
    @CaseRunner
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9')
                i = expend(s, i, sb);
            else
                sb.append(c);
            i++;
        }
        return sb.toString();
    }

    private int expend(String s, int idx, StringBuilder sb) {
        int count = 0;
        boolean paternStart = false;
        StringBuilder sub = new StringBuilder();
        StringBuilder ret = new StringBuilder();
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (c >= '0' && c <= '9') {
                if (!paternStart)
                    count = count * 10 + c - '0';
                else
                    idx = expend(s, idx, sub);
            } else if (c == '[') {
                paternStart = true;
            } else if (c == ']') {
                for (int i = 0; i < count; i++) {
                    ret.append(sub);
                }
                sb.append(ret);
                return idx;
            } else {
                sub.append(c);
            }
            idx++;
        }
        return idx;
    }
}
