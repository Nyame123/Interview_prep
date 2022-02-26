package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

/**
 * Find the longest substring with k unique characters in a given string
 * Difficulty Level : Hard
 * Last Updated : 12 Dec, 2021
 * Given a string you need to print longest possible substring that has exactly M unique characters. If there are more than one substring of longest possible length, then print any one of them.
 *
 * Examples:
 *
 * "aabbcc", k = 1
 * Max substring can be any one from {"aa" , "bb" , "cc"}.
 *
 * "aabbcc", k = 2
 * Max substring can be any one from {"aabb" , "bbcc"}.
 *
 * "aabbcc", k = 3
 * There are substrings with exactly 3 unique characters
 * {"aabbcc" , "abbcc" , "aabbc" , "abbc" }
 * Max is "aabbcc" with length 6.
 *
 * "aaabbb", k = 3
 * There are only two unique characters, thus show error message.
 **/
public class LongestSubstringWithAtMostKDistinctCharacters {

    public static void main(String[] args) {
        String s = "aabbcc";
        int k = 3;
        int res = lengthOfLongestSubstringWithKUniqueCharacters(s,k);
        System.out.println(res);
    }

    static int lengthOfLongestSubstringWithKUniqueCharacters(String s,int len) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int k = 0, i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int totalSize = map.size();
            if (totalSize == len && !map.containsKey(c)) {
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
