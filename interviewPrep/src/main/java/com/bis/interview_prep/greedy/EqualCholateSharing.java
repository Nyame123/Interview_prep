    package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.List;

public class EqualCholateSharing {

    public static int equal(List<Integer> arr) {
        // Write your code here
        int[] result = new int[5];
        int min = getMinFrom(arr);

        for(int i = 0; i < result.length; i++){

            for(int j = 0; j < arr.size(); j++){
                int diff = arr.get(j)-min;
                result[i] += diff/5 + (diff%5)/2 + ((diff % 5) % 2);
            }
            System.out.println(min);
            min--;
        }

        return Arrays.stream(result).min().getAsInt();
    }

    static int getMinFrom(List<Integer> arr){
        int min = Integer.MAX_VALUE;
        for(int i: arr){
            min = Math.min(min,i);
        }

        return min;
    }
}
