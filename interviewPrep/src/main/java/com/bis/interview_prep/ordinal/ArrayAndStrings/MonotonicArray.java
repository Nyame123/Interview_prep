package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Given an array nums, return whether or not its values are monotonically increasing or monotonically decreasing.
 * Note: An array is monotonically increasing if for all values i <= j, nums[i] <= nums[j]. Similarly an array
 * is monotonically decreasing if for all values i <= j, nums[i] >= nums[j].
 **/
public class MonotonicArray {

    public static void main(String[] args) {

        int[] nums = {8, 4, 6};

        boolean res = isMonotonic(nums);
        System.out.print(res);
    }

    static boolean isMonotonic(int[] nums) {
        boolean increasing = true;
        boolean decreasing = true;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                increasing = false;
            }

            if (nums[i] < nums[i + 1]) {
                decreasing = false;
            }
        }

        return increasing || decreasing;
    }

    public boolean isMonotonic1(int[] nums) {
        boolean increasing = false;
        int n = nums.length;

        int i = 0;
        int data = nums[0];
        i++;
        while (i < n && data == nums[i]) {
            i++;
        }

        if (i == n) {
            return true;
        }

        increasing = data < nums[i];

        for (; i < n - 1; i++) {
            if (increasing) {
                if (nums[i] > nums[i + 1]) {
                    return false;
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    return false;
                }
            }

        }

        return true;
    }
}
