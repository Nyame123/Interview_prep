package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;

/**
 *This problem was asked by Google.
 *
 * Given a string which we can delete at most k, return whether you can make a palindrome.
 *
 * For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
 **/
public class KWordPalindromeCheck {

    public static void main(String[] args) {
        String word = "waterrfetawx";
        int k = 2;

        boolean res = minDeletePalindrome(word,k);
        System.out.println(res);
    }

    /**
     * This can be solved by using a recursive approach. We know that a palindrome is when reading the
     * word from left is the same as reading from right.
     * 1. We use two pointers aproach for this so if when the left and right pointer are the same, we
     * increase the left pointer and decrease the right pointer while the k still the same.
     * 2. When the two pointers are not the same, we increase the left pointer or decrease the right pointer and
     * decrease the k by 1.
     *
     * 3. When K is negative, then there is no solution and when the left pointer is greater than the right pointer
     * then we have a solution.
     *
     * Time Complexity = O(2^k) since we can make k operations.
     **/
    static boolean isPalindromeWordAfterK(String word, int k) {
        if (word.isEmpty()){
            return true;
        }
        int n = word.length();
        return isPalindromeRecursive(word,k,0,n-1);
    }

    static boolean isPalindromeRecursive(String word, int k,int l, int r){
        if (k < 0){
            return false;
        }
        if (l > r){
            return true;
        }

        if (word.charAt(l) == word.charAt(r)){
           return isPalindromeRecursive(word,k,l+1,r-1);
        }else{
           return isPalindromeRecursive(word,k-1,l+1,r) || isPalindromeRecursive(word,k-1,l,r-1);
        }
    }

    /**
     *From the above solution, we see that the problem is broken down into smaller subproblems
     * and the can be optimal substructure and so we can use memoization to solve this piece to
     * efficiently increase the runtime.
     *
     **/
    static boolean isPalindromeWordAfterKDp(String word, int k) {
        if (word.isEmpty()){
            return true;
        }
        int n = word.length();
        HashMap<String,Boolean> map = new HashMap<>();
        return isPalindromeRecursiveDp(word,k,0,n-1,map);
    }

    static boolean isPalindromeRecursiveDp(String word, int k,int l, int r,HashMap<String,Boolean> memo){
        String key = String.format("%s,%s,%s",l,r,k);
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        if (k < 0){
            return false;
        }
        if (l > r){
            return true;
        }

        boolean res;
        if (word.charAt(l) == word.charAt(r)){
            res = isPalindromeRecursiveDp(word,k,l+1,r-1,memo);
        }else{
            res = isPalindromeRecursiveDp(word,k-1,l+1,r,memo) || isPalindromeRecursiveDp(word,k-1,l,r-1,memo);
        }

        memo.put(key,res);
        return res;
    }

    /**
     * We can again solve this problem by checking the minimum K delete to make
     *
     * Time Complexity = O(n*m)
     * Space Complexity = O(n*m)
     **/
    static int minDeletePalindrome(String word,String rev, int n, int m){

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0){
                    dp[i][j] = j;
                }else if(j == 0){
                    dp[i][j] = i;
                }else if (word.charAt(i-1) == rev.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + 1;
                }
            }
        }
        return dp[n][m];
    }

    static String reverse(String word){
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    static boolean minDeletePalindrome(String word, int k) {

        String rev = word;
        rev = reverse(rev);
        int len = word.length();
        return minDeletePalindrome(word,rev,len,len) <= k*2;
    }

    /**
     * Another approach is using least Common Sequence (LCS) and
     * get the length and substract from the original length.
     * If the result is less than or equal to k then it is true or else false.
     *
     **/
}
