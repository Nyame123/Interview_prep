package com.bis.interview_prep.greedy;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *Given two strings s and t, return the index of the first occurrence of t within s if it exists; otherwise, return -1.
 *
 * Ex: Given the following strings s and t…
 *
 * s = "abc", t = "a", return 0.
 * Ex: Given the following strings s and t…
 *
 * s = "abc", t = "abcd", return -1.
 **/
public class ContainPrefix {

    public static void main(String[] args) {
        String s = "geeksfffffoorrfoorforgeeks";
        String t = "for";

        int firstIndex = containSubstringZAlgorithm(s,t);
        System.out.println(firstIndex);
    }

    /**
     * We can do this by making use of Z-Algorithm which make search for a pattern
     * very efficient.
     *
     * Time Complexity = O(n+m)
     * Space Complexity = O(n+m)
     **/
    static int containSubstringZAlgorithm(String s, String t){
        //create combined string
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();
        int n = s.length();
        int m = t.length();
        char[] fullCharArr = new char[n+m+1];
        int index = 0;
        for (int i = 0; i < m; i++) {
            fullCharArr[index++] = tCharArr[i];
        }

        fullCharArr[index++] = '$';

        for (int i = 0; i < n; i++) {
            fullCharArr[index++] = sCharArr[i];
        }

        int[] zArr = new int[n+m+1];

        //make zArray of values
        fillZArray(zArr,fullCharArr);

        for (int i = 0; i < zArr.length; i++) {
            if (zArr[i] == m)
                return i - m - 1;
        }

        return -1;
    }

    static void fillZArray(int[] zArr, char[] fullCharArr) {

        int left = 0, right = 0;
        int n = fullCharArr.length;
        for (int i = 1; i < n; i++) {
            if (i > right){
                left = right = i;
                while (right < n && (fullCharArr[right] == fullCharArr[right-left])){
                    right++;
                }

                zArr[i] = right-left;
                right--;
            }else {
                int k = i - left;
                if (zArr[k] < right-i+1){
                    zArr[i] = zArr[k];
                }else{
                    left = k;
                    while (right < n && fullCharArr[right] == fullCharArr[right-left]){
                        right++;
                    }

                    zArr[i] = right-left;
                    right--;
                }

            }
        }

    }

    /**
     *In this problem, we check every index of the string to determine where
     * the pattern can begin.
     *
     * Time Complexity = O(n*m) where n = len(string) and m = len(pattern)
     **/
    static int containSubstring(String s, String t) {
        int n = s.length();
        int m = t.length();

        if (m > n)
            return -1;
        next:
        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = 0; j < m; j++) {
                if (s.charAt(k) == t.charAt(j)){
                    k++;
                }else {
                    continue next;
                    //break;
                }
            }
//            if (k-i == m){
//                return i;
//            }
            return i;
        }

        return -1;
    }


}
