# 原题

767 Reorganize String
Medium

1334

64

Add to List

Share
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 

Accepted
71,611
Submissions
150,090
Seen this question in a real interview before?

Yes

No
Contributor
ti9yowl
Rearrange String k Distance Apart
Hard
Task Scheduler
Medium
Alternate placing the most common letters.


# 解法
统计每个字母出现数字，然后对数字进行排序，从大到小。 最大出现次数拿出来循环，然后一个各一个加入第二，第三大字母。
比如： aab，a:2， b:1
放入优先队列，取出最大：a:2
然后填充第二大：b:1
得到 aba
容易理解，如果第二大弄完了还没有填充完，pq里再取，如果pq空了，就返回失败。如果第二大还有剩余，放回pq。



## 复杂度
时间复杂度 O(N) : O(N KLogK) 其中K是字母数（因为要对字母的计数排序），因为字母数是常数，所以就是 O(N)
空间复杂度 O(1)


## 代码
```Java

```
