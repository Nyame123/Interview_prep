package com.bis.interview_prep.ordinal.dataStructure;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string containing just the characters '(' and ')',
 * return the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 **/
public class LongestValidParenthesisUsingStack{

    public static void main(String[] args) {
        String s = ")()())";
        int len = longestValidParentheses(s);
        System.out.println(len);
    }

    static int longestValidParentheses(String s) {
        //Using stack
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i < n; i++){

            if(!stack.isEmpty() && s.charAt(stack.peek()) == '(' && s.charAt(i) == ')'){
                stack.pop();
            }else{
                stack.push(i);
            }
        }

        int index = n;
        int max = 0;
        while(!stack.isEmpty()){
            int k = stack.pop();
            max = Math.max(max, index - k - 1);
            index = k;
        }

        max = Math.max(max, index-1);

        return max;
    }
}
