package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet871MinimumNumberRefuelingStops extends BasicStudy {

        @CaseData
        public List<CaseParameters> data1() {
                List<CaseParameters> cases = new ArrayList<>();
                cases.add(CaseParameters.builder().parameters(new Object[] { 1, 1, new int[][] {} }).answer(0)
                                .description("Example 1").build());
                cases.add(CaseParameters.builder().parameters(new Object[] { 100, 1, new int[][] { { 10, 100 } } })
                                .answer(0)
                                .description("Example 2").build());
                cases.add(CaseParameters.builder()
                                .parameters(new Object[] { 100, 10,
                                                new int[][] { { 10, 60 }, { 20, 30 }, { 30, 30 }, { 60, 40 } } })
                                .answer(0)
                                .description("Example 3").build());
                return cases;
        }

        @CaseRunner
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
                return 0;

        }

}
