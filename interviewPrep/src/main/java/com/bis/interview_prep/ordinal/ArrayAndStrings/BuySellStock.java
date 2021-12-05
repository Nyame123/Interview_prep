package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a
 * different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 **/
public class BuySellStock {

    public static void main(String[] args) {

        int[] arr = {7,1,8,3,6,4};
        System.out.println(maxProfitKadane(arr));
    }

    public static int maxProfitKadane(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + (prices[i] - prices[i - 1]));
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    public static int maxProfit(int[] prices) {
        //using two pointers
        int n = prices.length;
        int min = Integer.MAX_VALUE;
        int ans = 0;

        for(int i = 0; i < n; i++){

            if(prices[i] < min){
                min = prices[i];
            }else{
                ans = Math.max(ans,prices[i] - min);
            }
        }

        return ans;
    }
}
