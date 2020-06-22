# 原题
1249 Minimum Remove to Make Valid Parentheses
Medium

628

23

Add to List

Share
Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 

Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
Accepted
53,258
Submissions
86,635
# 解法
用stack，发现"（"立即push。发现"）"：如果stack空，那么）无效，对应的i要mark成删除。如果stack不空，pop。
循环结束后stack内剩余的index都要删除。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> stack = new LinkedList<>();
        char[] sc = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (sc[i] == '(') {
                stack.push(i);
            } else if (sc[i] == ')') {
                if (!stack.isEmpty())
                    stack.pop();
                else
                    sc[i] = '*';
            }
        }
        while (!stack.isEmpty())
            sc[stack.pop()] = '*';

        for (int i = 0; i < sc.length; i++) {
            if (sc[i] != '*')
                sb.append(sc[i]);
        }

        return sb.toString();
    }
```
