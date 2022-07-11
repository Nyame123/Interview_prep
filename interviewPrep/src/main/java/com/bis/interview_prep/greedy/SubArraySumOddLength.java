package com.bis.interview_prep.greedy;
/**
 * Given an integer array, nums, return the sum of all subarrays within nums that have an odd length.
 *
 * Ex: Given the following nums…
 *
 * nums = [1, 2, 3], return 12 ([1], [2], [3], [1, 2, 3] sum to 12).
 * Ex: Given the following nums…
 *
 * nums = [3, 1, 5, 2, 4], return 58.
 *
 **/
public class SubArraySumOddLength {

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 4};
        int sum = subArrSumOddLenOptimized(arr);
        System.out.println(sum);
    }

    /**
     * Sub array sum of odd length can be done by using
     * Sub array but we restrict the length to odd length.
     * 1. we start with len 1 and increment by 2.
     * 2. Sum all the elements in the valid subArray
     *
     * Time Complexity = O(N^3)
     **/
    static int subArrSumOddLen(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int len = 1; len <= n; len += 2) {

            for (int i = 0; i < n - (len - 1); i++) {

                for (int j = i; j < i + len; j++) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    /**
     * Optimized solution
     * 1. when the sub array is generated for the array, it could be found that
     * for every element at a position,i, there are i+1 choices on the left and n-i choices
     * on the right.
     * 2. Frequency = [((i+1)*(n-i) +1) / 2] * arr[i]
     *
     * Time Complexity = O(N)
     **/
    static int subArrSumOddLenOptimized(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int len = 0; len < n; len ++) {
            sum += (((len+1) * (n-len) + 1) / 2) * arr[len];
        }
        return sum;
    }
}
