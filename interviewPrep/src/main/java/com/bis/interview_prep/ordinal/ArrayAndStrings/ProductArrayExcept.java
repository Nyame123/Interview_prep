package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Arrays;

/**
 * Given an integer array nums, return an array where each element i represents the product of all values in nums excluding nums[i].
 * Note: You may assume the product of all numbers within nums can safely fit within an integer range.
 *
 * Ex: Given the following array nums…
 *
 * nums = [1, 2, 3], return [6,3,2].
 * 6 = 3 * 2 (we exclude 1)
 * 3 = 3 * 1 (we exclude 2)
 * 2 = 2 * 1 (we exclude 3)
 *
 **/
public class ProductArrayExcept {

    public static void main(String[] args) {
        int[] nums = {-1,1,0,-3,3};
        int[] res = productExceptSelf(nums);
        for(int a: res){
            System.out.print(a+" ");
        }
    }

    static int[] productExceptSelf(int[] nums) {
        int n  = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = nums[0];

        for(int i = 1; i < n; i++){
            left[i] = left[i-1] * nums[i];
        }

        right[n-1] = nums[n-1];
        for(int i = n-2; i >= 0; i--){
            right[i] = right[i+1] * nums[i];
        }

        int[] prods = new int[n];
        Arrays.fill(prods,1);
        for(int i = 0; i < n; i++){
            int l = i-1;
            int r = i+1;
            if(l >= 0){
                prods[i] *= left[l];
            }

            if(r < n){
                prods[i] *= right[r];
            }
        }

        return prods;
    }
}
