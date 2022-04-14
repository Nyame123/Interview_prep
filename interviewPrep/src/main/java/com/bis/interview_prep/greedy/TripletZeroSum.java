package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given a list of integers, nums, return a list containing all triplets that sum to zero.
 * <p>
 * Ex: Given the following nums...
 * <p>
 * nums = [0], return [].
 * Ex: Given the following nums...
 * <p>
 * nums = [0, -1, 1, 1, 2, -2], return [[-2,0,2],[-2,1,1],[-1,0,1]].
 **/
public class TripletZeroSum {

    public static void main(String[] args) {
        int[] nums = {0, -1, 1, 1, 2, -2};
        List<List<Integer>> res = tripletZeroSum(nums);
        System.out.println(res);
    }

    /**
     * This problem can be solved by using cubic subArray count
     * We sum the three elements and if it is zero then we add it to
     * results.
     * <p>
     * Time Complexity = O(N^3)
     **/
    private static List<List<Integer>> tripletZeroSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        res.add(triplet);
                    }
                }
            }
        }

        return res;
    }

    /**
     * We can solve this problem too with quadratic time complexity
     * and using Hashset
     * Time Complexity = O(N^2)
     * Space Complexity = O(N)
     **/
    private static List<List<Integer>> tripletZeroSumHash(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int x = -(nums[i] + nums[j]);
                if (set.contains(x)) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(x);
                    res.add(triplet);
                } else {
                    set.add(nums[j]);
                }
            }
        }

        return res;
    }

    /**
     * Sort the array in ascending order
     * Using two pointer approach
     * 1. when the pointers sum to zero, traverse both pointers.
     * 2. When the sum < 0, increase the left pointer
     * 3. When the sum > 0, decrease the right pointer
     * <p>
     * Time Complexituy = O(N^2)
     **/
    private static List<List<Integer>> tripletZeroSumTwoPointer(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int left = i + 1;
            int right = n - 1;
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[left]);
                        triplet.add(nums[right]);
                        res.add(triplet);

                        while (nums[left] == nums[left+1])
                            left++;
                        while (nums[right] == nums[right-1])
                            right--;
                        left++;
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }
}
