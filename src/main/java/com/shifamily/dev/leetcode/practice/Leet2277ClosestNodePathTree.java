package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet2277ClosestNodePathTree extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(
                CaseParameters.builder()
                        .parameters(new Object[] { 7,
                                new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 } },
                                new int[][] { { 5, 3, 4 }, { 5, 3, 6 } } })
                        .answer(new int[] { 0, 2 })
                        .description("Example 1").build());
        cases.add(
                CaseParameters.builder()
                        .parameters(new Object[] { 7,
                                new int[][] { { 0, 1 }, { 1, 2 } },
                                new int[][] { { 0, 1, 2 } } })
                        .answer(new int[] { 0 })
                        .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> childrenNode = nodeMap.getOrDefault(edge[1], new LinkedList<>());
            childrenNode.add(edge[0]);
            nodeMap.put(edge[1], childrenNode);

            childrenNode = nodeMap.getOrDefault(edge[0], new LinkedList<>());
            childrenNode.add(edge[1]);
            nodeMap.put(edge[0], childrenNode);
        }

        Map<List<Integer>, List<Integer>> mem = new HashMap<>();
        int[] res = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int[] q = query[i];
            List<Integer> p1 = bfs(nodeMap, q[0], q[1], mem);
            List<Integer> p2 = bfs(nodeMap, q[0], q[2], mem);

            int k = 0;
            while (k < p1.size() && k < p2.size()) {
                if (p1.get(k) != p2.get(k) && k > 0) {
                    res[i] = p1.get(k - 1);
                    break;
                }
                k++;
            }
        }

        return res;
    }

    private List<Integer> bfs(Map<Integer, List<Integer>> nodeMap, int start, int end, Map<List<Integer>, List<Integer>> mem) {
        List<Integer> keyPair = Arrays.asList(start, end);
        if (mem.containsKey(keyPair))
            return mem.get(keyPair);

        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, List<Integer>> nodePath = new HashMap<>();
        q.offer(start);
        List<Integer> startPath = new LinkedList<>();
        startPath.add(start);
        nodePath.put(start, startPath);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                List<Integer> path = nodePath.get(node);
                if (node == end) {
                    mem.put(keyPair, path);
                    return path;
                }
                List<Integer> adjNodes = nodeMap.get(node);
                for (int adjNode : adjNodes) {

                    if (nodePath.containsKey(adjNode))
                        continue;
                    q.offer(adjNode);
                    List<Integer> adjPath = new LinkedList<>(path);
                    adjPath.add(adjNode);
                    nodePath.put(adjNode, adjPath);
                }
            }
        }
        return null;
    }

}
