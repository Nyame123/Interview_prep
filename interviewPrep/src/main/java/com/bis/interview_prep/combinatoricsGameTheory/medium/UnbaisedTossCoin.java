package com.bis.interview_prep.combinatoricsGameTheory.medium;

import java.util.Random;

/**
 *Assume you have access to a function toss_biased() which
 *  returns 0 or 1 with a probability that's not 50-50 (but also not 0-100
 *  or 100-0). You do not know the bias of the coin.
 *
 * Write a function to simulate an unbiased coin toss.
 **/
public class UnbaisedTossCoin {

    public static void main(String[] args) {

        int res = unbaisedTossedCoin();
        System.out.println(res);
    }

    // Given method that returns 0
    // with 60% probability and 1 with 40%
    static int tossBiased(){
        Random random = new Random();
        double randValue = random.nextDouble();
        if (randValue < 0.6){
            return 0;
        }
        return 1;
    }

    static int unbaisedTossedCoin(){
        int toss1 = tossBiased();
        int toss2 = tossBiased();

        // Will reach here with
        // 0.24 probability
        if (toss1 == 1 && toss2 == 0){
            return 1;
        }

        // Will reach here with
        // 0.24 probability
        if (toss1 == 0 && toss2 == 1){
            return 0;
        }

        // will reach here with
        // (1 - 0.24 - 0.24) probability
       return unbaisedTossedCoin();
    }
}
