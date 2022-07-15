package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet528RandomPickWeight extends BasicStudy {

    @ClassCaseData
    public List<ClassCaseParameters> data2() {
        List<ClassCaseParameters> cases = new ArrayList<>();
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "Solution", "pickIndex" })
                .operationParameters(new Object[][] {
                        { new int[] { 1 } }, {}
                }).answer(new Object[] { null, 0 })
                .description("Example 1").build());
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "Solution", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "pickIndex" })
                .operationParameters(new Object[][] {
                        { new int[] { 1, 3 } }, {}, {}, {}, {}, {}
                }).answer(new Object[] { null, 1, 1, 1, 1, 0 })
                .description("Example 1").build());
        return cases;
    }

    @CaseRunner("Solution")
    class Solution {

        public Solution(int[] w) {

        }

        public int pickIndex() {
            return 0;

        }
    }
}
