package com.bis.interview_prep.greedy;

/**
 * Implement division of two positive integers without using the division,
 * multiplication, or modulus operators. Return the quotient as an integer, ignoring the remainder.
 **/
public class DivideTwoInteger {

    public static void main(String[] args) {
        int a = 40, b = 3;
        System.out.println(divide(a, b));

        int a1 = -2147483648, b1 = -1;
       // System.out.println(divide(a1, b1));
    }

    static long divideUsingBit(long dividend, long divisor) {
        //determine the sign
        long sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        long quotient = 0, temp = 0;

        for (int i = 31; i >= 0; --i) {
            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= 1L << i;
            }
        }

        if (sign == -1)
            quotient = -quotient;
        return quotient;
    }

    static long divide(long dividend, long divisor) {
        //cal the sign
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long quotient = 0;
        dividend = Math.abs((long) dividend);
        divisor = Math.abs((long) divisor);
        while (dividend >= divisor) {
            dividend -= divisor;
            quotient++;
        }

        return ((sign > 0) ? quotient : -quotient);
    }

    static int divide(int dividend, int divisor) {
        int sign = ((dividend < 0) ^ (divisor < 0))? -1:1;

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        if (ldivisor == 0){
            return Integer.MAX_VALUE;
        }

        if (ldividend == 0 || ldividend < ldivisor){
           return 0;
        }

        long res = ldivide(ldividend,ldivisor);

        if (res > Integer.MAX_VALUE){
            if (sign == 1)
                return Integer.MAX_VALUE;
            else
                return Integer.MIN_VALUE;
        }else{
            return (int) (sign*res);
        }

    }

    static long ldivide(long dividend,long divisor){

        //base case
        if (dividend < divisor){
            return 0;
        }
        long sum = divisor;
        long multiple = 1;

        while ((sum+sum) <= dividend){
            sum += sum;
            multiple += multiple;
        }

        return multiple + ldivide(dividend-sum,divisor);
    }



}
