package com.bis.interview_prep.greedy;
/**
 * Given a non-empty integer array, nums, return the minimum number of moves to make all values in nums equal.
 * A move consists of incrementing all but one element in the array by one.
 *
 * Ex: Given the following nums...
 *
 * nums = [1, 2, 3], return 3. [1, 2, 3] -> [2, 3, 3] -> [3, 4, 3] -> [4, 4, 4].
 **/
public class EqualArray {

    public static void main(String[] args) {
        int[] nums = {4,3,4};
        int res = minimumOperations(nums);
        System.out.println(res);
    }


    /**
     * 1. Find the total sum of the array.
     * 2. Find the smallest number in the array.
     * 3. Op = sum - smallest*n
     *
     *Time Complexity = O(N)
     * Space Complexity =O(1)
     **/
    static int minimumOperations(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int smallest = nums[0];

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            smallest = Math.min(smallest,nums[i]);
        }


        return sum - (smallest*n);
    }
}
