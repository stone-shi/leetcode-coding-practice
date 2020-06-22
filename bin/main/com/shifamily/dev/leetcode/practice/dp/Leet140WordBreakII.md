# 原题
140 Word Break II
Hard

1664

353

Add to List

Share
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
# 解法

DFS + memorized string (using hash map)


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public List<String> wordBreak(String s, List<String> wordDict) {

        List<String> ret = dfs(s, new HashSet<>(wordDict), new HashMap<>());
        if (ret == null){
            ret = new LinkedList<>();
        }

        return ret;


    }



    private List<String> dfs(String s, Set<String> dict, Map<String, List<String> > cache){

        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        List<String> res = new LinkedList<>();

        if (s.length() == 0){
            res.add("");
            return res;
        }

        if (dict.contains(s))
            res.add(s);

        for (int i = 1; i < s.length(); i++) {
            String w = s.substring(i);
            if (dict.contains(w)){
                List<String> rs = dfs( s.substring(0, i), dict, cache);
                if (rs.size() > 0){
                    for (String sub : rs) {
                        if (sub.length() > 0)
                            res.add( sub + " " + w);
                        else
                            res.add(w);
                    }
                    cache.put(s, res);
                }
            }
        }
        cache.put(s, res);
        return res;
    }


```
