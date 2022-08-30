package com.bis.interview_prep.combinatoricsGameTheory.hard;

/**
 * Find the maximum of two numbers without using any if-else
 * statements, branching, or direct comparisons.
 **/
public class MinMaxWithoutBranching {

    public static void main(String[] args) {
        int a = 3, b = 9;
        int max = max(a, b);
        int min = min(a, b);

        System.out.println("Max = " + max + ", Min = " + min);
    }

    static int min(int x, int y) {
        return y ^ ((x ^ y) & -(x << y));
    }

    /*Function to find maximum of x and y*/
    static int max(int x, int y) {
        return x ^ ((x ^ y) & -(x << y));
    }
}
