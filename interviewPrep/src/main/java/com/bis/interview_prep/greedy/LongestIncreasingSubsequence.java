package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This problem was asked by Microsoft.
 * <p>
 * Given an array of numbers, find the length of
 * the longest increasing subsequence in the array. The subsequence does not necessarily have to be contiguous.
 * <p>
 * For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15],
 * the longest increasing subsequence has length 6: it is 0, 2, 6, 9, 11, 15.
 **/

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int res = longestSubsequenceUsingBinarySearch(arr);
        //List<Integer> res = printLongestSubsequence(arr);
        System.out.println(res);
    }

    private static int longestSubsequence(int[] arr) {
        int n = arr.length;
        int[] pileTop = new int[n];
        int[] pileLength = new int[n];

        Arrays.fill(pileTop, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i] < pileTop[j]) {
                    pileTop[j] = arr[i];
                    pileLength[j] += 1;
                    break;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, pileLength[i]);
        }

        return max;
    }

    private static int longestSubsequenceUsingBinarySearch(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] pileTop = new int[n];

        pileTop[0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > pileTop[len - 1]) {
                pileTop[len++] = arr[i];
            } else {
                int j = binarySearch(pileTop, 0, len - 1, arr[i]);

                if (j < 0) {
                    j = (-1 * j) - 1;
                }
                pileTop[j] = arr[i];
                // pileLength[j] += 1;
            }
        }


        return len;
    }

    private static int binarySearch(int[] pileTop, int i, int n, int x) {

        int l = i, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (pileTop[mid] == x) {
                return mid;
            } else if (pileTop[mid] < x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -(l + 1);
    }
}
