package com.bis.interview_prep.greedy;

import java.util.HashMap;

/**
 * Given an array of elements, return the length of the longest subarray where all its elements are distinct.
 *
 * For example, given the array [5, 1, 3, 5, 2, 3, 4, 1], return 5 as
 * the longest subarray of distinct elements is [5, 2, 3, 4, 1].
 **/
public class LongestSubArray {

    public static void main(String[] args) {
        int[] arr = {5, 1, 3, 5, 2, 3, 4, 1};
        int res = longestSubArr(arr);
        System.out.println(res);
    }

    /**
     * 1. Use a hashmap to store the index of every element.
     * 2. use two indices, i and j. ie. i is the ending counter and
     * j = starting counter.
     * 3. Calculate the max length of subarray for every iteration.
     *
     * Time Complexity = O(N)
     **/
    static int longestSubArray(int[] arr) {
        int n = arr.length;
        int max = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {

            j = Math.max(map.getOrDefault(arr[i], 0),j);

            max = Math.max(max,i - j + 1);

            map.put(arr[i],i+1);
        }
        return max;
    }

    static int longestSubArr(int[] arr){
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap<>();

        int j = 0;
        int max = 0;
        for (int i = 0; i < n;) {
            if (map.containsKey(arr[i])){
                operationMap(map,arr[j],-1);
                j++;
            }else{
                operationMap(map,arr[i],1);
                i++;
                max = Math.max(max,map.size());
            }
        }

        max = Math.max(max,map.size());
        return max;
    }

    static void operationMap(HashMap<Integer,Integer> map,int index,int newValue){
        int oldValue = map.getOrDefault(index,0);
        map.put(index,oldValue+newValue);

        if (map.get(index) <= 0){
            map.remove(index);
        }
    }
}
