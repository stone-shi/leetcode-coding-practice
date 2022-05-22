package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import com.shifamily.dev.ClassCaseData;
import com.shifamily.dev.ClassCaseParameters;

public class Leet981TimeBasedKeyValueStore extends BasicStudy {
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

    @CaseRunner
    static public class TimeMap {
        Map<String, TreeMap<Integer, String>> map = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> valMap = map.getOrDefault(key, new TreeMap<>());
            valMap.put(timestamp, value);
            map.put(key, valMap);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> valMap = map.get(key);
            if (valMap == null)
                return "";
            if (valMap.floorKey(timestamp) != null)
                return valMap.floorEntry(timestamp).getValue();
            return "";
        }
    }

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */
}
