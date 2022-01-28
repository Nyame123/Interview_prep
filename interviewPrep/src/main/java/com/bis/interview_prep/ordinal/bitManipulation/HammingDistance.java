package com.bis.interview_prep.ordinal.bitManipulation;
/**
 * Given two integers x and y, return the hamming distance between the two numbers.
 * Note: The hamming distance between two numbers is the number of bit positions in
 * which the bits differ.
 *
 * Examples:
 *
 * Input: n1 = 9, n2 = 14
 * Output: 3
 * 9 = 1001, 14 = 1110
 * No. of Different bits = 3
 *
 * Input: n1 = 4, n2 = 8
 * Output: 2
 **/
public class HammingDistance {

    public static void main(String[] args) {
        int n1 = 9, n2 = 14;
        System.out.println(hammingDistance1(n1, n2));
    }

    static int hammingDistance(int n1,int n2){
        //calculate using XOR operator to get different set bit at a position
        int mask = n1 ^ n2;
        int ans = 0;
        while (mask != 0){
            ans += mask & 1;
            mask >>= 1;
        }

        return ans;
    }

    static int hammingDistance1(int n1,int n2){
        //calculate using XOR operator to get different set bit at a position
        int mask = Math.max(n1,n2);
        int ans = 0;
        while (mask != 0){
            int c1 = n1 & 1;
            int c2 = n2 & 1;

            if (c1 != c2){
                ans++;
            }

            mask >>= 1;
            n1 >>= 1;
            n2 >>= 1;
        }

        return ans;
    }
}
