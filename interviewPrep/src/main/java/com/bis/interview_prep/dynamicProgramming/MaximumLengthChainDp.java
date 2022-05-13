package com.bis.interview_prep.dynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class MaximumLengthChainDp {
    public static void main(String[] args) {
        int[][] arr = {
                {3,4},
                {5, 6},
                {7, 8},
        };

        int longestChain = findLongestChain1(arr);
        System.out.println(longestChain);
    }

    static int findLongestChain1(int[][] arr){
        Arrays.sort(arr,Comparator.comparingInt((int[] a)->a[1]));
        int max = 0;
        HashMap<String,Integer> memo = new HashMap<>();

        max = maxLen(arr, 1, arr[0], memo)+1;
        return max;
    }

    static int maxLen(int[][] arr, int index, int[] pair, HashMap<String, Integer> memo){
        int n = arr.length;
        if(index >= n){
            return 0;
        }

        String key = Arrays.toString(arr[index]);

        if(memo.containsKey(key)){
            return memo.get(key);
        }

        int max = 0;

        if(pair[1] < arr[index][0]){

            max = maxLen(arr, index+1,arr[index], memo)+1;
            memo.put(key, max);
        }

        int len = maxLen(arr, index+1,pair, memo);
        memo.put(key, Math.max(len,max));


        return memo.get(key);
    }

    static int findLongestChain(int[][] arr){
        int n = arr.length;
        Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[1]));

        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i < n; i++){
            int max = 0;
            for(int j = 0; j < i; j++){
                if(arr[j][1] < arr[i][0]){
                    max = Math.max(max,dp[j]);
                }
            }

            dp[i] = max + 1;
        }

        int ans = 0;
        for(int i = 0; i < dp.length; i++){
            ans = Math.max(ans,dp[i]);
        }

        return ans;
    }
}
