package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * We say a number is sparse if there are no adjacent ones in its binary representation.
 * For example, 21 (10101) is sparse, but 22 (10110) is not. For a given input N, find the
 * smallest sparse number greater than or equal to N.
 * <p>
 * Do this in faster than O(N log N) time.
 **/
public class NextSparseNumber {

    public static void main(String[] args) {
        int num = 6;
        int res = nextSparseElement(num);
        System.out.println(res);
    }

    //Time Complexity = O(LogX)
    private static int nextSparseElement(int num){

        List<Integer> binaryBits = new ArrayList<>();
        while (num != 0){
            binaryBits.add(num & 1);
            num >>= 1;
        }

        //increase the precision by one at most
        binaryBits.add(0);
        int n = binaryBits.size();
        int finalBit = 0;

        for (int i = 0; i < n-1; i++) {
            if (binaryBits.get(i) == 1 && binaryBits.get(i-1) == 1 && binaryBits.get(i+1) == 0){
                binaryBits.set(i+1,1);
                for (int j = i; j >= finalBit; j--) {
                    binaryBits.set(j,0);
                }
                finalBit = i+1;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += binaryBits.get(i) * (1 << i);
        }

        return ans;
    }

    /**
     * Find the binary representation of the num
     * 1. check if the number is a sparse i.e no adjacent ones
     * 2. If that is not a sparse number then we increase the current number by 1.
     *
     * Time Complexity = O(NLogN)
     *
     **/
    private static int nextGreaterElement(int num) {
        for (int i = num; i < 2 * num; i++) {
            if (isSparse(i)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isSparse(int num) {
        int prev = 0;
        while (num != 0) {
            int bit = num & 1;
            if (prev == 1 && prev == bit) {
                return false;
            }
            prev = bit;
            num >>= 1;
        }

        return true;
    }
}
