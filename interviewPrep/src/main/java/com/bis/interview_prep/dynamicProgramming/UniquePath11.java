package com.bis.interview_prep.dynamicProgramming;

public class UniquePath11 {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0},
                {1, 1},
                {0, 0}
        };

        int res = uniquePathsWithObstacles(grid);
        System.out.println(res);
    }

    static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        if(obstacleGrid[n-1][m-1] == 1){
            return 0;
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] == 0){
                dp[i][0] = (i == 0 || dp[i-1][0] == 1)? 1: 0;
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] == 0){
                dp[0][i] = (i == 0 || dp[0][i-1] == 1)? 1: 0;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[n-1][m-1];
    }
}
