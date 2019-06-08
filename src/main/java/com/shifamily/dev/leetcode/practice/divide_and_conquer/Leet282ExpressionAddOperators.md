# 原题

LeetCode 282

282 Expression Add Operators
Hard

687

95

Favorite

Share
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []

# 解法

深度优先搜索 DFS
参考： https://segmentfault.com/a/1190000003797204


## 思路
因为要输出所有可能的情况，必定是用深度优先搜索。问题在于如何将问题拆分成多次搜索。加减法很好处理，每当我们截出一段数字时，将之前计算的结果加上或者减去这个数，就可以将剩余的数字字符串和新的计算结果代入下一次搜索中了，直到我们的计算结果和目标一样，就完成了一次搜索。然而，乘法如何处理呢？这里我们需要用一个变量记录乘法当前累乘的值，直到累乘完了，遇到下一个加号或减号再将其算入计算结果中。这里有两种情况:

乘号之前是加号或减号，例如2+3*4，我们在2那里算出来的结果，到3的时候会加上3，计算结果变为5。在到4的时候，因为4之前我们选择的是乘号，这里3就应该和4相乘，而不是和2相加，所以在计算结果时，要将5先减去刚才加的3得到2，然后再加上3乘以4，得到2+12=14，这样14就是到4为止时的计算结果。

另外一种情况是乘号之前也是乘号，如果2+3*4*5，这里我们到4为止计算的结果是14了，然后我们到5的时候又是乘号，这时候我们要把刚才加的3*4给去掉，然后再加上3*4*5，也就是14-3*4+3*4*5=62。这样5的计算结果就是62。

因为要解决上述几种情况，我们需要这么几个变量，一个是记录上次的计算结果currRes，一个是记录上次被加或者被减的数prevNum，一个是当前准备处理的数currNum。当下一轮搜索是加减法时，prevNum就是简单换成currNum，当下一轮搜索是乘法时，prevNum是prevNum乘以currNum。

## 注意
第一次搜索不添加运算符，只添加数字，就不会出现+1+2这种表达式了。

我们截出的数字不能包含0001这种前面有0的数字，但是一个0是可以的。这里一旦截出的数字前导为0，就可以return了，因为说明前面就截的不对，从这之后都是开始为0的，后面也不可能了。


## 复杂度
时间复杂度 O(N^2)
空间复杂度 O(N)


## 代码
```Java
   public List<String> addOperators(String num, int target) {

        List<String> res = new LinkedList<>();

        helper(num, null, target, 0, 0, res);

        return res;

    }

    private void helper(String num, String ans, int target, long currResult, long prev, List<String> res){

        if (currResult == target && (num == null || num.isEmpty())){
            res.add(ans);
            return;
        }

        for (int i = 1; i <= num.length(); i++) {

            String currStr = num.substring(0, i);
            if (currStr.length() > 1 && currStr.charAt(0) == '0')
                return;

            long currVal = Long.parseLong(currStr);

            if (ans == null || ans.isEmpty()){
                helper(num.substring(i), currStr, target, currVal, currVal, res);
            }else{
                helper(num.substring(i), ans + "+" + currStr, target, currResult + currVal, currVal, res );
                helper(num.substring(i), ans + "-" + currStr, target, currResult - currVal, -1 * currVal, res );
                helper(num.substring(i), ans + "*" + currStr, target, (currResult - prev) + prev * currVal, prev * currVal, res );
            }
            
        }


    }
```
