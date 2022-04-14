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
     *
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

    static ArrayList<ArrayList<Integer>>
    combinationSum(ArrayList<Integer> arr, int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        // first do hashing since hashset does not always
        // sort
        //  removing the duplicates using HashSet and
        // Sorting the arrayList

        Set<Integer> set = new HashSet<>(arr);
        arr.clear();
        arr.addAll(set);
        Collections.sort(arr);

        findNumbers(ans, arr, sum, 0, temp);
        return ans;
    }

    static void
    findNumbers(ArrayList<ArrayList<Integer>> ans,
                ArrayList<Integer> arr, int sum, int index,
                ArrayList<Integer> temp) {

        if (sum == 0) {

            // Adding deep copy of list to ans

            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < arr.size(); i++) {

            // checking that sum does not become negative

            if ((sum - arr.get(i)) >= 0) {

                // adding element which can contribute to
                // sum

                temp.add(arr.get(i));

                findNumbers(ans, arr, sum - arr.get(i), i,
                        temp);

                // removing element from list (backtracking)
                temp.remove(arr.get(i));
            }
        }
    }

    // Driver Code

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(2);
        arr.add(4);
        arr.add(6);
        arr.add(8);

        int sum = 8;

        ArrayList<ArrayList<Integer>> ans
                = combinationSum(arr, sum);

        // If result is empty, then
        if (ans.size() == 0) {
            System.out.println("Empty");
            return;
        }

        // print all combinations stored in ans

        for (int i = 0; i < ans.size(); i++) {

            System.out.print("(");
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.print(") ");
        }
    }
}