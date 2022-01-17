package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * https://leetcode.com/problems/jump-game-ii/
 **/
public class JumpGame11 {

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5};
        int res = jumpDyn(num);
        System.out.println(res);
    }

    public static int jumpRec(int[] nums) {
        return recursiveJump(nums,0, new HashMap<>());
    }

    static int recursiveJump(int[] nums, int index, Map<Integer,Integer> map){
        //base case
        if(map.containsKey(index)){
            return map.get(index);
        }

        if(index >= nums.length){ //no jump
            return Integer.MAX_VALUE;
        }

        if(index+1 == nums.length){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int i = index+1; i <= index+nums[index]; i++){
            int ways = recursiveJump(nums,i,map);
            if (ways != Integer.MAX_VALUE){

                min = Math.min(min,ways+1);
            }

        }

        map.put(index,min);

        return min;
    }

    static int jumpDyn(int[] nums){

        int n = nums.length;
        int[] memo = new int[n];

        //base case
        if (n == 0){
            return Integer.MAX_VALUE;
        }

        memo[0] = 0;

        for (int i = 1; i < n; i++) {
            memo[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (i <= j + nums[j] && memo[j] != Integer.MAX_VALUE){
                    memo[i] = Math.min(memo[i],memo[j]+1);
                    break;
                }
            }
        }

        return memo[n-1];
    }

    //using greedy method
    /**
     * The main idea is based on greedy. Let's say the range of the current jump is
     * [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can
     * reach. Once the current point reaches curEnd, then trigger another jump, and set the new curEnd
     * with curFarthest, then keep the above steps, as the following:
     **/
    public static int jump(int[] nums) {

        int n = nums.length-1;
        int count = 0;
        int curEnd = 0;
        int curFarthest = 0;
        for(int i = 0; i < n;i++){
            curFarthest = Math.max(curFarthest,i+nums[i]);
            if(i == curEnd){
                count++;
                curEnd = curFarthest;
            }
        }
        return count;
    }

    static int jumpDp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1001);
        dp[n - 1] = 0;  // start from last index. No jumps required to reach end if we are already here
        // same as above. For each index, explore all jump sizes and use the one requiring minimum jumps to reach end
        for(int i = n - 2; i >= 0; i--)
            for(int jumpLen = 1; jumpLen <= nums[i]; jumpLen++)
                dp[i] = Math.min(dp[i], 1 + dp[Math.min(n - 1, i + jumpLen)]);  // min(n-1, i + jumpLen) for bounds handling
        return dp[0];
    }
}
