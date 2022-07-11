package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;

/**
 * In front of you is a row of N coins, with values v1, v1, ..., vn.
 *
 * You are asked to play the following game. You and an opponent take turns
 * choosing either the first or last coin from the row, removing it from
 * the row, and receiving the value of the coin.
 *
 * Write a program that returns the maximum amount of money you can win with
 * certainty, if you move first, assuming your opponent plays optimally.
 **/
public class OptimalStrategyGame {

    public static void main(String[] args) {
        int[] arr = {8, 15, 3, 7 };
        int maxMoney = maxMoneyRec(arr);
        System.out.println(maxMoney);
    }

    /**
     * 1. This problem can be solved by using DP.
     * 2. When the user picks, Vi, the opponent has the option of
     * picking Vi+1 or Vj.
     * 3. Then the user can pick Vi + min(F(i+2,j), F(i+1,j-1));
     * when the opponent picks i+1, user picks next F(i+2,j)
     * and opponent picks j, user picks next F(i+1,j-1)
     * 4. If user picks vj + min(F(i,j-2), F(i+1,j-1));
     * when the opponent picks i, user picks next F(i+1,j-1) and
     * when opponent picks j-1, user picks next F(i,j-2)
     *
     * Time Complexity = O(N^2)
     * Space Complexity = O(N^2)
     **/
    static int maxMoneyGameWithOpponent(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];


        return 0;
    }


    static int maxMoneyRec(int[] arr){
        int n = arr.length;
        int i = 0, j = n-1;
        return maxMoneyTopDown(arr,i, j, n, new HashMap<>());
    }
    static int maxMoneyTopDown(int[] arr, int i, int j, int n, HashMap<String, Integer> map){
        //base case
        String key = String.format("%s,%s",i,j);
        if (map.containsKey(key)){
            return map.get(key);
        }
        if ((i > j) || (i >= n) || (j < 0)){
            return 0;
        }

        //user picking ith item
        int option1 = arr[i] + Math.min(
                maxMoneyTopDown(arr,i+2,j,n,map), //opponent picks i+1
                maxMoneyTopDown(arr,i+1,j-1,n,map)  //opponentn picks j
                );

        //user picking jth item
        int option2 = arr[j] + Math.min(
                maxMoneyTopDown(arr,i+1,j-1,n,map), //opponent picks i
                maxMoneyTopDown(arr,i,j-2,n,map) //opponent picks j-1
        );

        map.put(key,Math.max(option1,option2));

        return map.get(key);
    }
}
