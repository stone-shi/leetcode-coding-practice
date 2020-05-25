package com.shifamily.dev.utils;

import java.util.LinkedList;
import java.util.List;

public class NestedInteger {
    // Constructor initializes an empty nested list.

    Integer val = null;
    List<NestedInteger> data = new LinkedList<>();

    public NestedInteger(){

    }

    // Constructor initializes a single integer.
    public NestedInteger(int value){
        val = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger(){
        return val != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger(){
        return val;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value){
        val = value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni){
        val = null;
        data.add(ni);

    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList(){
        return data;
    }

    private static int scanNextToken(char[] s, int start){
        int count = 0;
        while (start < s.length){
            if (s[start] == '[')
                count++;
            else if (s[start] == ']') {
                if (count == 0)
                    return start;
                else
                    count--;
            }
            start++;
        }
        return -1;
    }

    public static NestedInteger fromString(String s){

        char[] ca = s.toCharArray();
        int i = 0;

        NestedInteger root = new NestedInteger();
        StringBuilder sb =  new StringBuilder();
        while (i < ca.length){
            if (ca[i] != ',' && ca[i] != '[' && ca[i] != ']' && ca[i] > '9' && ca[i] < '0') {
                i++;
                continue;
            }
            if (ca[i] == '['){
                int end = scanNextToken(ca, i + 1);
                if (end == -1)
                    return null;
                String part = s.substring(i + 1, end );
                NestedInteger ni = fromString(part);
                if (ni.isInteger()){
                    NestedInteger newNi = new NestedInteger();
                    newNi.add(ni);
                    root.add(newNi);
                }else
                    root.add(ni);
                i = end + 1;
            }else if (ca[i] == ',' ){
                if ( sb.length() > 0) {
                    int val = Integer.parseInt(sb.toString());
                    root.add(new NestedInteger(val));
                    sb = new StringBuilder();
                }
                i++;
            }else{
                sb.append(ca[i++]);
            }
        }
        if ( sb.length() > 0 ){
            int val = Integer.parseInt( sb.toString() );
            if (root.data.size() == 0)
                root.setInteger(val);
            else
                root.add(new NestedInteger(val));
        }

        return root;


    }
}