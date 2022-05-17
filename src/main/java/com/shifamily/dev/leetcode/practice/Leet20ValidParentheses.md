# 原题
20 Valid Parentheses
Easy

3985

192

Add to List

Share
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
# 解法

Stack，发现([{ 入栈，发现)]}出栈

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public boolean isValid(String s){

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> matching = new HashMap<>();
        matching.put(']', '[');
        matching.put(')', '(');
        matching.put('}', '{');

        char[] ca = s.toCharArray();
        for (char c: ca) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (c == ')' || c == '}' || c == ']') {
                if (!stack.empty()) {
                    if (!stack.pop().equals( matching.get(c)))
                        return false;
                } else
                    return false;
            }
        }
        return stack.isEmpty();


    }
```
