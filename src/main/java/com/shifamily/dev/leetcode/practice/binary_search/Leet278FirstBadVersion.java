package com.shifamily.dev.leetcode.practice.binary_search;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Map;

/*
278. First Bad Version
Easy
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version.
 */
public class Leet278FirstBadVersion extends BasicStudy {

    private Map<Integer, Integer> checker = new HashMap<>();

    public Leet278FirstBadVersion() {

        int[] casesP1 = {5, 6, 10000, 2126753390};
        int[] answers = {4, 2, 2, 1702766719};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p1 = new Object[1];
            p1[0] = casesP1[i];
            addParameterAndAnswer(p1, answers[i], false);
            checker.put(casesP1[i], answers[i]);
        }
    }

    @CaseRunner
    public int caseRun(int n){
        currentAnswer = checker.get(n);
        return firstBadVersion(n);
    }

    private int currentAnswer = -1;

    private boolean isBadVersion(int version) {
        return version >= currentAnswer;
    }

    private int firstBadVersion(int n) {

        int l = 1;
        int r = n;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m))
                r = m ;
            else
                l = m + 1;
        }
        return l;
    }

}
