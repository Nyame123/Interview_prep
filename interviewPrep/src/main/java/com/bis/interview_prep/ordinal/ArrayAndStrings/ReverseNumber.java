package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 *Given a 32 bit signed integer, reverse it and return the result.
 * Note: You may assume that the reversed integer will always fit within the bounds of the integer data type.
 *
 * Ex: Given the following integer num…
 *
 * num = 550, return 55
 * Ex: Given the following integer num…
 *
 * num = -37, return -73
 **/
public class ReverseNumber {

    public static void main(String[] args) {
        int num = 6777;
        int res = reverseNumber(num);
        System.out.println(res);
    }

    static int reverseNumber(int num) {
        int reverseNumber = 0;
        while (num != 0){
            reverseNumber = reverseNumber*10 + num%10;
            num /= 10;
        }
        return reverseNumber;
    }
}
