package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 *  Given an array of numbers, move all zeroes in the array to the end while maintaining the relative order of the other numbers.
 * Note: You must modify the array you’re given (i.e. you cannot create a new array).
 *
 * Ex: Given the following array nums…
 *
 * nums = [3, 7, 0, 5, 0, 2], rearrange nums to look like the following [3,7,5,2,0,0]
 **/
public class MoveZerosToEnd {

    public static void main(String[] args) {
        int[] nums = {3, 7, 0, 0, 5,2};
        moveZeroesFaster(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] +" ");
        }
    }

    static void moveZeroes(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[count++] = nums[i];
            }
        }

        while(count < nums.length){
            nums[count++] = 0;
        }
    }

    static void moveZeroesFaster(int[] nums) {
        int count = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                swap(nums,count,i);
                count++;
            }
        }
    }

    static void swap(int[] nums,int a, int b){
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
