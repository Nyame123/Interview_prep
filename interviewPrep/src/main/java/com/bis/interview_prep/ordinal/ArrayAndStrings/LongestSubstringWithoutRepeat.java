package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

/**
 * Longest Substring Without Repeating Characters
 * Medium
 * <p>
 * Add to List
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 **/
public class LongestSubstringWithoutRepeat {

    public static void main(String[] args) {
        String s = "geeekfor";
        int res = lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int k = 0, i = 0;
        while (i < n) {
            char c = s.charAt(i);
            map.putIfAbsent(c, 0);
            int totalChar = map.get(c);
            if (totalChar == 1) {
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

    static int longestUniqueSubsttr(String s) {

        // Creating a set to store the last positions of occurrence
        HashMap<Character, Integer> seen = new HashMap<>();
        int maximum_length = 0;

        // starting the initial point of window to index 0
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            // Checking if we have already seen the element or not
            if (seen.containsKey(s.charAt(end))) {
                // If we have seen the number, move the start pointer
                // to position after the last occurrence
                start = Math.max(start, seen.get(s.charAt(end)) + 1);
            }

            // Updating the last seen value of the character
            seen.put(s.charAt(end), end);
            maximum_length = Math.max(maximum_length, end - start + 1);
        }
        return maximum_length;
    }


    static void memorize(HashMap<Character, Integer> map, char key, int it) {
        int newValue = map.get(key) + it;
        if (newValue == 0) {
            map.remove(key);
        } else {
            map.put(key, newValue);
        }


    }
}
