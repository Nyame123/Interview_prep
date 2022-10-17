package com.bis.interview_prep.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair of elements in the subset (i, j) satisfies either i % j = 0 or j % i = 0.
 *
 * For example, given the set [3, 5, 10, 20, 21], you should return [5, 10, 20].
 * Given [1, 3, 6, 24], return [1, 3, 6, 24].
 **/
public class LongestDivisibleSubset {

    public static void main(String[] args) {
        int[] arr = {18, 1, 3, 6, 13, 17};
        longestDivisible(arr);
        List<Integer> res = longestDivisibleSubset(arr);
        System.out.println(res);
    }

    //Longest Divisible subset
    static void longestDivisible(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int maxLength = 0;
        int maxIndex = 0;
        Arrays.sort(arr);
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0){
                    max = Math.max(max,dp[j]);
                }
            }

            dp[i] = max+1;
            if (maxLength < dp[i]){
                maxIndex = i;
                maxLength = dp[i];
            }
        }

        System.out.println(maxLength);

        List<Integer> res = new LinkedList<>();
        res.add(arr[maxIndex]);
        int lastDiv = arr[maxIndex];
        maxIndex--;
        maxLength--;
        while (maxIndex >= 0){
            if (dp[maxIndex] == maxLength && lastDiv % dp[maxIndex] == 0){
                res.add(arr[maxIndex]);
                lastDiv = arr[maxIndex];
                maxLength--;
            }

            maxIndex--;
        }

        System.out.println(res);
    }















    /**
     * In this problem, we can use dynamic programming to solve it.
     * 1. Sort the array in ascending order
     * 2. Use the idea from Longest Increasing subsequence to solve it
     * 3. Collect the elements that forms the longest divisible subset.
     *
     * Time Complexity = O(N^2)
     * Space Complexity = O(N)
     **/
    private static List<Integer> longestDivisibleSubset(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length;
        Arrays.sort(arr);
        int[] dp = new int[n];

        Arrays.fill(dp,1);
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0){
                    max = Math.max(max,dp[j]);
                }
            }

            dp[i] = max + 1;
            maxLen = Math.max(maxLen,dp[i]);
        }


        Integer prev = null;
        for (int i = n-1; i >= 0; i--) {
            if ((prev == null || prev % arr[i] == 0) && dp[i] == maxLen) {
                res.add(0,arr[i]);
                prev = arr[i];
                maxLen--;
            }
        }

        return res;
    }
}
