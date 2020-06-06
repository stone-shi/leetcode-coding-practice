package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Arrays;

/**
 670. Maximum Swap
 Medium

 901

 64

 Add to List

 Share
 Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

 Example 1:
 Input: 2736
 Output: 7236
 Explanation: Swap the number 2 and the number 7.
 Example 2:
 Input: 9973
 Output: 9973
 Explanation: No swap.
 Note:
 The given number is in the range [0, 108]
 Accepted
 62,242
 Submissions
 145,336
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 SoumyaroopRoy
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 16

 ByteDance
 |
 2
 Create Maximum Number
 Hard
 */
public class Leet670MaximumSwap extends BasicStudy {


    public Leet670MaximumSwap() {
        int[] caseP1 = {1993, 98368, 2736, 9973};
        int[] answer = {9913, 98863, 7236, 9973};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public int maximumSwap(int num) {

        int tmp = num;
        int dig = 0;
        int[] digs = new int[16];
        int maxVal = Integer.MIN_VALUE;
        int maxDig = -1;
        int exchange = -1;
        int exchangeTo = -1;
        while (tmp > 0){
            digs[dig] = tmp % 10;
            tmp = tmp / 10;
            if (digs[dig] > maxVal){
                maxVal = digs[dig];
                maxDig = dig;
            }else if (digs[dig] < maxVal){
                exchange = dig;
                exchangeTo = maxDig;
            }
            dig++;
        }

        if (exchange != -1){
            tmp = digs[exchange];
            digs[exchange] = digs[exchangeTo];
            digs[exchangeTo] = tmp;
        }else
            return num;

        tmp = digs[--dig];
        while (dig > 0)
            tmp = tmp * 10 + digs[--dig];


        return tmp;

    }
}
