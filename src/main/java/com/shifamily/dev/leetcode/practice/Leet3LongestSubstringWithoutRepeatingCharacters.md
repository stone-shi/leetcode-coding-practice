# Leetcode #3. Longest Substring Without Repeating Characters

## 原题

3 Longest Substring Without Repeating Characters
Medium 23815 1058

Given a string s, find the length of the longest substring without repeating characters.

**Example 1:**

> `Input: s = "abcabcbb"`
`Output: 3`
Explanation: The answer is "abc", with the length of 3.

**Example 2:**

> `Input: s = "bbbbb"`
`Output: 1`
Explanation: The answer is "b", with the length of 1.

**Example 3:**

> `Input: s = "pwwkew"`
`Output: 3`
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

**Constraints:**

- 0 <= s.length <= 5 * 104
- s consists of English letters, digits, symbols and spaces.

## 解法

双指针 + 字母计数的数组。

## 复杂度

## 代码

```Java
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

```
