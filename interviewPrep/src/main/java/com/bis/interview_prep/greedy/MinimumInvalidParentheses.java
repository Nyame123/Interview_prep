package com.bis.interview_prep.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string of parentheses, write a function to compute
 * the minimum number of parentheses to be removed to make the string
 * valid (i.e. each open parenthesis is eventually closed).
 * <p>
 * For example, given the string "()())()", you should return 1. Given
 * the string ")(", you should return 2, since we must remove all of them.
 **/
public class MinimumInvalidParentheses {

    public static void main(String[] args) {
        String s = "()())()";
        int res = minimumInvalidParenthesesToRemove(s);
        System.out.println(res);
    }


    static int minimumInvalidParenthesesToRemove(String s) {
        int n = s.length();
        int len = 0;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '('){
                len++;
            }else if (c == ')' && len > 0){
                len--;
            }else {
               temp++;
            }
        }

        return len + temp;
    }
}
