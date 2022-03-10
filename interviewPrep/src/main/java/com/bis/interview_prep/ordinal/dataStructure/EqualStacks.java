package com.bis.interview_prep.ordinal.dataStructure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EqualStacks {

    public static void main(String[] args) {
        List<Integer> arr1 = Arrays.asList(1,1,3,1);
        List<Integer> arr2 = Arrays.asList(1,1,4,1);
        List<Integer> arr3 = Arrays.asList(1,1,2,1);

        int result = equalStacks(arr1,arr2,arr3);
        System.out.println(result);
    }

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        // Write your code here
        //get their sums
        int s1 = 0,s2 = 0,s3 = 0;
        int n1 = h1.size();
        int n2 = h2.size();
        int n3 = h3.size();
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        int max = 0;
        for(int i = n1-1; i >= 0; i--){
            s1 += h1.get(i);
            set1.add(s1);

        }


        for(int i = n2-1; i >= 0; i--){
            s2 += h2.get(i);
            set2.add(s2);
        }

        for(int i = n3-1; i >= 0; i--){
            s3 += h3.get(i);
            if(set1.contains(s3) && set2.contains(s3)){
                max = s3;
            }
        }



        //System.out.print(s1+" "+s2+" "+s3);
        return max;

    }
}
