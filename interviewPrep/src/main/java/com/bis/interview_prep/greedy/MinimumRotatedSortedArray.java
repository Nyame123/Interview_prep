package com.bis.interview_prep.greedy;

/**
 *Given an sorted integer array, nums, that has been rotated at an unknown index, return the minimum value.
 * Note: The array only contains unique values.
 *
 * Ex: Given the following array nums...
 *
 * nums = [7, 9, 10, 2, 4, 6], return 2.
 *
 **/
public class MinimumRotatedSortedArray {


    public static void main(String[] args) {

    }

    public int findMin(int[] nums) {
        int n = nums.length;

        if(n == 1){
            return nums[0];
        }

        int left = 0, right = n-1;

        if(nums[right] > nums[0]){
            return nums[0];
        }

        while(left < right){
            int mid = left + (right - left) /2;

            if(nums[mid+1] < nums[mid]){
                return nums[mid+1];
            }

            if(nums[mid] < nums[mid-1]){
                return nums[mid];
            }

            if(nums[mid] > nums[0]){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        return -1;
    }

    public int findMin1(int[] nums) {
        int min = nums[0];

        int n = nums.length;
        for(int i = 1; i < n; i++){
            if(nums[i-1] > nums[i]){
                return nums[i];
            }
        }

        return min;
    }
}
