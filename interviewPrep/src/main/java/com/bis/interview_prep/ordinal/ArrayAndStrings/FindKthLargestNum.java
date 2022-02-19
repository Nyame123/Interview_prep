package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of integers, nums, and a value k, return the kth largest element.
 * <p>
 * Ex: Given the following array nums…
 * <p>
 * [1, 2, 3], k = 1, return 3.
 * Ex: Given the following array nums…
 * <p>
 * [9, 2, 1, 7, 3, 2], k = 5, return 2.
 **/
public class FindKthLargestNum {

    public static void main(String[] args) {
        int[] arr = {-1,-1};
        int k = 2;
        int res = findLargestKthItemUsingBuildArray(arr, k);
        System.out.println(res);
    }

    //Time Complexity = O(n*k)
    static int findLargestKthItemUsingBuildArray(int[] arr, int k) {
        int n = arr.length;
        int[] kArray = new int[k];
        Arrays.fill(kArray,Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            int it = arr[i];
            int l = k - 1;
            while (l >= 0) {
                if (it > kArray[l]) {
                    int t = kArray[l];
                    kArray[l] = it;
                    it = t;
                }
                l--;
            }
        }

        return kArray[0];
    }

    //Time Complexity = O(nlogn)
    static int findLargestKthItemUsingMaxHeap(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(t1, integer);
            }
        });

        for (int i = 0; i < n; i++) {
            maxHeap.add(arr[i]);
        }

        int kthItem = 0;
        for (int i = 0; i < k; i++) {
            kthItem = maxHeap.poll();
        }

        return kthItem;
    }

    //Time Complexity = O(nlogn)
    static int findLargestKthItemUsingSorting(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        return arr[n - k];
    }
}
