package com.bis.interview_prep.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

public class SherLockAndCost {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,2,3,4,6);
        int result = cost(arr);
        System.out.println(result);
    }

    public static int cost(List<Integer> b){

        int n = b.size();
        int low = 0,high = 0,max = 0;

        for(int i = 1; i < n; i++){
            int hiLo = Math.abs(b.get(i)-1);
            int hiHi = Math.abs(b.get(i)-b.get(i-1));
            int loHi = Math.abs(b.get(i-1)-1);

            int aTemp = Math.max(high+hiHi,low+hiLo);
            int bTemp = loHi+high;
            low = bTemp;
            high = aTemp;

        }
        max = Math.max(low,high);
        return max;
    }
}
