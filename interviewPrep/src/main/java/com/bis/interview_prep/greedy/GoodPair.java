package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 *Given an integer array that is sorted in ascending order and a value target,
 *  return two unique indices (one based) such that the values at those indices sums to the given target.
 * Note: If no two such indices exist, return null.
 *
 * Ex: Given the following nums and target…
 *
 * nums = [1, 2, 5, 7, 9], target = 10, return [1,5].
 * Ex: Given the following nums and target…
 *
 * nums = [1, 3, 8], target = 13, return null.
 *
 **/
public class GoodPair {

    public static void main(String[] args) {
        int[] nums = {1, 3, 8};
        int target = 13;
        int[] indices = goodPair(nums,target);
        System.out.println(Arrays.toString(indices));
    }

    /**
     * In this problem, the integers are already sorted, so we can use two pointer approach algorithm.
     * 1. When the sum of the elements at the two pointers are same as the target, we found the solution.
     * 2. When the sum of the elements at the two pointers are less than the target, we increase the left pointer.
     * 3. When the sum of the elements at the two pointers are greater than the target, we decrease the right pointer.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(1)
     **/
    static int[] goodPair(int[] nums, int target) {
        int n = nums.length;
        int i = 0, j = n-1;
        while (i < j){
            int sum = nums[i] + nums[j];
            if(sum < target){
                i++;
            }else if (sum > target){
                j--;
            }else{
               return new int[]{i+1,j+1};
            }
        }

        return null;
    }
}
