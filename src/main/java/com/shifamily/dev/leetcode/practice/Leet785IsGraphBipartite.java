package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 785. Is Graph Bipartite?
 Medium

 1356

 152

 Add to List

 Share
 Given an undirected graph, return true if and only if it is bipartite.

 Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

 The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

 Example 1:
 Input: [[1,3], [0,2], [1,3], [0,2]]
 Output: true
 Explanation:
 The graph looks like this:
 0----1
 |    |
 |    |
 3----2
 We can divide the vertices into two groups: {0, 2} and {1, 3}.
 Example 2:
 Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 Output: false
 Explanation:
 The graph looks like this:
 0----1
 | \  |
 |  \ |
 3----2
 We cannot find a way to divide the set of nodes into two independent subsets.


 Note:

 graph will have length in range [1, 100].
 graph[i] will contain integers in range [0, graph.length - 1].
 graph[i] will not contain i or duplicate values.
 The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 Accepted
 104,427
 Submissions
 223,176
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 awice
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 18

 Amazon
 |
 5

 Walmart Labs
 |
 2

 eBay
 |
 2


 */
public class Leet785IsGraphBipartite extends BasicStudy {

    public Leet785IsGraphBipartite() {
        int[][][] casesP1 = {
                {{1,3}, {0,2}, {1,3}, {0,2}} ,
                {{1,2,3}, {0,2}, {0,1,3}, {0,2}}
        };
        boolean[] answers = { true, false};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = casesP1[i];
            addParameterAndAnswer(p, answers[i], false);
        }

    }

    @CaseRunner
    public boolean isBipartite(int[][] graph) {

        int len = graph.length;
        int[] nodeColor = new int[len];

        for (int i = 0; i < len; i++) {
            if (nodeColor[i] == 0 && !verifyNode(graph, i, nodeColor))
                return false;
        }
        return true;
    }
    private boolean verifyNode(int[][] graph, int node, int[] nodeColor){

        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        nodeColor[node] = 1;
        while (!q.isEmpty()){
            int n = q.poll();
            int[] children = graph[n];
            int color = nodeColor[n];
            for (int c : children) {
                if (nodeColor[c] == 0) { //uncolored
                    nodeColor[c] = -color;
                    q.offer(c);
                } else {
                    if (nodeColor[c] == color)
                        return false;
                }
            }
        }
        return true;
    }

}
