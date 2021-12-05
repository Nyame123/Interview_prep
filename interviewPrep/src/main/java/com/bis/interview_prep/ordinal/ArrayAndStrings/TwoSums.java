package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 **/
public class TwoSums {

    public static void main(String[] args) {

        int[] arr = {3,2,4};
        int n = 6;
        int[] res = twoSum1(arr,n);
        System.out.println(res[0]+","+res[1]);

        StringBuilder sb = new StringBuilder("");
    }

    //Time Complexity = O(n)
    //Time Complexity = O(n)
    public static int[] twoSum1(int[] nums, int target){
        int n = nums.length;
        int[] results = new int[2];
        HashMap<Integer, List<Integer>> maps = new HashMap<>();
        for(int i = 0; i < n; i++){
            int diff = target-nums[i];
            if (maps.containsKey(diff)){
                results[0] = i;
                List<Integer> indices = maps.get(diff);
                results[1] = indices.get(0);
                if (indices.size() > 1 && indices.get(0) == i){
                    results[1] = indices.get(1);
                }else if (indices.size() == 1 && indices.get(0) != i){
                    results[1] = indices.get(0);
                    return results;
                }

            }else{
                maps.putIfAbsent(nums[i],new ArrayList<>());
                List<Integer> indices = maps.get(nums[i]);
                indices.add(i);
            }

        }

        return results;
    }

    //Time Complexity=O(nlogn)
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> maps = new HashMap<>();
        for(int i = 0; i < n; i++){
            maps.putIfAbsent(nums[i],new ArrayList<>());
            List<Integer> indices = maps.get(nums[i]);
            indices.add(i);
        }

        Arrays.sort(nums);
        int[] results = new int[2];
        for(int i = 0; i < n; i++){
            int item = nums[i];
            int searched = target-item;
            int indexFound = -1;
            //results[0] = i;
            if(searched <= item){
                indexFound = binarySearch(nums,0,i-1,searched,i);
            }else{
                indexFound = binarySearch(nums,i+1,n-1,searched,i);
            }

            if(indexFound != -1 && indexFound != i){
                // System.out.println(nums[i] +"d="+maps.get(nums[i]));
                // System.out.println(nums[indexFound] +"d="+maps.get(nums[indexFound]));
                results[0] = maps.get(nums[i]).get(0);
                List<Integer> indices = maps.get(nums[indexFound]);

                int nonSimilarIndex = indices.get(0);
                if(indices.size() > 1 && indices.get(0) == results[0]){
                    nonSimilarIndex =  indices.get(1);
                    //System.out.println("CHeck "+indices.get(1)+"num ind "+i);
                }
                //System.out.println("CHeck "+indices+"num ind "+i);
                results[1] = nonSimilarIndex;
                return results;
            }
        }

        return results;
    }

    private static int binarySearch(int[] nums,int left, int right,int searched, int index){

        while(left <= right){
            int mid = left+(right-left)/2;
            if(nums[mid] == searched){
                return mid;
            }else if(nums[mid] > searched){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }

        return -1;
    }
}
