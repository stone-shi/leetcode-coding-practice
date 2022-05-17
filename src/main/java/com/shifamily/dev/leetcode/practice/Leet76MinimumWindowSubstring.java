package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Map;

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
        String[] caseP1 = {"QABCCD", "ADOBECODEBANC" , "AAA", "aBcedF"};
        String[] caseP2 = {"ACC", "ABC", "CC" , "cd"};

        String[] answer = {"ABCC", "BANC", "", "ced"};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    /* second try */
    @CaseRunner
    public String minWindow2nd(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++)
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1 );

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int matchedLen = t.length();
        String res = "";

        while (left < s.length() && right < s.length()){
            char c = s.charAt(right);
            if ( map.containsKey(c) ){
                int ct = map.get(c);
                if (ct > 0)
                    matchedLen--;
                map.put(c, ct + 1);
            }
            while ( matchedLen == 0 ){
                if (right - left + 1 < minLen){
                    minLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }

                char c1 = s.charAt(left);
                if (map.containsKey(c1)){
                    int ct = map.get(c1);
                    if (ct > 0)
                        matchedLen++;
                    map.put(c1, ct - 1);
                }
                left++;
            }
            right++;
        }


        return res;

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
