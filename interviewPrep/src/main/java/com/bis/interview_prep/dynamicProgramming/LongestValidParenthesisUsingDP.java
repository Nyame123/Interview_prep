package com.bis.interview_prep.dynamicProgramming;

/**
 * Given a string containing just the characters '(' and ')',
 * return the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 **/
public class LongestValidParenthesisUsingDP {
    public static void main(String[] args) {
        String s = ")()())";
        int len = longestValidParentheses(s);
        System.out.println(len);
    }

    static int longestValidParentheses(String s) {

        int n = s.length();
        int max = 0;
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + ((i - 2 >= 0) ? dp[i - 2] : 0);
                } else {
                    int lastPoint = i - dp[i - 1] - 1;
                    if (lastPoint >= 0 && s.charAt(lastPoint) == '(') {
                        dp[i] = dp[i - 1] + 2 + ((lastPoint - 1) >= 0 ? dp[lastPoint - 1] : 0);
                    }

                }
            }

            max = Math.max(dp[i], max);

        }

        return max;
    }
}
