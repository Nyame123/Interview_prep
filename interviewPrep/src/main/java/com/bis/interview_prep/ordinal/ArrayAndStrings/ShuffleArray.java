package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Random;

/**
 * Given a function that generates perfectly random numbers between 1 and k (inclusive),
 * where k is an input, write a function that shuffles a deck of cards represented as an array using only swaps.
 *
 * It should run in O(N) time.
 *
 * Hint: Make sure each one of the 52! permutations of the deck is equally likely.
 **/
public class ShuffleArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};

        shuffleArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ",arr[i]);
        }
    }

    //Time Complexity = O(n);
    //using Fisher-Yates Algorithm
    private static void shuffleArray(int[] arr) {
        int n = arr.length;
        Random random = new Random();
        for (int i = n-1; i > 0; --i) {

            int j = random.nextInt(i+1);

            //swap the element at j with i
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
