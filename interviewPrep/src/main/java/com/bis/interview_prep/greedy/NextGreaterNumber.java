package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 * Given a number represented by a list of digits, find the next greater permutation of a
 * number, in terms of lexicographic ordering. If there is not greater permutation possible,
 * return the permutation with the lowest value/ordering.
 * <p>
 * For example, the list [1,2,3] should return [1,3,2]. The list [1,3,2] should return [2,1,3].
 * The list [3,2,1] should return [1,2,3]
 **/
public class NextGreaterNumber {

    public static void main(String[] args) {
        int[] arr = {3,2,1};
        nextGreaterOptimized(arr);
    }

    /**
     * 1. Finding the smaller element which is behind a greater number
     * 2. Find a number which is between the smaller and the greater number next of smaller
     * 3. Swap the smaller and the next smaller number
     * 4. Then sort the numbers from the next smaller to the end of the array
     * <p>
     * Time complexity of (NlogN)
     **/
    private static void nextGreater(int[] arr) {
        int n = arr.length;
        int i = 0;
        for (i = n - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                break;
            }
        }

        //if there is no smaller element found
        if (i == 0) {
            Arrays.sort(arr);
            System.out.println(Arrays.toString(arr));
            return;
        }

        //find the possible element between smaller and the next bigger
        int smaller = arr[i - 1];
        int min = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] > smaller && arr[j] < arr[min]) {
                min = j;
            }
        }

        //swap the element
        swap(arr, i - 1, min);

        //sort the elements from min pointer
        Arrays.sort(arr, i, n);
        System.out.println(Arrays.toString(arr));
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * From the above described algorithm, we can optimize the last step
     * If you work out the steps in a book, you will realize that after detecting the
     * two numbser for swapping, the rest of the numbers are just reversed ordered.
     * <p>
     * So why do we bother ourselves with sorting which increases the time complexity to NlogN.
     * We can reverse the order and finally the order will be O(N)
     **/

    private static void nextGreaterOptimized(int[] arr) {
        int n = arr.length;
        int i = 0;
        for (i = n - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                break;
            }
        }

        //if there is no smaller element found
        //find the possible element between smaller and the next bigger

        int min = i;
        if (i >= 0) {
            for (int j = n - 1; j > i; j--) {
                if (arr[j] > arr[i - 1]) {
                    min = j;
                    break;
                }
            }

            //swap the element
            swap(arr, i - 1, min);
        }

        //reverse the elements from min pointer
        if (i <= n - 1) {
            for (int j = i; j < (n + i) / 2; j++) {
                swap(arr, i, n - j);
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

class Solution {

    public static void main(String[] args) {
        int[] arr = {3,2,1};
        Solution solution = new Solution();
        solution.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
