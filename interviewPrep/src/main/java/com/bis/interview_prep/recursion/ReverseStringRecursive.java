package com.bis.interview_prep.recursion;

import java.util.Arrays;

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is a printable ascii character.
 **/
public class ReverseStringRecursive {

    public static void main(String[] args) {
        char[] input = "Hannah".toCharArray();
        reverseString(input);
        System.out.println(Arrays.toString(input));
    }

    private static void reverseString(char[] input) {
        reverseRec(input,0);
    }

    /**
     * 1. Recurse to the midpoint of the input character array.
     * 2. If the array length is odd, return the midpoint+1 as the swapPosition
     * 3. If the array length is even, return the midpoint
     *
     * Time Complexity = O(N/2)=O(N)
     * Axillary Space = O(N/2)=O(N)
     **/
    static int reverseRec(char[] input, int index){
        //base case
        int n = input.length;
        if (n/2 == index){
            return (n%2 == 0)? index : index+1;
        }

        int swapPosition = reverseRec(input,index+1);
        char temp = input[index];
        input[index] = input[swapPosition];
        input[swapPosition] = temp;

        return swapPosition+1;
    }
}
