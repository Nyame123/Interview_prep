package com.bis.interview_prep.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 **/
public class WordBreak {

    public static void main(String[] args) {
       String s = "aaaaaaa";
       List<String> wordDict = Arrays.asList("aaaa","aaa");
       System.out.println(wordBreak1(s,wordDict));
    }


    //using hashset
    static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                String str = s.substring(i,j+1);
                if(set.contains(str) && j+1-str.length() >= 0){
                    dp[j+1] = dp[j+1-str.length()];
                }
            }

            if (dp[n])
                break;
        }

        return dp[n];
    }


    static boolean wordBreak1(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i = 0; i < n; i++){
            for(String word: wordDict){
                if (i+word.length() <= n) {
                    String str = s.substring(i, i + word.length());
                    if (str.equals(word)) {
                        dp[i + word.length()] = dp[i];

                    }
                }
            }

            if (dp[n])
                break;
        }

        return dp[n];
    }
}
