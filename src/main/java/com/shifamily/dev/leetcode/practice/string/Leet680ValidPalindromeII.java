package com.shifamily.dev.leetcode.practice.string;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 680. Valid Palindrome II
 Easy

 1462

 96

 Add to List

 Share
 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class Leet680ValidPalindromeII extends BasicStudy {
    public Leet680ValidPalindromeII() {
        String[] caseP1 = {"aba", "abca" };
        Boolean[] answer = {true, true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }

    }

    @CaseRunner
    public boolean validPalindrome(String s) {
        return helper(s, 1, 0, s.length() - 1);
    }

    private boolean helper(String s, int allowDel, int left, int right){
        if (allowDel < 0)
            return false;

        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return helper(s, allowDel - 1, left + 1, right) ||
                        helper(s, allowDel -1, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
}
