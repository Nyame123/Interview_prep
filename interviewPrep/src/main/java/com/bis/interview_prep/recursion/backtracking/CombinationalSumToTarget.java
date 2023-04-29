package com.bis.interview_prep.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of nums and a target, return all the unique combinations of nums that sum to target.
 * <p>
 * Ex: Given the following nums and target...
 * <p>
 * nums = [8, 2, 2, 4, 5, 6, 3]
 * target = 9
 * return [[2,2,5],[2,3,4],[3,6],[4,5]].
 **/
public class CombinationalSumToTarget {

    public static void main(String[] args) {
        int[] nums = {8, 2, 2, 4, 5, 6, 3};
        int target = 9;

        List<List<Integer>> res = prepareArray(nums, target);
        System.out.println(res);
    }

    /**
     * 1. Remove all the duplicates of the array using hashSet.
     * 2. Sort the array in non-descending order
     **/
    static List<List<Integer>> prepareArray(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        List<Integer> arr = new ArrayList<>(set);
        Collections.sort(arr);

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> store = new ArrayList<>();
        findAllUniqueCombination(arr, target, ans, store, 0);
        return ans;
    }


    /**
     * This tries all possible combinations
     * <p>
     * Time Complexity = O(2^N)
     **/
    private static void findAllUniqueCombination(List<Integer> nums, int target, List<List<Integer>> ans,
                                                 List<Integer> store, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(store));
            return;
        }

        for (int i = index; i < nums.size(); i++) {

            if (target - nums.get(i) >= 0) {
                int it = nums.get(i);
                store.add(it);
                findAllUniqueCombination(nums, target - it, ans, store, i);

                //backtrack
                store.remove(nums.get(i));
            } else {
                return;
            }
        }
    }
}

class GFG {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(8, 2, 2, 4, 5, 6, 3);
        int target = 9;

        List<List<Integer>> res = combinationSum(nums, target);
        System.out.println(res);
    }

    static List<List<Integer>> combinationSum(List<Integer> arr, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        Collections.sort(arr);

        findNumbers(ans, arr, sum, 0, temp);
        return ans;
    }

    static void findNumbers(List<List<Integer>> ans, List<Integer> arr, int sum, int index, List<Integer> temp) {

        //base case
        if (index >= arr.size())
            return;

        if (sum == 0) {
            // Adding deep copy of list to ans
            ans.add(new ArrayList<>(temp));
            return;
        }

        if (sum < 0)
            return;

        //take index
        temp.add(arr.get(index));
        findNumbers(ans, arr, sum - arr.get(index), index, temp);
        //do not take index
        temp.remove(arr.get(index));
        findNumbers(ans, arr, sum, index + 1, temp);
    }
}

class CombinationSumUsingDP {

    public static void main(String[] args) {
        int target = 9;
        int[] coins = {8, 2, 2, 4, 5, 6, 3};

        List<List<Integer>> result = combinationSum(coins, target);
        System.out.println(result);
    }

    static List<List<Integer>> combinationSum(int[] coins, int target) {
        int n = coins.length;
        int[][] dp = new int[n + 1][target + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coins[i - 1]];
                }

                dp[i][j] += dp[i - 1][j];
            }
        }

        System.out.println(dp[n][target]);
        return printCombination(coins, dp);
    }

    static List<List<Integer>> printCombination(int[] coins, int[][] dp) {
        int i = dp.length - 1;
        int j = dp[0].length - 1;

        List<List<Integer>> results = new ArrayList<>();

        while (dp[i][j] != 0) {
            List<Integer> ans = new ArrayList<>();
            while (j > 0) {
                if (i == 1 || dp[i][j] != dp[i - 1][j]) {
                    ans.add(coins[i - 1]);
                    dp[i][j]--;
                    j -= coins[i - 1];
                } else {
                    while (i != 1 && dp[i][j] == dp[i - 1][j]) {
                        dp[i][j]--;
                        i--;
                    }
                }
            }
            results.add(ans);
            i = dp.length - 1;
            j = dp[0].length - 1;
        }
        return results;
    }
}