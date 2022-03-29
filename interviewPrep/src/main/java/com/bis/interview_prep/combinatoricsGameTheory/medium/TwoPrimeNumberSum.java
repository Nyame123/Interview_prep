package com.bis.interview_prep.combinatoricsGameTheory.medium;

import java.util.Arrays;

/**
 *Given an even number (greater than 2), return two prime numbers whose sum will be equal to the given number.
 *
 * A solution will always exist. See Goldbach’s conjecture.
 *
 * Example:
 *
 * Input: 4
 * Output: 2 + 2 = 4
 * If there are more than one solution possible, return the lexicographically smaller solution.
 *
 * If [a, b] is one solution with a <= b, and [c, d] is another solution with c <= d, then
 *
 * [a, b] < [c, d]
 * If a < c OR a==c AND b < d.
 *
 **/
public class TwoPrimeNumberSum {

    public static void main(String[] args) {
        int n = 70;

        System.out.println(Arrays.toString(findPair(n)));
    }

    /**
     * In this problem, we have to find a way of generating all prime numbers up
     * to the given sum.
     *
     * We can find all these prime numbers with the help of Sieve of Erastosthene
     *
     * From the sieve of the Erastosthene, we find the pair of numbers that give the given sum
     **/

    static int[] findPair(int n){
        boolean[] allPrimes = allPrime(n);

        for (int i = 0; i < n; i++) {
            if (allPrimes[i] && allPrimes[n-i]){
                return new int[]{i,n-i};
            }
        }

        return new int[]{-1,-1};
    }

    //Sieve of Erastosthene algorithm
    static boolean[] allPrime(int n){
        boolean[] primes = new boolean[n+1];


        //mark all possible primes
        for(int i = 2; i <= n; i++){
            primes[i] = true;
        }

        //test all primes
        for (int p = 2; p*p < n; p++) {
            if (primes[p]){
                for (int i = p*p; i <= n; i+=p) {
                    primes[i] = false;
                }
            }
        }

        return primes;
    }
}
