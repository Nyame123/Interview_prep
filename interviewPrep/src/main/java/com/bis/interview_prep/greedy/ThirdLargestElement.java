package com.bis.interview_prep.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 *Given an array nums, return the third largest (distinct) value.
 * Note: If the third largest number does not exist, return the largest value.
 *
 *
 * Ex: Given the following array nums…
 *
 * nums = [1, 4, 2, 3, 5], return 3.
 * Ex: Given the following array nums…
 *
 * nums = [2, 3, 3, 5], return 2.
 * Ex: Given the following array nums…
 *
 * nums = [9, 5], return 9.
 *
 **/
public class ThirdLargestElement {

    public static void main(String[] args) {
        int arr[] = {1, 4, 2, 3, 5};
        int n = arr.length;
        int res = thirdLargest(arr, n);
        System.out.println(res);
    }

    private static int thirdLargest(int[] arr, int n) {
        if (n < 3){
            return findLargest(arr);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        System.out.println(set);
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;

        for (int a: set) {
            if (a > first){
                third = second;
                second = first;
                first = a;
            }else if (a > second){
                third = second;
                second = a;
            }else if (a > third){
                third = a;
            }
        }

        return third;
    }

    static int findLargest(int[] arr){
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max,arr[i]);
        }

        return max;
    }
}
