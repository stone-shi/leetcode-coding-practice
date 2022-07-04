# Leetcode #1882. Process Tasks Using Servers

## 原题

[1882 Process Tasks Using Servers](https://leetcode.com/problems/process-tasks-using-servers/)

**<span style="color:blue">Medium</span>** 620 176

<p>You are given two <strong>0-indexed</strong> integer arrays <code>servers</code> and <code>tasks</code> of lengths <code>n</code>​​​​​​ and <code>m</code>​​​​​​ respectively. <code>servers[i]</code> is the <strong>weight</strong> of the <code>i<sup>​​​​​​th</sup></code>​​​​ server, and <code>tasks[j]</code> is the <strong>time needed</strong> to process the <code>j<sup>​​​​​​th</sup></code>​​​​ task <strong>in seconds</strong>.</p>

<p>Tasks are assigned to the servers using a <strong>task queue</strong>. Initially, all servers are free, and the queue is <strong>empty</strong>.</p>

<p>At second <code>j</code>, the <code>j<sup>th</sup></code> task is <strong>inserted</strong> into the queue (starting with the <code>0<sup>th</sup></code> task being inserted at second <code>0</code>). As long as there are free servers and the queue is not empty, the task in the front of the queue will be assigned to a free server with the <strong>smallest weight</strong>, and in case of a tie, it is assigned to a free server with the <strong>smallest index</strong>.</p>

<p>If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately assign the next task. If multiple servers become free at the same time, then multiple tasks from the queue will be assigned <strong>in order of insertion</strong> following the weight and index priorities above.</p>

<p>A server that is assigned task <code>j</code> at second <code>t</code> will be free again at second <code>t + tasks[j]</code>.</p>

<p>Build an array <code>ans</code>​​​​ of length <code>m</code>, where <code>ans[j]</code> is the <strong>index</strong> of the server the <code>j<sup>​​​​​​th</sup></code> task will be assigned to.</p>

<p>Return <em>the array </em><code>ans</code>​​​​.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> servers = [3,3,2], tasks = [1,2,3,2,1,2]
<strong>Output:</strong> [2,2,0,2,1,2]
<strong>Explanation: </strong>Events in chronological order go as follows:
- At second 0, task 0 is added and processed using server 2 until second 1.
- At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
- At second 2, task 2 is added and processed using server 0 until second 5.
- At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
- At second 4, task 4 is added and processed using server 1 until second 5.
- At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
<strong>Output:</strong> [1,4,1,4,1,3,2]
<strong>Explanation: </strong>Events in chronological order go as follows: 
- At second 0, task 0 is added and processed using server 1 until second 2.
- At second 1, task 1 is added and processed using server 4 until second 2.
- At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4. 
- At second 3, task 3 is added and processed using server 4 until second 7.
- At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9. 
- At second 5, task 5 is added and processed using server 3 until second 7.
- At second 6, task 6 is added and processed using server 2 until second 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
        <li><code>servers.length == n</code></li>
        <li><code>tasks.length == m</code></li>
        <li><code>1 &lt;= n, m &lt;= 2 * 10<sup>5</sup></code></li>
        <li><code>1 &lt;= servers[i], tasks[j] &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

## 解法

两个 prioritie queue (heap)，一个是available server, 一个是working servers
对于每个任务，先检查working servers 中完成的，放入available servers。
如果有 available server， 拿出来处理任务 （时间是 i + tasks[i])，然后放入working queue
如果没有 available servers，拿出最早可能就绪的working server,(时间是 这个working server的 s[2] + tasks[i])，然后放回working queue

## 复杂度

O(N Log N) - 放入 avaiable queue
O(M Log N) - Working queue

## 代码

```Java
   @CaseRunner
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        int[] res = new int[m];

        // int[] 0 - weight, 1 - index, 2 - available time
        // if available, we ignore time (no longer valid), just consider weight and index
        PriorityQueue<int[]> avaliableQueue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // if still working, we sort by time, then weight, index
        PriorityQueue<int[]> workingQueue = new PriorityQueue<>(
                (a, b) -> a[2] != b[2] ? a[2] - b[2] : (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));

        // add to avaiable
        // O( n log n)
        for (int i = 0; i < n; i++)
            avaliableQueue.offer(new int[] { servers[i], i, 0 });

        // assiging task
        for (int i = 0; i < m; i++) {
            // check "done" in working queue
            while (!workingQueue.isEmpty() && workingQueue.peek()[2] <= i)
                avaliableQueue.offer(workingQueue.poll());

            // if have available, use it, time should be i + task[i] (ignore time in s[])
            if (!avaliableQueue.isEmpty()) {
                int[] s = avaliableQueue.poll();
                res[i] = s[1];
                s[2] = i + tasks[i];
                workingQueue.offer(s);
            } else { // if no available, we can get smallest in working queue, however, time should be s[2] (time of running task) + task[i]
                int[] s = workingQueue.poll();
                res[i] = s[1];
                s[2] += tasks[i];
                workingQueue.offer(s);
            }
        }
        return res;
    }


```