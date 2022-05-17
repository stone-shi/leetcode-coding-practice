package com.shifamily.dev.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

import com.shifamily.dev.BasicStudy;

/*
359. Logger Rate Limiter
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
public class Logger {
    Map<String, Integer> map = new HashMap<>(); // msg : lst print timestamp
    int limiter = 10;
    public Logger() {

    }

    Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. 
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message, timestamp);
            return true;
        }else{
            if(timestamp - map.get(message) >= limiter){
                map.put(message, timestamp);
                return true;
            }
        }

        return false;
    }
}


 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 

*/

public class Leet359LoggerRateLimiter extends BasicStudy {
    static class LoggerQueue {
        static class MessageNode {
            int timestamp;
            String msg;

            MessageNode(int timestamp, String msg) {
                this.timestamp = timestamp;
                this.msg = msg;
            }
        }

        int limiter = 10;
        Queue<MessageNode> q = new LinkedList<>();

        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!q.isEmpty()) {
                while (timestamp - q.peek().timestamp >= limiter) {
                    q.poll();
                }
                Iterator<MessageNode> it = q.iterator();
                while (it.hasNext()) {
                    if (it.next().msg == message)
                        return false;
                }
            }
            q.offer(new MessageNode(timestamp, message));
            return true;
        }
    }

    static class LoggerLHM {
        int limiter = 10;
        int lastTimestamp = 0;

        Map<String, Integer> map ;

        LoggerLHM(){
            map = new LinkedHashMap<>(){
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest){
                    return lastTimestamp - eldest.getValue() >=  limiter;
                }
            };
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            lastTimestamp = timestamp;
            if (map.containsKey(message) && timestamp - map.get(message) < limiter  )
                return false;
            map.put(message, timestamp);
            return true;
        }
    }

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "foo", "bar", "foo", "bar", "foo", "foo" },
                        new int[] { 1, 2, 3, 8, 10, 11 }, new boolean[] { true, true, false, false, false, true } })
                .answer(true)
                .description("case a").build());

        return cases;
    }

    @CaseRunner
    public boolean caseToRun1(String[] message, int[] timestamp, boolean[] shouldPrint) {
        LoggerLHM logger = new LoggerLHM();
        for (int i = 0; i < shouldPrint.length; i++) {
            if (logger.shouldPrintMessage(timestamp[i], message[i]) != shouldPrint[i]) {
                return false;
            }
        }
        return true;
    }

    @CaseRunner
    public boolean caseToRun2(String[] message, int[] timestamp, boolean[] shouldPrint) {
        LoggerQueue logger = new LoggerQueue();
        for (int i = 0; i < shouldPrint.length; i++) {
            if (logger.shouldPrintMessage(timestamp[i], message[i]) != shouldPrint[i]) {
                return false;
            }
        }
        return true;
    }
}
