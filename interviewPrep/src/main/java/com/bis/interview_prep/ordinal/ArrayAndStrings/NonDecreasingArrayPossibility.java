package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 *This problem was asked by Facebook.
 *
 * Given an array of integers, write a function to determine whether the array could become
 * non-decreasing by modifying at most 1 element.
 *
 * For example, given the array [10, 5, 7], you should return true, since we can modify the
 * 10 into a 1 to make the array non-decreasing.
 *
 * Given the array [10, 5, 1], you should return false, since we can't modify any one element to get a non-decreasing array.
 *
 **/
public class NonDecreasingArrayPossibility {

    public static void main(String[] args) {

        int[] arr = {1,4,1,2};
        System.out.println(checkPossibility(arr));
    }

    static boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i=1; i < n && count <=1; i++){
            if(nums[i-1] > nums[i]){
                count++;

                if(i-2 < 0 || nums[i-2] <= nums[i])
                    nums[i-1] = nums[i];
                else
                    nums[i] = nums[i-1];
            }
        }

        return count <= 1;
    }
}
