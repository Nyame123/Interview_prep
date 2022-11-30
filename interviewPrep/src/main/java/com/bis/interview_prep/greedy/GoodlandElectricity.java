package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Goodland is a country with a number of evenly spaced cities along a line. The distance
 * between adjacent cities is 1 unit. There is an energy infrastructure project planning meeting,
 * and the government needs to know the fewest number of power plants needed to provide electricity
 * to the entire list of cities. Determine that number. If it cannot be done, return -1.
 *
 * You are given a list of city data. Cities that may contain a power plant have been labeled 1.
 * Others not suitable for building a plant are labeled 0. Given a distribution range of k, find the lowest
 * number of plants that must be built such that all cities are served. The distribution range limits supply
 * to cities where distance is less than k.
 *
 * For example
 *
 * 6 2 arr[] size n = 6, k = 2
 * 0 1 1 1 1 0   arr = [0, 1, 1, 1, 1, 0]
 *
 * output
 * 2
 **/
public class GoodlandElectricity {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(0, 1, 1, 1, 0, 1, 0);
        int k = 2;
        int res = pylonsShort(k,arr);
        System.out.println(res);

    }

    //Time Complexity = O(n) using two pointer approach
    public static int pylonsShort(int k, List<Integer> arr) {
        // Write your code here
        int n  = arr.size();
        int sum = 0;
        int last = -1;
        List<Integer> prv = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int p = arr.get(i);
            if(p == 1) last = i;
            prv.add(last);
        }
        for(int i = 0; i < n;){
            //find the power plant possible location
            int take = prv.get(Math.min(n-1,i+k-1));
            if (take == -1 && take + k <= i){
                return -1;
            }

            sum++;

            if (take + k <= i){
                return -1;
            }

            i = take + k;

        }

        return sum;
    }

    //Time Complexity = O(n) using two pointer approach
    public static int pylons(int k, List<Integer> arr) {
        // Write your code here
        int n  = arr.size();
        int sum = 0;
        for(int i = 0; i < n;){
            //find the power plant possible location
            boolean found = false;
            int j = Math.min(i+k-1,n-1);
            while(j >= Math.max(i-k+1,0) ){
                if(arr.get(j) == 1){
                    sum++;
                    i = j + k;
                    found = true;
                }

                j--;
            }

            if(!found){
                return -1;
            }
        }

        return sum;
    }

}
