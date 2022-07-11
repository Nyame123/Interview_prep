package com.bis.interview_prep.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 * Example 1:
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 *
 *
 * Follow up: What if the input numbers come in one by one as an infinite st
 **/
public class MaxConsecutiveOnes {

    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0,0};
        int maxOnes = maxConsecutiveOnesOptimized(nums);

        System.out.println(maxOnes);
    }

    /**
     * 1. We use the sliding windows algorithm to count the maximum consecutive ones in the array.
     *
     * Time Complexity = O(N^2)
     **/
    private static int maxConsecutiveOnes(int[] nums) {
        int n = nums.length;

        boolean zeroConsumed = false;
        int max = 0;
        int startIndex = 0;
        int endingIndex = 0;
        // result = endingIndex - startingIndex
        // 1,0,1,1,0,1 ; startIndex = 0, and endingIndex = 4; max = 4 - 0;
        while (endingIndex < n) {
            if (nums[endingIndex] != 1) {
                if (zeroConsumed) { //move to the next index of zero in the set
                    max = Math.max(max, endingIndex - startIndex);
                    startIndex++;
                    endingIndex = startIndex;
                    zeroConsumed = false;

                    if (nums[endingIndex] == 0) {
                        zeroConsumed = true;
                    }

                } else {
                    zeroConsumed = true;
                }
            }
            endingIndex++;
        }

        max = Math.max(max,endingIndex - startIndex);

        return max;
    }

    /**
     * 1. We use the sliding windows algorithm to count the maximum consecutive ones in the array.
     *
     * Time Complexity = O(N)
     **/
    static int maxConsecutiveOnesOptimized(int[] nums) {
        int n = nums.length;
        int k = 1;
        int endingIndex = 0;
        int startIndex = 0;
        // result = endingIndex - startingIndex
        // 1,0,1,1,0,1 ; startIndex = 0, and endingIndex = 4; max = 4 - 0;
        while (endingIndex < n) {
            if (nums[endingIndex] == 0) {
                k--;
            }

            if (k < 0){
                k += 1 - nums[startIndex];
                startIndex++;
            }
            endingIndex++;
        }

        return endingIndex - startIndex;
    }
}
