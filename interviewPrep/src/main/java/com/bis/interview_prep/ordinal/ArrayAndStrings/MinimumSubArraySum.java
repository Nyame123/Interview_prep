package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Minimum Size Subarray Sum
 * Given an array of positive integers nums and a positive integer target, return the minimal length of
 * a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal
 * to target. If there is no such subarray, return 0 instead.
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]  // 8 + 7 = 15
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * Constraints:
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log(n)).
 **/
public class MinimumSubArraySum {

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1,1,1};
        int target = 11;
        int res = minimSubArrCount(arr, target);
        System.out.println(res);
    }

    /**
     * In this problem, we are going to use two-pointer approach
     * 1. First, we start from the left and we keep increasing the ending pointer.
     * 2. So when the total sum of the elements between the two pointer is more than the target,
     * then we increase the starting pointer.
     * 3. Then we try to get the minimum length of those valid subArrays i.e sub array that meets
     * the condition
     *
     * Time Complexity = O(N)
     **/
    static int minimSubArrCount(int[] arr, int target){
        int n = arr.length;
        int start = 0;
        int end = 0;
        int runningSum = 0;
        int minLen = Integer.MAX_VALUE;

        //2,3,1,2,4,3 ; target = 7; minLen = 3
        while (start < n && end < n){

            if (runningSum >= target){
                minLen = Math.min(minLen,end-start+1);

                //adjust our pointers
                runningSum -= arr[start];
                start++;
            }else{
                runningSum += arr[end];
            }
            if (runningSum < target){
                end++;
            }
        }
        if (runningSum >= target) {
            minLen = Math.min(minLen, end - start + 1);
        }

        return minLen == Integer.MAX_VALUE? 0 : minLen;
    }

    /**
     * In this problem, we are going to use two-pointer approach
     * 1. First, we start from the left and we keep increasing the ending pointer.
     * 2. So when the total sum of the elements between the two pointer is more than the target,
     * then we increase the starting pointer.
     * 3. Then we try to get the minimum length of those valid subArrays i.e sub array that meets
     * the condition
     *
     * Time Complexity = O(N^2)
     **/
    static int minimumSubArrayCount(int[] arr, int target) {
        int n = arr.length;
        int minLen = Integer.MAX_VALUE;

        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }

        if (totalSum < target){
            return 0;
        }
        //2,3,1,2,4,3; target = 7 ; minLen = 2

        for (int startPointer = 0; startPointer < n; startPointer++) {
           int endPointer = startPointer;
            int curSum = 0;
            while (endPointer < n){
                curSum += arr[endPointer];

                if (curSum >= target){
                    minLen = Math.min(minLen,endPointer-startPointer+1);
                    break;
                }

                endPointer++;
            }
        }

        return minLen;
    }


}
