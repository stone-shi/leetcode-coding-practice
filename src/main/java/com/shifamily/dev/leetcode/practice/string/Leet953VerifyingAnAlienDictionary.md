# 原题

953. Verifying an Alien Dictionary
Easy

174

64

Favorite

Share
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.


# 解法
首先把oder的字符串映射到26个字符的int数组里。
比如order = "hlabcdefgijkmnopqrstuvwxyz"

int orderMap 里面：
orderMap['h' - 'a'] = 0 因为h是第一个。
orderMap['l' - 'a'] = 1 因为h是第二个。

这样我们就有了英文字母到alien order的映射。

然后检查字符串。只要当前一个和后一个比较。
两个字符串比较的时候，一个个字符比较：
如果字符一样，移动到下一个
如果字符不一样，查找orderMap，找出order值，如果前一个字符的Order值小于等于后一个，检查完毕，break，否则也检查完毕，返回false。

最后，处理字符串可以比较的不部分都一样，比如abc vs abcdefg。那前一个必须不长于后一个。否则返回false。

## 复杂度
时间复杂度 O(N * M)
空间复杂度 O(1)

## 代码
```Java
    public boolean isAlienSorted(String[] words, String order) {
        //建立order 到"真实"字符的顺序映射
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++)
            orderMap[ order.charAt(i) - 'a'] = i;

        //字符串和后一个比较，比较的时候只要一个个字符看，如果字符一样，就下一个，不一样就查表拿到"真实"的顺序进行比较。
        for (int i = 0; i < words.length - 1; i++) {

            int j = 0;
            boolean checked = false;
            while ( j < words[i].length() && j < words[i+1].length()){
                //如果字符一样，下一个
                if (words[i].charAt(j) == words[i+1].charAt(j)) {
                    j++;
                    continue;
                }
                if ( orderMap[ words[i].charAt(j) - 'a'] >  orderMap[ words[i+1].charAt(j) - 'a']  )
                    return false;
                else {
                    checked = true;
                    break;
                }
            }
            //这个检查的是，如果字符都一样，较短的字符串必须在前，否则就是不对。
            if (! checked && words[i].length() > words[i+1].length())
                return false;

        }
        return true;

    }

```
