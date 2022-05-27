# Leetcode #418. Sentence Screen Fitting

## 原题

418 Sentence Screen Fitting

Given a `rows x cols` screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

- A word cannot be split into two lines.
- The order of words in the sentence must remain unchanged.
- Two consecutive words in a line must be separated by a single space.
- Total words in the sentence won’t exceed 100.
- Length of each word is greater than 0 and won’t exceed 10.
- `1 ≤ rows, cols ≤ 20,000.`

**Example 1:**

> `Input: rows = 2, cols = 8, sentence = ["hello", "world"]`
`Output: 1`
Explanation:
hello---
world---
The character '-' signifies an empty space on the screen.

**Example 2:**

> `Input:rows = 3, cols = 6, sentence = ["a", "bcd", "e"]`
`Output: 2`
Explanation:
a-bcd- 
e-a---
bcd-e-
The character '-' signifies an empty space on the screen.

**Example 3:**

> `Input:rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]`
`Output: 1`
Explanation:
I-had
apple
pie-I
had--
The character '-' signifies an empty space on the screen.

## 解法

从一个start index开始fit全部的字符串，如果能fit，那么就返回前进行数。
这个start和前进行数可以记录起来。


## 复杂度


## 代码


```Java
   public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] l = new int[sentence.length];
        for (int i = 0; i < l.length; i++) {
            l[i] = sentence[i].length();
            if (l[i] > cols)
                return 0;
        }
        Map<Integer, int[]> cache = new HashMap<>();
        int count = 0;
        int row = 0;
        int startCol = 0;
        int[] r;
        while (true) {
            if (cache.containsKey(startCol))
                r = cache.get(startCol);
            else
                r = fit(l, cols, startCol, cache);
            if (row + r[0] >= rows)
                return count;
            row += r[0];
            startCol = r[1];
            count++;
        }
    }

    private int[] fit(int[] l, int cols, int startCol, Map<Integer, int[]> cache) {
        int i = 0;
        int colCount = 0;
        int colIndex = startCol;
        while (i < l.length) {
            if (l[i] + colIndex <= cols) {
                colIndex += l[i] + 1;
                i++;
            } else {
                colCount++;
                colIndex = 0;
            }
        }
        int[] r = new int[2];
        r[0] = colCount;
        r[1] = colIndex;
        cache.put(startCol, r);
        return r;
    }

```
