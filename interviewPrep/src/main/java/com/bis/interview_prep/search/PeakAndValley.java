package com.bis.interview_prep.search;

import java.util.Arrays;
import java.util.HashSet;

/**
 * In an array of integers, a "peak" is an element which is greater than or equal
 * to the adjacent integers and a "valley" is an element which is less than or equal to the adjacent
 * integers. For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys. Given an
 * array of integers, sort the array into an alternating sequence of peaks and valleys.
 * EXAMPLE
 * Input: {5, 3,1,2, 3}
 * Output: {5, 1,3,2, 3}
 **/
public class PeakAndValley {

    public static void main(String[] args) {

        //int[] arr = {5,3,1,2,3};
        int[] arr = {5, 8, 6, 2, 3, 4, 6};
        int[] valPeakArr = valleyPeakUnSortedMeth(arr);
        for (int a : valPeakArr) {
            System.out.print(a + ",");
        }

    }




    //Time Complexity = O(n)
    private static int[] valleyPeakUnSortedMeth(int[] arr) {

        //loop over the elements in a jump of 2
        for (int i = 1; i < arr.length; i+=2) {
            //find the biggest adjacent element
            int prevIndex = i-1;
            int nextIndex = i+1;
            int biggestIndex = i;
            if (arr[prevIndex] > arr[i])
                biggestIndex = prevIndex;
            else if (nextIndex < arr.length && arr[nextIndex] > arr[i])
                biggestIndex = nextIndex;

            if (biggestIndex != i){
                //swap the element with the biggest on the left
                swap(arr,i,biggestIndex);
            }

        }

        return arr;
    }

    //Time Complexity = O(nlogn)
    private static int[] valleyPeakSortedMeth(int[] arr) {

        //first the sort the array
        Arrays.sort(arr);

        //loop over the elements in a jump of 2
        for (int i = 1; i < arr.length; i+=2) {
            //swap the element with the biggest on the left
            swap(arr,i,i+1);
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int nextI) {
        int temp = arr[i];
        arr[i] = arr[nextI];
        arr[nextI] = temp;
    }
}
