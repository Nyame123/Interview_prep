package com.bis.interview_prep.ordinal.bitManipulation;
/**
 * Generating a power set of a number
 * 0 => {}
 * 1 => {},{1}
 * 1,2 => {},{1},{2},{1,2}
 **/
public class PowerSet {

    public static void main(String[] args) {
        findSubsets(new int[]{1,2,3});
    }


    //Time Complexity = O(n*2^n)
    private static void findSubsets(int array[]) {
        int numOfSubsets = 1 << array.length;

        for (int i = 0; i < numOfSubsets; i++) {
            int pos = array.length - 1;
            int bitmask = i;

            System.out.print("{");
            while (bitmask > 0) {
                if ((bitmask & 1) == 1)
                    System.out.print(array[pos] + ",");
                bitmask >>= 1;
                pos--;
            }
            System.out.print("}");
        }
    }
}
