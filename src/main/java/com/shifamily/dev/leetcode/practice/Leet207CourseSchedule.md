
# 原题

207 Course Schedule
Medium

3626

172

Add to List

Share
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
Accepted
401,639
Submissions
945,564
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Microsoft
|
11

Amazon
|
11

Facebook
|
9

Oracle
|
3

ByteDance
|
3

Google
|
2

Apple
|
2
Course Schedule II
Medium
Graph Valid Tree
Medium
Minimum Height Trees
Medium
Course Schedule III
Hard
Topological sort could also be done via BFS.


# 解法

Topologic sort 



## 复杂度
时间复杂度 O(∣E∣+∣V∣)
空间复杂度 O(∣E∣+∣V∣)


## 代码
```Java

        Map<Integer, Set<Integer>> courses = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int[] pair : prerequisites){
            if (courses.containsKey(pair[1]))
                courses.get(pair[1]).add(pair[0]);
            else{
                Set<Integer> set = new HashSet<>();
                set.add(pair[0]);
                courses.put(pair[1], set);
            }
            inDegree.put(pair[0], inDegree.getOrDefault(pair[0], 0) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!inDegree.containsKey(i))
                queue.offer(i);
        }
        Set<Integer> res = new HashSet<>();
        while (!queue.isEmpty()){
            int c = queue.poll();
            res.add(c);
            Set<Integer> out = courses.get(c);
            if (out != null) {
                for (Integer co : out) {
                    Integer ct = inDegree.get(co);
                    if (ct == 1) {
                        queue.offer(co);
                    }
                    inDegree.put(co, ct - 1);
                }
            }
        }
        return res.size() == numCourses;

```
