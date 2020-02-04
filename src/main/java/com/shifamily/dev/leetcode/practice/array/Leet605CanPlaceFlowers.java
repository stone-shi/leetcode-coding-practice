package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

public class Leet605CanPlaceFlowers extends BasicStudy {

    public Leet605CanPlaceFlowers() {
        int[][] caseP1 = {{1,0,0,0,1}, {1,0,0,0,1} };
        int[] caseP2 = {1, 2};
        boolean[] answer = {true, false};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], false);
        }
    }

    @CaseRunner
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if ( (i == 0 || flowerbed[i - 1] == 0) && flowerbed[i] == 0  && ( i == flowerbed.length - 1 || flowerbed[i+1] ==0 )  ) {
                flowerbed[i] = 1;
                n--;
            }

        }

        return n <= 0;

    }

}
