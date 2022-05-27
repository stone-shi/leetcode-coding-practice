package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet853CarFleet extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { 12, new int[] { 10, 8, 0, 5, 3 }, new int[] { 2, 4, 1, 1, 3 } }).answer(3)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { 10, new int[] { 3 }, new int[] { 3 } }).answer(1)
                .description("case b").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { 100, new int[] { 0, 2, 4 }, new int[] { 4, 2, 1 } }).answer(1)
                .description("case c").build());
        return cases;
    }

    @CaseRunner
    public int carFleet(int target, int[] position, int[] speed) {
        PriorityQueue<int[]> pq = new PriorityQueue<>( (o1, o2) -> o2[0] - o1[0] );
        for (int i = 0; i < speed.length; i++) {
            pq.offer(new int[]{position[i], speed[i]});
        }
        int fleet = 0;
        double arriveTime = 0;
        while (!pq.isEmpty()){
            int[] pair = pq.poll();
            double time = (target - pair[0]) * 1.0 / pair[1];
            if ( time > arriveTime){
                fleet++;
                arriveTime = time;
            }
        }
        return fleet;
    }

}
