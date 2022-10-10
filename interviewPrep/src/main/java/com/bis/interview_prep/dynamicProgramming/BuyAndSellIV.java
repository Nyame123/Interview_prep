package com.bis.interview_prep.dynamicProgramming;

/**
 * Given an array of numbers representing the stock prices of a company in chronological order and an integer k,
 * return the maximum profit you can make from k buys and sells. You must buy the stock before you can sell it,
 * and you must sell the stock before you can buy it again.
 * <p>
 * For example, given k = 2 and the array [5, 2, 4, 0, 1], you should return 3.
 **/
public class BuyAndSellIV {

    public static void main(String[] args) {
        int k = 2;
        int[] array = {3, 2, 6, 5, 0, 3};

        int profit = bestBuyNSellOptimize(array, k);

        System.out.println(profit);
    }


    /**
     * This problem can be solved best using dynamic programming.
     * 1. We use Dp to cache the solutions for the smaller problems
     * 2. Assuming we can know the best profit for a transaction ith in the kth day, then
     * in the future days, we can know the next best transaction up to the future date by comparing the
     * previous transaction ith in (k-1)th day and the current transaction.
     * <p>
     * Time Complexity = O(kN^2)
     * Space Complexity = O(kN)
     **/
    private static int bestBuyNSell(int[] array, int k) {
        int n = array.length;
        int[][] profit = new int[k + 1][n + 1];

        //In the first day, no matter how much transaction made, the profit is 0
        for (int i = 0; i <= k; i++) {
            profit[i][0] = 0;
        }

        //On the first transaction, the profit is 0
        for (int i = 0; i < n; i++) {
            profit[0][i] = 0;
        }

        for (int i = 1; i <= k; i++) {

            for (int j = 1; j < n; j++) {

                int curSum = 0;
                for (int m = 0; m < j; m++) {
                    curSum = Math.max(curSum, array[j] - array[m] + profit[i - 1][j - 1]);
                }

                profit[i][j] = Math.max(curSum, profit[i][j - 1]);
            }
        }
        return profit[k][n - 1];
    }

    /**
     * The above algorithm is good but not efficient. We can optimize the time complexity
     * by reducing the inner computation
     * <p>
     * Time Complexity = O(kN)
     * Space Complexity = O(kN)
     **/
    private static int bestBuyNSellOptimize(int[] array, int k) {
        int n = array.length;
        int[][] profit = new int[k + 1][n + 1];

        //In the first day, no matter how much transaction made, the profit is 0
        for (int i = 0; i <= k; i++) {
            profit[i][0] = 0;
        }

        //On the first transaction, the profit is 0
        for (int i = 0; i < n; i++) {
            profit[0][i] = 0;
        }

        for (int i = 1; i <= k; i++) {

            int prevDif = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {

                prevDif = Math.max(prevDif, profit[i - 1][j - 1] - array[j - 1]);

                profit[i][j] = Math.max(array[j] + prevDif, profit[i][j - 1]);
            }
        }
        return profit[k][n - 1];
    }
}
