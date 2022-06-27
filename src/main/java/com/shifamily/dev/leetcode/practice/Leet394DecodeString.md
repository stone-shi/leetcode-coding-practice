# Leetcode #394 Decode String

## 原题

[394 Decode String](https://leetcode.com/problems/decode-string/)

**<span style="color:blue">Medium</span>** 8183 350

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

**Example 1:**

> `Input: s = "3[a]2[bc]"`
`Output: "aaabcbc"`

**Example 2:**

> `Input: s = "3[a2[c]]"`
`Output: "accaccacc"`

**Example 3:**

> `Input: s = "2[abc]3[cd]ef"`
`Output: "abcabccdcdcdef"`

**Constraints:**

- 1 <= s.length <= 30
- s consists of lowercase English letters, digits, and square brackets '[]'.
- s is guaranteed to be a valid input.
- All the integers in s are in the range [1, 300].

## 解法

解法1， 递归 recurision，碰到需要解码的地方， 数字，直接call解码函数，解码函数中碰到数字，递归解码函数。
解法2，用stack，碰到嵌套的解码，前一层入栈 - string和count，然后计算这一层，这一层结束后把前一层出栈。


## 复杂度


## 代码

```Java
 @CaseRunner
    public String decodeString2(String s) {
        Deque<StringBuilder> s1 = new LinkedList<>();
        Deque<Integer> s2 = new LinkedList<>();
        StringBuilder ret = new StringBuilder();

        StringBuilder part = new StringBuilder();

        int i = 0;
        int count = 0;
        boolean isPatern = false;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (!isPatern) {
                    count = count * 10 + c - '0';
                } else {
                    s1.push(part);
                    s2.push(count);
                    isPatern = false;
                    count = c - '0';
                    part = new StringBuilder();
                }
            } else if (c == '[') {
                isPatern = true;
            } else if (c == ']') {
                StringBuilder currentPart = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    currentPart.append(part);
                }
                if (!s1.isEmpty()) {
                    isPatern = true;
                    count = s2.pop();
                    part = s1.pop();
                    part.append(currentPart);
                } else {
                    isPatern = false;
                    count = 0;
                    part = new StringBuilder();
                    ret.append(currentPart);
                }
            } else {
                if (!isPatern)
                    ret.append(c);
                else
                    part.append(c);
            }

            i++;
        }
        return ret.toString();

    }

    // solution 1 - recursion solution
    @CaseRunner
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9')
                i = expend(s, i, sb);
            else
                sb.append(c);
            i++;
        }
        return sb.toString();
    }

    private int expend(String s, int idx, StringBuilder sb) {
        int count = 0;
        boolean paternStart = false;
        StringBuilder sub = new StringBuilder();
        StringBuilder ret = new StringBuilder();
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (c >= '0' && c <= '9') {
                if (!paternStart)
                    count = count * 10 + c - '0';
                else
                    idx = expend(s, idx, sub);
            } else if (c == '[') {
                paternStart = true;
            } else if (c == ']') {
                for (int i = 0; i < count; i++) {
                    ret.append(sub);
                }
                sb.append(ret);
                return idx;
            } else {
                sub.append(c);
            }
            idx++;
        }
        return idx;
    }

```
