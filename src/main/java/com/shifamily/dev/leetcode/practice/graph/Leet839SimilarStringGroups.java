package com.shifamily.dev.leetcode.practice.graph;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/**
 839. Similar String Groups
 Hard

 319

 107

 Add to List

 Share
 Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

 For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

 Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

 We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?



 Example 1:

 Input: A = ["tars","rats","arts","star"]
 Output: 2


 Constraints:

 1 <= A.length <= 2000
 1 <= A[i].length <= 1000
 A.length * A[i].length <= 20000
 All words in A consist of lowercase letters only.
 All words in A have the same length and are anagrams of each other.
 The judging time limit has been increased for this question.
 Accepted
 21,907
 Submissions
 57,223
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 awice
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 8


 */
public class Leet839SimilarStringGroups extends BasicStudy {

    public Leet839SimilarStringGroups() {
        String[][] casesP1 = {{"abc", "abc"}, {"tars","rats","arts","star" }};
        int[] answers = { 1, 2 };

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = casesP1[i];
            addParameterAndAnswer(p, answers[i], false);
        }
    }

    @CaseRunner
    public int numSimilarGroups(String[] A) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null)
                continue;
            String s = A[i];
            A[i] = null;
            res++;
            dfs(A, s);
        }

        return res;

    }

    private void dfs(String[] A, String s){

        for (int i = 0; i < A.length; i++) {
            if (A[i] == null)
                continue;

            String s1 = A[i];
            if (isSimilar(s1, s)){
                A[i] = null;
                dfs(A, s1);
            }

        }

    }

    private boolean isSimilar(String a, String b){
        int diff = 0;
        int i = 0;

        while (i < a.length()){
            if (a.charAt(i) != b.charAt(i))
                diff++;
            i++;
        }
        return diff == 2 || diff == 0;
    }
}
