package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given a sorted list of integers, square the elements and give the output in sorted order.
 *
 * For example, given [-9, -2, 0, 2, 3], return [0, 4, 4, 9, 81].
 **/
public class SquareAndSortArr {

    public static void main(String[] args) {
        int[] arr = {-9, -2, 0, 2, 3};
        int[] res = squareNMerge(arr);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Simple solution is to use square and sort using nlogN approach
     *
     * Time Complexity = O(nlogn)
     **/
    private static int[] squareAndSort(int[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] *= arr[i];
        }

        Arrays.sort(arr);
        return arr;
    }

    /**
     * Another approach is to use Divide and Merge Approach
     * 1. Since we know the array is sorted, we can divide the array into two parts.
     * 2. The first part is the negative part and the second part is the positive part.
     * 3. We merge the two arrays into one.
     *  Time Complexity = O(N)
     **/
    static int[] squareNMerge(int[] arr){
        List<Integer> temp = new ArrayList<>();

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0){
                temp.add(arr[i]*arr[i]);
            }else{
                break;
            }
        }

        int[] negArr = new int[temp.size()];
        int[] posArr = new int[n-temp.size()];
        int index = 0;
        for (int i = temp.size()-1; i >= 0; i--) {
            negArr[index++] = temp.get(i);
        }

        int index2 = 0;
        for (int i = temp.size(); i < n; i++) {
            posArr[index2++] = arr[i]*arr[i];
        }

        //merge the two arrays together
        int i = 0, j = 0, k = 0;
        while (i < posArr.length && j < negArr.length){
            if (posArr[i] < negArr[j]){
                arr[k++] = posArr[i++];
            }else{
                arr[k++] = negArr[j++];
            }
        }

        while (i < posArr.length){
            arr[k++] = posArr[i++];
        }

        while (j < negArr.length){
            arr[k++] = negArr[j++];
        }

        return arr;
    }

    /**
     *Using two pointer approach
     *
     * Time Complexity = O(N)
     **/
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;

        for (int k = nums.length - 1; k >= 0; k--) {
            if (Math.abs(nums[end]) > Math.abs(nums[start]))
                ans[k] = nums[end] * nums[end--];
            else
                ans[k] = nums[start] * nums[start++];
        }
        return ans;
    }
}
