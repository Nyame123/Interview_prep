package com.bis.interview_prep.dynamicProgramming;
/**
 * Given an integer array nums, find a
 * subarray
 *  that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 **/
public class MaximumProductSubArray {

    public static void main(String[] args) {
       int[] nums = {2,3,-2,4};
       int res = maxProduct(nums);
       System.out.println(res);
    }

    static int maxProduct(int[] nums) {

        int min = 1;
        int max = 1;
        int n = nums.length;
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            result = Math.max(result,nums[i]);
        }

        for(int i = 0; i < n; i ++){
            int newMin = min * nums[i];
            int newMax = max * nums[i];

            max = max(nums[i],newMax,newMin);
            min = min(nums[i],newMax,newMin);

            result = max(result,max,min);
        }

        return result;
    }

    static int max(int a, int b, int c){
        return Math.max(a,Math.max(b,c));
    }

    static int min(int a, int b, int c){
        return Math.min(a,Math.min(b,c));
    }
}
