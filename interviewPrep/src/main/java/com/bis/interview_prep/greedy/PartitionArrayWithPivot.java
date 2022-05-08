package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 * Given a pivot x, and a list lst, partition the list into three parts.
 * <p>
 * The first part contains all elements in lst that are less than x
 * The second part contains all elements in lst that are equal to x
 * The third part contains all elements in lst that are larger than x
 * Ordering within a part can be arbitrary.
 * <p>
 * For example, given x = 10 and lst = [9, 12, 3, 5, 14, 10, 10], one partition may be [9, 3, 5, 10, 10, 12, 14].
 * <p>
 * <p>
 * First phase
 * 1. [9, 12, 3, 5, 14, 10, 10], pivot = 10
 * 2. [9, 3, 12, 5, 14, 10, 10], pivot = 10
 * 3. [9, 3, 5, 12, 14, 10, 10], pivot = 10
 * Second phase
 * 3. [9, 3, 5, 12, 10, 10, 14], pivot = 10
 * 4. [9, 3, 5, 10, 10, 12, 14], pivot = 10
 **/
public class PartitionArrayWithPivot {

    public static void main(String[] args) {
        int[] arr = {9, 12, 3, 5, 14, 10, 10};
        int pivot = 3;
        partitionArr(arr, pivot);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * In this problem, we take a first traversal and bring those element less than
     * the pivot to the front of the pivot.
     * 2. We bring the elements greater than pivot and bring them to the rear of the pivot
     * <p>
     * Time Complexity = O(N)
     */
    private static void partitionArr(int[] arr, int pivot) {
        int n = arr.length;
        //first phase
        for (int i = 0; i < n; i++) {
            if (arr[i] >= pivot) {
                //look forward for element less than pivot
                int j = i;
                while (j < n && arr[j] >= pivot) {
                    j++;
                }

                if (j < n)
                    swap(i, j, arr);
                else
                    break;
            }
        }

        //second phase
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] <= pivot) {
                int j = i;
                while (j >= 0 && arr[j] <= pivot) {
                    j--;
                }

                if (j >= 0)
                    swap(i, j, arr);
                else
                    break;
            }
        }
    }

    static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
