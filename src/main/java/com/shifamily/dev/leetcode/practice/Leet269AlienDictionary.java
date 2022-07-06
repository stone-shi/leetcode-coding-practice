package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet269AlienDictionary extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "wrt", "wrf", "er", "ett", "rftt" } }).answer("wertf")
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "z", "x" } }).answer("zx")
                .description("Example 2").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "z", "x", "z" } }).answer("")
                .description("Example 3").build());
        return cases;
    }

    // 3rd try - 2022/7/5
    @CaseRunner
    public String alienOrder3(String[] words) {
        int[] inDegree = new int[26];
        Map<Character, Set<Character>> mapEdges = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    inDegree[c2 - 'a']++;
                    Set<Character> outEdge = mapEdges.getOrDefault(c1, new HashSet<>());
                    outEdge.add(c2);
                    mapEdges.put(c1, outEdge);
                    if (!mapEdges.containsKey(c2))
                        mapEdges.put(c2, new HashSet<>());
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        for (char c : mapEdges.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                q.offer(c);
            }
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            Set<Character> children = mapEdges.get(c);
            for (char child : children) {
                inDegree[child - 'a']--;
                if (inDegree[child - 'a'] == 0)
                    q.offer(child);
            }
        }

        if (sb.length() != mapEdges.size())
            return "";

        return sb.toString();

    }

    /*
     * Second try
     */
    @CaseRunner
    public String alienOrder2nd(String[] words) {

        if (words == null || words.length == 0)
            return null;

        Map<Character, Set<Character>> outbound = new HashMap<>();
        Map<Character, Integer> inbound = new HashMap<>();

        for (String w : words) {
            for (Character c : w.toCharArray())
                inbound.put(c, 0);
        }

        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            int idx = 0;
            while (idx < words[i].length()
                    && idx < prev.length()
                    && prev.charAt(idx) == words[i].charAt(idx))
                idx++;

            if (idx == words[i].length() || idx == prev.length()) {
                if (words[i].length() > prev.length())
                    prev = words[i];
                continue;
            }
            Character from = prev.charAt(idx);
            Character to = words[i].charAt(idx);
            if (outbound.containsKey(from))
                outbound.get(from).add(to);
            else {
                Set<Character> toSet = new HashSet<>();
                toSet.add(to);
                outbound.put(from, toSet);
            }
            inbound.put(to, inbound.get(to) + 1);
            prev = words[i];
        }

        PriorityQueue<Character> q = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> e : inbound.entrySet()) {
            if (e.getValue() == 0) {
                q.offer(e.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Character c = q.poll();
            sb.append(c);
            Set<Character> toSet = outbound.get(c);
            if (toSet != null) {
                for (Character to : toSet) {
                    int inDegree = inbound.get(to) - 1;
                    if (inDegree == 0)
                        q.offer(to);
                    inbound.put(to, inDegree);
                }
            }
        }

        if (inbound.size() != sb.length())
            return "";

        return sb.toString();

    }

    @CaseRunner
    public String alienOrder(String[] words) {

        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // 为每个字符建立入度表 ，初始化0
        for (String w : words) {
            for (char c : w.toCharArray())
                inDegree.put(c, 0);
        }

        // 循环所有的单词
        for (int i = 0; i < words.length - 1; i++) {

            char[] w1 = words[i].toCharArray();
            char[] w2 = words[i + 1].toCharArray();
            int len = Math.min(w1.length, w2.length);

            // 前一个单词和后一个比较，一个个字符比较直到有不同的（我们可以知道这个不同的字符的顺序）.
            for (int j = 0; j < len; j++) {
                if (w1[j] != w2[j]) {

                    // 建立第一个字符到其他字符的边， 如果这个组合不存在，后一个字符的入度+1 （应为第一个字符指向他）
                    Set<Character> s = map.getOrDefault(w1[j], new HashSet<>());
                    if (!s.contains(w2[j])) {
                        s.add(w2[j]);
                        map.put(w1[j], s);
                        inDegree.put(w2[j], inDegree.get(w2[j]) + 1);
                    }
                    // 看到不同后就停止，因为后面字符不是必须按照顺序的
                    break;
                }
            }
        }

        // 对有向图进行BFS遍历，每个节点，加到结果
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> e : inDegree.entrySet()) {
            if (e.getValue() == 0)
                queue.offer(e.getKey());
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {

            char c = queue.poll();
            sb.append(c);

            Set<Character> nexts = map.get(c);
            if (nexts != null) {
                for (char n : nexts) {
                    int inDe = inDegree.get(n) - 1;
                    inDegree.put(n, inDe);
                    if (inDe == 0)
                        queue.offer(n);
                }
            }
        }
        // 如果长度不匹配意味着有向图不能遍历所有节点，这个排序是无效的
        if (sb.length() != inDegree.size())
            return "";
        return sb.toString();
    }
}
