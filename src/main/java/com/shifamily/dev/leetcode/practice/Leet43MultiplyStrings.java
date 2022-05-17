package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;
import java.util.Collections;

/**
 43. Multiply Strings
 Medium

 1651

 753

 Add to List

 Share
 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

 Example 1:

 Input: num1 = "2", num2 = "3"
 Output: "6"
 Example 2:

 Input: num1 = "123", num2 = "456"
 Output: "56088"
 Note:

 The length of both num1 and num2 is < 110.
 Both num1 and num2 contain only digits 0-9.
 Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 Accepted
 283,235
 Submissions
 849,220
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 18

 Microsoft
 |
 5

 Google
 |
 4

 Apple
 |
 3

 Amazon
 |
 2
 Add Two Numbers
 Medium
 Plus One
 Easy
 Add Binary
 Easy
 Add Strings
 Easy
 */
public class Leet43MultiplyStrings extends BasicStudy {
    public Leet43MultiplyStrings() {
        String[] case1P1 = {"9", "2", "123"};
        String[] case1P2 = {"99", "3", "456"};
        String[] ans = {"891", "6", "56088"};

        for (int i = 0; i < case1P1.length; i++) {
            Object[] p1 = new Object[2];
            p1[0] = case1P1[i];
            p1[1] = case1P2[i];
            addParameterAndAnswer(p1, ans[i], true);
        }

    }


    @CaseRunner
    public String multiply(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int[] res = new int[num1.length() + num2.length()];
            for (int i = num2.length() - 1; i >= 0; i--) {
                int a = 0;
                char n2 = num2.charAt(i);
                int dig =  num2.length() - 1 - i;
                for (int j = num1.length() - 1; j >= 0 ; j--) {
                    char n1 = num1.charAt(j);
                    int r = (n1 - '0') * (n2 - '0')  + a + res[dig];
                    a = r / 10;
                    res[dig++] = r % 10;
                }
                if (a > 0)
                    res[dig] += a;
            }

            boolean leadingZero = true;
            for (int i = res.length - 1; i >= 0 ; i--) {
                if (res[i] == 0 && leadingZero)
                    continue;
                else {
                    leadingZero = false;
                    sb.append(res[i]);
                }
            }
            if (sb.length() == 0)
                return "0";

            return sb.toString();
    }
}
