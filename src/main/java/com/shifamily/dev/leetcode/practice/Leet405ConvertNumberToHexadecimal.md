# 原题
405 Convert a Number to Hexadecimal
Easy

416

108

Add to List

Share
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
Accepted
63,967
Submissions
147,013
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
4


# 解法
首先建立数字到字符的map
char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

然后对 num >>> 4 （位右移4位），得到的数字 & 0x0f，取出最小4位。把这个4位代表的数字到map查到对应的字符。加到结果里面。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public String toHex(int num) {
        if (num == 0)
            return "0";

        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        StringBuilder sb = new StringBuilder();
        while (num != 0){
            int tmp = num & 0x0f;
            sb.append(digits[tmp]);
            num = num >>> 4;
        }

        sb.reverse();
        return sb.toString();

    }
```
