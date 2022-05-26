package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array, nums, return a list containing all subsets of nums
 * that contain at least two elements and have an increasing sequence.
 * <p>
 * Ex: Given the following nums…
 * <p>
 * nums = [1, 2, 3], return [[1,2],[1,2,3],[1,3],[2,3]].
 * Ex: Given the following nums…
 * <p>
 * nums = [2, 4, 3, 5], return [[2,4],[2,4,5],[2,3],[2,3,5],[2,5],[4,5],[3,5]].
 **/
public class BiggerAndBigger {

    public static void main(String[] args) {
        int[] nums = {2, 4, 3, 5,6};
        List<List<Integer>> res = increasingSubsequenceSubset(nums);
        System.out.println(res);
    }

    /**
     * In this problem, we can use an idea of substring to generate these.
     * <p>
     * Time Complexity = O(N^2)
     **/
    private static List<List<Integer>> increasingSubsequenceSubset(int[] nums) {
        int n = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int len = res.size();
            for (int j = 0; j < len; j++) {
                int clen = res.get(j).size();
                if (nums[i] > res.get(j).get(clen - 1)) {
                    List<Integer> child = new ArrayList<>(res.get(j));
                    child.add(nums[i]);
                    res.add(child);
                }
            }
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    List<Integer> child = new ArrayList<>();
                    child.add(nums[j]);
                    child.add(nums[i]);
                    res.add(child);
                }
            }
        }
        return res;
    }
}
