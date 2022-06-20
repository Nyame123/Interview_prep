package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a collection of intervals, find the minimum number
 * of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Intervals can "touch", such as [0, 1] and [1, 2], but they won't be considered overlapping.
 *
 * For example, given the intervals (7, 9), (2, 4), (5, 8), return 1 as
 * the last interval can be removed and the first two won't overlap.
 *
 * The intervals are not necessarily sorted in any order.
 **/
public class MinimumInterNonOverlapping {

    public static void main(String[] args) {
        int[][] intervals = {
                {7,9},
                {2,4},
                {5,8}
        };

        int minIntervals = minimumIntervalsToBeRemoved(intervals);
        System.out.println(minIntervals);
    }

    /**
     * In this problem, we remove the overlapping intervals
     * 1. Sort the intervals according to the starting values.
     * 2. Traverse over the array and remove all the overlapping intervals.
     * 3. If the previous interval end value is greater than the start value of the current interval,
     * then we remove the interval with a maximum end value
     *
     * Time Complexity = O(nLogn)
     **/
    static int minimumIntervalsToBeRemoved(int[][] intervals) {
        int n = intervals.length;

        if (n <= 1){
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]));

        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (end > intervals[i][0]){
                count++;
                end = Math.min(end,intervals[i][1]);
            }else{
                end = intervals[i][1];
            }
        }

        return count;
    }
}
