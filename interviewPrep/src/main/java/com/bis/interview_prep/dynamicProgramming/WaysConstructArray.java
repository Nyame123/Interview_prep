package com.bis.interview_prep.dynamicProgramming;

/**
 * Your goal is to find the number of ways to construct an array such that consecutive positions
 * contain different values.
 * Specifically, we want to construct an array with  elements such that each element between
 * and 1 and k, inclusive. We also want the first and last elements of the array to be 1 and x.
 * Given  n,k and x, find the number of ways to construct such an array. Since the answer may be large,
 * only find it modulo 10^9+7.
 **/
public class WaysConstructArray {


    public static void main(String[] args) {
        int n = 4, k = 3, x = 2;
        long result = countArray(n, k, x);
        System.out.println(result);

    }

    public static long countArray(int n, int k, int x) {
        // Return the number of ways to fill in the array.
        long[] notEndX = new long[n];
        long[] endX = new long[n];

        endX[0] = x == 1 ? 1L : 0L;
        notEndX[0] = x == 1 ? 0L : 1L;
        long MOD = 1000000007;

        for (int i = 1; i < n; i++) {
            endX[i] = notEndX[i - 1];
            notEndX[i] = (endX[i - 1] * (k - 1) + notEndX[i - 1] * (k - 2)) % MOD;
        }

        return endX[n - 1];
    }
}
