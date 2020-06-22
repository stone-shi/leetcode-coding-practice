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

## 复杂度
时间复杂度 O(NLgN) 排序
空间复杂度 O(N)


## 代码
```Java
   public int minMeetingRooms(int[][] intervals) {

        if (intervals == null|| intervals.length == 0)
            return 0;

        //对会议开始的时间排序，会议总是要从先开始的开始处理的，这个是 O（NlgN)
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        //mimi heap
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0][1]);  //放入第一个会议的结束时间
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= queue.peek()) //检查当前需要安排的会议，是否有已经进行的会议结束了
                queue.poll(); //结束的会议出queue
            queue.offer(intervals[i][1]); //开始这个会议
        }
        return queue.size(); //所有的会议安排完了，看看有多少个同时进行的

    }
```
