package com.bis.interview_prep.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * */
public class MinimumInvalidParenthesesII {

    public static void main(String[] args) {
          String s = s = "a)b(c)d";
          System.out.println(minRemoveToMakeValid(s));
    }

    static String minRemoveToMakeValid(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(i);
            }else if(c == ')'){
                if(stack.isEmpty()){
                    set.add(i);
                }else{
                    stack.pop();
                }
            }
        }

        set.addAll(stack);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            if(!set.contains(i))
                sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
