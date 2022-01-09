package com.bis.interview_prep.recursion;
/**
 * Find the number of ways that a given integer, X, can be expressed as the sum of the Nth powers of unique, natural numbers.
 * For example, if X = 13 and N = 2 , we have to find all combinations of unique squares adding up to . The only solution is
 * 2^2 + 3^2.
 **/
public class PowerSum {

    public static void main(String[] args) {
        int x = 100;
        int n = 3;
        int res = powerSum(x,n);
        System.out.print(res);
    }

    public static int powerSum(int X, int N) {
        // Write your code here
        int result = powerSumRec(X,N,1);
        return result;

    }

    public static int powerSumRec1(int X, int N,int index) {
        // Write your code here
        int remainingX = (int) (X -  Math.pow(index,N));
        //base case
        if(remainingX == 0){
            return 1;
        }

        if(remainingX < 0){
            return 0;
        }

        return powerSumRec(remainingX,N,index+1) + powerSumRec(X,N,index+1);

    }

    public static int powerSumRec(int X, int N,int index) {
        // Write your code here
        if(X == 0){
            return 1;
        }


        int ways = 0;
//        index++;
        while( ((int) (X -  Math.pow(index,N))) >= 0 ){
            ways += powerSumRec(((int) (X -  Math.pow(index,N))),N,index+1);
            index++;
        }
        return ways;
    }
}
