# Leetcode #1525 Number of Good Ways to Split a String

## 原题

[1525 Number of Good Ways to Split a String](https://leetcode.com/problems/number-of-good-ways-to-split-a-string/)

**<span style="color:blue">Medium</span>** 1345 30

You are given a string s.

A split is called good if you can split s into two non-empty strings sleft and sright where their concatenation is equal to s (i.e., s~left~ + s~right~ = s) and the number of distinct letters in s~left~ and s~right~ is the same.

Return the number of good splits you can make in s.

**Example 1:**

> `Input: s = "aacaba"`
`Output: 2`
**Explanation:** There are 5 ways to split "aacaba" and 2 of them are good. 
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.

**Example 2:**

> `Input: s = "abcd"`
`Output: 1`
**Explanation:** Split the string as follows ("ab", "cd").

**Constraints:**

* `1 <= s.length <= 105`
* s consists of only lowercase English letters.

## 解法

First scan to use hash set to get distinct set for each char index, the scan from right to left to remove each char.
We can use array too (instead of hash set)

## 复杂度

O(N)

## 代码


```Java
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


```
