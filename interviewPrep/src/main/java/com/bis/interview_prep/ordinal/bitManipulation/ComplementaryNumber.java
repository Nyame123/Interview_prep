package com.bis.interview_prep.ordinal.bitManipulation;

public class ComplementaryNumber {

    public static void main(String[] args) {
        int n = 4;
        int complement = complementNumber1(n);
        System.out.println(complement);
    }

    static int complementNumber1(int n){
        int c = (int) Math.floor(Math.log(n)/Math.log(2))+1;

        return (int) (Math.pow(2,c)-1)-n;
    }

    static int complementNumber2(int n){
        int mask = 1;
        while ((mask & n) != n){
            mask = (mask << 1) | 1;
        }

        return n ^ mask;
    }

    static int complementNumber(int n){
        int count = 0;
        int num = n;
        while (num != 0){
            num >>= 1;
            count++;
        }

        int mask = (int) Math.pow(2,count-1);
        mask = mask + (mask-1);

        return n ^ mask;
    }
}
