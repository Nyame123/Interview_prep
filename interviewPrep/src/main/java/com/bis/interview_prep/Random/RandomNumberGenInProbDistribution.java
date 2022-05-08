package com.bis.interview_prep.Random;

import java.util.Random;

/**
 * Given n numbers, each with some frequency of occurrence. Return a random number with
 * probability proportional to its frequency of occurrence.
 * <p>
 * Example:
 * <p>
 * Let following be the given numbers.
 * arr[] = {10, 30, 20, 40}
 * <p>
 * Let following be the frequencies of given numbers.
 * freq[] = {1, 6, 2, 1}
 * <p>
 * The output should be
 * 10 with probability 1/10
 * 30 with probability 6/10
 * 20 with probability 2/10
 * 40 with probability 1/10
 **/
public class RandomNumberGenInProbDistribution {

    static Random random = new Random();

    public static void main(String[] args) {
        int[] arr = {10, 30, 20, 40};
        int[] freq = {1, 6, 2, 1};

        for (int i = 0; i < 5; i++)
            System.out.println(myRandGen(arr, freq, arr.length));
    }

    /**
     * In this solution, we are to return the item value proportional to the
     * frequency of its occurence.
     * <p>
     * 1. One solution is to spread all the values according to the frequency of occurence.
     * 2. Generate a random number from 0 to the length of the spread array.
     * 3. Return the value in the index of the random Index generated.
     * <p>
     * Above solution can work but it is not space optimized. If the value has high chance of occuring, say 3243432323,
     * then a lot of memory will be used.
     * <p>
     * 4. We can use prefix sum to complex the data and find a random index generated from the prefix sum array.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static int myRandGen(int[] arr, int[] freq, int length) {

        int[] prefixSum = new int[length];
        prefixSum[0] = freq[0];

        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + freq[i];
        }

        //generated random number
        int rand = random.nextInt(prefixSum[length - 1]);
        int indexRandom = findItemInPrefixSumArray(prefixSum, 0, length-1, rand);
        return arr[indexRandom];
    }

    static int findItemInPrefixSumArray(int[] arr, int l, int h, int rand) {

        while (l < h) {

            int mid = l + (h - l) / 2;

            if (arr[mid] == rand) {
                return mid;
            } else if (rand > arr[mid]) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return (arr[l] >= rand) ? l : -1;
    }
}

/**
 * This problem was asked by Triplebyte.
 *
 * You are given n numbers as well as n probabilities that sum up to 1. Write a function
 * to generate one of the numbers with its corresponding probability.
 *
 * For example, given the numbers [1, 2, 3, 4] and probabilities [0.1, 0.5, 0.2, 0.2],
 * your function should return 1 10% of the time, 2 50% of the time, and 3 and 4 20% of the time.
 *
 * You can generate random numbers between 0 and 1 uniformly.
 **/
class RandomNumberGenInDecimalProbDistribution {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        double[] prob = {0.1, 0.5, 0.2, 0.2};

        for (int i = 0; i < 5; i++)
            System.out.println(myRandGen(arr, prob, arr.length));
    }

    /**
     * In this solution, we are to return the item value proportional to the
     * frequency of its occurence.
     * <p>
     * 1. One solution is to spread all the values according to the frequency of occurence.
     * 2. Generate a random number from 0 to the length of the spread array.
     * 3. Return the value in the index of the random Index generated.
     * <p>
     * Above solution can work but it is not space optimized. If the value has high chance of occuring, say 3243432323,
     * then a lot of memory will be used.
     * <p>
     * 4. We can use prefix sum to complex the data and find a random index generated from the prefix sum array.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static int myRandGen(int[] arr, double[] freq, int length) {

        double[] prefixSum = new double[length];
        prefixSum[0] = freq[0];

        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + freq[i];
        }

        //generated random number
        double rand = Math.random();
        int indexRandom = findItemInPrefixSumArray(prefixSum, 0, length-1, rand);
        return arr[indexRandom];
    }

    static int findItemInPrefixSumArray(double[] arr, int l, int h, double rand) {

        while (l < h) {

            int mid = l + (h - l) / 2;

            if (arr[mid] == rand) {
                return mid;
            } else if (rand > arr[mid]) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return (arr[l] >= rand) ? l : -1;
    }
}

