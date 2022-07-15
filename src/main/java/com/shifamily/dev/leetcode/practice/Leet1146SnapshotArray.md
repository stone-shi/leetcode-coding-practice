# Leetcode #1146 Snapshot Array

## 原题
**<span style="color:blue">Medium</span>**

[1146 Snapshot Array](https://leetcode.com/problems/snapshot-array/)

Implement a SnapshotArray that supports the following interface:

* `SnapshotArray(int length)` initializes an array-like data structure with the given length.  Initially, each element equals 0.
* void `set(index, val)` sets the element at the given index to be equal to val.
* `int snap()` takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
* `int get(index, snap_id)` returns the value at the given index, at the time we took the snapshot with the given snap_id
 
**Example 1:**

> `Input: ["SnapshotArray","set","snap","set","get"]`
`[[3],[0,5],[],[0,6],[0,0]]`
`Output: [null,null,0,null,5]`
**Explanation:**
`SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3`
`snapshotArr.set(0,5);  // Set array[0] = 5`
`snapshotArr.snap();  // Take a snapshot, return snap_id = 0`
`snapshotArr.set(0,6);`
`snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5`
 
**Constraints:**

* `1 <= length <= 50000`
* At most 50000 calls will be made to set, snap, and get.
* `0 <= index < length`
* `0 <= snap_id < (the total number of times we call snap())`
* `0 <= val <= 10^9`

## 解法

每个element可以是一个TreeMap，保留存入时候的版本号，然后用floorEntry()取得 等于或小于版本号的 值。也可以初始化 (0,0)，否则要检查floorEntry为空

## 复杂度

get O(log N) - floorEntry是binery search

## 代码

```Java
   static public class SnapshotArray {
        int currentVersion = 0;
        Map<Integer, TreeMap<Integer, Integer>> data = new HashMap<>();
        int length;

        public SnapshotArray(Integer length) {
            this.length = length;
        }

        public void set(int index, int val) {
            TreeMap<Integer, Integer> item = data.getOrDefault(index, new TreeMap<>());
            item.put(currentVersion, val);
            data.put(index, item);
        }

        public int snap() {
            return currentVersion++;
        }

        public int get(int index, int snap_id) {
            if (!data.containsKey(index))
                return 0;

            TreeMap<Integer, Integer> item = data.get(index);
            Entry<Integer, Integer> e = item.floorEntry(snap_id);

            if (e == null)
                return 0;
            else
                return e.getValue();
        }
    }
```