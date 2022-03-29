package com.bis.interview_prep.combinatoricsGameTheory.easy;

import java.util.HashMap;

/**
 *Given an integer n, return whether or not it is a power of three.
 *
 * Ex: Given the following value for n...
 *
 * n = 9, return true
 * Ex: Given the following value for n...
 *
 * n = 50, return false
 **/
public class TheThirdPower {

    public static void main(String[] args) {
        int n = 50;
        boolean res = isPowerOfThrees(n);
        System.out.println(res);
    }

    /**
     * We can solve this problem using Recursive approach.
     * 1. A number which is a power of 3 can be divisible by 3 i.e n%3 == 0.
     * 2. We keep dividing the number recursively by 3 and checking if the resulting number is divisible by 3.
     * 3. When the resulting number is less than or equal to 0. it is false.
     * 4. WHen the resulting number is equal to one, then it is true.
     *
     * Time Complexity = O((log3)N)
     **/
    static boolean isThirdPower(int n) {
        if (n <= 0){
            return false;
        }

        if (n == 1){
            return true;
        }

        if (n % 3 == 0){
            return isThirdPower(n/3);
        }

        return false;
    }

    /**
     * Another approach is by using 32 bit.
     * 1. Assuming all numbers can be represented by 32 bits, powers of three has
     * 3^19 as a largest power of 3.
     * 2. If the testing number can go into the largest power of 3, then it is true else false
     **/

    static boolean isPowerOfThree(int n){
        if (n <= 0){
            return false;
        }
        int largestP3 = (int) Math.pow(3,19);
        return largestP3 % n == 0;
    }

    /**
     * Another approach is by tracing the pattern of multiples of 3.
     * 1. The last digit of a number that is a multiple of 3 = [1,3,7,9]
     * 2. When the last digit of a number is in this map, then it has the ability of being a multiple of 3.
     * 3. We keep checking for multiples of 3 with the power.
     *
     *
     **/
    static boolean isPowerOfThrees(int n){
        if (n <= 0){
            return false;
        }

        if (n == 1){
            return true;
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(3,1);
        map.put(9,2);
        map.put(7,3);
        map.put(1,4);

        if (map.containsKey(n)){
            int power = map.get(n);
            while (Math.pow(3,power) <= n){
                if (Math.pow(3,power) == n){
                    return true;
                }
                power += 4;
            }
        }

        return false;
    }
}
