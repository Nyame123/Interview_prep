package com.bis.interview_prep.dynamicProgramming;

public class MinimumMoveReachTargetDp {

    public static void main(String[] args) {
        int target = 233, maxDouble = 2;
        int res = minMoves(target,maxDouble);
        System.out.println(res);
    }

    public static int minMovesBottomUp(int target, int maxDouble) {
        int[][] dp = new int[target+1][maxDouble+1];
        for (int i = 2; i <= target; i++) {
            for (int j = 0; j <= maxDouble; j++) {
                dp[i][j] = dp[i-1][j]+1;
                if (i % 2 == 0 && j > 0){
                    dp[i][j] = Math.min(dp[i][j],dp[i/2][j-1]+1);
                }
            }
        }

        return dp[target][maxDouble];
    }

    public static int minMoves(int target, int maxDouble) {
        int[][] dp = new int[target+1][maxDouble+1];
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j <= maxDouble; j++) {
                dp[i][j] = -1;
            }
        }

        return minMoves(target,maxDouble,dp);
    }

    //using recursion
    public static int minMoves(int target, int maxDouble,int[][] dp) {
        //base case
        if (target <= 1){
            return 0;
        }

        if (maxDouble <= 0){
            return target-1;
        }

        if (dp[target][maxDouble] >= 0){
            return dp[target][maxDouble];
        }

        int res = minMoves(target-1,maxDouble,dp) +1;
        if (target % 2 == 0){
            res = Math.min(res,minMoves(target/2,maxDouble-1,dp)+1);
        }

        dp[target][maxDouble] = res;
        return res;
    }
}
