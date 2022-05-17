package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.LinkedList;
import java.util.List;

/*
LeetCode 282. Expression Add Operators
Hard
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
 */
public class Leet282ExpressionAddOperators extends BasicStudy {

    public Leet282ExpressionAddOperators() {
        String[] casesP1 = {"123", "232", "105", "00", "3456237490" };
        int[] casesP2 = {6, 8, 5, 0, 9191};
        String[][] answers = {{"1+2+3", "1*2*3"}, {"2*3+2", "2+3*2"}, {"1*0+5","10-5"}, {"0+0", "0-0", "0*0"}, {}};

        for (int i = 0; i < casesP1.length; i++) {

            Object[] p = new Object[2];
            p[0] = casesP1[i];
            p[1] = casesP2[i];

            addParameterAndAnswer(p, answers[i], false);
        }

    }

    @CaseRunner
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

}
