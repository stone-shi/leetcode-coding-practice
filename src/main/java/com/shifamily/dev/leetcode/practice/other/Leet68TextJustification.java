package com.shifamily.dev.leetcode.practice.other;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
68. Text Justification
Hard
516
1325
Add to List
Share
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
Accepted
118,545
Submissions
460,8
 */

public class Leet68TextJustification extends BasicStudy {

    public Leet68TextJustification() {
        String[][] caseP1 = {
                {"This", "is", "an", "example", "of", "text", "justification."},
                {"What","must","be","acknowledgment","shall","be"},
                {"Science","is","what","we","understand","well","enough","to","explain",
                        "to","a","computer.","Art","is","everything","else","we","do"}
        };
        int[] caseP2 = {16, 16, 20};
        List<String>[] answer = new List[3];
        answer[0] = new ArrayList<>(Arrays.asList(
                "This    is    an",
                "example  of text",
                "justification.  "
        ) );
        answer[1] = new ArrayList<>(Arrays.asList(
                "What   must   be",
                "acknowledgment  ",
                "shall be        "
        ) );
        answer[2] = new ArrayList<>(Arrays.asList(
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
        ) );

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }
    }

    private String nSpace(int n){
        char[] charArray = new char[n];
        Arrays.fill(charArray, ' ');
        return new String(charArray);
    }

    private String prepareLine(String[] words, int start, int end, int maxWidth, int currentCount, boolean padNeeded){

        StringBuilder sb = new StringBuilder();
        int spCount = end - start - 1 ;

        int padCount;
        int extraSpace;
        int endingPad ;

        if (spCount == 0)
            padNeeded = false;

        if (!padNeeded){
            padCount = 1;
            extraSpace = 0;
            endingPad = maxWidth - currentCount - spCount;
        }else{
          padCount = (maxWidth - currentCount) / spCount;
          extraSpace = (maxWidth - currentCount) % spCount;
          endingPad = 0;
        }

        while (start < end - 1){
            sb.append(words[start]);
            sb.append(nSpace(padCount));
            if (extraSpace-- > 0)
                sb.append(' ');
            start++;
        }
        sb.append(words[start]);
        if (endingPad > 0)
            sb.append(nSpace(endingPad));
        return sb.toString();
    }

    @CaseRunner
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int i = 0;
        int currentCount = 0;
        int startIndex = 0;
        while ( i < words.length) {
            int lineWidth = currentCount +  i - startIndex + words[i].length();
            if ( lineWidth > maxWidth) {
                res.add(prepareLine(words, startIndex, i, maxWidth, currentCount, true));
                startIndex = i;
                currentCount = 0;
            }
            currentCount += words[i].length();
            i++;
        }
        if (currentCount > 0)
            res.add(prepareLine(words, startIndex, i, maxWidth, currentCount, false));

        return res;
    }
}
