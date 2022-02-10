package com.bis.interview_prep.ordinal.bitManipulation;
/**
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: true
 * Explanation: The binary representation of 5 is: 101
 * Example 2:
 *
 * Input: n = 7
 * Output: false
 * Explanation: The binary representation of 7 is: 111.
 * */
public class FlipFlopping {

    public static void main(String[] args) {
        int n = 5;
        boolean res = hasAlternatingBits(n);
        System.out.println(res);
    }

    static boolean hasAlternatingBits(int n) {

        int last = (n&1);
        n >>= 1;

        while(n > 0){
            int cur = n&1;
            if(cur == last){
                return false;
            }
            last = cur;
            n >>= 1;
        }

        return true;
    }
}
