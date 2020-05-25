package com.shifamily.dev.leetcode.practice.heap;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;
import java.util.PriorityQueue;

/**
 215. Kth Largest Element in an Array
 Medium

 3434

 233

 Add to List

 Share
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 Example 1:

 Input: [3,2,1,5,6,4] and k = 2
 Output: 5
 Example 2:

 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4
 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.

 Accepted
 594,726
 Submissions
 1,102,301
 Seen this question in a real interview before?

 Yes

 No
 Contributor
 mithmatt
 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

 Facebook
 |
 32

 Amazon
 |
 14

 LinkedIn
 |
 6

 Google
 |
 5

 Microsoft
 |
 3

 Goldman Sachs
 |
 2

 Apple
 |
 2

 Bloomberg
 |
 2

 Uber
 |
 2

 Adobe
 |
 2

 Oracle
 |
 2

 Spotify
 |
 2
 Wiggle Sort II
 Medium
 Top K Frequent Elements
 Medium
 Third Maximum Number
 Easy
 Kth Largest Element in a Stream
 Easy
 K Closest Points to Origin
 Medium
 */
public class Leet215KthLargestElementInArray extends BasicStudy {
    public Leet215KthLargestElementInArray() {
        int[][]caseP1 = {{3,2,1,5,6,4}, {3,2,3,1,2,4,5,5,6}};
        int[] caseP2 = {2, 4};
        int[] answer = {5, 4};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }

    }
    @CaseRunner
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int n: nums){
            pq.offer(n);
            if (pq.size() > k)
                pq.poll();
        }

        return pq.poll();
    }
}
