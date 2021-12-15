package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 * Find the smallest positive number missing from an unsorted array | Set 1
 * Difficulty Level : Hard
 * Last Updated : 01 Dec, 2021
 * You are given an unsorted array with both positive and negative elements.
 * You have to find the smallest positive number missing from the array in O(n)
 * time using constant extra space. You can modify the original array.
 **/
public class MissingSmallestPositve {

    public static void main(String[] args) {
        //int arr[] = { 2, 3, -7, 6, 8, 1, -10, 15 };
        int arr[] = { 3, 4, -1, 1};
        int n = arr.length;
        int ans = findMissingPosInteger(arr);

        System.out.println(ans);
    }

    static int findMissingPosInteger(int[] arr){
        //find if 1 is present
        int ptr = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if(arr[i] == 1){
                ptr = 1;
                break;
            }
        }

        if(ptr == 0){
            return 1;
        }

        //change the numbers less than 0 and greater than n to 1
        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n){
                arr[i] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            arr[(arr[i] - 1)%n] += n;
        }

        //finding the index which has value less than n
        for (int i = 0; i < n; i++) {
            if (arr[i] <= n){
                return i+1;
            }
        }


        //if there is a value from 1 t0 n return n+1
        return (n+1);

    }
}
