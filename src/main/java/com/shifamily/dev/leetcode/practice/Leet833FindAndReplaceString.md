# Leetcode #833 Find And Replace in String

## 原题

[833 Find And Replace in String](https://leetcode.com/problems/find-and-replace-in-string/)

**<span style="color:blue">Medium</span>**

You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.

To complete the i^th^ replacement operation:

* Check if the substring sources[i] occurs at index indices[i] in the original string s.
* If it does not occur, do nothing.
* Otherwise if it does occur, replace that substring with targets[i].
* For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.

* For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.

Return the resulting string after performing all replacement operations on s.

A substring is a contiguous sequence of characters in a string.

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2021/06/12/833-ex1.png)

> `Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]`
`Output: "eeebffff"`
**Explanation:**
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".

**Example 2:**
![Example 1](https://assets.leetcode.com/uploads/2021/06/12/833-ex2-1.png)

> `Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]`
`Output: "eeecd"`
**Explanation:**
"ab" occurs at index 0 in s, so we replace it with "eee".
"ec" does not occur at index 2 in s, so we do nothing.

**Constraints:**

* `1 <= s.length <= 1000`
* `k == indices.length == sources.length == targets.length`
* `1 <= k <= 100`
* `0 <= indexes[i] < s.length`
* `1 <= sources[i].length, targets[i].length <= 50`
* `s consists of only lowercase English letters.`
* `sources[i] and targets[i] consist of only lowercase English letters.`

## 解法

对indexes排序，然后按照顺序比较和apply change

## 复杂度


## 代码


```Java
 // solution 1 - hashmap record change index, scan original s and get change
    // idicateor - a bit slow
    @CaseRunner
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, String[]> replaceMap = new HashMap<>();

        for (int i = 0; i < indices.length; i++) {
            if (indices[i] + sources[i].length() > s.length())
                continue;
            boolean match = true;
            for (int j = 0; j < sources[i].length(); j++) {
                if (s.charAt(indices[i] + j) != sources[i].charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match)
                replaceMap.put(indices[i], new String[] { sources[i], targets[i] });
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            String[] pair = replaceMap.get(i);
            if (pair == null)
                builder.append(s.charAt(i++));
            else {
                builder.append(pair[1]);
                i += pair[0].length();
            }
        }
        return builder.toString();
    }

    // solution 2 - sort the change indicator
    @CaseRunner
    public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < indices.length; i++) {
            pq.offer(new int[] { indices[i], i });
        }
        StringBuilder sb = new StringBuilder();
        int lastIdx = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int idx = pair[1];
            int pos = pair[0];
            sb.append(s.substring(lastIdx, pos));
            lastIdx = pos;
            if (pos + sources[idx].length() <= s.length()
                    && sources[idx].equals(s.substring(pos, pos + sources[idx].length()))) {
                sb.append(targets[idx]);
                lastIdx += sources[idx].length();
            }
        }
        sb.append(s.substring(lastIdx));
        return sb.toString();
    }
```
