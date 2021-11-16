package com.bis.interview_prep.search;
/**
 *Given a sorted array of n integers that has been rotated an unknown
 * number of times, write code to find an element in the array. You may assume that the array was
 * originally sorted in increasing order.
 * EXAMPLE
 * Input find 5 in {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14}
 * Output 8 (the index of 5 in the array)
 **/
public class RotatedSortedSearch {

    public static void main(String[] args) {
        int[] arr = {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14};
        int index = sortedIndex(arr,25);
        System.out.println(index);
    }

    //Time Complexity = O(logn)
    static int sortedIndex(int[] arr,int item) {
        //find the rotated point
        int rotatedIndex = findPivot(arr);
        int resultIndex = -1;
        //check the left half for index
        if (arr[0] <= item && item <= arr[rotatedIndex]){
            resultIndex = binarySearch(arr,0,rotatedIndex,item);
        }else{
            resultIndex = binarySearch(arr,rotatedIndex+1,arr.length,item);
        }
        return resultIndex;
    }

    private static int binarySearch(int[] arr, int low, int high, int item) {

        /*int mid = low + (high - low) / 2;
        if (arr[mid] == item) {
            return mid;
        } else if (arr[mid] < item) {
            return binarySearch(arr,mid+1,high,item);
        } else {
            return binarySearch(arr,low,mid,item);
        }*/
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == item) {
                return mid;
            } else if (arr[mid] < item) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return -1;
    }

    private static int findPivot(int[] arr) {
        //the array is sorted in increasing order
        //so after rotation, the left half must increase up to
        // the pivot where the odds happens then we find the pivot
        int i = 0;
        while (i < arr.length && arr[i] <= arr[i+1]){
            i++;
        }
        return i;
    }
}
