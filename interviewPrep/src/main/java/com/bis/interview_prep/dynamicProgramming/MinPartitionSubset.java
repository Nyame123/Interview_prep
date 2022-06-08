package com.bis.interview_prep.dynamicProgramming;
/**
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the
 * absolute difference between their sums is minimum.
 * If there is a set S with n elements, then if we assume Subset1 has m elements,
 * Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
 *
 * Example:
 *
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 **/
public class MinPartitionSubset {

    public static void main(String[] args) {
        int[] arr = {1, 6, 11, 5};
        int minDif = minPartitionSubsetDp(arr);
        System.out.println(minDif);
    }

    /**
     * We can use Recursive approach here to set it up
     * 1. we find the total sum
     * 2. we recurse from the index 0, taking element  or not taking element and
     * finding the min difference in all the possible solutions.
     *
     * Time Complexity = O(2^n)
     **/
    private static int minPartitionSubsetDif(int[] arr) {

        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        return minFindPartitionSubset(arr,n,0,sum);
    }

    private static int minFindPartitionSubset(int[] arr, int n, int subtotal, int sum) {

        if (n == 0){
            return Math.abs((sum-subtotal) - subtotal);
        }

        return Math.min(
                minFindPartitionSubset(arr,n-1,subtotal+arr[n-1],sum), // include the element,
                minFindPartitionSubset(arr, n-1,subtotal,sum) //exclude the element
        );
    }


    /**
     * We can use a dynamic programming approach here
     *
     **/
    static int minPartitionSubsetDp(int[] arr){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        boolean[][] dp = new boolean[n+1][sum+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j]; //excluding the element

                if (arr[i-1] <= j){
                    dp[i][j] |= dp[i][j-arr[i-1]];
                }
            }
        }

        int dif = Integer.MAX_VALUE;
        for (int i = sum/2; i >= 0; i--) {
            if (dp[n][i]){
                dif = sum - 2*i;
                break;
            }
        }

        return dif;
    }
}
