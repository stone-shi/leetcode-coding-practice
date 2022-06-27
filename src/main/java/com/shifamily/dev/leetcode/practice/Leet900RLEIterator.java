package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet900RLEIterator extends BasicStudy {

    @ClassCaseData
    public List<ClassCaseParameters> data2() {
        List<ClassCaseParameters> cases = new ArrayList<>();
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "RLEIterator", "next", "next", "next", "next" })
                .operationParameters(new Object[][] {
                        { new int[] { 3, 8, 0, 9, 2, 5 } }, { 2 }, { 1 }, { 1 },
                        { 2 }
                }).answer(new Object[] { null, 8, 8, 5, -1 })
                .description("case a").build());

        return cases;
    }

    @CaseRunner("RLEIterator")
    static public class RLEIterator2 {
        int[] encoding;
        int currentEle = 0;
        int ct = 0;
        int len = 0;

        public RLEIterator2(int[] encoding) {
            this.encoding = encoding;
            this.len = encoding.length;
            ct = encoding[0];
        }

        public int next(int n) {
            while (n > 0 && currentEle < len - 1) {
                if (ct >= n) {
                    ct = ct - n;
                    return encoding[currentEle + 1];
                } else {
                    n = n - ct;
                    currentEle += 2;
                    if (currentEle > len - 1)
                        return -1;
                    ct = encoding[currentEle];
                }
            }
            return -1;
        }
    }

    @CaseRunner("RLEIterator")
    static public class RLEIterator {
        int[] encoding;
        int index = 0;
        int current = 0;
        boolean overflow = false;

        public RLEIterator(int[] encoding) {
            this.encoding = encoding;
        }

        public int next(int n) {
            if (overflow)
                return -1;
            int ct = encoding[index];

            while (ct - current - n < 0) {
                if (index + 2 >= encoding.length - 1) {
                    overflow = true;
                    return -1;
                }
                n = n - (ct - current);
                current = 0;
                index += 2;
                ct = encoding[index];
            }

            current += n;
            return encoding[index + 1];
        }
    }

    /**
     * Your RLEIterator object will be instantiated and called as such:
     * RLEIterator obj = new RLEIterator(encoding);
     * int param_1 = obj.next(n);
     */
}
