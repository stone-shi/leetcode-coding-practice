package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.*;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */
public class Leet301RemoveInvalidParentheses extends BasicStudy {

    //prepare test cases
    public Leet301RemoveInvalidParentheses() {

        String[] case1 = {"()())()"};
        String[] answer1 = {"()()()", "(())()"};

        String[] case2 = {"(a)())()"};
        String[] answer2 = {"(a)()()", "(a())()"};

        String[] case3 = {")("};
        String[] answer3 = {""};

        String[] case4 = {"()(((((((()"};
        String[] answer4 = {"()()"};


        addParameterAndAnswer(case1, answer1, false);
        addParameterAndAnswer(case2, answer2, false);
        addParameterAndAnswer(case3, answer3, false);
        addParameterAndAnswer(case4, answer4, false);
    }

    /* 2nd try 5/24/2020 */
    @CaseRunner
    public List<String> removeInvalidParentheses2ndDFS(String s) {

        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length() ; i++) {
            if (s.charAt(i) == '('){
                left++;
            }else if (s.charAt(i) == ')'){
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }
        Set<String> res = new HashSet<>();
        dfs(0, left, right, s, res);
        return new LinkedList<>(res);
    }

    private void dfs(int start, int left, int right, String s, Set<String> res){
        if (left == 0 && right == 0){
            if (!res.contains(s) && isValid2nd(s)){
                res.add(s);
            }
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(i) == s.charAt(i-1))
                continue;

            if (s.charAt(i) == '(' && left > 0)
                dfs(i, left - 1, right,  s.substring(0, i) + s.substring(i + 1) , res);
            else if (s.charAt(i) == ')' && right > 0)
                dfs(i , left, right - 1,  s.substring(0, i) + s.substring(i + 1), res);

        }


    }

    private boolean isValid2nd(String s){
        int ct = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') ct++;
            if (s.charAt(i) == ')') ct--;
            if (ct < 0) return false;
        }
        return ct == 0;

    }








    /* =================================================== */


    /*
    BFS解法 - iteration
     */
    @CaseRunner
    public List<String> removeInvalidParenthesesBFS(String s) {

        //建立BFS的Queue，并且放入初始字符
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        //用HashSet过滤已经检查过的字符串。
        Set<String> dupCheck = new HashSet<>();

        List<String> result = new LinkedList<>();

        boolean found = false ;
        int size;

        while (!q.isEmpty()){
            size = q.size();
            //这个循环是BFS用来保证这个循环里面的数据都是这一层树的，这个循环里面加入Queue都是树的下一层
            for (int i = 0; i < size; i++) {
                String check = q.poll();
                if (dupCheck.contains(check))
                    continue;
                dupCheck.add(check);
                //如果找到了，添加到结果，并且设置found flag不再继续往树的下层查找(因为题目要求最小修改)
                if ( isValid(check) ) {
                    found = true;
                    result.add(check);
                }

                if (!found){ //只有这层没有找到才会添加下层，下层是这个字符串每次删掉一个字符。
                    for (int j = 0; j < check.length(); j++) {
                        if (check.charAt(j) != ')' && check.charAt(j) != '(')
                            continue;
                        q.offer(check.substring(0, j) + check.substring(j + 1));
                    }
                }
            }

            //如果这层找到了，就不再继续往下层找了。
            if (found)
                break;
        }


        return result;
    }

    /*
    Recursion
     */
    @CaseRunner
    public List<String> removeInvalidParenthesesRecursion(String s) {

        //预先计算左括号多出来还是右括号多出来
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                left++;
            else if (c == ')'){
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }

        List<String> result = new LinkedList<>();

        helper(0, left, right, s, result);

        return result;

    }

    private void helper(int start, int left, int right, String s, List<String> result){

        //如果左括号和右括号一样多，检查是否有效
        if (left == 0 && right == 0){
            if (isValid(s))
                result.add(s);

            return;
        }

        //从当前字符，往下查找，之前找过的就不找了
        for (int i = start; i < s.length() ; i++) {

            //检查是否多个同向括号，主要处理第一个。比如 ((()，删掉第一个（，第二个，第三个都没有区别的
            if (i > start && s.charAt(i) == s.charAt(i-1))
                continue;

            //左括号太多，删左括号继续检查
            if (left > 0 && s.charAt(i) == '(')
                helper(i, left -1, right, s.substring(0, i) + s.substring(i+1), result);
            else if (right > 0 && s.charAt(i) == ')' ) //右括号太多
                helper(i, left, right - 1, s.substring(0, i) + s.substring(i+1), result);


        }

    }


    private boolean isValid(String s){
        char[] cs = s.toCharArray();

        int ct = 0;
        for (char c: cs) {
            if (c == '(') ct++;
            if (c == ')') ct--;
            if (ct < 0)
                return false;
        }

        return ct == 0;
    }

}
