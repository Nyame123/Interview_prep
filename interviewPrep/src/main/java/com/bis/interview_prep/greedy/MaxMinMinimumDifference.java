package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * You will be given a list of integers, arr, and a single integer k.
 * You must create an array of length  from elements of  such that its unfairness is minimized. Call that array arr'.
 * Unfairness of an array is calculated as max(arr')-min(arr');
 *
 * eg. arr = [1,4,7,2] and k = 2
 * */
public class MaxMinMinimumDifference {

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(1,4,7,2);
        int k = 2;
        long res = maxMin(k,arr);
        System.out.print(res);
    }

    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here

        //sort the array in increasing order
        Collections.sort(arr);
        int n = arr.size();
        int ans = Integer.MAX_VALUE;

        System.out.println(arr);
        k--;
        for(int i = k; i < n; i++){

            int max = arr.get(i);
            int min = arr.get(i-k);
            int newAns = max-min;
            //System.out.printf("Ans %d, Min %d, Max %d\n",newAns,min,max);
            ans = Math.min(ans,newAns);
        }

        return ans;
    }
}
