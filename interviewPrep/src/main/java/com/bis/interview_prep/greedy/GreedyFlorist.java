package com.bis.interview_prep.greedy;

import java.util.Arrays;

/**
 * A group of friends want to buy a bouquet of flowers.
 * The florist wants to maximize his number of new customers and the money he makes.
 * To do this, he decides he'll multiply the price of each flower by the number of that customer's
 * previously purchased flowers plus 1. The first flower will be original price, (0+1) * original_price ,
 * the next will be (1+1) * original_price  and so on.
 *
 * Given the size of the group of friends, the number of flowers they want to purchase and the original prices of the flowers,
 * determine the minimum cost to purchase all of the flowers. The number of flowers they want equals the length of the c array.
 * eg. c = [1,2,3,4] and k = 3;
 * */
public class GreedyFlorist {

    public static void main(String[] args) {
        int[] c = {1,2,3,4};
        int k = 3;
        int res = getMinimumCost(k,c);
        System.out.println(res);
    }

    static int getMinimumCost(int k, int[] c) {

        //sort the flowers
        Arrays.sort(c);
        int n  = c.length;
        int count = 0;
        int ans = 0;
        for(int i = n-1; i >= 0; i--){

            int div = count/k+1;
            //System.out.println(div);
            ans += c[i]*div;
            count++;
        }

        return ans;

    }

    // Complete the getMinimumCost function below.
    static int getMinimumCost1(int k, int[] c) {

        //sort the flowers
        Arrays.sort(c);
        int n  = c.length;
        int group = k;
        int count = 1;
        int ans = 0;
        for(int i = n-1; i >= 0; i--){

            if(group == 0){
                group = k;
                count++;
            }
            group--;
            ans += c[i]*count;
        }

        return ans;

    }
}
