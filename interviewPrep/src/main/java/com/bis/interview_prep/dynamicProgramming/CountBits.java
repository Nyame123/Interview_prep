package com.bis.interview_prep.dynamicProgramming;

import java.util.Arrays;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 **/
public class CountBits {

    public static void main(String[] args) {
        int n = 5;
        int[] res = countBits(n);
        System.out.println(Arrays.toString(res));
    }


    //DP problem O(n)
    static int[] countBits(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        int power = 1;

        for(int i= 1; i <= n; i++){
            if(power*2 == i){
                power = i;
            }
            dp[i] = 1 + dp[i-power];
        }
        return dp;
    }

    //O(nLogn)
    public int[] countBits1(int n) {
        int[] res = new int[n+1];
        for(int i= 0; i <= n; i++){
            res[i] = countBit(i);
        }
        return res;
    }

    static int countBit(int n){
        int count = 0;
        while(n != 0){
            count++;
            n &= n-1;
        }

        return count;
    }
}
