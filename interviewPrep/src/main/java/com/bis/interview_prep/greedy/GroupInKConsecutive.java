package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an integer array, nums, and an integer, k, return whether or
 * not it’s possible to divide nums into groups of size k where every
 * group contains consecutive numbers.
 * <p>
 * Ex: Given the following nums and k…
 * <p>
 * nums = [1, 2, 2, 1], k = 2, return true ([1, 2] and [1, 2]).
 * Ex: Given the following nums and k…
 * <p>
 * nums = [1, 2, 3, 3], k = 2, return false.
 **/
public class GroupInKConsecutive {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,3,3};
        int k = 3;

        boolean res = groupInKConsecutive(nums, k);
        System.out.println(res);
    }

    /**
     * We check if the array numbers can be grouped equally into K consecutive numbers.
     * 1. We store the frequency of the elements in a hashMap
     * 2. We traverse over the keys making sure that there is a consecutive elements in the array.
     * 3. If yes, return true else return false;
     * <p>
     * Time Complexity = O(N*LogN)
     * Space Complexity = O(N)
     **/
    static boolean groupInKConsecutive(int[] nums, int k) {

        int n = nums.length;
        if (n % k != 0) {
            return false;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();
            if (value > 0) {
                for (int i = 0; i < k; i++) {

                    if (!map.containsKey(key + i)) {
                        return false;
                    }

                    map.put(key + i, map.get(key + i) - value);

                    if (map.get(key + i) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : nums) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        Arrays.sort(nums);
        for (int a : nums) {
            if (map.get(a) > 0) {
                for (int i = 0; i < k; i++) {
                    if (!map.containsKey(a + i) || map.get(a + i) == 0) {
                        return false;
                    } else {
                        map.put(a + i, map.get(a + i) - 1);
                    }
                }
            }
        }
        return true;
    }
}
