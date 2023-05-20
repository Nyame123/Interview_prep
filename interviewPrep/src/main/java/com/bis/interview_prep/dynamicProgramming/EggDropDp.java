package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given N identical eggs and access to a building with k floors.
 * Your task is to find the lowest floor that will cause an egg to break,
 * if dropped from that floor. Once an egg breaks, it cannot be dropped again.
 * If an egg breaks when dropped from the xth floor, you can assume it will
 * also break when dropped from any floor greater than x.
 * <p>
 * Write an algorithm that finds the minimum number of trial drops
 * it will take, in the worst case, to identify this floor.
 * <p>
 * For example, if N = 1 and k = 5, we will need to try dropping the egg
 * at every floor, beginning with the first, until we reach the fifth floor,
 * so our solution will be 5.
 **/
public class EggDropDp {

    public static void main(String[] args) {
        int floors = 3, eggs = 2;
        int minDrop = minimumEggDropRecursive(floors, eggs);
        System.out.println(minDrop);
    }


    //Recursive approach for egg drop
    static int minimumEggDropRecursive(int n, int egg) {
        return miniEggDropRec(n, egg, new HashMap<>());
    }

    static int miniEggDropRec(int n, int egg, Map<String, Integer> memo) {
        //base case
        String key = String.format("%s,%s", n, egg);
        if (memo.containsKey(key))
            return memo.get(key);

        if (n == 1 || n == 0)
            return n;

        if (egg == 1)
            return n;

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int res = Math.max(
                    miniEggDropRec(i - 1, egg - 1, memo), //Egg breaks
                    miniEggDropRec(n - i, egg, memo) //Egg does not break
            );

            min = Math.min(min, res);
        }

        memo.put(key, min + 1);

        return min + 1;
    }

    /**
     * We are to find the minimum drops that gets an egg break and we use DP.
     * 1. We cover two cases here.
     * 2. We have when the egg breaks on the floor
     * 3. When the egg does not break on the floor.
     * 4. We break the problem down into sub-problems
     * <p>
     * Time Complexity = O(N^2*E)
     * Space Complexity = O(N*E)
     **/
    static int minimumEggDrop(int floors, int eggs) {
        int[][] dp = new int[eggs + 1][floors + 1];

        for (int i = 1; i <= eggs; i++) {
            for (int j = 1; j <= floors; j++) {
                //base cases
                if (i == 1) {
                    //only one egg
                    dp[i][j] = j;
                } else if (j == 1) {
                    //only one floor
                    dp[i][j] = 1;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = 1; k <= j; k++) {
                        //when egg breaks or does not break cases
                        int max = Math.max(
                                dp[i - 1][k - 1], //egg breaks
                                dp[i][j - k] //egg does not break
                        );
                        min = Math.min(max, min);
                    }

                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[eggs][floors];
    }
}
