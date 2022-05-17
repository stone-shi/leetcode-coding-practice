package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/*
243. Shortest Word Distance
Easy

389

35

Add to List

Share
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class Leet243ShortestWordDistance extends BasicStudy {
    public Leet243ShortestWordDistance() {
        String[][] caseP1 = {{"practice", "makes", "perfect", "coding", "makes"} , {"practice", "makes", "perfect", "coding", "makes"}};
        String[] caseP2 = {"coding", "makes" };
        String[] caseP3 = {"practice", "coding" };

        int[] answer = {3, 1};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[3];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            p[2] = caseP3[i];
            addParameterAndAnswer(p, answer[i], false);
        }

    }


    @CaseRunner
    public int shortestDistance(String[] words, String word1, String word2) {

        int min = Integer.MAX_VALUE;
        int p1 = -1;
        int p2 = -1;

        for (int i = 0; i < words.length;  i++) {
            if (words[i].equals(word1))
                p1 = i;
            if (words[i].equals(word2))
                p2 = i;

            if (p1 != -1 && p2 != -1)
                min = Math.min(min, Math.abs(p1 - p2));
        }
        return min;

    }


}
