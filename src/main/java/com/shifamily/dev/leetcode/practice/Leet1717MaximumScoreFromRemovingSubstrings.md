# Leetcode #1717 Maximum Score From Removing Substrings

## 原题

[1717 Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings/description/)
**<span style="color:blue">Medium</span>**

You are given a string s and two integers x and y. You can perform two types of operations any number of times.

- Remove substring "ab" and gain x points.
  - For example, when removing "ab" from "cabxbae" it becomes "cxbae".
- Remove substring "ba" and gain y points.
  - For example, when removing "ba" from "cabxbae" it becomes "cabxe".

Return the maximum points you can gain after applying the above operations on s.

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cdbcbbaaabab&quot;, x = 4, y = 5
<strong>Output:</strong> 19
<strong>Explanation:</strong>
- Remove the &quot;ba&quot; underlined in &quot;cdbcbbaaa<u>ba</u>b&quot;. Now, s = &quot;cdbcbbaaab&quot; and 5 points are added to the score.
- Remove the &quot;ab&quot; underlined in &quot;cdbcbbaa<u>ab</u>&quot;. Now, s = &quot;cdbcbbaa&quot; and 4 points are added to the score.
- Remove the &quot;ba&quot; underlined in &quot;cdbcb<u>ba</u>a&quot;. Now, s = &quot;cdbcba&quot; and 5 points are added to the score.
- Remove the &quot;ba&quot; underlined in &quot;cdbc<u>ba</u>&quot;. Now, s = &quot;cdbc&quot; and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbaaxybbaabb&quot;, x = 5, y = 4
<strong>Output:</strong> 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
        <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
        <li><code>1 &lt;= x, y &lt;= 10<sup>4</sup></code></li>
        <li><code>s</code> consists of lowercase English letters.</li>
</ul>
## 解法

## 复杂度

## 代码

```Java
    public int maximumGain2(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        if (x > y)
            return remove(sb, x, "ab") + remove(sb, y, "ba");
        else
            return remove(sb, y, "ba") + remove(sb, x, "ab");
    }

    private int remove(StringBuilder s, int point, String p) {
        int i = 0;
        int n = s.length();
        int res = 0;
        for (int j = 0; j < n; j++) {
            s.setCharAt(i, s.charAt(j));
            if (i >= 1 &&  s.charAt(i - 1) == p.charAt(0) && s.charAt(i) == p.charAt(1)) {
                res += point;
                i -= 2;
            }
            i++;
        }
        s.setLength(i);
        return res;
    }

       // this is a brutal force + memory dfs solution and will cause TLE
    @CaseRunner
    public int maximumGain(String s, int x, int y) {
        Map<String, Integer> mem = new HashMap<>();
        String[] keys = new String[] { "ab", "ba" };
        int[] points = new int[] { x, y };
        int res = dfs(s, keys, points, mem);
        return res;
    }

    private int dfs(String s, String[] keys, int[] points, Map<String, Integer> mem) {
        if (mem.containsKey(s))
            return mem.get(s);

        int res = 0;
        for (int i = 0; i < keys.length; i++) {
            int idx = s.indexOf(keys[i]);
            if (idx == -1)
                continue;
            res = Math.max(res,
                    points[i] + dfs(s.substring(0, idx) + s.substring(idx + keys[i].length()), keys, points, mem));
        }
        mem.put(s, res);
        return res;
    }

```