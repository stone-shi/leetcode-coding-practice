package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;


/*
482. License Key Formatting
Easy

You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules described above.

Example 1:
Input: S = "5F3Z-2e-9-w", K = 4

Output: "5F3Z-2E9W"

Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
Example 2:
Input: S = "2-5g-3-J", K = 2

Output: "2-5G-3J"

Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
Note:
The length of string S will not exceed 12,000, and K is a positive integer.
String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
String S is non-empty.

 */
public class Leet482LicenseKeyFormatting extends BasicStudy {

    public Leet482LicenseKeyFormatting() {
        String[] casesP1 = {"--a-a-a-a--", "5F3Z-2e-9-w", "2-5g-3-J"};
        int[] casesP2 = {2, 4, 2};
        String[] answers = {"AA-AA", "5F3Z-2E9W", "2-5G-3J"};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = casesP1[i];
            p[1] = casesP2[i];

            addParameterAndAnswer(p, answers[i], true);
        }
    }

    @CaseRunner
    public String licenseKeyFormatting(String S, int K) {

        char[] cs = S.toUpperCase().toCharArray();

        StringBuilder sb = new StringBuilder();

        int ct = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '-')
                continue;

            if (ct == K) {
                ct = 0;
                sb.append('-');
            }
            sb.append(cs[i]);
            ct++;
        }
        sb.reverse();
        return sb.toString();
    }
}
