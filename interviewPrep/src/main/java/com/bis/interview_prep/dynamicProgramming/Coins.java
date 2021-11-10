package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an array of coins in small denomination, find
 * a way of representing n coins.
 **/
public class Coins {

    public static void main(String[] args) {
        int[] coins = {25,10,5,1};
        int n = 10;//1070270201
//        int ways = coinRep(coins,n,0,new HashMap<>());
        int ways = makeChange(coins,n);
        System.out.println(ways);
    }

    static int makeChange(int[] coins,int target){
        return makeChange(coins,0,target/*,new int[target+1][coins.length]*/);
    }

    private static int makeChange(int[] coins, int index,int target,int[][] memo) {
        //base case
        if (memo[target][index] > 0)
            return memo[target][index];

        if (index >= coins.length-1)
            return 1;

        int denomination = coins[index];
        int ways = 0;
        for (int i = 0; i*denomination <= target; i++) {
            int remainingDeno = target - (i*denomination);
            ways += makeChange(coins,index + 1,remainingDeno);
        }
        memo[target][index] = ways;
        return ways;
    }

    private static int makeChange(int[] coins, int index,int target) {
        //base case
        if (index >= coins.length-1)
            return 1;

        int denomination = coins[index];
        int ways = 0;
        for (int i = 0; i*denomination <= target; i++) {
            int remainingDeno = target - (i*denomination);
            ways += makeChange(coins,index + 1,remainingDeno);
        }
        return ways;
    }

    //with dynamic programming, pick a coin or discard the coin
    //Time complexity = O(n) n = targetCoin
    static int coinRep(int[] coins, int target,int index, HashMap<String,Integer> memo){
        //base case
        String key = target+","+index;
        if (memo.containsKey(key))
            return memo.get(key);

        if (index >= coins.length)
            return 0;

        if (target == 0){
            return 1;
        }

        if (target < 0)
            return 0;

        int ways = coinRep(coins,target-coins[index],index,memo) //use the coin and reduce the target coin
                +
                coinRep(coins,target,index+1,memo); //discard the coin

        memo.put(key,ways);

        return ways;
    }


    //recursion, pick a coin or discard the coin
    //Time complexity = O(2^n) where n = targetCoin
    static int coinRep(int[] coins,int target,int index){
        //base case
        if (target == 0){
            return 1;
        }

        if (index >= coins.length)
            return 0;

        if (target < 0)
            return 0;

        int ways = coinRep(coins,target-coins[index],index) //use the coin and reduce the target coin
                +
                coinRep(coins,target,index+1); //discard the coin

        return ways;
    }
}
