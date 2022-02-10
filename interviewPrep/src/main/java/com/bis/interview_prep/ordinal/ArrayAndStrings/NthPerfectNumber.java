package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 *A number is considered perfect if its digits sum up to exactly 10.
 * Given a positive integer n, return the n-th perfect number.
 *
 * For example, given 1, you should return 19. Given 2, you should return 28.
 **/
public class NthPerfectNumber {

    public static void main(String[] args) {
        int num = 324;
        System.out.print(findNthSmart(num));
    }

    static int findNth(int num) {
        int count = 0;
        for (int cur = 1; ; cur++) {

            int sum = 0;
            for (int x = cur; x > 0; x /= 10){
                sum += x%10;
            }

            if (sum == 10)
                count++;

            if (count == num)
                return cur;
        }
    }

    static int findNthSmart(int num){

        int count = 0;
        for (int cur = 19; ; cur += 9) {

            int sum = 0;
            for (int x = cur; x > 0; x /= 10){
                sum += x%10;
            }

            if (sum == 10)
                count++;

            if (count == num)
                return cur;
        }

    }
}
