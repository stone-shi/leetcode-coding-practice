package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 767. Reorganize String
 Medium

 1334

 64

 Add to List

 Share
 Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

 If possible, output any possible result.  If not possible, return the empty string.

 Example 1:

 Input: S = "aab"
 Output: "aba"
 Example 2:

 Input: S = "aaab"
 Output: ""
 Note:

 S will consist of lowercase letters and have length in range [1, 500].


 Accepted
 71,611
 Submissions
 150,090
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 ti9yowl
 Rearrange String k Distance Apart
 Hard
 Task Scheduler
 Medium
 Alternate placing the most common letters.
 */
public class Leet767ReorganizeString extends BasicStudy {
    public Leet767ReorganizeString() {
        String[] caseP1 = {"aab", "aaab" };
        String[] answer = {"aba", ""};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = caseP1[i];
            addParameterAndAnswer(p, answer[i], false);
        }


    }
    class CharCount {
        char c;
        int count;

        public CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    @CaseRunner
    public String reorganizeString(String S) {
        int[] count = new int[26];
        for (int i = 0; i < S.length(); i++)
            count[S.charAt(i) - 'a']++;
        PriorityQueue<CharCount> pq = new PriorityQueue<>( (o1, o2) -> o2.count - o1.count );
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0)
                pq.add(new CharCount((char)(i + 'a'), count[i]));
        }
        StringBuilder sb = new StringBuilder();
        while (! pq.isEmpty()){
            CharCount charCount = pq.poll();
            CharCount filler = pq.poll();
            for (int i = 0; i < charCount.count - 1; i++) {
                sb.append(charCount.c);
                if (filler == null || (filler.count == 0 && null == (filler = pq.poll())) )
                    return "";
                sb.append(filler.c);
                filler.count--;
            }
            sb.append(charCount.c);
            if (filler != null && filler.count > 0)
                pq.offer(filler);
        }
        return sb.toString();

    }
}
