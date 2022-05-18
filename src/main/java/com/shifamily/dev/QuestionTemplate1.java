package com.shifamily.dev;

import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;


public class QuestionTemplate1 extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 0, 2, 3, 4 } }).answer(0)
        .description("case a").build() );
        cases.add(new CaseParameters("case b", new Object[] { new int[] { 1, 2, 3, 4 } }, 1, null, false, -1));

        return cases;
    }

    @CaseRunner
    public int caseToRun1(int[] nums) {
        return nums[0];
    }

}
