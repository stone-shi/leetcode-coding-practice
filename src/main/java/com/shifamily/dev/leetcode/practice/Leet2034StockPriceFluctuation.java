package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet2034StockPriceFluctuation extends BasicStudy {

    @ClassCaseData
    public List<ClassCaseParameters> data2() {
        List<ClassCaseParameters> cases = new ArrayList<>();
        cases.add(ClassCaseParameters.builder().operations(
                new String[] { "StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update",
                        "minimum" })
                .operationParameters(new Object[][] { {}, { 1, 10 }, { 2, 5 }, {}, {}, { 1, 3 }, {}, { 4, 2 }, {} })
                .answer(new Object[] { null, null, null, 5, 10, null, 5, null, 2 })
                .description("Example 1").build());
        return cases;
    }

    @CaseRunner("StockPrice")
    class StockPrice {
        int currentTime = 0;
        PriorityQueue<Integer> pq;

        public StockPrice() {
            

        }

        public void update(int timestamp, int price) {

        }

        public int current() {
            return 0;

        }

        public int maximum() {
            return 0;

        }

        public int minimum() {
            return 0;

        }
    }
}
