# Leetcode #2277 Closest Leaf in a Binary Tree LeetCode

## 原题

2277 Closest Node to Path in Tree

**<span style="color:red">Hard</span>**

You are given a postive integer n representing the number of nodes in a tree, numbered from 0 to n -1 (inclusive). You are also given a 2D integer array edges of length n -1, where <code>edges[i] = [node1~i~, node~2~]</code> denotes that there is a bidirectional edge connecting node1~i~ and node2~i~ in the tree.

You have give a 0-indexed integer array query of length m where <code>query[i] = [start~i~, end~i~, node~i~]</code> means that for the i^th^ query, you are tasked with finding the node on the path from start~i~ to end~i~ that is cloest to node~i~.

Return an integer array answer of length m, where answer[i] is the answer to the i^th^ query.

**Example 1**

![](https://cloud.shifamily.com/index.php/s/DC4EbU7dr3ETYM0/download?path=%2Fimages&files=2277-1.png)

> `Input: n = 7, edges = [[0,1], [0,2], [0,3], [1,4], [2,5], [2,6]], query = [[5,3,4], [5,3,6]]`
`Output: [0,2]`
**Explanation:**
The path from node 5 to node 3 consists of the nodes 5, 2, 0 and 3.
The distance between node 4 and node 0 is 2.
Node 0 is the node in the path closest to node 4, so the answer to the first query is 0.
The distance between node 6 and node 2 is 1.

**Example 2**

![](https://cloud.shifamily.com/index.php/s/DC4EbU7dr3ETYM0/download?path=%2Fimages&files=2277-2.png)

> `Input: n = 3, edge = [[0,1], [1,2]], query  [[0,1,2]]`
> `Output: [1]`

## 解法

无向图最短路径，BFS。
建立adj map，所有edge **双向** 计入map。
从 start找到end，记录路径。
从 start找到 node (query[2])，记录路径
找到分岔口。

## 复杂度

O(V + E) - V vertical, E edge count

## 代码

```Java
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

        Map<int[], List<Integer>> mem = new HashMap<>();
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

    private List<Integer> bfs(Map<Integer, List<Integer>> nodeMap, int start, int end, Map<int[], List<Integer>> mem) {
        if (mem.containsKey(new int[] { start, end }))
            return mem.get(new int[] { start, end });

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
                if (node == end)
                    return path;
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

```