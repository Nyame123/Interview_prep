package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class UtopianTree {
    public static void main(String[] args) {

        int n = 2;
        int result = utopianTreeMathematically(n);
        System.out.println(result);
    }

    public static int utopianTreeMathematically(int n) {
        // Write your code here
        //if the n = 0, then the default height = 1
        int height = (int) (Math.pow(2,((n+1)/2) + 1) - 1 - (n%2));
        return height;

    }

    public static int utopianTree(int n) {
        // Write your code here
        //if the n = 0, then the default height = 1
        int height = 0;
        for(int i = 0; i <= n; i++){
            if(i%2 == 0){ //even
                height += 1;
            }else{ //odd
                height *= 2;
            }
        }

        return height;

    }

}
