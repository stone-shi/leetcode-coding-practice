package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Random;

/**
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
 */
public class Leet415AddStrings extends BasicStudy {

    public Leet415AddStrings() {

        String[] caseP1 = { "9", "1234567890", "978" };
        String[] caseP2 = { "99", "9876543210", "12345" };
        String[] answer = { "108", "11111111100", "13323"};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }

        generateRandom(10);
    }

    private void generateRandom(int count){

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int p1 = random.nextInt(Integer.MAX_VALUE / 10);
            int p2 = random.nextInt(Integer.MAX_VALUE / 10);
            Object[] p = new Object[2];
            p[0] = Integer.toString(p1);
            p[1] = Integer.toString(p2);
            String answer = Integer.toString(p1 + p2);

            addParameterAndAnswer(p, answer, false);

        }

    }

    @CaseRunner
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int r = 0;
        int sum = 0;
        while (i >= 0 && j >= 0){
            sum = num1.charAt(i--) - '0' + num2.charAt(j--) - '0' + r;
            r = sum / 10;
            sb.append(sum % 10);
        }
        while (i >= 0 ){
            sum = num1.charAt(i--) - '0' + r;
            r = sum / 10;
            sb.append(sum % 10);
        }
        while (j >= 0 ){
            sum = num2.charAt(j--) - '0' + r;
            r = sum / 10;
            sb.append(sum % 10);
        }
        if (r > 0)
            sb.append(r);
        sb.reverse();
        return  sb.toString();

    }
}
