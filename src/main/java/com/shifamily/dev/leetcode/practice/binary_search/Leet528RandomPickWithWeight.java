package com.shifamily.dev.leetcode.practice.binary_search;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 528. Random Pick with Weight
 Medium

 559

 1042

 Add to List

 Share
 Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

 Note:

 1 <= w.length <= 10000
 1 <= w[i] <= 10^5
 pickIndex will be called at most 10000 times.
 Example 1:

 Input:
 ["Solution","pickIndex"]
 [[[1]],[]]
 Output: [null,0]
 Example 2:

 Input:
 ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 [[[1,3]],[],[],[],[],[]]
 Output: [null,0,1,1,1,0]
 Explanation of Input Syntax:

 The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

 Accepted
 62,008
 Submissions
 141,638
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 1337c0d3r
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 12

 Google
 |
 6

 Microsoft
 |
 3

 Twitter
 |
 2

 Amazon
 |
 2
 Random Pick Index
 Medium
 Random Pick with Blacklist
 Hard
 Random Point in Non-overlapping Rectangles
 Medium


 Explaination of question itself:
 for the array of {1, 3, 4, 6}
 the pickIndex() call will have 1/(1+3+4+6) = 1/14 = ~7% chance of picking index 0; 3/14 = 21% change of picking index 1; 4/14 = 29% change of picking index 2; and 6/14 = 43% of picking index 3.


 Given an array w of positive integers sent to Solution(), where w[i] describes the weight of index i. [1,3] would mean index 0 has weight 1, while index 1 has weight 3.
 Write a function pickIndex() which randomly picks an index in proportion to its weight. pickIndex() will be called multiple times

 Example 1:
 w[] = [1]
 expected return values from pickIndex() calls:
 0

 Example 2:
 Input:
 w[] = [1,3]
 expected return values from pickIndex() calls:
 0,1,1,1,0

 Note:
 1 <= w.length <= 10000
 1 <= w[i] <= 10^5
 pickIndex will be called at most 10000 times.
 */

@Slf4j
public class Leet528RandomPickWithWeight extends BasicStudy {
    public Leet528RandomPickWithWeight() {

        int [] times = {1000, 5000};
        int[][] w = {
                {1},
                {1,3}
        };

        for (int i = 0; i < times.length; i++) {
            Object[] p1 = new Object[2];
            p1[0] = times[i];
            p1[1] = w[i];
            addParameterAndAnswer(p1,true, false);
        }
        Object[] p1 = new Object[2];
        p1[0] = 10000;
        p1[1] = randomW(10000);
        addParameterAndAnswer(p1,true, false);

    }
    private int[] randomW(int count){
        Random random = new Random();
        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = random.nextInt(100000);
        }
        return res;
    }

    @CaseRunner
    public boolean runIt(int times, int[] w){

        int[] count = new int[w.length];
        Solution solution = new Solution(w);
        double[] indexWeight = new double[w.length];

        for (int i = 0; i < times; i++) {
            int idx = solution.pickIndex();
            count[idx]++;
        }

        double totalSum = 0;
        for (int value : w) totalSum += value;
        for (int i = 0; i < indexWeight.length; i++)
            indexWeight[i] = w[i] / totalSum;

        double allowedOffset = 0.05;
        for (int i = 0; i < w.length; i++) {
            double rate = ((double)count[i] / times)  ;
            if (rate < indexWeight[i] - allowedOffset || rate > indexWeight[i] + allowedOffset ){
                log.error("Answer is wrong index: {}, got rate {}, expected rate {}", i, rate, indexWeight[i]);
                return false;
            }


        }

        return true;

    }


    class Solution {

        int[] prefixSum;
        int totalSum;
        public Solution(int[] w) {

            prefixSum = new int[w.length];
            totalSum = w[0];

            prefixSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                prefixSum[i] = prefixSum[i-1] + w[i];
                totalSum += w[i];
            }

        }


        /* binary */
        public int pickIndex() {

            double r = Math.random() * totalSum;

            int low = 0;
            int high = prefixSum.length - 1;
            int mid = 0;
            while (low < high){

                mid = low + (high - low) / 2;
                if (prefixSum[mid] >= r)
                    high = mid ;
                else
                    low = mid + 1;

            }
            return low;

        }

        public int pickIndexLinear() {

            double r = Math.random() * totalSum;
            for (int i = 0; i < prefixSum.length ; i++) {
                if (r < prefixSum[i])
                    return i;
            }
            return 0;


        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
}






