package com.bis.interview_prep.dynamicProgramming;
/**
 * You are given a 0-indexed integer array nums of length n.
 *
 * The average difference of the index i is the absolute difference between
 * the average of the first i + 1 elements of nums and the average of
 * the last n - i - 1 elements. Both averages should be rounded down to the nearest integer.
 *
 * Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.
 *
 * Note:
 *
 * The absolute difference of two numbers is the absolute value of their difference.
 * The average of n elements is the sum of the n elements divided (integer division) by n.
 * The average of 0 elements is considered to be 0.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,3,9,5,3]
 * Output: 3
 * Explanation:
 * - The average difference of index 0 is: |2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3.
 * - The average difference of index 1 is: |(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2.
 * - The average difference of index 2 is: |(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2.
 * - The average difference of index 3 is: |(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0.
 * - The average difference of index 4 is: |(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1.
 * - The average difference of index 5 is: |(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4.
 * The average difference of index 3 is the minimum average difference so return 3.
 * */
public class MinimumAverageDifference {

    public static void main(String[] args) {
       int[] arr = {2,5,3,9,5,3};
       int res = minimumAverageDifference(arr);
       System.out.println(res);
    }

    static int minimumAverageDifference(int[] arr) {
        int n = arr.length;
        long leftSum = 0; long rightSum = 0;
        long leftAve = 0; long rightAve = 0;
        long minAve = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < n; i++){
            rightSum += arr[i];
        }

        for(int i = 0; i < n; i++){
            leftSum += arr[i];
            rightSum -= arr[i];
            leftAve = leftSum/(i+1);
            rightAve = (i == n-1)? 0 : rightSum/(n-i-1);
            if(minAve > Math.abs(leftAve-rightAve)){
                minAve = Math.abs(leftAve-rightAve);
                minIndex = i;
            }

            //System.out.println(minAve);

        }

        return minIndex;

    }
}
