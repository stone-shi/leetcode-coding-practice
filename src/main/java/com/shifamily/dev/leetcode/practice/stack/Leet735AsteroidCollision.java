package com.shifamily.dev.leetcode.practice.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

/*
735. Asteroid Collision
Medium

3489

279

Add to List

Share
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 

Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
Accepted
197,506
Submissions
445,252
*/

public class Leet735AsteroidCollision extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 5, 10, -5 } }).answersOrderMatter(true)
                .answer(new int[] { 5, 10 })
                .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 8, -8 } }).answersOrderMatter(true)
                .answer(new int[] {})
                .description("case b").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 10, 2, -5 } }).answersOrderMatter(true)
                .answer(new int[] { 10 })
                .description("case c").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { -2, -1, 1, 2 } })
                .answersOrderMatter(true)
                .answer(new int[] { -2, -1, 1, 2 })
                .description("case negative value first").build());
        return cases;
    }

    @CaseRunner
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> s = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] == 0)
                continue;

            int income = asteroids[i];
            while (!s.isEmpty()) {
                int last = s.peek();
                if ((last > 0 && income < 0)) {
                    int lastMass = Math.abs(last);
                    int incomeMass = Math.abs(income);
                    if (lastMass == incomeMass) {
                        s.pop();
                        income = 0;
                        break;
                    } else if (lastMass < incomeMass) {
                        s.pop();
                    } else {
                        income = 0;
                        break;
                    }
                } else
                    break;
            }
            if (income != 0)
                s.push(income);
        }
        int[] result = new int[s.size()];
        int i = 0;
        while (!s.isEmpty())
            result[i++] = s.pollLast();
        return result;
    }

}
