package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet299BullsCows extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { "1122", "1222" }).answer("3A0B")
                .description("case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "1807", "7810" }).answer("1A3B")
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "1123", "0111" }).answer("1A1B")
                .description("case b").build());

        return cases;
    }

    // second try - 2022/06/25
    @CaseRunner
    public String getHint3(String secret, String guess) {

        int[] ct = new int[10];
        int a = 0, b = 0;

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                a++;
            } else {
                if (ct[g] > 0)
                    b++;
                if (ct[s] < 0)
                    b++;
                ct[g]--;
                ct[s]++;
            }
        }
        return String.valueOf(a) + 'A' + String.valueOf(b) + 'B';
    }

    // solution 2 - 1 pass - best
    @CaseRunner
    public String getHint1(String secret, String guess) {
        int[] charCount = new int[10];
        int a = 0;
        int b = 0;

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g)
                a++;
            else {
                if (charCount[g] > 0)
                    b++;
                if (charCount[s] < 0)
                    b++;
                charCount[s]++;
                charCount[g]--;
            }
        }
        return String.valueOf(a) + "A" + String.valueOf(b) + "B";
    }

    // solution 1 - require 2 pass
    @CaseRunner
    public String getHint(String secret, String guess) {
        int[] charCount = new int[10];
        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i))
                charCount[secret.charAt(i) - '0']++;
            else
                aCount++;
        }

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                if (charCount[guess.charAt(i) - '0'] > 0) {
                    bCount++;
                    charCount[guess.charAt(i) - '0']--;
                }
            }
        }
        return String.valueOf(aCount) + "A" + String.valueOf(bCount) + "B";
    }

}
