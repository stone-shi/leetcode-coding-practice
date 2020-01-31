# 原题
57 Insert Interval
Hard

1232

143

Add to List

Share
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
# 解法
第一个循环把 i.end < new.start的加到result
第二个循环merge，找所有 i.start <= new.end的，连同new，start取最小值，end取最大值
第三个循环把剩下的加入



## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new LinkedList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start ){
            result.add(intervals.get(i));
            i++;
        }
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newStart = Math.min(intervals.get(i).start, newStart);
            newEnd = Math.max(intervals.get(i).end, newEnd);
            i++;
        }
        result.add(new Interval(newStart, newEnd));

        while (i < intervals.size())
            result.add(intervals.get(i++));


        return result;


    }

```
