package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 *Given an array of integers, nums, sort the array in any manner such that when i is even, nums[i]
 *  is even and when i is odd, nums[i] is odd.
 * Note: It is guaranteed that a valid sorting of nums exists.
 *
 * Ex: Given the following array nums…
 *
 * nums = [1, 2, 3, 4], one possible way to sort the array is [2,1,4,3]
 **/
public class ArrayShuffle_SortByParity {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int[] res = sortArrayByParityII(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(res[i]+" ");
        }

    }

    public int[] sortArrayByParity(int[] nums){

        int j = 1;
        for(int i = 0; i < nums.length; i+=2){
            if(nums[i] % 2 == 1){
                while(nums[j] % 2 == 1){
                    j += 2;
                }

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        return nums;
    }


    static int[] sortArrayByParityII(int[] nums) {

        int n = nums.length;
        int[] evn = new int[n/2];
        int[] odd = new int[n/2];
        int eC = 0, oC = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] % 2 == 0){
                evn[eC++] = nums[i];
            }else{
                odd[oC++] = nums[i];
            }
        }

        int index = 0;
        for(int i = 0; i < evn.length; i++){
            nums[index] = evn[i];
            index += 2;
        }

        index = 1;
        for(int i = 0; i < odd.length; i++){
            nums[index] = odd[i];
            index += 2;
        }

        return nums;

    }
}
