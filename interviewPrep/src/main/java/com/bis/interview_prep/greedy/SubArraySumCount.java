package com.bis.interview_prep.greedy;

import java.util.HashMap;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 **/
public class SubArraySumCount {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        int k = 2;
        int res = sumSubArrayCount(arr, k);
        System.out.println(res);
    }


    /**
     * We can use two pointers, the start and the end pointer.
     * We use a two nested loops with the outer one behaving as start and the
     * inner one behaving as end pointer.
     * <p>
     * Time Complexity = O(N^2)
     **/

    static int findSubArraySumCount(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            //start pointer
            int curSum = 0;
            for (int j = i; j < n; j++) {
                curSum += arr[j];
                if (curSum == k) {
                    count++;
                }
            }

        }

        return count;
    }


    /**
     * We can optimize the above solution to get better and efficient solution
     * using hashMap
     * <p>
     * Time Complexity = O(N)
     **/
    static int sumSubArrayCount(int[] arr, int k) {
        int count = 0;
        int n = arr.length;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            if (sum == k) {
                count++;
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}