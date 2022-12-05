package com.bis.interview_prep.dynamicProgramming;

public class BurstBalloons {

    public static void main(String[] args) {

        int[] arr = {3,1,5,8};
        int res = maxCoins(arr);
        System.out.println(res);
    }

    static int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int len = 1; len <= n; len++){
            for(int i = 0; i <= n-len; i++){
                int j = i+len-1;
                for(int k = i; k <= j; k++){
                    int left = 1;
                    int right = 1;
                    if(i != 0){
                        left = nums[i-1];
                    }

                    if(j != n-1){
                        right = nums[j+1];
                    }

                    int before = 0;
                    int after = 0;
                    if(k != i){
                        before = dp[i][k-1];
                    }

                    if(k != j){
                        after = dp[k+1][j];
                    }

                    dp[i][j] = Math.max(dp[i][j],before+after+ (left*nums[k]*right));
                }
            }
        }

        return dp[0][n-1];
    }
}
