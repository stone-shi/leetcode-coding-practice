package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Map;


/**
 166. Fraction to Recurring Decimal
 Medium

 810

 1761

 Add to List

 Share
 Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

 If the fractional part is repeating, enclose the repeating part in parentheses.

 Example 1:

 Input: numerator = 1, denominator = 2
 Output: "0.5"
 Example 2:

 Input: numerator = 2, denominator = 1
 Output: "2"
 Example 3:

 Input: numerator = 2, denominator = 3
 Output: "0.(6)"
 Accepted
 121,328
 Submissions
 569,500
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 Shangrila
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Goldman Sachs
 |
 10

 Facebook
 |
 7

 IXL
 |
 5

 Google
 |
 4
 No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
 Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
 Notice that once the remainder starts repeating, so does the divided result.
 Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
 */
public class Leet166FractionToRecurringDecimal extends BasicStudy {
    public Leet166FractionToRecurringDecimal() {
        int[] caseP1 = {1, 1, 2, 2};
        int[] caseP2 = {6, 2, 1, 3};
        String[] answer = {"0.1(6)", "0.5", "2", "0.(6)"};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i]);
        }

    }

    @CaseRunner
    public String fractionToDecimal(int numerator, int denominator) {

        StringBuilder sb = new StringBuilder();
        sb.append(numerator/denominator);

        int reminder = numerator % denominator;

        if (reminder == 0)
            return sb.toString();

        sb.append('.');
        Map<Integer, Integer> map = new HashMap<>();
        map.put(reminder, sb.length());

        while (reminder > 0){
            reminder *= 10;
            sb.append( reminder / denominator );
            reminder %= denominator;
            if (map.containsKey(reminder)) {
                int p = map.get(reminder);
                sb.insert(p, '(');
                sb.append(')');
                return sb.toString();
            }
            map.put(reminder, sb.length());

        }

        return sb.toString();
    }
}
