package com.bis.interview_prep.combinatoricsGameTheory.medium;

import static java.lang.Math.min;

import java.util.HashMap;

/**
 *Given an integer array, nums, return the total number of “partners” in the array.
 * Note: Two numbers in our array are partners if they reside at different indices and both contain the same value.
 *
 * Ex: Given the following array nums…
 *
 * nums = [5, 5, 3, 1, 1, 3, 3], return 5.
 * 5 (index 0) and 5 (index 1) are partners.
 * 1 (index 3) and 1 (index 4) are partners.
 * 3 (index 2) and 3 (index 5) are partners.
 * 3 (index 2) and 3 (index 6) are partners.
 * 3 (index 5) and 3 (index 6) are partners.
 *
 **/
public class Partners {

    public static void main(String[] args) {

        int[] arr = {5, 5, 3, 1, 1, 3, 3};

        //int res = binomialCoefficientWay(30,2);
        int res = countPartners(arr);
        System.out.println(res);
    }

    /**
     * Here we would use hash map to count the numbers with number of occurrences
     * We then resort to using combination to calculate number of partners of two possible from
     * the occurrences.
     *
     * T
     **/
    static int countPartners(int[] arr){
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int key = arr[i];
            map.put(key,map.getOrDefault(key,0)+1);
        }

        //calculate partner combination
        int count = 0;
        for(int a: map.keySet()){
            count += combination(map.get(a),2);
        }
        return count;
    }

    //Time complexity = O(n*k)
    //Space complexity = O(n*k)
    static int binomialCoefficientWay(int n, int r){
        int[][] dp = new int[n+1][r+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= min(i,r); j++) {
                if (j == 0 || j == i){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
            }
        }

        return dp[n][r];
    }


    //Time complexity = O(r * (logn)
    //Space complexity = O(n*k)
    static int combination(int n, int r){
        int p = 1, q = 1;
        if (n - r < r){
            r = n-r;
        }

        if (r != 0) {
            while (r > 0) {
                p *= n;
                q *= r;

                int gcd = gcd(p, q);
                p /= gcd;
                q /= gcd;
                n--;
                r--;
            }
        }else{
            p = 1;
        }

        return p;
    }


    static int gcd(int a, int b){
        return a % b == 0? Math.abs(b) : gcd(b,a%b);
    }
}
