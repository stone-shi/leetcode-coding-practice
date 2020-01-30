package com.shifamily.dev.leetcode.practice.slidwindow;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.util.HashMap;

/*
76. Minimum Window Substring
Hard

3390

247

Add to List

Share
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Leet76MinimumWindowSubstring extends BasicStudy {
    public Leet76MinimumWindowSubstring() {
        String[] caseP1 = {"ADOBECODEBANC" , "AAA", "aBcedF"};
        String[] caseP2 = {"ABC", "CC" , "cd"};

        String[] answer = {"BANC", "", "ced"};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }


    @CaseRunner
    public String minWindow(String s, String t) {

        int counter = t.length();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }


        int minLen = Integer.MAX_VALUE;
        int resRightPt = -1;
        int resLeftPt = -1;

        int leftPt = 0, rightPt = 0;
        while (rightPt < s.length()){
             char cr = s.charAt(rightPt++);
             if (map.containsKey(cr)) {
                 int ct = map.get(cr);
                 if (ct > 0)
                     counter--;
                 map.put(cr, ct - 1);

                 while (leftPt <= rightPt && counter == 0) {
                     if (rightPt - leftPt < minLen) {
                         minLen = rightPt - leftPt;
                         resRightPt = rightPt;
                         resLeftPt = leftPt;
                     }
                     char cl = s.charAt(leftPt++);
                     if (map.containsKey(cl)){
                         ct = map.get(cl);
                         map.put(cl, ct + 1);
                         if (ct >= 0)
                            counter++;
                     }

                 }
             }
        }

        if (resLeftPt == -1 || resRightPt == -1)
            return "";
        return s.substring(resLeftPt, resRightPt);
    }
}
