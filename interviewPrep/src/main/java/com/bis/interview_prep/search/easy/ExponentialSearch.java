package com.bis.interview_prep.search.easy;

import java.util.Arrays;

/**
 * Exponential search involves two steps:
 *
 * Find range where element is present
 * Do Binary Search in above found range.
 * How to find the range where element may be present?
 * The idea is to start with subarray size 1, compare its last element with x, then try size 2, then 4 and so on until
 * last element of a subarray is not greater.
 * Once we find an index i (after repeated doubling of i), we know that the element must be present between i/2 and
 * i (Why i/2? because we could not find a greater value in previous iteration)
 * Given below are the implementations of above steps.
 *
 * https://www.geeksforgeeks.org/exponential-search/
 *
 * */
public class ExponentialSearch {

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 10, 40};
        int x = 10;
        int result = exponentialSearch(arr,
                arr.length, x);

        System.out.println((result < 0) ?
                "Element is not present in array" :
                "Element is present at index " +
                        result);
    }

    //Time complexity = O(logn)
    static int exponentialSearch(int[] arr,int n, int x){

        if (arr[0] == x){
            return 0;
        }

        //find the range for binary search
        int i = 1;
        while (i < n && arr[i] < x){
            i *= 2;
        }

        //perform binary search with the range found
        return Arrays.binarySearch(arr,i/2,Math.min(n,i),x);
    }
}
