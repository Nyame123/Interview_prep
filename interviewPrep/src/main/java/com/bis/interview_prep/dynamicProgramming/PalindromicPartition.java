package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if
 * every substring of the partition is a palindrome. For example, “aba|b|bbabb|a|b|aba” is
 * a palindrome partitioning of “ababbbabbababa”. Determine the fewest cuts needed for a
 * palindrome partitioning of a given string. For example, minimum of 3 cuts are needed for
 * “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”. If a string is a palindrome,
 * then minimum 0 cuts are needed. If a string of length n containing all different characters,
 * then minimum n-1 cuts are needed.
 **/
public class PalindromicPartition {

    public static void main(String[] args) {
        String s = "ababbbabbababa";
        int minPartition = minimumPalindromicPartitionDPUsingLIS(s);
        System.out.println(minPartition);
    }

    /**
     * Using dynamic programming concept to calculate the minimum partition to
     * make in a string.
     * We break the string into multiple parts and solve each substring from
     * i to j.
     *
     * The algorithm used on Longest Increasing Subsequence can be modified to solve this.
     *  Time Complexity = O(N^2)
     *  Space Complexity = O(N^2)
     **/
    static int minimumPalindromicPartitionDPUsingLIS(String s){
        int n = s.length();

        int[] cut = new int[n];
        boolean[][] palindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
        }

        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i-j < 2 || palindrome[j+1][i-1])){
                    palindrome[j][i] = true;
                    min = Math.min(min,j == 0? 0 : cut[j-1]+1);
                }
            }
            cut[i] = min;
        }

        return cut[n-1];
    }

    /**
     * Using dynamic programming concept to calculate the minimum partition to
     * make in a string.
     * We break the string into multiple parts and solve each substring from
     * i to j.
     *
     * The algorithm used on MatrixMultiplicationCost can be modified to solve this.
     *
     * Time Complexity = O(N^3)
     * Space Complexity = O(N^2)
     **/
    static int minimumPalindromicPartitionUsingMMC(String s) {
        int n = s.length();
        int[][] cut = new int[n][n];
        boolean[][] palindromic = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            cut[i][i] = 0;
            palindromic[i][i] = true;
        }

        for (int len = 1; len < n; len++) {

            for (int i = 0, j = len; i < n && j < n; j++, i++) {
                boolean same = s.charAt(i) == s.charAt(j);
                if (len == 1) {
                    palindromic[i][j] = same;
                } else {
                    palindromic[i][j] = same && palindromic[i + 1][j - 1];
                }

                if (palindromic[i][j]) {
                    cut[i][j] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, cut[i][k] + cut[k + 1][j] + 1);
                    }

                    cut[i][j] = min;
                }
            }
        }

        return cut[0][n - 1];
    }

    //Using recursion or top down solution
    static int palindromicPartitionTopDown(String s) {
        int n = s.length();
        int minPartition = recurse(s,0,n-1,new HashMap<>());
        return minPartition;
    }


    static int recurse(String s, int i, int j, Map<String, Integer> cuts) {
        String key = String.format("%s,%s", i, j);
        if (cuts.containsKey(key)) {
            return cuts.get(key);
        }

        if (i > j)
            return 0;

        if (i == j) {
            cuts.put(key, 0);
            return 0;
        }

        if (isPalindromic(s,i,j)){
            cuts.put(key,0);
            return 0;
        }else {
            int min = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                min = Integer.min(min,
                        recurse(s, i, k, cuts) + recurse(s, k + 1, j, cuts) + 1
                );
            }

            cuts.put(key,min);
            return min;

        }
    }


    static boolean isPalindromic(String s, int i, int j){
        int left = i, right = j;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
