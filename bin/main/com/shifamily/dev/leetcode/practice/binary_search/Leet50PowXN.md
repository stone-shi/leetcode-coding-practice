# 原题
50 Pow(x, n)
Medium

1390

2899

Add to List

Share
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
Accepted
449,277
Submissions
1,524,982
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
23

Amazon
|
9

Google
|
5

LinkedIn
|
4

Asana
|
4

Microsoft
|
3

Oracle
|
3

Bloomberg
|
2

Uber
|
2

Goldman Sachs
|
2
Sqrt(x)
Easy
Super Pow
Medium
# 解法

可以用不断 x * x，循环n次来做，这样时间复杂度是 O(n)

还可以 x * x后把这个数再次相乘，复杂度是 O(Log N)


## 复杂度
时间复杂度 O(Log N)
空间复杂度 O(1)


## 代码
```Java
    public double myPow(double x, int n) {
        if (n < 0){
            x = 1 / x;
            n = -n;
        }

        return fastPower(x, n);

    }

    private double fastPower(double x, int n) {
        if (n == 0)
            return 1.0;

        double half = fastPower(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else
            return half * half * x;

    }
```
