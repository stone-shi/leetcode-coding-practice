package com.shifamily.dev.leetcode.practice.string;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.*;

/**
 * 249. Group Shifted Strings
 * Medium
 * <p>
 * 444
 * <p>
 * 79
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * <p>
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * <p>
 * Example:
 * <p>
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 * Accepted
 * 68,081
 * Submissions
 * 127,120
 * Seen this question in a real interview before?
 * <p>
 * Yes
 * <p>
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 * <p>
 * Facebook
 * |
 * 13
 * <p>
 * Google
 * |
 * 2
 * <p>
 * Amazon
 * |
 * 2
 * Group Anagrams
 * Medium
 */

public class Leet249GroupShiftedStrings extends BasicStudy {

    public Leet249GroupShiftedStrings() {
        String[][] caseP1 = {{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}};
        String[][][] answer = {
                {
                        {"abc", "bcd", "xyz"},
                        {"az", "ba"},
                        {"acef"},
                        {"a", "z"}
                }
        };


        Comparator<String[]> comparator =  ((o1, o2) -> o1[0].compareTo(o2[0]));

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false, comparator);
        }

    }

    @Override
    protected Object convertReturn(Object r) {
        List<List<String>> res = (List<List<String>>)r;
        String[][] ret = new String[res.size()][];
        int i = 0;
        for (List<String> lst: res){
            String[] content = new String[lst.size()];
            ret[i++] = lst.toArray(content);
        }
        return ret;
    }

    @CaseRunner
    public List<List<String>> groupStrings(String[] strings) {

        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings){
            String d = getDelta(s);
            List<String> lst = map.getOrDefault(d, new ArrayList<>());
            lst.add(s);
            map.put(d, lst);
        }
        List<List<String>> res = new LinkedList<>();
        for (Map.Entry<String, List<String>> entry: map.entrySet())
            res.add(entry.getValue());

        return res;
    }

    private String getDelta(String s){
        if (s.length() == 1)
            return "-1";

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int d = s.charAt(i - 1) - s.charAt(i);
            if (d < 0)
                d += 26;
            sb.append(d).append(',');
        }
        return sb.toString();
    }
}
