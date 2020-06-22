# 原题
139 Word Break
Medium

3912

211

Add to List

Share
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
Accepted
517K
Submissions
1.3M
# 解法
可以用DFS或者BFS，都一样，加mem数组记住是否访问过（bfs）或者这个位置是否有解（dfs）

也可以用dp来做。
dp[i] 代表 第i个结尾的子串是否有解。
第一个循环计算从1开始到length的dp。i=1；i<=length
第二个循环计算[0,i)的中能否分成2个部分，分别有解， s1= [0, j) s2 = [j , i)
s1其实就是dp[j]
如果s2在字典中，那么dp[i]就是有解的。
最后返回dp[length]

## 复杂度
时间复杂度 O(N^2)
空间复杂度 O(N)


## 代码
```Java

    @CaseRunner
    public boolean wordBreakBfs(String s, List<String> wordDict) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] mem = new boolean[s.length()];

        while (!queue.isEmpty()){
            int start = queue.poll();
            if (!mem[start]) {
                for (int i = start + 1; i <= s.length(); i++) {
                    String sub = s.substring(start, i);
                    if (dict.contains(sub)) {
                        queue.add(i);
                        if (i == s.length())
                            return true;
                    }
                }
            }
            mem[start] = true;

        }

        return false;

    }


    @CaseRunner
    public boolean wordBreakDP(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> dict = new HashSet<>(wordDict);

        for (int i = 1; i <= s.length() ; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    @CaseRunner
    public boolean wordBreakDfs(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        int[] mem = new int[s.length()];
        return dfs(s, dict, 0, mem);

    }

    private boolean dfs(String s, Set<String> dict, int start, int[] mem){

        if (start == s.length())
            return true;
        if (mem[start] != 0)
            return mem[start] == 1;

        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (dict.contains(sub) && dfs(s, dict, i, mem)) {
                mem[start] = 1;
                return true;
            }
        }
        mem[start] = -1;
        return false;
    }
```
