# Leetcode #792 Number of Matching Subsequences

## 原题

[792 Number of Matching Subsequences](https://leetcode.com/problems/number-of-matching-subsequences/)

**<span style="color:blue">Medium</span>** 2948 152

Given a string s and an array of strings words, return the number of `words[i]` that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".

**Example 1:**

> `Input: s = "abcde", words = ["a","bb","acd","ace"]`
`Output: 3`
**Explanation:** There are three strings in words that are a subsequence of s: "a", "acd", "ace".

**Example 2:**

> `Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]`
`Output: 2`
 
**Constraints:**

* `1 <= s.length <= 5 * 104`
* `1 <= words.length <= 5000`
* `1 <= words[i].length <= 50`
* `s and words[i] consist of only lowercase English letters.`


## 解法

- 把所有的word放到首字母开头的array里。
- 扫描s, 每一个字符就找array里那个words的list，代表有这些word等待，去掉第一个字符，然后放到下一个等待array
oExplanation:
I go through S once, and while I'm doing that, I move through all words accordingly. That is, I keep track of how much of each word I've already seen, and with each letter of S, I advance the words waiting for that letter. To quickly find the words waiting for a certain letter, I store each word (and its progress) in a list of words waiting for that letter. Then for each of the lucky words whose current letter just occurred in S, I update their progress and store them in the list for their next letter.

Let's go through the given example:

S = "abcde"
words = ["a", "bb", "acd", "ace"]
I store that "a", "acd" and "ace" are waiting for an 'a' and "bb" is waiting for a 'b' (using parentheses to show how far I am in each word):

'a':  ["(a)", "(a)cd", "(a)ce"]
'b':  ["(b)b"]
Then I go through S. First I see 'a', so I take the list of words waiting for 'a' and store them as waiting under their next letter:

'b':  ["(b)b"]
'c':  ["a(c)d", "a(c)e"]
None: ["a"]
You see "a" is already waiting for nothing anymore, while "acd" and "ace" are now waiting for 'c'. Next I see 'b' and update accordingly:

'b':  ["b(b)"]
'c':  ["a(c)d", "a(c)e"]
None: ["a"]
Then 'c':

'b':  ["b(b)"]
'd':  ["ac(d)"]
'e':  ["ac(e)"]
None: ["a"]
Then 'd':

'b':  ["b(b)"]
'e':  ["ac(e)"]
None: ["a", "acd"]
Then 'e':

'b':  ["b(b)"]
None: ["a", "acd", "ace"]
And now I just return how many words aren't waiting for anything anymore

Explanation:
I go through S once, and while I'm doing that, I move through all words accordingly. That is, I keep track of how much of each word I've already seen, and with each letter of S, I advance the words waiting for that letter. To quickly find the words waiting for a certain letter, I store each word (and its progress) in a list of words waiting for that letter. Then for each of the lucky words whose current letter just occurred in S, I update their progress and store them in the list for their next letter.

Let's go through the given example:

S = "abcde"
words = ["a", "bb", "acd", "ace"]
I store that "a", "acd" and "ace" are waiting for an 'a' and "bb" is waiting for a 'b' (using parentheses to show how far I am in each word):

'a':  ["(a)", "(a)cd", "(a)ce"]
'b':  ["(b)b"]
Then I go through S. First I see 'a', so I take the list of words waiting for 'a' and store them as waiting under their next letter:

'b':  ["(b)b"]
'c':  ["a(c)d", "a(c)e"]
None: ["a"]
You see "a" is already waiting for nothing anymore, while "acd" and "ace" are now waiting for 'c'. Next I see 'b' and update accordingly:

'b':  ["b(b)"]
'c':  ["a(c)d", "a(c)e"]
None: ["a"]
Then 'c':

'b':  ["b(b)"]
'd':  ["ac(d)"]
'e':  ["ac(e)"]
None: ["a"]
Then 'd':

'b':  ["b(b)"]
'e':  ["ac(e)"]
None: ["a", "acd"]
Then 'e':

'b':  ["b(b)"]
None: ["a", "acd", "ace"]
And now I just return how many words aren't waiting for anything anymore.



## 复杂度


## 代码


```Java
   public int numMatchingSubseq2(String s, String[] words) {
        List<StringBuilder>[] waitingList = new List[26];
        for (int i = 0; i < 26; i++) {
           waitingList[i] = new LinkedList<>(); 
        }
        for (String w : words) {
            waitingList[w.charAt(0) - 'a'].add(new StringBuilder(w));
        }
        char[] ca = s.toCharArray();
        int res = 0;
        for (char c : ca) {
            int idx = c - 'a';
            List<StringBuilder> sbs =  waitingList[idx];
            waitingList[idx] = new LinkedList<>();
            for (StringBuilder sb: sbs){
                sb.deleteCharAt(0);
                if (sb.length() > 0){
                    waitingList[sb.charAt(0) - 'a'].add(sb);
                }else{
                    res++;
                }
            }
        }
        return res;
    }

```
