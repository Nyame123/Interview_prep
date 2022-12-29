package com.bis.interview_prep.ordinal.bitManipulation;
/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 **/
public class TwoSum {

    public static void main(String[] args) {
        int a = 2; int b = 3;
        int sum = getSum(a,b);
        System.out.println(sum);
    }


    static int getSum(int a, int b) {
        while(b != 0){
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }

        return a;
    }
}
