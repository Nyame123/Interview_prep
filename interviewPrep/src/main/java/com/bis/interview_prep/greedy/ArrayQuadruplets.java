package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 * Given an unsorted array of integers arr and a number s, write a function findArrayQuadruplet that
 * finds four numbers (quadruplet) in arr that sum up to s. Your function should return an array of
 * these numbers in an ascending order. If such a quadruplet doesn’t exist, return an empty array.
 * <p>
 * Note that there may be more than one quadruplet in arr whose sum is s. You’re asked to return
 * the first one you encounter (considering the results are sorted).
 * <p>
 * Explain and code the most efficient solution possible, and analyze its time and space complexities.
 * <p>
 * Example:
 * <p>
 * input:  arr = [2, 7, 4, 0, 9, 5, 1, 3], s = 20
 * <p>
 * output: [0, 4, 7, 9] # The ordered quadruplet of (7, 4, 0, 9)
 * # whose sum is 20. Notice that there
 * # are two other quadruplets whose sum is 20:
 * # (7, 9, 1, 3) and (2, 4, 9, 5), but again you’re
 * # asked to return the just one quadruplet (in an
 * # ascending order)
 **/
public class ArrayQuadruplets {

    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 4};
        int sum = 16;

        int[] results = findArrayQuadruplet(arr, sum);

        System.out.println(Arrays.toString(results));
    }

    static int[] findArrayQuadruplet(int[] arr, int s) {
        // your code goes here
        Arrays.sort(arr);

        int n = arr.length;

        for (int i = 0; i <= n - 4; i++) {

            for (int j = i + 1; j <= n - 3; j++) {
                int sum = arr[i] + arr[j];
                int rem = s - sum;

                int low = j + 1;
                int high = n - 1;

                while (low < high) {
                    if (arr[low] + arr[high] < rem) {
                        low++;
                    } else if (arr[low] + arr[high] > rem) {
                        high--;
                    } else {
                        return new int[]{arr[i], arr[j], arr[low], arr[high]};
                    }
                }
            }
        }

        return new int[]{};
    }
}
