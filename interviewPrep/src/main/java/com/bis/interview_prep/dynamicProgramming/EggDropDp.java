package com.bis.interview_prep.dynamicProgramming;

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
        int floors = 6, eggs = 2;
        int minDrop = minimumEggDrop(floors, eggs);
        System.out.println(minDrop);
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
