package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Given an integer array nums and a value, val, remove all instances of val in-place and return the length of the new array.
 * Note: It does not matter what values you leave in the array beyond the array’s new length.
 * Ex: Given the following nums and val...
 * <p>
 * nums = [1, 2, 3], val = 2, return 2 (after your modifications your array could look like [1, 3, 3]).
 **/
public class RemoveElementInArray {

    public static void main(String[] args) {
        int[] arr = {3, 2, 2, 3};
        int val = 3;
        int res = removeElementInArr(arr, val);
        System.out.println(res);
    }


    /**
     * Idea is to use two pointers approach.
     * 1. Left pointer pointing to the val element and the right pointer pointing to the non-val element to remove.
     * 2. The left pointer is moving forward and the right pointer is moving backward
     * 3. when the left pointer is on the val element and the right pointer is on the non-val element, then we
     * swap the content.
     * <p>
     * Time Complexity = O(N)
     **/
    static int removeElementInArr(int[] nums, int val) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (true) {
            while (r >= 0 && nums[r] == val) {
                r--;
            }

            while (l < n && nums[l] != val) {
                l++;
            }

            if (l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            } else {
                break;
            }
        }

        return l;
    }

    //another approach, same two pointer approach
    static int removeElement(int[] arr, int val) {
        int n = arr.length;
        int i = 0;
        while (i < n) {
            if (arr[i] == val) {
                arr[i] = arr[n - 1];
                n--;
            } else {
                i++;
            }
        }

        return n;
    }


    static int removeElements(int[] nums, int val) {
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}
