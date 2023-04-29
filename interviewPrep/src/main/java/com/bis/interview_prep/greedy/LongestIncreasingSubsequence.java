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

/**
 * NLogN version of Longest Increasing Subsequence
 */
class LongestIncreasingSubsequenceLogN {

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int res = findLongestIncreasingSubsequence(arr);
        //List<Integer> res = printLongestSubsequence(arr);
        System.out.println(res);
    }


    static int findLongestIncreasingSubsequence(int[] inputs) {
        int n = inputs.length;

        if (n == 0)
            return 0;

        int len = 0;
        int[] T = new int[n];
        int[] results = new int[n];

        Arrays.fill(results, -1);
        T[0] = 0;

        for (int i = 1; i < n; i++) {
            if (inputs[i] < inputs[T[0]]) { //when the input[i] is less than first element
                T[0] = i;
            } else if (inputs[i] > inputs[T[len]]) { //when the input[i] is greater than the last element
                len++;
                T[len] = i;
                results[i] = T[len - 1];
            } else { //search for the element in the sorted indices
                int index = ceilIndex(inputs, T, len, inputs[i]);
                T[index] = i;
                results[i] = T[index-1];
            }
        }

        int end = T[len];
        while (end != -1){
            System.out.print(inputs[end] +" ");
            end = results[end];
        }

        System.out.println();
        return len+1;
    }

    static int ceilIndex(int[] inputs, int[] T, int end, int key) {
        int start = 0;
        int len = end;
        while (start <= end) {
            int middle = start + (end - start) / 2;

            if (middle < len && inputs[T[middle]] < key && inputs[T[middle+1]] >= key){
                return middle+1;
            }else if (inputs[T[middle]] < key){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }

        return -1;
    }
}
