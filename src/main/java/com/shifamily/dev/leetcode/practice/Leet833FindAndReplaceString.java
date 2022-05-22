package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet833FindAndReplaceString extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { "abcde", new int[] { 2, 2 }, new String[] {
                        "cdef", "bc" },
                        new String[] { "f", "fe" } })
                .answer("abcde")
                .description("case 0").build());

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { "abcd", new int[] { 0, 2 }, new String[] { "a", "cd" },
                        new String[] { "eee", "ffff" } })
                .answer("eeebffff")
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { "abcd", new int[] { 0, 2 }, new String[] { "ab", "ec" },
                        new String[] { "eee", "ffff" } })
                .answer("eeecd")
                .description("case b").build());
        return cases;
    }

    // solution 1 - hashmap record change index, scan original s and get change
    // idicateor - a bit slow
    @CaseRunner
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, String[]> replaceMap = new HashMap<>();

        for (int i = 0; i < indices.length; i++) {
            if (indices[i] + sources[i].length() > s.length())
                continue;
            boolean match = true;
            for (int j = 0; j < sources[i].length(); j++) {
                if (s.charAt(indices[i] + j) != sources[i].charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match)
                replaceMap.put(indices[i], new String[] { sources[i], targets[i] });
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            String[] pair = replaceMap.get(i);
            if (pair == null)
                builder.append(s.charAt(i++));
            else {
                builder.append(pair[1]);
                i += pair[0].length();
            }
        }
        return builder.toString();
    }

    // solution 2 - sort the change indicator
    @CaseRunner
    public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < indices.length; i++) {
            pq.offer(new int[] { indices[i], i });
        }
        StringBuilder sb = new StringBuilder();
        int lastIdx = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int idx = pair[1];
            int pos = pair[0];
            sb.append(s.substring(lastIdx, pos));
            lastIdx = pos;
            if (pos + sources[idx].length() <= s.length()
                    && sources[idx].equals(s.substring(pos, pos + sources[idx].length()))) {
                sb.append(targets[idx]);
                lastIdx += sources[idx].length();
            }
        }
        sb.append(s.substring(lastIdx));
        return sb.toString();
    }
}
