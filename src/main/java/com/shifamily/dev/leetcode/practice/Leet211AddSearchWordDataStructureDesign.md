# 原题
211. Add and Search Word - Data structure design
Medium

1606

81

Add to List

Share
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

Accepted
174,342
Submissions
487,487
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
30

Amazon
|
11

Google
|
2

Uber
|
2

Salesforce
|
2

eBay
|
2
Implement Trie (Prefix Tree)
Medium
Prefix and Suffix Search

# 解法

Trie + backtracking

## 复杂度
时间复杂度 O(M)
空间复杂度 O(M)


## 代码
```Java
   class WordDictionary {

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean end;
        }

        TrieNode root = new TrieNode();
        /** Initialize your data structure here. */
        public WordDictionary() {

        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - 'a'] == null )
                    curr.children[c - 'a'] = new TrieNode();
                curr = curr.children[c - 'a'];
            }
            curr.end = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {

            return helper(word, 0, root);

        }

        private boolean helper(String word, int start, TrieNode current){
            if (start == word.length())
                return current.end;
            for (int i = start; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c == '.') {
                    for (TrieNode node : current.children){
                        if (node != null && helper(word, i + 1, node))
                            return true;
                    }
                    return false;
                }else{
                    current = current.children[c - 'a'] ;
                    if (current == null)
                        return false;
                }
            }
            return current.end;

        }
    }
```
