package com.shifamily.dev;

import java.util.*;
import com.shifamily.dev.*;

public class QuestionTemplate1 extends BasicStudy {

    @ClassCaseData
    public List<ClassCaseParameters> data2() {
        List<ClassCaseParameters> cases = new ArrayList<>();
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "TimeMap", "set", "get", "get", "set", "get", "get" })
                .operationParameters(new Object[][] {
                        {}, { "foo", "bar", 1 }, { "foo", 1 }, { "foo", 3 }, { "foo", "bar2", 4 }, { "foo", 4 },
                        { "foo", 5 }

                }).answer(new Object[] { null, null, "bar", "bar", null, "bar2", "bar2" })
                .description("case a").build());

        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "TimeMap","set","set","get","get","get","get","get" })
                .operationParameters(new Object[][] {
                    {},{"love","high",10},{"love","low",20},{"love",5},{"love",10},{"love",15},{"love",20},{"love",25}
                }).answer(new Object[] { null, null, null, "", "high", "high", "low", "low" })
                .description("case a").build());
        return cases;
    }

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

    // value of annotation is optional, if present, it will match operation[0]
    @CaseRunner("TimeMap")
    static public class TimeMap {
        Map<String, TreeMap<Integer, String>> map = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
        }

        public String get(String key, int timestamp) {
            return "";
        }
    }

}
