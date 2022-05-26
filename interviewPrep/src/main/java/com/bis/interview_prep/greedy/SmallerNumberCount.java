package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an array of integers, return a new array where each element in the new array
 * is the number of smaller elements to the right of that element in the original input array.
 * <p>
 * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
 * <p>
 * There is 1 smaller element to the right of 3
 * There is 1 smaller element to the right of 4
 * There are 2 smaller elements to the right of 9
 * There is 1 smaller element to the right of 6
 * There are no smaller elements to the right of 1
 **/
public class SmallerNumberCount {

    public static void main(String[] args) {
        int[] nums = {3, 4, 9, 6, 1};
        List<Integer> res = countSmaller(nums);
        System.out.println(res);
    }


    /**
     * Using a merge sort to know the number of elements on the right
     * side greater smaller than the left side
     *
     * Time Complexity = O(nLogn)
     **/
    static int[] count;
    static List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        int n = nums.length;
        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for(int i = 0; i < n; i++){
            indexes[i] = i;
        }

        mergeSort(nums, indexes, 0, nums.length-1);

        for(int i = 0; i < n; i++){
            res.add(count[i]);
        }

        return res;
    }


    static void mergeSort(int[] nums, int[] indexes, int l , int r){
        if(l < r){
            int mid = l + (r - l) / 2;

            mergeSort(nums, indexes, l, mid);
            mergeSort(nums, indexes, mid+1, r);

            merge(nums, indexes, l, mid, r);
        }
    }

   static void merge(int[] nums, int[] indexes, int l, int mid, int r){
        int leftSize = mid-l+1;
        int rightSize = r-mid;

        int[] lArr = new int[leftSize];
        int[] rArr = new int[rightSize];

        for(int i = 0; i < leftSize; i++){
            lArr[i] = indexes[l+i];
        }

        for(int i = 0; i < rightSize; i++){
            rArr[i] = indexes[mid+1+i];
        }

        int i = 0, j = 0, k = l, rightCount = 0;
        while(i < leftSize && j < rightSize){
            if(nums[lArr[i]] <= nums[rArr[j]]){
                indexes[k++] = lArr[i];
                count[lArr[i]] += rightCount;
                i++;
            }else{
                indexes[k++] = rArr[j++];
                rightCount++;
            }
        }

        while(i < leftSize){
            indexes[k++] = lArr[i];
            count[lArr[i]] += rightCount;
            i++;
        }

        while(j < rightSize){
            indexes[k++] = rArr[j++];
        }

    }


    /**
     * Find the count of smaller elements from the right of each
     * number.
     * <p>
     * Time Complexity = O(N^2)
     **/
    static List<Integer> countSmaller1(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j])
                    count++;
            }

            ans.add(count);
        }

        return ans;
    }

    /**
     * This is using Binary Search Tree data structure
     *  Time Complexity (nlogn) in best or average case and O(N^2) in worse case scenario
     **/
    static List<Integer> countSmaller2(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();

        if (n == 0) {
            return ans;
        }

        TreeNode root = new TreeNode(nums[n - 1]);
        ans.add(0);

        for (int i = n - 2; i >= 0; i--) {
            int res = insertNode(root, nums[i]);
            ans.add(res);
        }

        Collections.reverse(ans);

        return ans;
    }

    static int insertNode(TreeNode root, int val) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count++;
                if (root.left == null) {
                    TreeNode node = new TreeNode();
                    node.val = val;
                    root.left = node;
                    break;
                }

                root = root.left;
            } else {
                count += root.count;

                if (root.right == null) {
                    TreeNode node = new TreeNode();
                    node.val = val;
                    root.right = node;
                    break;
                }

                root = root.right;
            }
        }

        return count;
    }

    static class TreeNode {
        TreeNode left, right;
        int val = 0;
        int count = 1;

        TreeNode() {
        }

        TreeNode(int d) {
            this.val = d;
        }
    }
}
