package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.List;

/**
 29. Divide Two Integers
 Medium

 1101

 5015

 Add to List

 Share
 Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

 Return the quotient after dividing dividend by divisor.

 The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

 Example 1:

 Input: dividend = 10, divisor = 3
 Output: 3
 Explanation: 10/3 = truncate(3.33333..) = 3.
 Example 2:

 Input: dividend = 7, divisor = -3
 Output: -2
 Explanation: 7/-3 = truncate(-2.33333..) = -2.
 Note:

 Both dividend and divisor will be 32-bit signed integers.
 The divisor will never be 0.
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 Accepted
 268,862
 Submissions
 1,650,413
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 22

 Amazon
 |
 6

 Apple
 |
 2

 Bloomberg
 |
 2


 */
public class Leet29DivideTwoIntegers extends BasicStudy {

    public Leet29DivideTwoIntegers() {
        int[] case1P1 = {7, -2147483648, 2147483647, -2147483648, 0, 10, 7, 9};
        int[] case1P2 = {-3, 1, 1, -1, 1, 3, -3, 3};
        int[] ans = {-2, -2147483648, 2147483647, 2147483647, 0, 3, -2, 3};

        for (int i = 0; i < case1P1.length; i++) {
            Object[] p1 = new Object[2];
            p1[0] = case1P1[i];
            p1[1] = case1P2[i];
            addParameterAndAnswer(p1, ans[i], true);
        }

    }

    @CaseRunner
    public int divideLinearSearch(int dividend, int divisor) {

        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1)
            return dividend;

        int res = 0;
        boolean negative = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
            negative = true;

        long dividendl = dividend;
        long divisorl = divisor;

        dividendl = Math.abs(dividendl);
        divisorl = Math.abs(divisorl);


        while (dividendl >= divisorl){
            res++;
            dividendl -= divisorl;
        }

        if (negative)
            res = -res;

        return res;

    }


    @CaseRunner
    public int divideBinarySearch(int dividend, int divisor) {

        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

       //quotient
        int res = 0;
        int idx = 0;
        long[] memOfBinary = new long[128];
        long[] memOfPower2 = new long[128];

        boolean negative = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
            negative = true;

        long dividendl = dividend;
        long divisorl = divisor;

        dividendl = Math.abs(dividendl);
        divisorl = Math.abs(divisorl);

        if (dividendl < divisorl)
            return 0;

        memOfBinary[0] = divisorl;
        memOfPower2[0] = 1;
        while (memOfBinary[idx] <= dividendl){
            idx++;
            memOfBinary[idx] = memOfBinary[idx - 1] + memOfBinary[idx - 1];
            memOfPower2[idx] = memOfPower2[idx - 1] + memOfPower2[idx - 1];
        }
        res = (int)memOfPower2[idx - 1];
        dividendl = dividendl - memOfBinary[idx - 1];

        while (dividendl >= divisorl){

            idx = 0;
            while (memOfBinary[idx] <= dividendl){
                idx++;
            }

            res += memOfPower2[idx - 1];
            dividendl = dividendl - memOfBinary[idx - 1];
        }
        if (negative)
            res = -res;

        return res;


    }


}
