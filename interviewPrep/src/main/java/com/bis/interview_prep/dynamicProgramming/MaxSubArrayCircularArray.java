package com.bis.interview_prep.dynamicProgramming;
/**
 * Given a circular array, compute its maximum subarray sum
 * in O(n) time. A subarray can be empty, and in this case the sum is 0.
 *
 * For example, given [8, -1, 3, 4], return 15 as we choose
 * the numbers 3, 4, and 8 where the 8 is obtained from wrapping around.
 *
 * Given [-4, 5, 1, 0], return 6 as we choose the numbers 5 and 1.
 **/
public class MaxSubArrayCircularArray {

    public static void main(String[] args) {
        int[] arr = {8, -1, 3, 4};
        int maxSum = maxSubArrayCircular(arr);
        System.out.println(maxSum);
    }

    /**
     * we use kadane algorithm here.
     * 1. Compute the total sum
     * 2. Compute the minSumArr and maxSumArr
     * 3. Return the max result of the two
     *
     * Time Complexity = O(N)
     **/
    static int maxSubArrayCircular(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        int curMaxSum = arr[0], maxSumSoFar = arr[0],
                curMinSum = arr[0], minSumSoFar = arr[0];

        for (int i = 1; i < n; i++) {
            curMaxSum = Math.max(curMaxSum+arr[i],arr[i]);
            maxSumSoFar = Math.max(maxSumSoFar,curMaxSum);

            curMinSum = Math.min(curMinSum+arr[i],arr[i]);
            minSumSoFar = Math.min(curMinSum,minSumSoFar);
        }

        return Math.max(maxSumSoFar,sum-minSumSoFar);
    }
}
