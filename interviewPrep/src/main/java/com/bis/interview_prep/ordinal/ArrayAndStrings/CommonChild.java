package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * A string is said to be a child of a another string if it can
 * be formed by deleting 0 or more characters from the other string.
 * Letters cannot be rearranged. Given two strings of equal length,
 * what's the longest string that can be constructed such that it is a child of both?
 * <p>
 * Example
 * These strings have two children with maximum length 3, ABC and ABD.
 * They can be formed by eliminating either the D or C from both strings. Return .
 **/
public class CommonChild {

    public static void main(String[] args) {
        String s1 = "harry";
        String s2 = "sally";
        int count = maxCommonChildLen(s1, s2);
        System.out.println(count);
    }

    private static int maxCommonChildLen(String s1, String s2) {

        int[][] T = new int[s1.length()+1][s2.length()+1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    T[i][j] = T[i-1][j-1] + 1;
                }else{
                    T[i][j] = Math.max(T[i-1][j],T[i][j-1]);
                }
            }
        }
        return T[s1.length()][s2.length()];
    }

    static class ResultCon {
        int lastIndex;
        StringBuilder data = new StringBuilder();
        int count;
    }
}
