package com.bis.interview_prep.dynamicProgramming;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning
 * if every substring of the partition is a palindrome. For example,
 * “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * Determine the fewest cuts needed for a palindrome partitioning of a given string.
 * For example, minimum of 3 cuts are needed for “ababbbabbababa”. The three cuts are
 * “a|babbbab|b|ababa”. If a string is a palindrome, then minimum 0 cuts are needed.
 * If a string of length n containing all different characters, then minimum n-1 cuts are needed.
 * <p>
 * Examples :
 * <p>
 * Input : str = “geek”
 * Output : 2
 * We need to make minimum 2 cuts, i.e., “g ee k”
 * Input : str = “aaaa”
 * Output : 0
 * The string is already a palindrome.
 * Input : str = “abcde”
 * Output : 4
 * Input : str = “abbac”
 * Output : 1
 **/
public class MinimumCutPalindromePartition {

    public static void main(String[] args) {
        String s = "ababbbabbababa";
        int minCut = palindromicPartitionOptimized(s);
        System.out.println(minCut);
    }

    /**
     * We can use the idea in Matrix Chain Multiplication Dp approach for this.
     * <p>
     * 1. We cut the word from the first position to the end and calculate
     * the cut that gives minimum cut for palindromic partition.
     * 2. If the length of the substring obtained from the cut is 1, then cut cost is zero and
     * palindromic and if the full substring or word is palindromic then the cut cost is zero too.
     * <p>
     * Time Complexity = O(N^3)
     * Space Complexity = O(N^2)
     **/
    static int palindromicPartition(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] sChars = s.toCharArray();
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                String substring = String.copyValueOf(sChars, i, len);
                if (isPalindromic(substring)) {
                    dp[i][j] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int cut = dp[i][k] + dp[k + 1][j];
                        min = Math.min(min, cut);
                    }
                    dp[i][j] = 1 + min;
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * An optimized solution for above O(N^3)
     *
     * Time Complexity = O(N^2)
     */
    static int palindromicPartitionOptimized(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        char[] sChars = s.toCharArray();
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (len == 2) {
                    dp[i][j] = sChars[i] == sChars[j];
                } else {
                    dp[i][j] = sChars[i] == sChars[j] && (dp[i + 1][j - 1]);
                }
            }
        }

        int[] cut = new int[n];
        for (int i = 0; i < n; i++) {
            if (dp[0][i]) {
                cut[i] = 0;
            } else {
                cut[i] = i;
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i] && 1 + cut[j] < cut[i]) {
                        cut[i] = cut[j] + 1;
                    }
                }
            }
        }
        return cut[n - 1];
    }

    static boolean isPalindromic(String s) {
        int right = s.length() - 1;
        int left = 0;

        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        }

        return true;
    }


    /**
     * We can optimize the above solution.
     * 1. We can precompute the palindromic substrings and reuse them.
     * 2. Starting from the len 0 to n, compute the min cut to make for every substring
     * to become palindromic.
     * <p>
     * Time Complexity = O(N^2)
     * Space Complexity = O(N^2)
     **/
    static int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] palindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int minCut = i;
            for (int j = 0; j <= i; j++) {
                if ((s.charAt(j) == s.charAt(i)) && (i - j < 2 || palindrome[j + 1][i - 1])) {
                    palindrome[j][i] = true;
                    minCut = Math.min(minCut, j == 0 ? 0 : dp[j - 1] + 1);
                }
            }

            dp[i] = minCut;
        }

        return dp[n - 1];
    }
}
