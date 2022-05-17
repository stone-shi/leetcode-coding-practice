package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.*;

/**
 139. Word Break
 Medium

 3912

 211

 Add to List

 Share
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".
 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 Accepted
 517K
 Submissions
 1.3M
 */
public class Leet139WordBreak extends BasicStudy {
    public Leet139WordBreak() {

        String[] caseP1 = {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                "aaaaaaa", "leetcode", "applepenapple", "catsandog" };
        String[][] caseP2 = {{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"}, {"aaaa","aaa"}, {"leet", "code"}, {"apple", "pen"}, {"cats", "dog", "sand", "and", "cat"} };
        boolean[] answer = {false, true, true, true, false};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = Arrays.asList(caseP2[i]);
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public boolean wordBreakBfs(String s, List<String> wordDict) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] mem = new boolean[s.length()];

        while (!queue.isEmpty()){
            int start = queue.poll();
            if (!mem[start]) {
                for (int i = start + 1; i <= s.length(); i++) {
                    String sub = s.substring(start, i);
                    if (dict.contains(sub)) {
                        queue.add(i);
                        if (i == s.length())
                            return true;
                    }
                }
            }
            mem[start] = true;

        }

        return false;

    }


    @CaseRunner
    public boolean wordBreakDP(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> dict = new HashSet<>(wordDict);

        for (int i = 1; i <= s.length() ; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    @CaseRunner
    public boolean wordBreakDfs(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        int[] mem = new int[s.length()];
        return dfs(s, dict, 0, mem);

    }

    private boolean dfs(String s, Set<String> dict, int start, int[] mem){

        if (start == s.length())
            return true;
        if (mem[start] != 0)
            return mem[start] == 1;

        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (dict.contains(sub) && dfs(s, dict, i, mem)) {
                mem[start] = 1;
                return true;
            }
        }
        mem[start] = -1;
        return false;
    }
}
