package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a string s, remove the minimum number of parentheses to make s valid. Return all possible results.
 * <p>
 * Ex: Given the following string s...
 * <p>
 * s = "(()()()", return ["()()()","(())()","(()())"].
 * Ex: Given the following string s...
 * <p>
 * s = "()()()", return ["()()()"].
 **/
public class AllMinimumParenthesisRemoval {

    public static void main(String[] args) {
        String s = "()())()";

        List<String> res = removeInvalidParentheses(s);
        System.out.println(res);
    }

    /**
     * Since the problem is looking at the minimum valid parenthesis to be generated.
     * 1. We can traverse all the positions of the parenthesis characters and remove each position and
     * check if after removal, the rest of the string can be valid.
     * 2. We will use backtracking approach to do this as we backtrack to check the next state if it is valid lest we
     * continue with the resulting string until there is no valid string left.
     * 2. if valid, we add to the result set.
     *
     * Time Complexity = O(2^n)
     */
    static List<String> allValidMinimumParenthesis(String s) {
        List<String> res = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        boolean level = false;
        while (!queue.isEmpty()){
            String peek = queue.poll();
            if (isValidString(peek)){
                res.add(peek);
                level = true;
            }

            if (level){
                continue;
            }
            int n = peek.length();
            for (int i = 0; i < n; i++) {
                if (isParenthesis(peek.charAt(i))) {
                    String sub = peek.substring(0, i) + peek.substring(i + 1);
                    if (!visited.contains(sub)) {
                        queue.add(sub);
                        visited.add(sub);
                    }
                }
            }
        }

        return res;
    }

    static boolean isParenthesis(char c) {
        return c == ')' || c == '(';
    }

    static boolean isValidString(String s) {
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (isParenthesis(c)) {
                if (c == '(')
                    count++;
                else {
                    count--;
                }
            }
            if (count < 0){
                return false;
            }
        }

        return count == 0;
    }


    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        char[] parentheses = new char[]{'(', ')'};
        helper(s, ans, parentheses, 0, 0);
        return ans;
    }

    private static void helper(String s, List<String> ans, char[] parentheses, int start_i, int start_j) {
        int balance = 0;
        for (int i = start_i; i < s.length(); i++) {
            if (s.charAt(i) == parentheses[0]) balance++;
            if (s.charAt(i) == parentheses[1]) balance--;
            if (balance >= 0) continue;
            for (int j = start_j; j <= i; j++) {
                if (s.charAt(j) == parentheses[1] && (j == start_j || s.charAt(j - 1) != parentheses[1])) {
                    String newStr = s.substring(0, j) + s.substring(j + 1);
                    helper(newStr, ans, parentheses, i, j);
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (parentheses[0] == '(') // finished left to right
            helper(reversed, ans, new char[]{')', '('}, 0, 0);
        else // finished right to left
            ans.add(reversed);
    }
}
