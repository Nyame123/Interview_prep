package com.bis.interview_prep.greedy;
/**
 * Given an integer array, nums, return whether or not you can split the array into
 * three subarrays all of which have an equal sum.
 *
 * Ex: Given the following nums…
 *
 * nums = [2, 3, 9, 9, 3, 2, 3, 2, 9], return true.
 * Ex: Given the following nums…
 *
 * nums = [1, 2, 3], return false.
 **/
public class ThreeEqualPartition {

    public static void main(String[] args) {
        int[] arr = {1,-1,1,-1};
        boolean res = isPartitionPossible(arr);
        System.out.println(res);
    }

    /**
     *  1. Check if the total sum is divisible by 3.
     *  2. If the total sum is not divisible, return false.
     *  3. If it is divisible, the check for the sub array of sum
     *   = sum/3; and the second sub array of sum = 2*Sum/3.
     *
     *   Time Complexity =O(N)
     *   Space Complexity =O(N)
     *
     **/
    static boolean isPartitionPossible(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if (!(sum % 3 == 0)){
            return false;
        }

        int[] preFixSum = new int[n];
        int firstSubIndex = -1;
        preFixSum[0] = arr[0];
        int firstSum = sum/3;
        int secondSum = 2*(sum/3);

        if (preFixSum[0] == firstSum){
            firstSubIndex = 0;
        }
        for (int i = 1; i < n; i++) {
            preFixSum[i] = preFixSum[i-1] + arr[i];
            if (preFixSum[i] == firstSum && firstSubIndex == -1){
                firstSubIndex = i;
            }else if (preFixSum[i] == secondSum && firstSubIndex != -1 && i < n-1){
                return true;
            }
        }


        return false;
    }
}
