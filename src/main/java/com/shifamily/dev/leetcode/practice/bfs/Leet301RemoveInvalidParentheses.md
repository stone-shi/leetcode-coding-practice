# 原题


301  Remove Invalid Parentheses
Hard

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).


Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]


# 解法

## 解法1 BFS

输入的字符串作为根，每次删除一个字符作为第一层，然后再删一个作为第二层，比如。
如果在某一层找到解，就不必再往下层找。 
```
        "()("
     /    |   \
  ")("  "(("   "()"
/
"(" ...

```
时间复杂度 O(2^N)
空间复杂度 O(N)

## 解法2 recursion  + 优化条件

解法一没有处理对是否会有解法的判断。这里对字符串先扫描一下，然后统计 多出来的左括号，或者多出来右括号，有可能既多出来左括号，又多出来右括号，比如 ")("

扫描后进入递归函数。

如果左括号和右括号一样多，检查是否有效。

如果多出来左括号，删了这个左括号然后递归检查，如果多出来右括号，删了这个右括号，然后递归检查。

解法1其实也可以加这个检查的。



