package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 *  Gray code is a binary code where each successive value differ in only one bit,
 *  as well as when wrapping around. Gray code is common in hardware so that
 *  we don't see temporary spurious values during transitions.
 *
 * Given a number of bits n, generate a possible gray code for it.
 *
 * For example, for n = 2, one gray code would be [00, 01, 11, 10].
 **/
public class GrayCodeGeneration {

    public static void main(String[] args) {
        int n = 3;
        List<String> arr = generateGrayCode(n);
        System.out.println(arr);
    }

    /**
     * In this problem, we use binary digits to generate a gray code. ie. 0 and 1.
     * 1. We first take 1 and 0 into the answer set
     * 2. We take the reverse of the solution set as an answer
     * 3. We append 0 to the first half and 1 to the second half.
     *
     * Time Complexity = O(2^N)
     **/
    private static List<String> generateGrayCode(int n) {

        if (n <= 0){
            return null;
        }

        List<String> ans = new ArrayList<>();
        ans.add("0");
        ans.add("1");

        for (int i = 2; i < (1 << n); i = (i << 1)) {

            //reverse addition
            for (int j = i-1; j >= 0; j--) {
                ans.add(ans.get(j));
            }


            //add 0 to the first half
            for (int j = 0; j < i; j++) {
                ans.set(j,"0"+ans.get(j));
            }

            //add 1 to the second half
            for (int j = i; j < (2*i); j++) {
                ans.set(j,"1"+ans.get(j));
            }

        }

        return ans;
    }
}
