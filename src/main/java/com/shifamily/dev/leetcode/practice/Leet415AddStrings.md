# 原题
415. Add Strings
Easy

839

252

Add to List

Share
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
Accepted
171,029
Submissions
365,625
# 解法
从末尾开始 每个char - '0'，然后相加。注意进位。
因为数组不一样长，要么循环内判断，要么外面做循环。 
最后位数都加完了也要注意进位。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)


## 代码
```Java

```
