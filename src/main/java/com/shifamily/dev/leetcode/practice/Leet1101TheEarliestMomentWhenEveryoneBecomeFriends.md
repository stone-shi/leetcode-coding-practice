# Leetcode #1101 The Earliest Moment When Everyone Become Friends

## 原题

1101 The Earliest Moment When Everyone Become Friends
**<span style="color:blue">Medium</span>**

In a social group, there are N people, with unique integer ids from 0 to N-1.

We have a list of logs, where each `logs[i] = [timestamp, id_A, id_B]` contains a non-negative integer timestamp, and the ids of two different people.

Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.

Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.

Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.

**Example 1:**

> `Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6`
`Output: 20190301`
**Explanation:**
The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friend anything happens.
The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 
**Note:**

* `2 <= N <= 100`
* <code>1 <= logs.length <= 10^4^</code>
* <code>0 <= logs[i][0] <= 10^9^ </code>
* `0 <= logs[i][1], logs[i][2] <= N - 1`
* It's guaranteed that all timestamps in `logs[i][0]` are different.
* logs are not necessarily ordered by some criteria.
* `logs[i][1] != logs[i][2]`

## 解法

用Union Find 直到 联通分量 = 1

[Union find](https://zhuanlan.zhihu.com/p/98406740)

## 复杂度

## 代码

```Java
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UF uf = new UF(N);
        for (int[] log: logs){
            int sz = uf.union(log[1], log[2]);
            if (sz == 1)
                return log[0];
        }
        return -1;
    }

    static public class UF {
        private int[] parents;
        private int count;
        public UF(int n){
            parents = new int[n];
            for (int i = 0; i < n; i++) 
                parents[i] = i;
            count = n;
        }

        public int getCount(){
            return count;
        }

        public int union(int a, int b){
            int[] pa = find(a);
            int[] pb = find(b);

            if (pa[0] != pb[0]){
                if (pa[1] < pb[1])
                    parents[pa[0]] = pb[0];
                else
                    parents[pb[0]] = pa[0];
                count--;
            }

            return count;

        }

        private int[] find(int a){
            int size = 1;
            while (parents[a] != a){
                a = parents[a];
                size++;
            }
            return new int[]{a, size};
        }
    }
```