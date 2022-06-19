package com.shifamily.dev.leetcode.practice;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

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

    // second try - 2022/06/19
    @CaseRunner
    public int[] asteroidCollision2(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int a : asteroids) {
            while (!stack.isEmpty()) {
                int a1 = stack.peek();
                if (a1 > 0 && a < 0) {
                    int mass = -1 * a;
                    if (a1 > mass) {
                        a = 0;
                        break;
                    } else if (a1 == mass) {
                        a = 0;
                        stack.pop();
                    } else {
                        stack.pop();
                    }
                } else
                    break;
            }
            if (a != 0)
                stack.push(a);
        }
        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty())
            res[i++]  = stack.pollLast();
        return res;

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
