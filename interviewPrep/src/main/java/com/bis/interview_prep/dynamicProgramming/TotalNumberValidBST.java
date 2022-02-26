package com.bis.interview_prep.dynamicProgramming;
/**
 *Given N, Find the total number of unique BSTs that can be made using values from 1 to N.
 * Examples:
 *
 *
 * Input: n = 3
 * Output: 5
 * For n = 3, preorder traversal of Unique BSTs are:
 * 1. 1 2 3
 * 2. 1 3 2
 * 3. 2 1 3
 * 4. 3 1 2
 * 5. 3 2 1
 *
 * Input: 4
 * Output: 14
 *
 **/
public class TotalNumberValidBST {

    public static void main(String[] args) {
        int n = 3;
        int res = totalNumberValidBST(n);
        System.out.println(res);
    }

    static int totalNumberValidBST(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}

