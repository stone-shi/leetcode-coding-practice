# 原题

253. Meeting Rooms II
Medium

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.


# 解法

先对会议进行按照开始时间排序，现实生活中处理还是按照会议先开始时间处理的。

然后建立一个mini heap (java的PriorityQueue)，把终止时间放进去。

然后遍历会议（从第二个开始，应为第一个已经在Queue里了）。检查这个会议的开始时间是否晚于或同时于 heap里面最小的时间。如果是的，意味着已经有结束的会议了。把这个会议出queue。

然后把新的会议结束时间放到queue里。

最后检查这个queue的size就可以得出最少需要多少个会议室了。