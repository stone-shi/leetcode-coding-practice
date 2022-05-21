package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet792NumberMatchingSubsequences extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { "abcde",new String[] {"a","bb","acd","ace" } }).answer(3)
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "dsahjpjauf",new String[] { "ahjpjau","ja","ahbwzgqnuk","tnmlanowax"} }).answer(2)
                .description("case b").build());

        return cases;
    }

    // solution 0, should work
    @CaseRunner
    public int numMatchingSubseq2(String s, String[] words) {
        List<StringBuilder>[] waitingList = new List[26];
        for (int i = 0; i < 26; i++) {
           waitingList[i] = new LinkedList<>(); 
        }
        for (String w : words) {
            waitingList[w.charAt(0) - 'a'].add(new StringBuilder(w));
        }
        char[] ca = s.toCharArray();
        int res = 0;
        for (char c : ca) {
            int idx = c - 'a';
            List<StringBuilder> sbs =  waitingList[idx];
            waitingList[idx] = new LinkedList<>();
            for (StringBuilder sb: sbs){
                sb.deleteCharAt(0);
                if (sb.length() > 0){
                    waitingList[sb.charAt(0) - 'a'].add(sb);
                }else{
                    res++;
                }
            }
        }
        return res;
    }

    // solution 1 - dfs. it will cause time out on leetocde, not good solution
    @CaseRunner
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] map = (List<Integer>[])new List<?>[26];
        for (int i = 0; i < s.length(); i++) {
            List<Integer> l = map[s.charAt(i) - 'a'];
            if (l == null){
                l = new LinkedList<>();
                map[s.charAt(i) - 'a'] = l;
            }
            l.add(i);
        }
        int res = 0;
        for (String w : words) {
            int p = search(w, 0, map, -1);
            if (p >= 0)
                res ++;
        }
        return res;
    }

    private int search(String s, int i, List<Integer>[] map, int pos){
        if (i == s.length())
            return pos;
        List<Integer> arr = map[s.charAt(i) - 'a'];
        if (arr == null)
            return -1;

        for (Integer p: arr) {
            if (p > pos){
                int next_p = search(s, i + 1, map, p);
                if (next_p >= 0)
                    return next_p;
            }
        }
        return -1;
    }

}
