package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

/*
1048. Longest String Chain
https://leetcode.com/problems/longest-string-chain/
Medium

3401

166

Add to List

Share
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
Accepted
202,233
Submissions
349,476
Seen this question in a real interview before?

Yes

No

*/

public class Leet1048LongestStringChain extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "ksqvsyq", "ks", "kss", "czvh", "zczpzvdhx", "zczpzvh",
                        "zczpzvhx", "zcpzvh", "zczvh", "gr", "grukmj", "ksqvsq", "gruj", "kssq", "ksqsq", "grukkmj",
                        "grukj", "zczpzfvdhx", "gru" } })
                .answer(7)
                .description("case c").build());

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "a", "ba" } }).answer(2)
                .description("case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "a", "b", "ba", "bca", "bda", "bdca" } }).answer(4)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" } }).answer(5)
                .description("case b").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new String[] { "abcd", "dbqca" } }).answer(1)
                .description("case c").build());
        return cases;
    }

    // solution 1 - dfs + dp
    int gMax = Integer.MIN_VALUE;

    @CaseRunner
    public int longestStrChain(String[] words) {
        gMax = 0;
        Map<Integer, List<String>> map = new TreeMap<>();
        Map<Integer, List<Integer>> mapChainSize = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            List<String> list = map.getOrDefault(len, new LinkedList<>());
            List<Integer> listSize = mapChainSize.getOrDefault(len, new LinkedList<>());
            list.add(words[i]);
            listSize.add(-1);
            map.put(len, list);
            mapChainSize.put(len, listSize);
        }
        Integer[] lenList = map.keySet().toArray(new Integer[0]);

        chainSize(null, lenList, 0, map, mapChainSize);
        return gMax + 1;

    }

    private int chainSize(String w1, Integer[] lenList, Integer currentLenIndex, Map<Integer, List<String>> map,
            Map<Integer, List<Integer>> mapChainSize) {
        if (currentLenIndex == lenList.length)
            return 0;

        int currentLen = lenList[currentLenIndex];
        List<String> strList = map.get(currentLen);

        List<Integer> sizeList = mapChainSize.get(currentLen);
        int maxChain = 0;

        for (int i = 0; i < strList.size(); i++) {
            String w2 = strList.get(i);
            if (sizeList.get(i) == -1) {
                int maxChainW2 = chainSize(w2, lenList, currentLenIndex + 1, map, mapChainSize);
                sizeList.set(i, maxChainW2);
                gMax = Math.max(gMax, maxChainW2);
            }
            if (!isChain(w1, w2))
                continue;
            maxChain = Math.max(maxChain, sizeList.get(i) + 1);
        }
        return maxChain;
    }

    private boolean isChain(String w1, String w2) {
        int skipCount = 0;
        if (w1 == null)
            return true;
        if (w1.length() != w2.length() - 1)
            return false;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) == w2.charAt(i + skipCount))
                continue;
            if (skipCount == 1 || w1.charAt(i) != w2.charAt(i + 1))
                return false;
            else
                skipCount++;
        }
        return true;
    }

    // solution 2
    @CaseRunner
    public int longestStrChain2(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            int chain = 0;
            for (int j = 0; j < w.length(); j++) {
                String wordToCheck = w.substring(0, j) + w.substring(j + 1);
                chain = Math.max(map.getOrDefault(wordToCheck, 0) + 1, chain);
            }
            res = Math.max(chain, res);
            map.put(w, chain);
        }
        return res;
    }

}
