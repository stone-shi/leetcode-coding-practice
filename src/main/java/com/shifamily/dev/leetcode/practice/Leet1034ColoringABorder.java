package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1034ColoringABorder extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[][] { { 1, 1 }, { 1, 2 } }, 0, 0, 3 })
                .answer(new int[][] { { 3, 3 }, { 3, 2 } })
                .description("Example 1").build());
        cases.add(
                CaseParameters.builder().parameters(new Object[] { new int[][] { { 1, 2, 2 }, { 2, 3, 2 } }, 0, 1, 3 })
                        .answer(new int[][] { { 1, 3, 3 }, { 2, 3, 3 } })
                        .description("Example 2").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, 1, 1, 2 })
                .answer(new int[][] { { 2, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 } })
                .description("Example 3").build());
        return cases;
    }

    @CaseRunner
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        return null;
    }

}
