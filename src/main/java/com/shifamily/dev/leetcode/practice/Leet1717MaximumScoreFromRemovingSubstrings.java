package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1717MaximumScoreFromRemovingSubstrings extends BasicStudy{

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { "cdbcbbaaabab", 4, 5 }).answer(19)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "aabbaaxybbaabb", 5, 4 }).answer(20)
                .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public int maximumGain(String s, int x, int y) {
        return 0;
    }
}
