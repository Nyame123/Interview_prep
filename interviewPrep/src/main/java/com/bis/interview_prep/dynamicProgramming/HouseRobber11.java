package com.bis.interview_prep.dynamicProgramming;
/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 **/
public class HouseRobber11 {

    public static void main(String[] args) {
        int[] nums = {2,3,2};
        int res = rob(nums);
        System.out.println(res);
    }

    static int rob(int[] nums) {
        int prevRob = 0;
        int prevNotRob = 0;
        int n = nums.length;

        if(n == 1){
            return nums[0];
        }

        for(int i = 0; i < n-1; i++){
            int curRob = prevNotRob + nums[i];
            int curNotRob = Math.max(prevNotRob,prevRob);
            prevRob = curRob;
            prevNotRob = curNotRob;
        }

        int maxFromLeft = Math.max(prevRob,prevNotRob);

        prevRob = 0;
        prevNotRob = 0;
        for(int i = n-1; i > 0; i--){
            int curRob = prevNotRob + nums[i];
            int curNotRob = Math.max(prevNotRob,prevRob);
            prevRob = curRob;
            prevNotRob = curNotRob;
        }

        int maxFromRight = Math.max(prevRob,prevNotRob);

        return Math.max(maxFromRight,maxFromLeft);
    }
}
