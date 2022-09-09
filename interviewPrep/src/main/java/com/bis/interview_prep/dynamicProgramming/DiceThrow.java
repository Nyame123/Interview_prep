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
        int face = 6, numberOfTimes = 3, total = 7;
        int numWays = findWaysTopDown(face, numberOfTimes, total);
        System.out.println(numWays);
    }

    private static int diceThrowBottomUp(int face, int numberOfTimes, int total) {
        int[][] dp = new int[numberOfTimes + 1][total + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= numberOfTimes; i++) {

            for (int j = i; j <= total; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                if (j - face - 1 >= 0) {
                    dp[i][j] -= dp[i][j - face - 1];
                }
            }
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

        memo.put(key,numberOfWays);
        return numberOfWays;
    }
}
