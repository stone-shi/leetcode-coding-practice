# Leetcode #1423 Maximum Points You Can Obtain from Cards

## 原题

[1423 Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/)

**<span style="color:blue">Medium</span>** 2921 121

There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

**Example 1:**

> `Input: cardPoints = [1,2,3,4,5,6,1], k = 3`
`Output: 12`
**Explanation:** After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.

**Example 2:**

> `Input: cardPoints = [2,2,2], k = 2`
`Output: 4`
**Explanation:** Regardless of which two cards you take, your score will always be 4.

**Example 3:**

> `Input: cardPoints = [9,7,7,9,7,7,9], k = 7`
`Output: 55`
**Explanation:** You have to take all the cards. Your score is the sum of points of all cards.
 
**Constraints:**

* `1 <= cardPoints.length <= 105`
* `1 <= cardPoints[i] <= 104`
* `1 <= k <= cardPoints.length`

## 解法

prefix sum - 先把左边k加起来，保存。然后从右边往左求和。

## 复杂度

time: O(K)
space O(1) for solution 2, O(k) for solution 1


## 代码


```Java
   // solution 1, space O(k)
    @CaseRunner
    public int maxScore(int[] cardPoints, int k) {
        int[] sumLeft = new int[k + 1];
        if (cardPoints.length <= k){
            return Arrays.stream(cardPoints).sum();   
        }
        for (int i = 1; i <= k; i++) {
            sumLeft[i] = sumLeft[i - 1] + cardPoints[i - 1];
        }
        int res = sumLeft[k];
        int sumRight = 0;
        for (int i = 0; i < k  ; i++) {
            sumRight += cardPoints[cardPoints.length - 1 - i];
            int m = sumRight + sumLeft[k - 1 - i];
            res = Math.max(res, m);
        }
        return res;
    }

    // solution 2, space o(1)
    @CaseRunner
    public int maxScore2(int[] cardPoints, int k) {
        int sumL = 0;
        int res = 0;
        int len = cardPoints.length;
        if (len <= k)
            return Arrays.stream(cardPoints).sum();
        for (int i = 0; i < k; i++) {
            sumL += cardPoints[i];
        }
        res = sumL;
        int sumR = 0;
        for (int i = 0; i < k; i++) {
            sumR += cardPoints[len - 1 - i];
            sumL -= cardPoints[k - 1 - i];
            res = Math.max(res, sumR + sumL);
        }
        return res;
    }

```
