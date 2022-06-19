package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet1525NumberGoodWaysSplitString extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { "aacaba" }).answer(2)
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "abcd" }).answer(1)
                .description("case b").build());
        return cases;
    }

    // second try - 2022/06/19
    @CaseRunner
    public int numSplits3(String s) {
        int n = s.length();
        int[] charCount = new int[26];
        int[] leftCt = new int[n];
        int ct = 0;

        for (int i = 0; i < n - 1; i++) {
            char l = s.charAt(i);
            charCount[l - 'a']++;
            if (charCount[l - 'a'] == 1)
                ct++;
            leftCt[i] = ct;
        }

        charCount = new int[26];
        int res = 0;
        ct = 0;
        for (int i = n - 1; i > 0; i--) {
            char r = s.charAt(i);
            charCount[r - 'a']++;
            if (charCount[r - 'a'] == 1)
                ct++;
            if (ct == leftCt[i - 1])
                res++;
        }
        return res;
    }

    // solution 1
    @CaseRunner
    public int numSplits(String s) {
        Set<Character> cs = new HashSet<>();
        int[] leftCharCount = new int[s.length()];
        int rs = 0;
        for (int i = 0; i < s.length(); i++) {
            cs.add(s.charAt(i));
            leftCharCount[i] = cs.size();
        }
        cs.clear();
        ;
        for (int i = s.length() - 1; i > 0; i--) {
            cs.add(s.charAt(i));
            if (cs.size() == leftCharCount[i - 1])
                rs++;
        }
        return rs;
    }

    // solution 2
    @CaseRunner
    public int numSplits2(String s) {
        int[] charCountLeft = new int[26];
        int[] charCountRight = new int[26];
        int distinctLeft = 0;
        int distinctRight = 0;
        int rs = 0;

        for (int i = 0; i < s.length(); i++) {
            charCountLeft[s.charAt(i) - 'a']++;
            if (charCountLeft[s.charAt(i) - 'a'] == 1)
                distinctLeft++;
        }

        for (int i = s.length() - 1; i > 0; i--) {
            charCountRight[s.charAt(i) - 'a']++;
            if (charCountRight[s.charAt(i) - 'a'] == 1)
                distinctRight++;
            charCountLeft[s.charAt(i) - 'a']--;
            if (charCountLeft[s.charAt(i) - 'a'] == 0)
                distinctLeft--;
            if (distinctRight == distinctLeft)
                rs++;
        }

        return rs;

    }

}
