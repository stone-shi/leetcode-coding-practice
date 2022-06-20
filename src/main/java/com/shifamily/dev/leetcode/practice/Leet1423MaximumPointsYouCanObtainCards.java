package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet1423MaximumPointsYouCanObtainCards extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 1000, 1 }, 1 }).answer(1)
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 2, 3, 4, 5, 6, 1 }, 3 }).answer(12)
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 2, 2, 2 }, 2 }).answer(4)
                .description("case b").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 9, 7, 7, 9, 7, 7, 9 }, 7 }).answer(55)
                .description("case c").build());
        return cases;
    }

    // second try - 2022/06/19  - it can be further optimize on space 
    @CaseRunner
    public int maxScore3(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] sum = new int[k + 1];
        int s = 0;
        for (int i = 0; i < k; i++){
            s += cardPoints[i];
            sum[i + 1] = s;
        }
        int res = s;
        s = 0;        
        for (int i = 0; i < k; i++){
            s += cardPoints[n - 1 - i];
            res = Math.max(res, s + sum[k - 1 -i]);
        }
        return res;        
    }

    // solution 1, space O(k)
    @CaseRunner
    public int maxScore(int[] cardPoints, int k) {
        int[] sumLeft = new int[k + 1];
        if (cardPoints.length <= k){
            return Arrays.stream(cardPoints).sum();   
        }
        for (int i = 1; i <= k; i++) {
            sumLeft[i] = sumLeft[i - 1] + cardPoints[i - 1];
        }
        int res = sumLeft[k];
        int sumRight = 0;
        for (int i = 0; i < k  ; i++) {
            sumRight += cardPoints[cardPoints.length - 1 - i];
            int m = sumRight + sumLeft[k - 1 - i];
            res = Math.max(res, m);
        }
        return res;
    }

    // solution 2, space o(1)
    @CaseRunner
    public int maxScore2(int[] cardPoints, int k) {
        int sumL = 0;
        int res = 0;
        int len = cardPoints.length;
        if (len <= k)
            return Arrays.stream(cardPoints).sum();
        for (int i = 0; i < k; i++) {
            sumL += cardPoints[i];
        }
        res = sumL;
        int sumR = 0;
        for (int i = 0; i < k; i++) {
            sumR += cardPoints[len - 1 - i];
            sumL -= cardPoints[k - 1 - i];
            res = Math.max(res, sumR + sumL);
        }
        return res;
    }

}
