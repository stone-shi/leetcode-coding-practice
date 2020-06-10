package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 66. Plus One
 Easy

 1427

 2282

 Add to List

 Share
 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.
 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.
 Accepted
 581,599
 Submissions
 1,379,196
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 LeetCode
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Google
 |
 9

 Facebook
 |
 2
 Multiply Strings
 Medium
 Add Binary
 Easy
 Plus One Linked List
 Medium
 Add to Array-Form of Integer
 Easy
 */

public class Leet66PlusOne extends BasicStudy {
    public Leet66PlusOne() {
        int[][] caseP1 = {{1,2,3}, {4,3,2,1}, {9,9,9}};
        int[][] answer = {{1,2,4}, {4,3,2,2}, {1,0,0,0} };

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i]);
        }

    }

    @CaseRunner
    public int[] plusOne(int[] digits) {

        int n = digits.length - 1;
        while (n >= 0){

            if (digits[n] == 9) {
                digits[n] = 0;
            }else{
                digits[n] += 1;
                return digits;
            }
            n--;
        }
        int[] res = new int[digits.length + 1];
        System.arraycopy(digits, 0, res, 1, digits.length);
        res[0] = 1;
        return res;
    }
    /* first try, worked but too complicated, just need deal with special case
    @CaseRunner
    public int[] plusOne(int[] digits) {

        List<Integer> res = new ArrayList<>();
        int adv = 1;
        for (int i = digits.length - 1; i >= 0 ; i--) {
            int r = digits[i] + adv;
            adv = r / 10;
            res.add( r % 10);
        }
        if (adv > 0)
            res.add(adv);

        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[res.size() - i - 1] = res.get(i);
        }
        return ret;
    }

     */
}
