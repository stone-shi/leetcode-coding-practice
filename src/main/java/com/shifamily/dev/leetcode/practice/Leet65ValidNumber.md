# 原题
65 Valid Number
Hard

694

4636

Add to List

Share
Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.

Accepted
164,677
Submissions
1,093,383
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
14

Amazon
|
3

LinkedIn
|
2

Google
|
2
String to Integer (atoi)

# 解法
没啥好说的，根据各类判断做。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
public boolean isNumber(String s) {

        boolean allowSign = true;
        boolean allowDot = true;
        boolean allowE = false;
        int ePos = -1;
        int dotPos = -1;
        boolean numberPresent = false;

        int i = 0;
        s = s.trim();

        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '.' && allowDot){
                allowDot = false;
                allowSign = false;
                dotPos = i;
            }else if (c == 'e' && allowE){
                allowDot = false;
                allowE = false;
                allowSign = true;
                numberPresent = false;
                ePos = i;
            }else if ((c == '+' || c == '-') && allowSign){
                allowSign = false;
            }else if (c >= '0' && c <= '9'){
                numberPresent = true;
                allowSign = false;
                if (ePos == -1)
                    allowE = true;
                if (dotPos == -1 && ePos == -1)
                    allowDot = true;
            }else
                return false;
        }


        return numberPresent;

    }
```
