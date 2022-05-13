package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 * Hackerland is a one-dimensional city with houses aligned at integral locations along a road. The Mayor
 * wants to install radio transmitters on the roofs of the city's houses. Each transmitter has a fixed
 * range meaning it can transmit a signal to all houses within that number of units distance away.
 *
 * Given a map of Hackerland and the transmission range, determine the minimum number of transmitters
 * so that every house is within range of at
 * least one transmitter. Each transmitter must be installed on top of an existing house.
 *
 * Example
 * 5 1         x[] size n = 5, k = 1
 * 1 2 3 4 5   x = [1, 2, 3, 4, 5]
 *
 * Output = 2;
 **/
public class HackerLandRadioTransmitter {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 1;
        int res = hackerlandRadioTransmitters(arr,k);
        System.out.println(res);
    }

    /**
     * In this problem, we basically have to use greedy approach
     * From the given k, we check the left and right of a terminal house where
     * a possible transmitter can be planted for every house to get radio signals
     *
     * 1. if the left house distance is <= house[i] + k; i++;
     * we do this for both left and right of the house terminal.
     *
     * Time Complexity = O(nLogn)
     **/
    public static int hackerlandRadioTransmitters(int[] x, int k) {
        // Write your code here
        Arrays.sort(x);
        int n = x.length;
        int count = 0;
        for (int i = 0; i < n;) {

            int leftTotal = x[i] + k;
            //left number of transmitters
            while(i < n && x[i] <= leftTotal){
                i++;
            }

            //right number of transmitters
            i--;
            int rightTotal = x[i] + k;
            while(i < n && x[i] <= rightTotal){
                i++;
            }


            count++;
        }

        return count;
    }
}
