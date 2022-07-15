package com.shifamily.dev.leetcode.practice;

import java.util.*;
import java.util.Map.Entry;

import com.shifamily.dev.*;


public class Leet1146SnapshotArray extends BasicStudy {

    @ClassCaseData
    public List<ClassCaseParameters> testData() {
        List<ClassCaseParameters> cases = new ArrayList<>();
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "SnapshotArray", "snap", "get", "get", "set", "get", "set", "get", "set" })
                .operationParameters(new Object[][] {
                        { 2 }, {}, { 1, 0 }, { 0, 0 }, { 1, 8 }, { 1, 0 }, { 0, 20 }, { 0, 0 }, { 0, 7 }
                }).answer(new Object[] { null, 0, 0, 0, null, 0, null, 0, null })
                .description("Case 0").build());
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "SnapshotArray", "set", "snap", "set", "get" })
                .operationParameters(new Object[][] {
                        { 3 }, { 0, 5 }, {}, { 0, 6 }, { 0, 0 }
                }).answer(new Object[] { null, null, 0, null, 5 })
                .description("Example 1").build());
        return cases;
    }

    @CaseRunner("SnapshotArray")
    static public class SnapshotArray {
        int currentVersion = 0;
        Map<Integer, TreeMap<Integer, Integer>> data = new HashMap<>();
        int length;

        public SnapshotArray(Integer length) {
            this.length = length;
        }

        public void set(int index, int val) {
            TreeMap<Integer, Integer> item = data.getOrDefault(index, new TreeMap<>());
            item.put(currentVersion, val);
            data.put(index, item);
        }

        public int snap() {
            return currentVersion++;
        }

        public int get(int index, int snap_id) {
            if (!data.containsKey(index))
                return 0;

            TreeMap<Integer, Integer> item = data.get(index);
            Entry<Integer, Integer> e = item.floorEntry(snap_id);

            if (e == null)
                return 0;
            else
                return e.getValue();
        }
    }

}
