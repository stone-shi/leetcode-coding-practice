package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet803BricksFallingWhenHit extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 0, 0, 0 }, { 1, 1, 1, 0 } }, new int[][] { { 1, 0 } } })
                .answer(new int[] { 2 })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 0, 0, 0 }, { 1, 1, 0, 0 } },
                        new int[][] { { 1, 1 }, { 1, 0 } } })
                .answer(new int[] { 0, 0 })
                .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public int[] hitBricks(int[][] grid, int[][] hits) {
        return null;

    }
}
