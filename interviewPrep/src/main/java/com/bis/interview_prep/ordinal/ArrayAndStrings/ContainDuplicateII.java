package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *Given an array, nums, and an integer k, return whether or not two unique indices
 * exist such that nums[i] = nums[j] and the two indices i and jj are at most k
 * elements apart. Ex: Given the following array nums and value k…
 *
 * nums = [1, 2, 1], k = 1, return false.
 * Ex: Given the following array nums and value k…
 *
 * nums = [2, 3, 2], k = 2, return true.
 **/
public class ContainDuplicateII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        int k = 1;
        System.out.println(containsNearbyDuplicateUsingSet(nums,k));
    }

    static boolean containsNearbyDuplicate(int[] nums, int k) {

        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; (j < i+k+1 && j < n); j++){
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }

        return false;
    }

    static boolean containsNearbyDuplicateUsingSet(int[] nums, int k) {

        if(k == 0)
            return false;

        int n = nums.length;
        Set<Integer> hash = new HashSet<>(k);
        for(int i = 0; i < n; i++){
            if (hash.size() > k){
                hash.remove(nums[i-k-1]);
            }

            if(!hash.add(nums[i])){
                return true;
            }
        }

        return false;
    }

    //using sliding Window and hashing
    static boolean containsNearbyDuplicateHashing(int[] nums, int k) {

        if(k == 0)
            return false;

        int n = nums.length;
        Map<Integer,Integer> hash = new HashMap<>(k);
        for(int i = 0; i < n; i++){
            if (hash.containsKey(nums[i])){
                return true;
            }

            if (hash.size() == k){
                hash.remove(nums[i-k]);
            }

            hash.put(nums[i],i);
        }

        return false;
    }
}
