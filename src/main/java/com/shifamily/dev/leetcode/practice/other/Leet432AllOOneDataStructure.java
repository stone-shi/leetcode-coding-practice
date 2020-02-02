package com.shifamily.dev.leetcode.practice.other;

import com.shifamily.dev.leetcode.practice.BasicStudy;
import com.shifamily.dev.leetcode.practice.CaseRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
432 All O`one Data Structure
Hard

470

61

Add to List

Share
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.

Accepted
25,810
Submissions
82,524
 */
@Slf4j
public class Leet432AllOOneDataStructure extends BasicStudy {

    public Leet432AllOOneDataStructure() {
        String[][] caseP1 = {
                {"inc", "inc","inc", "dec", "inc", "dec","getMaxKey", "getMinKey"},
                {"dec","inc", "inc", "inc", "inc", "inc", "inc", "dec", "dec", "getMinKey", "dec", "getMaxKey", "getMinKey"},
                {"inc", "inc", "inc", "inc", "inc", "inc", "getMaxKey", "getMinKey", "dec", "dec", "getMaxKey", "getMinKey"}};
        String[][] caseP2 = {
                {"a", "a", "b", "b", "b", "b", "a", "a"},
                {"d", "a", "b", "b", "c", "c", "c", "b", "b", "a", "a", "c", "c"},
                {"A", "A", "A", "B", "C", "B", "A", "C", "A", "A", "B", "A"}};
         boolean[] answer = {true, true, true};

        for (int i = 0; i < caseP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = caseP1[i];
            p[1] = caseP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }
    }

    @CaseRunner
    public boolean runIt(String[] operator, String[] parameter) {

        AllOne allOne = new AllOne();
        boolean rs = true;
        for (int i = 0; i < operator.length; i++) {
            log.info("{}({})", operator[i], parameter[i]);
            String ret = "null";
            if (operator[i].equals("inc")) {
                allOne.inc(parameter[i]);
            } else if (operator[i].equals("dec")) {
                allOne.dec(parameter[i]);
            } else if (operator[i].equals("getMaxKey")) {
                ret = allOne.getMaxKey();
                rs = ret.equals(parameter[i]);
            } else if (operator[i].equals("getMinKey")) {
                ret = allOne.getMinKey();
                rs = ret.equals(parameter[i]);
            }
            if (!rs) {
                log.info("Error: expect {} got {}", parameter[i], ret);
                return rs;
            }
        }

        return rs;
    }


    public static class AllOne {

        public static class LinkedCountKey {

            LinkedCountKey pre;
            LinkedCountKey next;
            Set<String> keySet = new HashSet<>();
            Integer count = 0;

        }


        HashMap<String, Integer> mapKeyCount = new HashMap<>();
        HashMap<Integer, LinkedCountKey> mapCountKey = new HashMap<>();
        LinkedCountKey head = null, tail = null;

        /**
         * Initialize your data structure here.
         */
        public AllOne() {
        }

        private void removeLinkedCountKey(LinkedCountKey linkedCountKey) {
            LinkedCountKey p = linkedCountKey.pre;
            LinkedCountKey n = linkedCountKey.next;

            if (p != null)
                p.next = n;
            else
                head = n;

            if (n != null)
                n.pre = p;
            else
                tail = p;
            linkedCountKey.pre = null;
            linkedCountKey.next = null;

        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            //get count from map
            Integer ct = 0;
            if (mapKeyCount.containsKey(key))
                ct = mapKeyCount.get(key);

            LinkedCountKey currentCountKey = null;
            LinkedCountKey newCountKey = null;
            LinkedCountKey upCountKey = null;


            if (ct > 0) {
                currentCountKey = mapCountKey.get(ct);
                currentCountKey.keySet.remove(key); //remove from current because count + 1
                upCountKey = currentCountKey.pre;
            } else {
                upCountKey = tail;
            }

            if (upCountKey == null) { //new head
                newCountKey = new LinkedCountKey();
                newCountKey.count = ct + 1;
                head = newCountKey;
                if (tail == null)
                    tail = newCountKey;
                newCountKey.next = currentCountKey;
                if (currentCountKey != null)
                    currentCountKey.pre = newCountKey;
                newCountKey.keySet.add(key);
            } else if (upCountKey.count > ct + 1) {//insert
                newCountKey = new LinkedCountKey();

                upCountKey.next = newCountKey;
                if (currentCountKey != null)
                    currentCountKey.pre = newCountKey;
                else
                    tail = newCountKey;
                newCountKey.pre = upCountKey;
                newCountKey.next = currentCountKey;

                newCountKey.count = ct + 1;
                newCountKey.keySet.add(key);
            } else { //exist
                upCountKey.keySet.add(key);
            }

            if (newCountKey != null)
                mapCountKey.put(ct + 1, newCountKey);

            if (currentCountKey != null && currentCountKey.keySet.isEmpty()) { //no more key in this count
                removeLinkedCountKey(currentCountKey);
                mapCountKey.remove(ct);
            }
            mapKeyCount.put(key, ct + 1);


        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            if (!mapKeyCount.containsKey(key))
                return;

            Integer ct = mapKeyCount.get(key);
            LinkedCountKey currentCountKey = mapCountKey.get(ct);
            LinkedCountKey newCountKey = null;
            LinkedCountKey downCountKey = null;

            currentCountKey.keySet.remove(key);

            if (ct > 1) {
                downCountKey = currentCountKey.next;
                currentCountKey.keySet.remove(key);

                if (downCountKey == null) { //new tail
                    downCountKey = new LinkedCountKey();
                    downCountKey.count = ct - 1;
                    downCountKey.keySet.add(key);

                    downCountKey.pre = currentCountKey;
                    currentCountKey.next = downCountKey;

                    tail = downCountKey;
                    newCountKey = downCountKey;
                } else if (downCountKey.count < ct - 1) {//insert
                    newCountKey = new LinkedCountKey();
                    currentCountKey.next = newCountKey;
                    newCountKey.pre = currentCountKey;
                    newCountKey.next = downCountKey;
                    downCountKey.pre = newCountKey;
                    newCountKey.count = ct - 1;
                    newCountKey.keySet.add(key);
                } else {
                    downCountKey.keySet.add(key);
                }
                mapKeyCount.put(key, ct - 1);
            }else{
                mapKeyCount.remove(key);
            }

            if (newCountKey != null)
                mapCountKey.put(ct - 1, newCountKey);

            if (currentCountKey.keySet.isEmpty()) { //no more key in this count
                removeLinkedCountKey(currentCountKey);
                mapCountKey.remove(ct);
            }

        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            if (head == null)
                return "";

            return head.keySet.iterator().next();
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            if (tail == null)
                return "";

            return tail.keySet.iterator().next();

        }
    }

}
