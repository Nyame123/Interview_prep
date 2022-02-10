package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a multiset of integers, return whether it can be partitioned into two subsets whose sums are the same.
 * <p>
 * For example, given the multiset {15, 5, 20, 10, 35, 15, 10}, it would return true, since
 * we can split it up into {15, 5, 10, 15, 10} and {20, 35}, which both add up to 55.
 * <p>
 * Given the multiset {15, 5, 20, 10, 35}, it would return false, since we can't split it up into
 * two subsets that add up to the same sum.
 **/
public class MultisetParitionDp {

    public static void main(String[] args) {
        int arr[] = {15, 5, 20, 10, 35, 15, 10};
        int n = arr.length;

        // Function call
        if (findPartition(arr, n))
            System.out.println("Can be divided into two "
                    + "subsets of equal sum");
        else
            System.out.println(
                    "Can not be divided into "
                            + "two subsets of equal sum");
    }

    static boolean findPartition(int[] arr, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if (sum % 2 != 0) {
            return false;
        } else {
            sum /= 2;
            return partitionDp(arr, sum);
        }
    }

    static boolean partitionDp(int[] arr, int sum) {

        boolean[] parts = new boolean[sum+1];
        for (int i = 0; i < arr.length; i++) {
            parts[0] = true;
            for (int j = sum; j >= arr[i]; j--) {
                if (parts[j-arr[i]]){
                    parts[j] = true;
                }
            }
        }

        return parts[sum];
    }

    static boolean partitionRec(int[] arr, int i, int sum, Map<Integer, Boolean> map) {

        if (map.containsKey(sum)) {
            return map.get(sum);
        }
        if (sum == 0) {
            return true;
        }

        if (sum < 0 || i >= arr.length) {
            return false;
        }

        boolean res =  partitionRec(arr, i + 1, sum - arr[i],map) || partitionRec(arr, i + 1, sum,map);
        map.put(sum,res);
        return res;

    }

}
