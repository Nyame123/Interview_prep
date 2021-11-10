package com.bis.interview_prep.dynamicProgramming;
/**
 * We need to mulply two numbers without using multiplication or division
 * We can only use addition or substraction or bitwise shift operators
 **/
public class RecursiveMultiplication {

    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int prod = multiply(a,b);
        System.out.println(prod);
    }

    static int multiply(int a, int b){
        int small = Math.min(a, b);
        int big = Math.max(a, b);

        return multiplyRecOptimized(small,big);
    }

    static int multiplyDp(int a, int b){
        int small = Math.min(a, b);
        int big = Math.max(a, b);

        return multiplyDp(small,big,new int[small+1]);
    }

    //Using dynamic programming
    //Time Complexity = O(log(s))
    //Space Complexity = O(log(s))
    static int multiplyDp(int small,int big,int[] memo){
        //base case
        if (small == 0){
            return 0; //since 0*big = 0
        }else if (small == 1){
            return big; //since big*1 = big
        }else if (memo[small] > 0){
            return memo[small];
        }

        //divide the work
        int mid = small >> 1; //divide by 2
        int resEven = multiplyDp(mid,big,memo);

        int resOdd = resEven;
        //if it is even, then we need to recurse for the other half
        if (small % 2 == 1){
            resOdd = multiplyDp(small-mid,big,memo);
        }
        int totalSum = resEven + resOdd;
        memo[small] = totalSum;
        return totalSum;
    }

    //Time Complexity = O(log(s))
    //Space Complexity = O(log(s))
    static int multiplyRec(int small,int big){
        //base case
        if (small == 0){
            return 0; //since 0*big = 0
        }else if (small == 1){
            return big; //since big*1 = big
        }

        //divide the work
        int mid = small >> 1; //divide by 2
        int resEven = multiplyRec(mid,big);
        int resOdd = resEven;
        //if it is even, then we need to recurse for the other half
        if (small % 2 == 1){
            resOdd = multiplyRec(small-mid,big);
        }

        return resEven + resOdd;
    }


    //when we analyse the case, we could see that even
    // when it is odd, we can just add extra big number the even call
    //Time Complexity = O(log(s))
    //Space Complexity = O(log(s))
    static int multiplyRecOptimized(int small,int big){
        //base case
        if (small == 0){
            return 0; //since 0*big = 0
        }else if (small == 1){
            return big; //since big*1 = big
        }

        //divide the work
        int mid = small >> 1; //divide by 2
        int resEven = multiplyRecOptimized(mid,big);
        int resOdd = resEven;
        //if it is even, then we need to recurse for the other half
        if (small % 2 == 1){
            resOdd += big ;
        }

        return resEven + resOdd;
    }
}
