package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet202HappyNumber extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { 19 }).answer(true)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 2 }).answer(false)
                .description("Example 2").build());
        return cases;
    }

    // optimized (Floyd’s Cycle Finding Algorithm for loop detection)
    @CaseRunner
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = getSumNumber(slow);
            fast = getSumNumber(fast);
            fast = getSumNumber(fast);
        } while (slow != fast && slow != 1);

        return slow == 1;
    }

    private int getSumNumber(int n) {
        int res = 0;
        while (n != 0) {
            int dig = n % 10;
            res += dig * dig;
            n = n / 10;
        }
        return res;
    }

    // Not optimized hash set loop detection
    @CaseRunner
    public boolean isHappy(int n) {
        Set<Long> visited = new HashSet<>();

        long res = n;
        while (res != 1) {
            long r = 0;
            while (res != 0) {
                long last = res % 10;
                r += last * last;
                res = res / 10;
            }
            res = r;
            if (visited.contains(res))
                break;
            visited.add(res);
        }
        return res == 1;
    }

}
