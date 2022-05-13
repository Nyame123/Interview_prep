package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * You are given n pairs of numbers and asked to form a chain. Two pairs of numbers can create
 * a link in the chain if the second number in the first pair is less than the first number
 * in the second pair. Return the length of the longest chain you can form.
 * Note: You may use the pairs of numbers in any order and the first number in a pair is
 * always less than the second number in a pair.
 *
 * Ex: Given the following pairs...
 *
 * pairs = [[3,4], [5, 6], [7, 8]], return 3.
 * Ex: Given the following pairs...
 *
 * pairs = [[9, 14], [3, 5], [4, 7]], return 2.
 **/
public class MaximumLengthChain {

    public static void main(String[] args) {
        int[][] arr = {
                {3,4},
                {5, 6},
                {7, 8},
        };

        int longestChain = findLongestChain(arr);
        System.out.println(longestChain);
    }

    static int findLongestChain(int[][] arr){
        int n = arr.length;
        Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[1]));

        return longestChainDp(arr,1,1,new HashMap<>());
    }

    static int longestChainDp(int[][] arr, int index,int count, HashMap<String,Integer> memo){

        if(index == arr.length){
            return count;
        }

        String key = Arrays.toString(arr[index]);
        if(memo.containsKey(key)){
            return memo.get(key);
        }

        //consider
        int res = 0;
        if(arr[index][0] > arr[index-1][1]){
            res = longestChainDp(arr,index+1,count+1,memo);
        }

        // not considered
        int oldRes = longestChainDp(arr,index+1,count,memo);

        memo.put(key,Math.max(oldRes,res));
        return memo.get(key);
    }

    /**
     * In this problem, we can use greedy method.
     * 1. sort the array according to the second item.
     * 2. Compare to form a chain and get the longest chain
     *
     * Time Complexity = O(nLogn)
     **/
    static int maximumLengthChain(int[][] arr) {

        int n = arr.length;
        Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[1]));

        int max = 1;
        int s = arr[0][1];
        for (int i = 1; i < n; i++) {
            if (arr[i][0] > s){
                max++;
                s = arr[i][1];
            }
        }
        return max;
    }
}
