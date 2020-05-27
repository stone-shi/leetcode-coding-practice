package com.shifamily.dev.leetcode.practice.backtracking;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
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

 */
public class Leet211AddSearchWordDataStructureDesign extends BasicStudy {
    public Leet211AddSearchWordDataStructureDesign() {
        char [][] case1P1 = {
                {'a','a','a','a','s','s','a','s','s','s','s','s','s'},
                {'a','a','a','s','s','s','s'}
        };
        String [][] case1P2 = {
                {"at","and","an","add","a",".at","bat",".at","an.","a.d.","b.","a.d","."},
                {"bad", "dad", "mad", "pad", "bad", ".ad", "b.."}
        };
        boolean [][] ans = {
                {true,true,true,true,false,false,true,true,true,false,false,true,false},
                {true, true, true, false, true, true, true}
        };

        for (int i = 0; i < case1P1.length; i++) {
            Object[] p1 = new Object[3];
            p1[0] = case1P1[i];
            p1[1] = case1P2[i];
            p1[2] = ans[i];
            addParameterAndAnswer(p1, true, true);
        }

    }

    @CaseRunner
    public boolean runIt(char[] ops , String[] arg1, boolean[] ans){
        WordDictionary wordDictionary = new WordDictionary();
        for (int i = 0; i < ops.length; i++){
            if (ops[i] == 'a'){
                wordDictionary.addWord(arg1[i]);
            }else if (ops[i] == 's'){
                boolean r = wordDictionary.search(arg1[i]);
                if (r != ans[i])
                    return false;
            }
        }

        return true;
    }

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

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
