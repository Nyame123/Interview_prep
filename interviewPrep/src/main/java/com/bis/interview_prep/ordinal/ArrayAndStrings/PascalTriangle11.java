package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 **/
public class PascalTriangle11 {

    public static void main(String[] args) {
        int rowIndex = 0;
        System.out.println(getRowCombOptimized(rowIndex));
    }

    public static List<Integer> getRow1(int rowIndex) {
        List<Integer> resultList = new ArrayList<Integer>();
        if (rowIndex==0){
            resultList.add(1);
            return resultList;
        }

        int num = rowIndex/2;
        long temp = 1; // Some test cases have numbers larger than Integer.MAX_VALUE
        resultList.add((int)temp);

        // Compute first half using C(n, m) = C(n, m-1) * (n-m+1) / m;
        for(int i = 1; i<=num; i++){
            temp*=rowIndex-i+1;
            temp/=i;
            resultList.add((int)temp);
        }

        // Mirror the second half
        for(int i = (rowIndex-1)/2; i>=0; i--){
            resultList.add(resultList.get(i));
        }

        return resultList;
    }

    //using combination to create pascal triangle
    /**
     *0C0
     *1C0 1C1
     *2C0 2C1 2C2
     *3C0 3C1 3C2 3C3
     *4C0 4C1 4C2 4C3 4C4
     **/
    public static List<Integer> getRowComb(int rowIndex){

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            int comb = usingCombination(rowIndex,i);
            list.add(comb);
        }

        return list;
    }

    public static List<Integer> getRowCombOptimized(int rowIndex){

        List<Integer> list = new ArrayList<>();
        if (rowIndex==0){
            list.add(1);
            return list;
        }

        int mid = rowIndex /2;
        for (int i = 0; i <= mid; i++) {
            int comb = usingCombination(rowIndex,i);
            list.add(comb);
        }

        //mid = (rowIndex%2 == 0)? mid-1: mid;
        for (int i = (rowIndex-1)/2; i >= 0; i--) {
            int comb = list.get(i);
            list.add(comb);
        }

        return list;
    }

    public static int gcd(int n, int r){
        return (n%r == 0)? Math.abs(r): gcd(r,n%r);
    }

    public static int usingCombination(int n, int r){

        if (n-r < r){
            r = n-r;
        }

        if(r == 0)
            return 1;
        else{
            int p = 1, k = 1;
            while (r > 0){
                p *= n;
                k *= r;
                int m = gcd(p,k);
                p /= m;
                k /= m;

                n--;
                r--;

            }
            return p;
        }
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if(rowIndex >= 0){
            list.add(1);
        }

        if(rowIndex >= 1){
            list.add(1);
        }

        for(int i=2; i<=rowIndex; i++){

            int temp = list.get(0);
            for(int j = 1; j < i; j++){
                int cur = list.get(j);
                list.set(j,cur+temp);
                //reset the temp to current item
                temp = cur;
            }

            list.add(1);
        }

        return list;
    }
}
