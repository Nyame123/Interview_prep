package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function, throw_dice(N, faces, total),
 * that determines how many ways it is possible to throw N dice with some number of faces each to get a specific total.
 * <p>
 * For example, throw_dice(3, 6, 7) should equal 15.
 **/
public class DiceThrow {

    public static void main(String[] args) {
        int face = 6, numberOfTimes = 3, total = 9;
        long numWays = diceThrowBottomUp(face, numberOfTimes, total);
        System.out.println(numWays);
    }

    public static long findWays(int m, int n, int x) {

    /* Create a table to store the results of subproblems.
    One extra row and column are used for simplicity
    (Number of dice is directly used as row index and sum is directly used as column index).
    The entries in 0th row and 0th column are never used. */
        long[][] table = new long[n + 1][x + 1];

        /* Table entries for only one dice */
        for (int j = 1; j <= m && j <= x; j++)
            table[1][j] = 1;

    /* Fill rest of the entries in table using recursive relation
    i: number of dice, j: sum */
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                for (int k = 1; k < j && k <= m; k++)
                    table[i][j] += table[i - 1][j - k];
            }
        }

         // Uncomment these lines to see content of table
        for(int i = 0; i< n+1; i++){
            for(int j = 0; j< x+1; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }

        return table[n][x];
    }

    private static int diceThrowBottomUp(int face, int numberOfTimes, int total) {
        int[][] dp = new int[numberOfTimes + 1][total + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= numberOfTimes; i++) {

            for (int j = i; j <= total; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                if (j - face - 1 >= 0) {
                    dp[i][j] -= dp[i-1][j - face - 1];
                }
            }
        }

        // Uncomment these lines to see content of table
        for(int i = 0; i< numberOfTimes+1; i++){
            for(int j = 0; j< total+1; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }


        return dp[numberOfTimes][total];
    }

    static int findWaysTopDown(int faces, int numTimes, int total) {
        return findWays(faces, numTimes, total, new HashMap<>());
    }

    static int findWays(int faces, int numTimes, int total, Map<String, Integer> memo) {
        // Base cases
        String key = String.format("%s,%s", numTimes, total);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (total < 1) return 0;
        if (numTimes == 1) return (total <= faces) ? 1 : 0;

        int i, numberOfWays = 0;
        for (i = 1; i <= faces; ++i)
            numberOfWays += findWays(faces, numTimes - 1, total - i, memo);

        memo.put(key, numberOfWays);
        return numberOfWays;
    }
}
