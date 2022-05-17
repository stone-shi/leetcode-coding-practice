package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


/*
244. Shortest Word Distance II
Medium

295

101

Add to List

Share
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

 */

@Slf4j
public class Leet244ShortestWordDistanceII extends BasicStudy {

    public Leet244ShortestWordDistanceII() {
        String[][] caseP1 = {{"practice", "makes", "perfect", "coding", "makes"} };
        String[][] caseP2 = {{"coding", "practice", "makes", "coding"} };
        int[][] caseP3 = {{3, 1} };
        boolean[] answer = {true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[3];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            p[2] = caseP3[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public boolean runIt(String[] p1, String[] p2, int[]p3){
        WordDistance wordDistance = new WordDistance(p1);

        for (int i = 0; i < p2.length / 2; i++) {
            log.info("shortest({}, {})", p2[i * 2], p2[ (i * 2) +1]);
            int rs = wordDistance.shortest(p2[i * 2], p2[ (i * 2) +1]);
            if (rs != p3[i]){
                log.info("Error: expect {} got {}", p3[i], rs);
                return false;
            }

        }

        return true;

    }


    public static class WordDistance {

        Map<String, List<Integer>> listMap = new HashMap<>();

        public WordDistance(String[] words) {
            for (int i = 0; i <  words.length; i++) {

                if (listMap.containsKey(words[i]))
                    listMap.get(words[i]).add(i);
                else{
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    listMap.put(words[i], l);
                }
            }
        }

        public int shortest(String word1, String word2) {
            Integer[] index1 = listMap.get(word1).toArray(new Integer[0]);
            Integer[] index2 = listMap.get(word2).toArray(new Integer[0]);

            int i = 0, j = 0;
            int res = Integer.MAX_VALUE;
            while (i < index1.length && j < index2.length){
                int dis = Math.abs(index1[i] - index2[j]);
                if (dis < res)
                    res = dis;
                if (index1[i] < index2[j])
                    i++;
                else
                    j++;
            }
            return res;
        }
    }
}
