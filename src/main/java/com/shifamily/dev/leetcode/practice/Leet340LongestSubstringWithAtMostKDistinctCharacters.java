package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Map;

/**
340. Longest Substring with At Most K Distinct Characters
Hard

1008

36

Add to List

Share
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
Accepted
126,284
Submissions
290,482
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
21

Microsoft
|
9

Google
|
7

Snapchat
|
2

Citadel
|
2

Wish
|
2
Longest Substring Without Repeating Characters
Medium
Longest Substring with At Most Two Distinct Characters
Medium
Longest Repeating Character Replacement
Medium
Subarrays with K Different Integers
Hard
Max Consecutive Ones III
 */
public class Leet340LongestSubstringWithAtMostKDistinctCharacters  extends BasicStudy {
    public Leet340LongestSubstringWithAtMostKDistinctCharacters() {
        String[] caseP1 = {"aba", "a", "abaccc", "eceba", "aa" };
        int[] caseP2 = {1, 0, 2, 2, 1};

        Integer[] answer = {1, 0, 4, 3, 2};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        if (k == 0)
            return 0;

        Map<Character, Integer> charInStr = new HashMap<>(k + 1);
        int left = 0;
        int right = 0;
        int lenMax = 0;

        while (right < s.length()){
            char rightChar = s.charAt(right);
            charInStr.put(rightChar, charInStr.getOrDefault(rightChar, 0) + 1);
            right++;
            if (charInStr.size() <= k){
                lenMax = Math.max (right - left   , lenMax);
            }else{
                while (charInStr.size() > k) {
                    char leftChar = s.charAt(left++);
                    int ct = charInStr.get(leftChar);
                    if (ct == 1)
                        charInStr.remove(leftChar);
                    else
                        charInStr.put(leftChar, ct - 1);
                }
            }
        }

        return lenMax;

    }

}
