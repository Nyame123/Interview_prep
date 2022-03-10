package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashSet;
import java.util.Set;

public class DuplicateNumber {

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int res = findDuplicate(nums);
        System.out.println(res);
    }

    /**
     * 1. Another method is using a brute force method and
     *  then tracking the duplicate which requires time
     *  complexity of O(n^2) and space complexity of (1)
     *
     * 2. Another method is using a Sort function and
     * then tracking the duplicate which requires time
     * complexity of O(nlogn) and space complexity of (1)
     *
     **/

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static int findDuplicateUsingHash(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>(n);
        for(int i = 0; i < n; i++){
            if (set.contains(nums[i])) {
                return nums[i];
            }

            set.add(nums[i]);
        }

        return -1;
    }

    //Time Complexity = O(n)
    //Space Complexity = O(1)
    //Using Negative Marking
    static int findDuplicate(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int it = Math.abs(nums[i]);
            if(nums[it] < 0){
                return Math.abs(it);
            }
            nums[it] *= -1;

        }

        return -1;
    }
}
