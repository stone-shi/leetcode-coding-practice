package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.*;

/**
 * 636. Exclusive Time of Functions
 * Medium
 * <p>
 * 709
 * <p>
 * 1262
 * <p>
 * Add to List
 * <p>
 * Share
 * On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
 * <p>
 * We store logs in timestamp order that describe when a function is entered or exited.
 * <p>
 * Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
 * <p>
 * A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
 * <p>
 * The CPU is single threaded which means that only one function is being executed at a given time unit.
 * <p>
 * Return the exclusive time of each function, sorted by their function id.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input:
 * n = 2
 * logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3, 4]
 * Explanation:
 * Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
 * Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
 * Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
 * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= n <= 100
 * Two functions won't start or end at the same time.
 * Functions will always log when they exit.
 * <p>
 * <p>
 * Accepted
 * 70,014
 * Submissions
 * 136,298
 * Seen this question in a real interview before?
 * <p>
 * Yes
 * <p>
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 * <p>
 * Facebook
 * |
 * 13
 * <p>
 * Oracle
 * |
 * 6
 * <p>
 * Microsoft
 * |
 * 5
 * <p>
 * Amazon
 * |
 * 3
 * <p>
 * LinkedIn
 * |
 * 2
 * <p>
 * Google
 * |
 * 2
 */
public class Leet636ExclusiveTimeOfFunctions extends BasicStudy {
    public Leet636ExclusiveTimeOfFunctions() {
        int[] caseP1 = {1, 2};
        String[][] caseP2 = {
                {"0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"},
                {"0:start:0", "1:start:2", "1:end:5", "0:end:6"}};
        int[][] answer = {{8}, {3, 4}};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = Arrays.asList(caseP2[i]);
            addParameterAndAnswer(p, answer[i], true);

        }
    }

    class ExecLog {
        int time;
        int fid;
        String ops;

        public ExecLog(String log) {
            String[] f = log.split(":");
            time = Integer.parseInt(f[2]);
            fid = Integer.parseInt(f[0]);
            ops = f[1];
        }
    }

    @CaseRunner
    public int[] exclusiveTime(int n, List<String> logs) {

        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int prev = -1;

        for (String log : logs) {
            ExecLog elog = new ExecLog(log);
            if (elog.ops.equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] = res[stack.peek()] + elog.time - prev;
                }
                stack.push(elog.fid);
                prev = elog.time;
            } else {
                int t = stack.pop();
                res[t] = res[t] + elog.time - prev + 1;
                prev = elog.time + 1;
            }
        }

        return res;


    }
}
