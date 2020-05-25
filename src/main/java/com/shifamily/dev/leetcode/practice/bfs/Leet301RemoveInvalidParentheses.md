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


### 复杂度
时间复杂度 O(2^N)
空间复杂度 O(N)

### 代码
```Java
   public List<String> removeInvalidParenthesesBFS(String s) {

        //建立BFS的Queue，并且放入初始字符
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        //用HashSet过滤已经检查过的字符串。
        Set<String> dupCheck = new HashSet<>();

        List<String> result = new LinkedList<>();

        boolean found = false ;
        int size;

        while (!q.isEmpty()){
            size = q.size();
            //这个循环是BFS用来保证这个循环里面的数据都是这一层树的，这个循环里面加入Queue都是树的下一层
            for (int i = 0; i < size; i++) {
                String check = q.poll();
                if (dupCheck.contains(check))
                    continue;
                dupCheck.add(check);
                //如果找到了，添加到结果，并且设置found flag不再继续往树的下层查找(因为题目要求最小修改)
                if ( isValid(check) ) {
                    found = true;
                    result.add(check);
                }

                if (!found){ //只有这层没有找到才会添加下层，下层是这个字符串每次删掉一个字符。
                    for (int j = 0; j < check.length(); j++) {
                        if (check.charAt(j) != ')' && check.charAt(j) != '(')
                            continue;
                        q.offer(check.substring(0, j) + check.substring(j + 1));
                    }
                }
            }

            //如果这层找到了，就不再继续往下层找了。
            if (found)
                break;
        }


        return result;
    }
```

## 解法2 recursion  + 优化条件

首先扫描字符串，找出左右括号多余数 （需要移除数字）。方法如下：
从左向右，发现（后 left++，发现）后，如果left>0，left--，否则right++.

然后进入dfs，扫描字符串，发现（而且left>0，移除这个（，递归下一层dfs。
发现）而且right>0,移除这个），递归进入下一层。

当left 和right同时为0，意味着多余的括号都被移除，对字符串检查，如果有效的化就加入结果。即使无效，也不进入下一层dfs了。

解法一没有处理对是否会有解法的判断。这里对字符串先扫描一下，然后统计 多出来的左括号，或者多出来右括号，有可能既多出来左括号，又多出来右括号，比如 ")("

扫描后进入递归函数。

如果左括号和右括号一样多，检查是否有效。

如果多出来左括号，删了这个左括号然后递归检查，如果多出来右括号，删了这个右括号，然后递归检查。

解法1其实也可以加这个检查的。

### 复杂度
时间复杂度 O(2^N)
空间复杂度 O(N)

### 代码
```Java
    public List<String> removeInvalidParenthesesRecursion(String s) {

        //预先计算左括号多出来还是右括号多出来
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                left++;
            else if (c == ')'){
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }

        List<String> result = new LinkedList<>();

        helper(0, left, right, s, result);

        return result;

    }

    private void helper(int start, int left, int right, String s, List<String> result){

        //如果左括号和右括号一样多，检查是否有效
        if (left == 0 && right == 0){
            if (isValid(s))
                result.add(s);

            return;
        }

        //从当前字符，往下查找，之前找过的就不找了
        for (int i = start; i < s.length() ; i++) {

            //检查是否多个同向括号，主要处理第一个。比如 ((()，删掉第一个（，第二个，第三个都没有区别的
            if (i > start && s.charAt(i) == s.charAt(i-1))
                continue;

            //左括号太多，删左括号继续检查
            if (left > 0 && s.charAt(i) == '(')
                helper(i, left -1, right, s.substring(0, i) + s.substring(i+1), result);
            else if (right > 0 && s.charAt(i) == ')' ) //右括号太多
                helper(i, left, right - 1, s.substring(0, i) + s.substring(i+1), result);


        }

    }

```

