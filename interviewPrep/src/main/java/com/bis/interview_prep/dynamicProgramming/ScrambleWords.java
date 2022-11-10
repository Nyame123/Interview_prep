package com.bis.interview_prep.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * We can scramble a string s to get a string t using the following algorithm:
 *
 * If the length of the string is 1, stop.
 * If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 * Apply step 1 recursively on each of the two substrings x and y.
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now, and the result string is "rgeat" which is s2.
 * As one possible scenario led s1 to be scrambled to s2, we return true.
 **/
public class ScrambleWords {

    public static void main(String[] args) {
        String s1 = "abca";
        String s2 = "caba";

        boolean result = isScramble(s1,s2);
        System.out.println(result);
    }

    static boolean isScramble(String s1, String s2) {
        return rec(s1,s2, new HashMap<>());
    }

    static boolean rec(String s1, String s2, Map<String,Boolean> memo){
        String key = String.format("%s,%s",s1,s2);

        //base case
        if(s1.length() == 1){
            return s1.equals(s2);
        }

        if (s1.length() != s2.length()){
            return false;
        }

        if(s1.equals(s2)){
            return true;
        }

        if(memo.containsKey(key)){
            return memo.get(key);
        }

        //prevent unnecesary recursion
        Map<Character,Integer> freq1 = new HashMap<>();
        Map<Character,Integer> freq2 = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            freq1.putIfAbsent(s1.charAt(i),0);
            int value = freq1.get(s1.charAt(i));
            freq1.put(s1.charAt(i),value+1);

            freq2.putIfAbsent(s2.charAt(i),0);
            int value2 = freq2.get(s2.charAt(i));
            freq2.put(s2.charAt(i),value2+1);
        }

        for(int i = 0; i < s1.length(); i++){

            if(freq1.get(s1.charAt(i)) != freq2.get(s1.charAt(i))){
                return false;
            }
        }

        /*int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                memo.put(key, false);
                return false;
            }
        }*/

        int n = s1.length();
        for(int i = 1; i < n; i++){
            boolean partition = rec(s1.substring(0,i),s2.substring(0,i), memo) && rec(s1.substring(i), s2.substring(i), memo);
            boolean partitionFlip = rec(s1.substring(n-i),s2.substring(0,i), memo) && rec(s1.substring(0,n-i),s2.substring(i), memo);

            if(partition || partitionFlip){
                memo.put(key,true);
                return true;
            }


        }

        memo.put(key,false);
        return false;
    }
}
