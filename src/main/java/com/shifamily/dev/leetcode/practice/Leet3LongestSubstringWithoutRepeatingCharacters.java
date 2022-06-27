package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet3LongestSubstringWithoutRepeatingCharacters extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { "abcabcbb" }).answer(3).description("case a")
                .build());
        cases.add(
                CaseParameters.builder().parameters(new Object[] { "bbbbb" }).answer(1).description("case b").build());
        cases.add(
                CaseParameters.builder().parameters(new Object[] { "pwwkew" }).answer(3).description("case c").build());

        return cases;
    }

    // second try - 2022/06/26
    @CaseRunner
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> charSet = new HashSet<>();

        int left = 0;
        int right = 0;
        int res = 0;

        while (right < n) {
            char c = s.charAt(right);
            if (charSet.contains(c)) {
                charSet.remove(s.charAt(left));
                left++;
            } else {
                charSet.add(c);
                right++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    @CaseRunner
    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int[] count = new int[128];
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < c.length) {
            if (count[c[right]] == 0) {
                count[c[right]]++;
                right++;
            } else {
                count[c[left]]--;
                left++;
            }
            res = Math.max(res, right - left);
        }

        return res;
    }

}
