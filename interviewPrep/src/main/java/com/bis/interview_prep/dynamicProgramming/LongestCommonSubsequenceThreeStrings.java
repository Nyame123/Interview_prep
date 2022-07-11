package com.bis.interview_prep.dynamicProgramming;

/**
 * Write a program that computes the length of the longest common
 * subsequence of three given strings. For example, given "epidemiologist",
 * "refrigeration", and "supercalifragilisticexpialodocious",
 * it should return 5, since the longest common subsequence is "eieio".
 */
public class LongestCommonSubsequenceThreeStrings {

    public static void main(String[] args) {
        String s1 = "epidemiologist", s2 = "refrigeration",
                s3 = "supercalifragilisticexpialodocious";

        int longestCommonLen = longestCommonSubsequence(s1, s2, s3);
        System.out.println(longestCommonLen);
    }

    /**
     * This problem can be done using same algorithm for the LCS for two strings
     * <p>
     * Time Complexity = O(N^3)
     * Space Complexity = O(N^3)
     **/
    static int longestCommonSubsequence(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();

        int[][][] dp = new int[n1 + 1][n2 + 1][n3 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                for (int k = 1; k <= n3; k++) {
                    if (s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(k-1)) {
                        dp[i][j][k] = 1 + dp[i - 1][j - 1][k - 1];
                    } else {
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j][k],
                                Math.max(dp[i][j - 1][k], dp[i][j][k - 1])
                        );
                    }
                }
            }
        }

        return dp[n1][n2][n3];
    }
}
