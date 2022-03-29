package com.bis.interview_prep.greedy;

/**
 * Given a list of integers and a number K, return which contiguous elements of the list sum to K.
 * <p>
 * For example, if the list is [1, 2, 3, 4, 5] and K is 9, then it should return [2, 3, 4], since 2 + 3 + 4 = 9.
 **/
public class SubArraySum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 9;
        sumSubArray(arr,k);
    }



    /**
     * We can use two pointers, the start and the end pointer.
     * We use a two nested loops with the outer one behaving as start and the
     * inner one behaving as end pointer.
     * <p>
     * Time Complexity = O(N^2)
     **/

    static void findSubArraySum(int[] arr, int k) {
        int n = arr.length;
        int start = 0, end = 0;
        boolean isFound = false;
        for (int i = 0; i < n; i++) {
            //start pointer
            start = i;
            int curSum = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (curSum == k) {
                    end = j;
                    isFound = true;
                    break;
                }
                curSum += arr[j];
            }

            if (isFound){
                break;
            }
        }

        for (int s = start; s < end; s++) {
            System.out.print(arr[s] + " ");
        }
    }


    /**
     * We can optimize the above solution to get better and efficient solution
     * using sliding window algorithm.
     *
     * Time Complexity = O(N)
     **/
    static void sumSubArray(int[] arr, int k){
        int n = arr.length;
        int curSum = 0;
        int i = 0, j = 0;
        for (i = 0, j = 0; i < n; i++) {
            if (curSum > k){
                curSum -= arr[j];
                j++;
            }

            if (curSum == k){
                break;
            }
            curSum += arr[i];

        }

        for (int s = j; s < i; s++) {
            System.out.print(arr[s] + " ");
        }
    }
}