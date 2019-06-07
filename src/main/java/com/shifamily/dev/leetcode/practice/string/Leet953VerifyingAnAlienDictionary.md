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
