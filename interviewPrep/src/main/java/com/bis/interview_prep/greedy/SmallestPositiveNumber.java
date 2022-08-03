package com.bis.interview_prep.greedy;
/**
 * Given a sorted array, find the smallest positive integer that is not the sum of a subset of the array.
 *
 * For example, for the input [1, 2, 3, 10], you should return 7.
 *
 * Do this in O(N) time.
 **/
public class SmallestPositiveNumber {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 10};
        int smallestPositive = findSmallestPositiveNo(arr);
        System.out.println(smallestPositive);
    }

    /**
     * The array is sorted already, so we can start checking from the
     * smallest positive element to see if it is missing from the
     * subset sum.
     * 1. Start with res = 1 and if the arr[i] <= res, res += arr[i]
     * 2. Else return the res as the answer.
     *
     * Time Complexity = O(N)
     **/
    static int findSmallestPositiveNo(int[] arr) {
        int n = arr.length;
        int res = 1;

        for (int i = 0; i < n; i++) {
            if (arr[i] <= res){
                res += arr[i];
            }else{
                return res;
            }
        }
        return res;
    }
}
