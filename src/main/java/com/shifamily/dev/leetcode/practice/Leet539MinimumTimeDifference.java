package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet539MinimumTimeDifference extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { Arrays.asList("23:59", "00:00") }).answer(1)
                .description("Example 1").build());
        cases.add(
                CaseParameters.builder().parameters(new Object[] { Arrays.asList("00:00", "23:59", "00:00") }).answer(0)
                        .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public int findMinDifference(List<String> timePoints) {
        return 0;
    }

}
