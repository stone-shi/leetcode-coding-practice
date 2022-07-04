package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;
import com.shifamily.dev.*;

public class Leet1101TheEarliestMomentWhenEveryoneBecomeFriends extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(
                CaseParameters.builder()
                        .parameters(new Object[] { new int[][] { { 20190101, 0, 1 }, { 20190104, 3, 4 },
                                { 20190107, 2, 3 }, { 20190211, 1, 5 }, { 20190224, 2, 4 }, { 20190301, 0, 3 },
                                { 20190312, 1, 2 }, { 20190322, 4, 5 } }, 6 })
                        .answer(20190301)
                        .description("Example 1").build());
        return cases;
    }

    @CaseRunner
    public int earliestAcq(int[][] logs, int N) {
        return 0;
    }

}
