package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet875KokoEatingBananas extends BasicStudy{

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 3, 6, 7, 11 }, 8 }).answer(4)
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 30, 11, 23, 4, 20 }, 5 }).answer(30)
                .description("case b").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 30, 11, 23, 4, 20 }, 6 }).answer(23)
                .description("case c").build());

        return cases;
    }

    @CaseRunner
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = Integer.MIN_VALUE;
        for (int i = 0; i < piles.length; i++) {
            maxPile = Math.max(maxPile, piles[i]);
        }
        int l = 1;
        int r = maxPile;

        while ( l <= r ){
            int m = l + (r - l) / 2;
            if ( canEatAll( m, h, piles))
                r = m - 1;
            else 
                l = m + 1;
        }
        return l;
    }

    private boolean canEatAll(int k, int h, int[] piles){
        int time = 0;
        for (int i = 0; i < piles.length; i++) 
            time += (piles[i] / k) + (piles[i] % k > 0 ? 1 :0);
        return h >= time;   
    }

}
