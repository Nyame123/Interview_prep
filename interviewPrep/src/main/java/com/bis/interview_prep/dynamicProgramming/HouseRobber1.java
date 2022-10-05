package com.bis.interview_prep.dynamicProgramming;

/**
 * You are a thief trying to steal from houses in a neighborhood. The amount of money that can be stolen from the
 * ith house is represented by nums[i]. While you’d like to steal from all the houses, if adjacent
 * houses are broken into, the police are notified via a security system. Return the largest
 * amount of money you can steal without alerting the police.
 * Note: You may not modify nums.
 * Ex: Given the following nums...
 * <p>
 * nums = [1, 3, 2, 5, 2], return 8.
 **/
public class HouseRobber1 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 2};

        int result = houseRobber(nums);
        System.out.println(result);
    }

    /**
     * In this problem, we are told that we can steal money from houses that are not adjacent.
     * Alarm is gone off when we steal money from house adjacent to each other.
     * 1. We can steal money from a current house
     * 2. We cannot steal money from a current house
     * 3. What happens when we steal money from a current house obeying the rules
     * 4. What happens when we do not steal money from a current house obeying the rules.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(1)
     **/
    private static int houseRobber(int[] nums) {
        int n = nums.length;
        int prevNotRob = 0;
        int prevRob = 0;

        for (int i = 0; i < n; i++) {
            int curRob = prevNotRob + nums[i];
            int curNotRob = Math.max(prevNotRob, prevRob);

            prevRob = curRob;
            prevNotRob = curNotRob;
        }

        return Math.max(prevNotRob, prevRob);
    }
}
