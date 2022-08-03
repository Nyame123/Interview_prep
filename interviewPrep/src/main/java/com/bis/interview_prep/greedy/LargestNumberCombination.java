package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of numbers, create an algorithm that arranges them in order to form the
 * largest possible integer.
 * For example, given [10, 7, 76, 415], you should return 77641510.
 **/
public class LargestNumberCombination {

    public static void main(String[] args) {
        int[] arr = {10, 7, 76, 415};
        String largestNum = largestNumCombination(arr);
        System.out.println(largestNum);
    }

    private static String largestNumCombination(int[] arr) {

        String[] transforms = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            transforms[i] = String.valueOf(arr[i]);
        }

        Comparator<String> orderingComparator = (s, t1) -> {
            String o1 = s + t1;
            String o2 = t1 + s;
            return o2.compareTo(o1);
        };
        Arrays.sort(transforms, orderingComparator);
        if (transforms[0].equals("0"))
            return "0";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(transforms[i]);
        }
        return sb.toString();
    }


}
