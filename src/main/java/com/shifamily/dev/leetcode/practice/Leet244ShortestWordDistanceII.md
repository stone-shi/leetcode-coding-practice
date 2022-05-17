# 原题
244 Shortest Word Distance II
Medium

295

101

Add to List

Share
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
# 解法
先建立word到所在位置的map，所在位置有多个，所以是个int list

分别取出word1, word2的位置列表，得到两个位置的排序的数组。

dis = abs(wi1[i] - wi2[j])
如果 wi1[i] < wi2[j]那么 i++，反之 j++，因为这是个有序的位置数组，然后i的位置小于J，那么i++才会有更优化结果。


## 复杂度
时间复杂度 O(N + M)
空间复杂度 O(N)


## 代码
```Java

    public static class WordDistance {

        Map<String, List<Integer>> listMap = new HashMap<>();

        public WordDistance(String[] words) {
            for (int i = 0; i <  words.length; i++) {

                if (listMap.containsKey(words[i]))
                    listMap.get(words[i]).add(i);
                else{
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    listMap.put(words[i], l);
                }
            }
        }

        public int shortest(String word1, String word2) {
            Integer[] index1 = listMap.get(word1).toArray(new Integer[0]);
            Integer[] index2 = listMap.get(word2).toArray(new Integer[0]);

            int i = 0, j = 0;
            int res = Integer.MAX_VALUE;
            while (i < index1.length && j < index2.length){
                int dis = Math.abs(index1[i] - index2[j]);
                if (dis < res)
                    res = dis;
                if (index1[i] < index2[j])
                    i++;
                else
                    j++;
            }
            return res;
        }
    }
```
