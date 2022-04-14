package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;

/**
 * You are given a 2-d matrix where each cell represents number of coins in that cell.
 * Assuming we start at matrix[0][0], and can only move right or down,
 * find the maximum number of coins you can collect by the bottom right corner.
 * <p>
 * For example, in this matrix
 * <p>
 * 0 3 1 1
 * 2 0 0 4
 * 1 5 3 1
 * The most we can collect is 0 + 2 + 1 + 5 + 3 + 1 = 12 coins.
 **/
public class MaxCoinPicking {

    public static void main(String[] args) {
        int[][] grid = {
                new int[]{0, 3, 1, 1},
                new int[]{2, 0, 0, 4},
                new int[]{1, 5, 3, 1},
        };

        int maxCoins = maxCoinBottomUp(grid, 0, 0);
        System.out.println(maxCoins);
    }

    /**
     * Using Top Down approach in recursive manner
     */
    static int maxCoinPickDp(int[][] grid, int r, int c) {

        return maxCoinPickDp(grid, r, c, new HashMap<>());
    }

    static int maxCoinPickDp(int[][] grid, int r, int c, HashMap<String, Integer> memo) {
        String key = String.format("%s,%s", r, c);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        //base case
        if (c >= grid[0].length || r >= grid.length) {
            return 0;
        }
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return grid[r][c];
        }

        int rightMax = maxCoinPickDp(grid, r, c + 1, memo);
        int downMax = maxCoinPickDp(grid, r + 1, c, memo);

        int max = Math.max(rightMax, downMax) + grid[r][c];
        memo.put(key, max);
        return max;
    }


    /**
     * using Bottom Up approach.
     **/
    static int maxCoinBottomUp(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = grid[i][0];
        }
        for (int i = 0; i < m; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }

        return dp[n - 1][m - 1];
    }

    static int maxCoinBottomUp(int[][] grid, int r, int c) {

        return maxCoinBottomUp(grid);
    }
}
