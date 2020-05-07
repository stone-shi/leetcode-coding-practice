# 原题
56 Merge Intervals
Medium

3650

266

Add to List

Share
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
# 解法

对start先排序
Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
排序完成后，判断如果 current end > inteval[i].start, 就是有重叠。然后更新 curr的end值，取 curr 和 i 的end大者。
如果没有重叠，把curr加到结果，然后更新curr指针。 

## 复杂度
时间复杂度 O(NLogN)
空间复杂度 O(1)


## 代码
```Java
   public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new LinkedList<>();

        int[] curr = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (curr[1] >= intervals[i][0]) {//overlap
                curr[0] = Math.min(curr[0], intervals[i][0]);
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }else{
                res.add(curr);
                curr = intervals[i];
            }
        }
        res.add(curr);

        int[][] ret = new int[res.size()][];
        return res.toArray(ret);
    }

```
