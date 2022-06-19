# Leetcode #359 Logger Rate Limiter

## 原题

359 Logger Rate Limiter

Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

**Example:**

```java
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
    /** Initialize your data structure here. */
    public Logger() {

    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
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

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
```

## 解法

简单地用HashMap可以实现功能，但并不完美，没有考虑到大数据且很多message不同的情形，这样HashMap很有可能造成内存过载。归根结底，这是一个微型系统设计 - design a logger system，因此也考验系统扩展性和健壮性。
如果使用Java的LinkedHashMap，可以很方便地实现eviction。实际上LinkedHashMap也可以用于实现LRU。
https://www.baeldung.com/java-linked-hashmap

另外一种解法是用queue，不断把之前的去掉。

## 复杂度

LinkedHashMap Time: O(1)
Queue: O(k)

## 代码


```Java
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

```
