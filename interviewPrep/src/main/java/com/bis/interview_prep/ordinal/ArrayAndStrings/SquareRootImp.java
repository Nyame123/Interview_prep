package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 *
 * Example 1:
 *
 * Input: x = 4
 * Output: 2
 * Example 2:
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 *
 * Constraints:
 *
 * 0 <= x <= 231 - 1
 **/
public class SquareRootImp {

    public static void main(String[] args) {
        int num = 2147395599;
        int sqrt = squareRoot(num);
        System.out.println(sqrt);
    }

    /**
     * We can use binary search here to find the element that can have square
     * equal to or less than the 'num'
     *
     * Time Complexity = O(Log(N))
     **/
    static int squareRoot(int num) {
        int left = 1, right = num;
//46339
        while (true){
           int mid = left + (right-left)/2;
            int square = mid*mid;
            if (left > right){ //not a perfect number
                return mid-1;
            }
            if (square == num){
                return mid;
            }else if (mid > (num/mid)){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
    }
}
