package com.bis.interview_prep.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 *Given an integer n, return whether or not it is a “magical” number. A magical number is an integer
 * such that when you repeatedly replace the number with the sum of the squares of its digits its eventually becomes one.
 *
 * Ex: Given the following integer n…
 *
 * n = 19, return true.
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1.
 * Ex: Given the following integer n…
 *
 * n = 22, return false
 **/
public class MagicNumber {

    public static void main(String[] args) {
        int n = 1234;
        boolean res = isMagicNumber(n);
        System.out.println(res);
    }

    /**
     * 1. We can do the replacement until the number is one then it is magic number
     * 2. Whenever there is number repetition then it is not possible for the number to be a magic number
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static boolean isMagicNumber(int n) {
        int[] cached = {0,1,4,9,16,25,36,49,64,81};
        Set<Integer> set = new HashSet<>();
        int sum = n;
        while (sum != 1){
            int next = 0;
            int sumNext = sum;
            while (sumNext != 0){
                int digit = sumNext%10;
                sumNext /= 10;
                next += cached[digit];
            }

            //if it contains next then it is not magic number
            if (set.contains(next)){
                return false;
            }

            //replace the sum with next;
            sum = next;
            set.add(next);
        }

        return true;
    }


}
