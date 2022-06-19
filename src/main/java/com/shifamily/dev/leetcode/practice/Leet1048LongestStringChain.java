package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

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

    // second try - 2022/06/19 sort + dp
    @CaseRunner
    public int longestStrChain4(String[] words) { 
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int res = Integer.MIN_VALUE;
        Map<String, Integer> dp = new HashMap<>();
        for (String s: words){
            int chain = Integer.MIN_VALUE;
            for (int i = 0; i < s.length(); i++) {
                String chk = s.substring(0, i) + s.substring(i + 1);
                chain = Math.max(chain,  1 + dp.getOrDefault(chk, 0));
            }
            dp.put(s, chain);
            res = Math.max(res, chain);
        }
        return res;
    }

    // second try - 2022/06/19 dfs + dp (not optimize)
    @CaseRunner
    public int longestStrChain3(String[] words) {
        int res = Integer.MIN_VALUE;
        int[] dp = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            res = Math.max(res, dfs(words, i, dp));
        }
        return res;
    }

    private boolean chain(String w1, String w2) {
        if (w1.length() != w2.length() - 1)
            return false;
        int diff = 0;
        int i = 0, j = 0;
        while (i < w1.length() && j < w2.length()) {
            if (w1.charAt(i) != w2.charAt(i + diff)) {
                if (diff > 0)
                    return false;
                diff++;
                j++;
                continue;
            }
            i++;
            j++;
        }
        return true;
    }

    private int dfs(String[] words, int idx, int[] dp) {
        if (dp[idx] > 0)
            return dp[idx];
        int chainLen = 1;
        for (int i = 0; i < words.length; i++) {
            if (idx == i)
                continue;
            if (chain(words[idx], words[i]))
                chainLen = Math.max(chainLen, 1 + dfs(words, i, dp));
        }
        dp[idx] = chainLen;
        return chainLen;
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
