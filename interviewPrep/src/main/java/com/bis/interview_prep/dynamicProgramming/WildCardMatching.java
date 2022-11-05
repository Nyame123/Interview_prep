package com.bis.interview_prep.dynamicProgramming;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 **/
public class WildCardMatching {

    public static void main(String[] args) {
        String s = "ho";
        String p = "**ho";
        boolean result = isMatch(s, p);
        System.out.println(result);
    }

    static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        char[] pattern = p.toCharArray();
        int index = 0;
        boolean isFirst = true;
        for (int i = 0; i < m; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[index++] = pattern[i];
                    isFirst = false;
                }
            } else {
                pattern[index++] = pattern[i];
                isFirst = true;
            }
        }

        boolean[][] dp = new boolean[n + 1][index + 1];

        if (m > 0 && p.charAt(0) == '*') {
            dp[0][1] = true;
        }
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (pattern[j - 1] == '?' || s.charAt(i - 1) == pattern[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[n][index];
    }

    static boolean isWildCardMatching(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        //replace multiple * with one *
        //eg. a**b**c -> a*b*c
        int writeIndex = 0;
        boolean isFirst = true;

        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                }
            } else {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            }
        }

        boolean[][] T = new boolean[str.length + 1][writeIndex + 1];

        if (writeIndex > 0 && pattern[0] == '*') {
            T[0][1] = true;
        }

        T[0][0] = true;


        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '?' || str[i - 1] == pattern[j - 1]) {
                    T[i][j] = T[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    T[i][j] = T[i - 1][j] || T[i][j - 1];
                }
            }
        }

        return T[str.length][writeIndex];
    }
}
