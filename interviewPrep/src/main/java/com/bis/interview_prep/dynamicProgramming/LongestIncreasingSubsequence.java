package com.bis.interview_prep.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        int res = longestSubsequenceRec(arr);
        //List<Integer> res = printLongestSubsequence(arr);
        System.out.println(res);
    }

    static int longestSubsequenceRec(int[] arr) {
        return longestSubseqRec(arr, 0, Integer.MIN_VALUE, new HashMap<>());
    }

    private static int longestSubseqRec(int[] arr, int cur, int st, Map<Integer,Integer> map) {
        if (map.containsKey(cur))
            map.get(cur);
        if (cur == arr.length) {
            return 0;
        }

        int nonTake = longestSubseqRec(arr, cur + 1, st,map);
        int take = 0;
        if (st < arr[cur])
             take = 1+longestSubseqRec(arr, cur + 1, arr[cur],map);

        int res = Math.max(nonTake,take);
        map.put(cur,res);
        return map.get(cur);
    }

    static int longestSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i] && dp[j] > dp[i]) {
                    dp[i] = dp[j];
                }
            }

            dp[i]++;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }

        return dp[n - 1];
    }

    static List<Integer> printLongestSubsequence(int[] arr) {
        int n = arr.length;
        ArrayList<Integer>[] dp = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[0].add(arr[0]);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i] && dp[j].size() > dp[i].size()) {
                    dp[i] = new ArrayList<>(dp[j]);
                }
            }

            dp[i].add(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(dp[i]);
        }

        return dp[n - 1];
    }
}
