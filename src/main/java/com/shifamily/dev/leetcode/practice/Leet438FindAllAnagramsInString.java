package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 438. Find All Anagrams in a String
 Medium

 2937

 168

 Add to List

 Share
 Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".
 Accepted
 256,310
 Submissions
 601,049
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 Stomach_ache
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 39

 Google
 |
 5

 Uber
 |
 4

 Amazon
 |
 4

 Microsoft
 |
 3

 Oracle
 |
 2
 Valid Anagram
 Easy
 Permutation in String
 Medium


 */
public class Leet438FindAllAnagramsInString  extends BasicStudy {

    public Leet438FindAllAnagramsInString() {
        String[] caseP1 = {"cbaebabacd"};
        String[] caseP2 = {"abc"};

        int[][] answer = {{0, 6}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }

    }

    @CaseRunner
    public List<Integer> findAnagrams(String s, String p) {

        int plen = p.length();
        int slen = s.length();
        List<Integer> res = new LinkedList<>();

        if (slen < plen)
            return res;

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (int i = 0; i < plen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, sCount))
            res.add(0);

        for (int i = 1; i < slen - plen + 1 ; i++) {
            sCount[s.charAt(i - 1) - 'a']--;
            sCount[s.charAt(i + plen - 1) - 'a']++;
            if (Arrays.equals(pCount, sCount))
                res.add(i);
        }

        return res;
    }

}
