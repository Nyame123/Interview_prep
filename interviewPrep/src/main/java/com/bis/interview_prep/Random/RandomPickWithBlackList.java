package com.bis.interview_prep.Random;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * This question was asked by Google.
 *
 * Given an integer n and a list of integers l, write a function that randomly
 * generates a number from 0 to n-1 that isn't in l (uniform).
 *
 * You are given an integer n and an array of unique integers blacklist.
 * Design an algorithm to pick a random integer in the range [0, n - 1] that
 * is not in blacklist. Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.
 *
 * Optimize your algorithm such that it minimizes the number of calls to the built-in random function of your language.
 *
 * Implement the Solution class:
 *
 * Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
 * int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * Output
 * [null, 0, 4, 1, 6, 1, 0, 4]
 *
 * Explanation
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // return 0, any integer from [0,1,4,6] should be ok. Note that for every call of pick,
 *                  // 0, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/4).
 * solution.pick(); // return 4
 * solution.pick(); // return 1
 * solution.pick(); // return 6
 * solution.pick(); // return 1
 * solution.pick(); // return 0
 * solution.pick(); // return 4
 *
 **/
public class RandomPickWithBlackList {

    public static void main(String[] args) {


    }

    /**
     * Let's briefly talk about the problem.
     * You are given a list of numbers from [0,n) and a blacklist which are within the
     * array of numbers given. You are supposed to randomly return any valid number not in the
     * blacklist uniformly.
     *
     * IDEA
     * ==========
     * 1. We have a blacklist so and we want to uniformly return valid numbers, so we have to determine
     * our size of the valid numbers. Assuming the size of the blacklist is B and the size of the array numbers is N.
     * Therefore the valid number to be picked uniformly is M = N-B.
     * But within the M list of numbers, we can have some of the blacklist numbers there so we need to sort of swap
     * those blackList numbers before M with the valid numbser after M.
     * 2. So we intend to do this with a map
     * 3. Now we can safely generate a random number from 0 to M with uniform probability.
     *
     **/

    HashMap<Integer,Integer> map;
    HashSet<Integer> blackset;
    int M = 0;
    Random random;
    public RandomPickWithBlackList(int n, int[] blacklist) {
        int B = blacklist.length;
        M = n - B;
        blackset = new HashSet<>();
        map = new HashMap<>();

        //store the blacklist above M in a hashset
        for(int b: blacklist){
            if (b >= M) {
                blackset.add(b);
            }
        }

        //remap all the blacklist below M to a valid number above M
        int val = M;
        for(int b: blacklist){
            if (b < M) {
                while (blackset.contains(val))
                    val++;
                map.put(b,val++);
            }
        }
        random = new Random();
    }

    public int pick() {
        int rand = random.nextInt(M);
        return map.getOrDefault(rand,rand);
    }
}
