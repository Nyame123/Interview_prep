package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Input : mat[][] = {{1, 3, 3},
 * {2, 1, 4},
 * {0, 6, 4}};
 * Output : 12
 * {(1,0)->(2,1)->(2,2)}
 * <p>
 * Input: mat[][] = { {1, 3, 1, 5},
 * {2, 2, 4, 1},
 * {5, 0, 2, 3},
 * {0, 6, 1, 2}};
 * Output : 16
 * (2,0) -> (1,1) -> (1,2) -> (0,3) OR
 * (2,0) -> (3,1) -> (2,2) -> (2,3)
 * <p>
 * Input : mat[][] = {{10, 33, 13, 15},
 * {22, 21, 04, 1},
 * {5, 0, 2, 3},
 * {0, 6, 14, 2}};
 * Output : 83
 * <p>
 * Given a gold mine of n*m dimensions. Each field
 * in this mine contains a positive integer which is the amount of gold in tons.
 * Initially the miner is at first column but can be at any row. He can move only (right->,right up /,right down\)
 * that is from a given cell, the miner can move to the cell diagonally up towards the right or right or
 * diagonally down towards the right. Find out maximum amount of gold he can collect.
 * <p>
 * https://www.geeksforgeeks.org/gold-mine-problem/
 **/
public class GoldMiner {

    static final int MAX = 100;

    public static void main(String[] args) {
        int gold[][] = {{10, 33, 13, 15}, {22, 21, 04, 1}, {5, 0, 2, 3}, {0, 6, 14, 2}};

        int m = 3, n = 3;
        System.out.print(maxGoldRecursive(gold, 0, 0, new HashMap<>()));
    }

    static int maxGoldRecursive(int[][] gold, int row, int col, Map<String, Integer> map) {
        String key = String.format("%d,%d", row, col);
        if (map.containsKey(key)) return map.get(key);
        if (col < 0 || col >= gold[0].length || row < 0 || row >= gold.length) return 0;

        int max = 0;
        for (int i = row; i < gold.length; i++) {
            int right = maxGoldRecursive(gold, i, col + 1, map);
            int right_up = maxGoldRecursive(gold, i - 1, col + 1, map);
            int right_down = maxGoldRecursive(gold, i + 1, col + 1, map);
            int total = (col >= gold[0].length) ? 0 : gold[i][col] + Math.max(right, Math.max(right_up, right_down));
            max = Math.max(total, max);
            //res[row] += total;
            map.put(key, max);
        }


        return max;
    }

    // Returns maximum amount of gold that
    // can be collected when journey started
    // from first column and moves allowed
    // are right, right-up and right-down
    static int getMaxGold(int gold[][]) {

        // Create a table for storing
        // intermediate results and initialize
        // all cells to 0. The first row of
        // goldMineTable gives the maximum
        // gold that the miner can collect
        // when starts that row

        int m = gold.length;
        int n = gold[0].length;
        int goldTable[][] = new int[m][n];

        /*for (int[] rows : goldTable)
            Arrays.fill(rows, 0);*/

        for (int col = n - 1; col >= 0; col--) {
            for (int row = 0; row < m; row++) {

                // Gold collected on going to
                // the cell on the right(->)
                int right = (col == n - 1) ? 0
                        : goldTable[row][col + 1];

                // Gold collected on going to
                // the cell to right up (/)
                int right_up = (row == 0 ||
                        col == n - 1) ? 0 :
                        goldTable[row - 1][col + 1];

                // Gold collected on going to
                // the cell to right down (\)
                int right_down = (row == m - 1
                        || col == n - 1) ? 0 :
                        goldTable[row + 1][col + 1];

                // Max gold collected from taking
                // either of the above 3 paths
                goldTable[row][col] = gold[row][col]
                        + Math.max(right, Math.max(right_up,
                        right_down));

            }
        }
        // The max amount of gold collected will be
        // the max value in first column of all rows
        int res = goldTable[0][0];

        for (int i = 1; i < m; i++)
            res = Math.max(res, goldTable[i][0]);

        return res;
    }
}
