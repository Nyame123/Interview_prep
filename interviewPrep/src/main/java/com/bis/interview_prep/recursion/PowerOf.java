package com.bis.interview_prep.recursion;

/**
 * Implement integer exponentiation. That is, implement the pow(x, y) function, where x and y are integers and returns x^y.
 * <p>
 * Do this faster than the naive method of repeated multiplication.
 * <p>
 * For example, pow(2, 10) should return 1024.
 */
public class PowerOf {
    public static void main(String[] args) {

        double x = 2;
        int m = -3;
        double res = myPow(x,m);
        System.out.print(res);
    }

    static double myPow(double x, int m) {
        if (m == 0) {
            return 1;
        }


        double temp = myPow(x, m / 2);
        if (m < 0) {
            x = 1 / x;
        }
        if (m % 2 == 0) {
            return temp * temp;
        } else {

            return x * temp * temp;

        }
    }
}
