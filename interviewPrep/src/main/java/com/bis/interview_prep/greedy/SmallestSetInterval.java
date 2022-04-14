package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 * Given a set of closed intervals, find the smallest set of numbers that covers all the intervals. If there are multiple smallest
 * sets, return any of them.
 * <p>
 * For example, given the intervals [0, 3], [2, 6], [3, 4], [6, 9], one set of numbers that covers all these intervals is {3, 6}.
 **/
public class SmallestSetInterval {

    public static void main(String[] args) {
        int[][] intervals = {
                new int[]{0, 3},
                new int[]{2, 6},
                new int[]{3, 4},
                new int[]{6, 9}
        };

        int[] res = smallestInterval(intervals);
        System.out.println(Arrays.toString(res));
    }

    /**
     * The approach here is that we find the set that is intersection of the intervals.
     * 1. We try to find the maximum start point.
     * 2. We find the minimum end point.
     * 3. We sort the two points above in ascending order.
     * <p>
     * Time Complexity = O(N)
     **/
    static int[] smallestInterval(int[][] intervals) {
        int[] res = new int[2];
        int n = intervals.length;
        if (n == 0) {
            return new int[]{-1, -1};
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] interval = intervals[i];
            min = Math.min(min, interval[1]);
            max = Math.max(max, interval[0]);
        }

        if (min < max) {
            res[0] = min;
            res[1] = max;
        } else {
            res[0] = max;
            res[1] = min;
        }

        return res;
    }
}
