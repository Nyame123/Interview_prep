package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer N, return the total number self divisible numbers that are strictly less than N (starting from one).
 * Note: A self divisible number if a number that is divisible by all of its digits.
 * <p>
 * Ex: Given the following value of N…
 * <p>
 * N = 17, return 12 because 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15 are all self divisible numbers.
 **/
public class CanDigitDivide {

    public static void main(String[] args) {

        int left = 1, right = 22;
        List<Integer> res = selfDividingNumbers(left,right);
        System.out.println(res);
    }

    static List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> ans = new LinkedList<>();
        int i = left;
        while (i <= right) {

            if (canDigitDivide(i)) {
                ans.add(i);
            }
            i++;
        }

        return ans;
    }

    static boolean canDigitDivide(int n) {
        int i = n;
        while (i > 0) {
            int j = i % 10;
            if (j == 0) {
                return false;
            }
            if (n % j != 0) {
                return false;
            }

            i /= 10;
        }

        return true;
    }
}
