package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;
import com.shifamily.dev.*;

public class Leet1882ProcessTasksUsingServers extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[] { 3, 3, 2 }, new int[] { 1, 2, 3, 2, 1, 2 } })
                .answer(new int[] { 2, 2, 0, 2, 1, 2 })
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[] { 5, 1, 4, 3, 2 }, new int[] { 2, 1, 2, 4, 5, 2, 1 } })
                .answer(new int[] { 1, 4, 1, 4, 1, 3, 2 })
                .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public int[] assignTasks(int[] servers, int[] tasks) {
        return null;

    }

}
