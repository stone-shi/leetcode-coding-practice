package com.shifamily.dev.leetcode.practice.dp;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.util.*;

/**
 * 140. Word Break II
 * Hard
 * <p>
 * 1664
 * <p>
 * 353
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */

public class Leet140WordBreakII extends BasicStudy {
    public Leet140WordBreakII() {
        String[] caseP1 = {
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "catsanddog",
                "aaaaaaaa","aaaaaaa", "catsanddog", "pineapplepenapple", "catsandog"};
        String[][] caseP2 = {
                {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"},
                {"cat", "cats", "and", "sand", "dog"},
                {"aaaa","aaa","aa"},
                {"aaaa","aaa"},
                {"cat", "cats", "and", "sand", "dog"},
                {"apple", "pen", "applepen", "pine", "pineapple"},
                {"cats", "dog", "sand", "and", "cat"}
        };
        String[][] answer = {
                {},
                {"cats and dog", "cat sand dog"},
                {"aa aa aa aa","aaaa aa aa","aaa aaa aa","aa aaaa aa","aaa aa aaa","aa aaa aaa","aa aa aaaa","aaaa aaaa"},
                {"aaaa aaa","aaa aaaa"},
                {"cats and dog", "cat sand dog"},
                {"pine apple pen apple","pineapple pen apple", "pine applepen apple"},
                {}
        };

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = Arrays.asList(caseP2[i]);
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    /* 2nd try 5/23/2020 DFS*/
    @CaseRunner
    public List<String> wordBreak2nd(String s, List<String> wordDict) {
        Map<String, List<String>> mem = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);

        return dfs2nd(s, dict, mem);

    }

    private List<String> dfs2nd(String s, Set<String> dict, Map<String, List<String>> mem){
        if (mem.containsKey(s))
            return mem.get(s);

        List<String> res = new LinkedList<>();
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (dict.contains(sub)){
                if (i < s.length()){
                    List<String> r = dfs2nd(s.substring(i), dict, mem);
                    for (String rs : r)
                        res.add(sub + " " + rs);
                }else{
                    res.add(sub);
                }
            }
        }
        mem.put(s, res);
        return res;

    }




    /*-----------------------*/

    @CaseRunner
    public List<String> wordBreak(String s, List<String> wordDict) {

        List<String> ret = dfs(s, new HashSet<>(wordDict), new HashMap<>());
        if (ret == null){
            ret = new LinkedList<>();
        }

        return ret;


    }



    private List<String> dfs(String s, Set<String> dict, Map<String, List<String> > cache){

        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        List<String> res = new LinkedList<>();

        if (s.length() == 0){
            res.add("");
            return res;
        }

        if (dict.contains(s))
            res.add(s);

        for (int i = 1; i < s.length(); i++) {
            String w = s.substring(i);
            if (dict.contains(w)){
                List<String> rs = dfs( s.substring(0, i), dict, cache);
                if (rs.size() > 0){
                    for (String sub : rs) {
                        if (sub.length() > 0)
                            res.add( sub + " " + w);
                        else
                            res.add(w);
                    }
                    cache.put(s, res);
                }
            }
        }
        cache.put(s, res);
        return res;
    }





}
