package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given an array of integers, find the length of the longest sub-sequence such that elements in
 * the subsequence are consecutive integers, the consecutive numbers can be in any order.
 * <p>
 * Examples:
 * <p>
 * Input: arr[] = {1, 9, 3, 10, 4, 20, 2}
 * Output: 4
 * Explanation:
 * The subsequence 1, 3, 4, 2 is the longest
 * subsequence of consecutive elements
 * <p>
 * Input: arr[] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42}
 * Output: 5
 * Explanation:
 * The subsequence 36, 35, 33, 34, 32 is the longest
 * subsequence of consecutive elements.
 **/
public class LongestConsecutiveSubsequence {

    public static void main(String[] args) {
        int[] arr = {0,3,7,2,5,8,4,6,0,1};
        int res = longestConsecutiveSubsequenceHash(arr);
        System.out.println(res);
    }

    /**
     * This cab be done by using a sort to sort the elements.
     * We have to handle the duplicates in the array
     * <p>
     * After we can go over the filtered out elements to count the longest consecutive
     * subsequence
     * <p>
     * Time complexity = O(NlogN) which is because of the sorting algorithm
     **/

    static int longestConsecutiveSubsequence(int[] arr) {

        if (arr.length == 0) {
            return 0;
        }
        int n = arr.length;

        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1]) {
                list.add(arr[i]);
            }
        }

        int count = 1, ans = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) + 1 == list.get(i)) {
                count++;
            } else {
                ans = Math.max(ans, count);
                count = 1;
            }
        }
        ans = Math.max(ans, count);
        return ans;
    }


    /**
     * We can optimize the above algorithm by using hashing.
     * We put the elements in the hash set then we iterate through
     * the set whenever there is a consecutive elements in the set
     * <p>
     * Now all the duplicates will be removed by the set since sets do not entertain
     * duplicates in an array.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static int longestConsecutiveSubsequenceHash(int[] arr) {
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }

        int count = 0;
        for (int num: set) {
            int j = num;
            if (!set.contains(j - 1)) {
                while (set.contains(j)) {
                    j++;
                }
            }

            count = Math.max(count, j - num);
        }

        return count;
    }
}
