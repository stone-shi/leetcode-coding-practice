package com.shifamily.dev.leetcode.practice.string;

/*
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
 */

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

public class Leet953VerifyingAnAlienDictionary extends BasicStudy {

    public Leet953VerifyingAnAlienDictionary() {

        String[][] caseP1 = {{"hello","leetcode"}, {"word","world","row"}, {"apple","app"} };
        String[] caseP2 = {"hlabcdefgijkmnopqrstuvwxyz","worldabcefghijkmnpqstuvxyz", "abcdefghijklmnopqrstuvwxyz"};
        Boolean[] answers = {true, false, false};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answers[i], false);
        }

    }

    /* second try 5/23/2020 */
    @CaseRunner
    public boolean isAlienSorted2nd(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++)
            orderMap[order.charAt(i) - 'a'] = i;

        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            while (j < words[i].length() && j < words[i + 1].length()
                    && words[i].charAt(j) == words[i + 1].charAt(j))
                j++;
            if (j == words[i + 1].length())
                return false;
            else if (j == words[i].length())
                continue;
            if (orderMap[words[i].charAt(j) - 'a'] > orderMap[words[i+1].charAt(j) - 'a'] )
                return false;
        }
        return true;

    }

    @CaseRunner
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

}
