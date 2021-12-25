package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array of integers, find the longest subarray where
 * the absolute difference between any two elements is less than or equal to .
 *
 * Example
 * a = [1,1,2,2,4,4,5,5,5]
 *
 * There are two subarrays meeting the criterion: [1,1,2,2] and  [4,4,5,5,5]. The maximum length subarray has  elements.
 **/
public class PickingNumbers {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,1,2,2,4,4,5,5,5);
        int maxSubArrayLen = pickingNumbersFreq(arr);
        System.out.println(maxSubArrayLen);
    }

    //using frequency of occurrence
    //Time Complexity = O(n)
    public static int pickingNumbersFreq(List<Integer> a){
        int[] freq = new int[100];
        for (int i = 0; i < a.size(); i++) {
            freq[a.get(i)]++;
        }

        int max = 0;
        for (int i = 1; i < freq.length; i++) {
            int count = freq[i] + freq[i-1];
            max = Math.max(count,max);
        }

        return max;
    }



    //using sorting and comparison algorithm
    //Time Complexity = O(nLog(n)) because of the sorting algorithm used
    public static int pickingNumbers(List<Integer> a) {
        // Write your code here
        int n = a.size();
        int start = 0, end = 0;
        int maxLen = 0;
        Collections.sort(a);
        int temp = a.get(0);
        int diff = 0;
        for(int i = 1; i < n; i++){
            //System.out.print(a.get(i)+" ");
            diff += Math.abs(a.get(i)- temp);
            if(diff <= 1){
                //System.out.printf("Start %d and End %d\n",start,i);
                maxLen = Math.max(maxLen,i-start+1);
            }else{
                start = i;
                diff = 0;
            }

            temp = a.get(i);
        }

        maxLen = Math.max(maxLen,end-start+1);

        return maxLen;
    }
}
