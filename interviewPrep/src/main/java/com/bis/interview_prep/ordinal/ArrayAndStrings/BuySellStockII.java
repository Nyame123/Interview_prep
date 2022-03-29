package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 *You are given the list of prices of a particular stock. Each element in the array represents
 *  the price of the stock at a given second throughout the current day. Return the maximum
 *  profit you can make trading this stock.
 * Note: You may only ever buy and sell a single share of the stock, but you may make multiple
 * transactions (i.e. buys and sells).
 * Ex: Given the following prices...
 *
 * prices = [8, 3, 2, 4, 6, 4, 5], return 5.
 **/
public class BuySellStockII {

    public static void main(String[] args) {
        int[] prices = {8, 3, 2, 4, 6, 4, 5};
        int res = maxProfit(prices);
        System.out.println(res);
    }

    /**
     * In this problem, you can buy a stock and sell it on another time when the price increases up.
     * You can buy another stock and sell it in future for more profit. We add the total profit.
     *
     * Time Complexity = O(N)
     **/
    static int maxProfitInAllTransactions(int[] prices) {
        int n = prices.length;
        int profit = 0;
        for (int i = 1; i < n; i++) {
            profit += Math.max(0,prices[i] - prices[i-1]);
        }
        return profit;
    }


    /**
     * Using Peak and Valley Algorithm
     **/
    static int maxProfit(int[] prices) {
        int i = 1, res = 0;
        while(i < prices.length){
            while(i < prices.length && prices[i] <= prices[i - 1])   i++;
            int valley = prices[i - 1];
            while(i< prices.length && prices[i] >= prices[i - 1])   i++;
            int peak = prices[i - 1];
            res += peak - valley;
        }
        return res;
    }
}
