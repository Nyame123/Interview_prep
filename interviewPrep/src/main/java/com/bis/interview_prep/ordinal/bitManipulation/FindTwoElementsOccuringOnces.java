package com.bis.interview_prep.ordinal.bitManipulation;

import java.util.Arrays;

/**
 *Given an array of integers in which two elements appear exactly once and all other elements appear exactly twice,
 *  find the two elements that appear only once.
 *
 * For example, given the array [2, 4, 6, 8, 10, 2, 6, 10], return 4 and 8. The order does not matter.
 *
 * Follow-up: Can you do this in linear time and constant space?
 **/
public class FindTwoElementsOccuringOnces {

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 2, 6, 10};
        int[] res = findTwoElementsOccuringOnce(arr);
        System.out.println(Arrays.toString(res));
    }

    /**
     * In this solution, we can have more than one method.
     * We can use Hashing algorithm, sorting and O(N^2) solution
     *  But today, we will consider and efficient algorithm which uses bit manipulation to
     *  work out this problem.
     *
     *  Time Complexity = O(N)
     *  Space Complexity =O(1)
     **/
    private static int[] findTwoElementsOccuringOnce(int[] arr) {
        int n = arr.length;
        int sum = 0;

        //use XOR operator to remove all the twice occuring elements
        for (int i = 0; i < n; i++) {
            sum ^= arr[i];
        }

        //find the two's complement of the result. This is to help find the
        //sum containing the rightmost set bit

        sum &= -sum;

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {

            if ((sum & arr[i]) > 0){
                sum1 ^= arr[i];
            }else {
                sum2 ^= arr[i];
            }
        }

        return new int[]{sum1,sum2};
    }
}
