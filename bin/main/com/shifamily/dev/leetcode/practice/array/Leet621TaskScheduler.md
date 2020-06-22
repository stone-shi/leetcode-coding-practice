# 原题


621. Task Scheduler
Medium

1636

277

Favorite

Share
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

# 解法

统计每个任务执行的次数，然后放入 PriorityQueue (max heap)   
上面例子队列如下：  
[3,3] 其实是 [A, B]    


然后按照 n + 1 循环拿出任务执行   
比如n = 2  
执行slot如下  
"---"    
拿出第一个任务A  
"A--"    
A进入等待列表  
拿出第二个任务B  
"AB-"
B进入等待列表  

这时候队列是空，但是等待列表不空，所以CPU轮空。

出循环后把等待队列的入queue

这个时候队列如下：  
[2, 2] 因为已经执行过一次了，实际代表 [A, B]

然后继续如上操作直到 queue和等待列表都是空。期间需要统计执行多少次包括轮空的。

## 复杂度
时间复杂度：O(n lgn)，主要是堆里面排序
空间复杂度：O(n)，需要保存到queue里

## 代码
```Java
    public int leastInterval(char[] tasks, int n) {

        //统计task出现次数
        int[] charCount = new int[26];
        for ( char c : tasks)
            charCount[c - 'A']++;

        // comparator 设成  Collections.reverseOrder() 是为了最大值在前 (max heap)
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        //把次数入queue，从最大次数开始排列，这里不记录是什么task，是因为题目不要求写出实际排列
        for (int ct : charCount) {
            if (ct > 0)
                queue.offer(ct);
        }
        //记录执行次数
        int time = 0;
        while (!queue.isEmpty()){
            //记录这个执行block里面的等待list
            List<Integer> waitingList = new LinkedList<>();
            for (int i = 0; i <= n ; i++) { //每个任务要等待 n
                if (!queue.isEmpty()) {
                    //提取一个任务执行，执行次数-1
                    int taskCt = queue.poll() - 1;
                    time++; //记录执行了这个任务
                    if (taskCt > 0) //如果这个任务还需要执行，放到等待list
                        waitingList.add(taskCt);
                }else{
                    //这里表示现有的执行队列里已经没有任务了，但是有等待列表，那CPU必须轮空（执行次数++)，如果等待列表都没了，说明执行完成了，CPU不需要轮空
                    if (!waitingList.isEmpty())
                        time++;
                }
            }
            //把执行列表里的数据放回执行队列
            for (Integer task: waitingList)
                queue.offer(task);
        }
        return time;
    }

```

