package com.shifamily.dev.leetcode.practice.graph;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.*;

/*
269. Alien Dictionary
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
 */
public class Leet269AlienDictionary extends BasicStudy {

    public Leet269AlienDictionary() {
        String[][] casesP1 = {  {"z", "x"}, {"wrt", "wrf", "er", "ett", "rftt"},  { "z", "x", "z"}};
        String[] answers = {  "zx", "wertf", ""};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = casesP1[i];
            addParameterAndAnswer(p, answers[i], false);
        }
    }

    @CaseRunner
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
}
