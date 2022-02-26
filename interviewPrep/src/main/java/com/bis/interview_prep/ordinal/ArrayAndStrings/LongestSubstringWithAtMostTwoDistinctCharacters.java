package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

/**
 * Given a string s, return the length of the longest substring containing at most two distinct characters.
 * Note: You may assume that s only contains lowercase alphabetical characters.
 *
 * Ex: Given the following value of s…
 *
 * s = "aba", return 3.
 * Ex: Given the following value of s…
 *
 * s = "abca", return 2.
 **/
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        String s = "aabbcc";
        int res = lengthOfLongestSubstringWithTwoUniqueCharacters(s);
        System.out.println(res);
    }

    static int lengthOfLongestSubstringWithTwoUniqueCharacters(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int k = 0, i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int totalSize = map.size();
            if (totalSize == 2 && !map.containsKey(c)) {
                max = Math.max(max, i - k);
                memorize(map, s.charAt(k), -1);
                k++;
            } else {
                memorize(map, s.charAt(i), 1);
                i++;
            }
        }
        max = Math.max(max, i - k);

        return max;
    }

    static void memorize(HashMap<Character, Integer> map, char key, int it) {
        map.putIfAbsent(key, 0);
        int newValue = map.get(key) + it;
        if (newValue == 0) {
            map.remove(key);
        } else {
            map.put(key, newValue);
        }


    }
}
