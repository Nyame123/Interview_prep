package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Largest Number At Least Twice of Others
 * You are given an integer array nums where the largest integer is unique.
 * <p>
 * Determine whether the largest element in the array is at least twice as much as every other
 * number in the array. If it is, return the index of the largest element, or return -1 otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,6,1,0]
 * Output: 1
 * Explanation: 6 is the largest integer.
 * For every other number in the array x, 6 is at least twice as big as x.
 * The index of value 6 is 1, so we return 1.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: -1
 * Explanation: 4 is less than twice the value of 3, so we return -1.
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: 0
 * Explanation: 1 is trivially at least twice the value as any other number because there are no other numbers
 * Constraints:
 * <p>
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 100
 * The largest element in nums is unique
 **/
public class LargestNumberTwice {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,8};
        int pos = largestNumberTwice(arr);
        System.out.println(pos);
    }


    /**
     * 1. We find the maximum element in the array
     * 2. We cross-check the max element with all the other elements left
     * to check if max is at least twice as much as the rest of the elements
     *
     * Time Complexity = O(N)
     */
    private static int largestNumberTwice(int[] arr) {
        int n = arr.length;
        if (n == 1){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int maxPos = -1;

        for (int i = 0; i < n; i++) {
            if (max < arr[i]){
                max = arr[i];
                maxPos = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != maxPos && (arr[i] * 2) > max){
                return -1;
            }
        }

        return maxPos;
    }
}
