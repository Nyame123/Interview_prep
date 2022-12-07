package com.bis.interview_prep.ordinal.bitManipulation;
/**
 * Given an array of integers where every integer occurs three
 * times except for one integer, which only occurs once, find and return the non-duplicated integer.
 *
 *  For example, given [6, 1, 3, 3, 3, 6, 6], return 1. Given [13, 19, 13, 13], return 19.

 Do this in O(N) time and O(1) space.
 **/
public class ElementAppearedOnce {

    public static void main(String[] args) {
        int[] arr = {6, 1, 3, 3, 3, 6, 6};
        int res = findSingleElementWithModulo(arr);
        System.out.println(res);
    }


    //using linear time and constant space
    static int findSingleElement(int[] arr){
        int n = arr.length;
        int ones = 0;
        int twos = 0;
        int common_bits = 0;

        for (int i = 0; i < n; i++) {

            twos |= (ones & arr[i]);

            ones ^= arr[i];

            common_bits = ~(ones & twos);

            ones &= common_bits;
            twos &= common_bits;
        }

        return ones;
    }

    //using linear time and constant space with modulo operation
    static int findSingleElementWithModulo(int[] arr){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            //creating a positional mask
            int bitMask = (1 << i);
            for (int j = 0; j < n; j++) {

                if ((bitMask & arr[j]) != 0){
                    sum++;
                }
            }

            if ((sum % 3) != 0){
                result |= bitMask;
            }

        }

        return result;
    }
}
