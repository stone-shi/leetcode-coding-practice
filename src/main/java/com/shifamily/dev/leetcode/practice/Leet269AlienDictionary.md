# 原题

Leetcode 269

269 Alien Dictionary
Hard

849

174

Favorite

Share
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
# 解法

构建有向图。任何能找出顺序的字符组，组成第一个字符到第二个的有向图边 比如 Example 1
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

t->f  
w->e  
r->t  
e->r  

方法：
循环字符串数组，第一个字符串和后一个比较。就像所有的比较，从第一个字符往后，直到有一个不同，在前的字符小于在后的，找到后break，后面字符未必有序。
wrt wrf 我们可以看到w, r一样，t，f不同，所以 t->f边

保存边在一个map里，key是这个字符，value是一个set。
同时建立一个入度map，key是第二个字符（因为是入度）, value是入度的值。如果多个字符指向这个字符，入度可以是 > 1

然后进行有向图的BFS遍历：

从入度0开始，把这些放入queue。
然后出queue，找到这个字符指向的所有顶点，那些顶点的入度-1
如果那些顶点入度减一后是0，入queue
直到queue是空。

每个出queue的字符都要放到结果集。

结果集和入度map的size比较。不一样的就返回空。因为顶点没有全部遍历到或者有循环指向。意味着这个顺序是无解的。



## 复杂度
时间复杂度 
空间复杂度 


## 代码
```Java
   public String alienOrder(String[] words) {

        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        //为每个字符建立入度表 ，初始化0
        for (String w : words) {
            for (char c: w.toCharArray())
                inDegree.put(c, 0);
        }

        //循环所有的单词
        for (int i = 0; i < words.length - 1; i++) {
            
            char[] w1 = words[i].toCharArray();
            char[] w2 = words[i + 1].toCharArray();
            int len = Math.min(w1.length, w2.length);

            //前一个单词和后一个比较，一个个字符比较直到有不同的（我们可以知道这个不同的字符的顺序）.
            for (int j = 0; j < len; j++) {
                if (w1[j] != w2[j]){

                    //建立第一个字符到其他字符的边， 如果这个组合不存在，后一个字符的入度+1 （应为第一个字符指向他）
                    Set<Character> s = map.getOrDefault(w1[j], new HashSet<>());
                    if (!s.contains(w2[j])) {
                        s.add(w2[j]);
                        map.put(w1[j], s);
                        inDegree.put(w2[j], inDegree.get(w2[j]) + 1);
                    }
                    //看到不同后就停止，因为后面字符不是必须按照顺序的
                    break;
                }
            }
        }

        //对有向图进行BFS遍历，每个节点，加到结果
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> e: inDegree.entrySet()){
            if (e.getValue() == 0)
                queue.offer(e.getKey());
        }
        StringBuilder sb = new StringBuilder();
        while (! queue.isEmpty()){

            char c = queue.poll();
            sb.append(c);

            Set<Character> nexts = map.get(c);
            if (nexts !=  null) {
                for (char n : nexts) {
                    int inDe = inDegree.get(n) - 1;
                    inDegree.put(n, inDe);
                    if (inDe == 0)
                        queue.offer(n);
                }
            }
        }
        //如果长度不匹配意味着有向图不能遍历所有节点，这个排序是无效的
        if (sb.length() != inDegree.size())
            return "";
        return sb.toString();
    }
```
