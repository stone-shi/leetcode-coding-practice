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



