package com.bis.interview_prep.combinatoricsGameTheory.easy;
/**
 *You have n fair coins and you flip them all at the same time.
 *  Any that come up tails you set aside. The ones that come up
 *  heads you flip again. How many rounds do you expect to play before only one coin remains?
 *
 * Write a function that, given n, returns the number of rounds you'd expect to play until one coin remains.
 *
 **/
public class CoinFlipRound {

    public static void main(String[] args) {
        int n = 20;
        int flipRound = round(n);
        System.out.println(flipRound);
    }

    /**
     * This problem is solved by using idea of logarithmic to base 2;
     **/
    private static int round(int n) {
        return (int) Math.ceil(Math.log(n)/Math.log(2));
    }
}
