package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.List;

/**
 * Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
 * All the children sit in a line and each of them has a rating score according to his or her performance in the class.
 * Alice wants to give at least 1 candy to each child. If two children sit next to each other, then the one with the higher
 * rating must get more candies. Alice wants to minimize the total number of candies she must buy.
 *
 * eg. arr = [4,6,4,5,6,2]
 * She gives the students candy in the following minimal amounts: [1,2,1,2,3,1] . She must buy a minimum of 10 candies.
 **/
public class CandiesSharing {

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(4,6,4,5,6,2);
        long res = candies(arr.size(),arr);
        System.out.print(res);
    }

    public static long candies(int n, List<Integer> arr) {
        // Write your code here

        int[] candies = new int[n];
        candies[0] = 1;
        long sum = 0;
        for(int i = 1; i < n; i++){
            if(arr.get(i) > arr.get(i-1)){
                candies[i] = candies[i-1]+1;
            }else{
                candies[i] = 1;
            }
        }

        for(int i = n-2; i >= 0; i--){
            if(arr.get(i) > arr.get(i+1) && candies[i] <= candies[i+1]){
                candies[i] = candies[i+1]+1;
            }
        }


        for(int i = 0; i < n; i++){
            sum += candies[i];
            //System.out.println(candies[i]);
        }


        return sum;

    }
}
