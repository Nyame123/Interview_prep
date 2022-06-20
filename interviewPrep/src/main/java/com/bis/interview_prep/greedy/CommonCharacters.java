package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 *  iven a string array, words, return a list containing all the characters that are common to all the words.
 * Note: If a character appears multiple times in all the words it should also appear multiple times in your return list.
 *
 * Ex: Given the following words…
 *
 * words = ["abc", "ab", "a"], return ["a"].
 * Ex: Given the following words…
 *
 * words = ["deef", "ddabee", "eebhk"], return ["e","e"].
 **/

public class CommonCharacters {

    public static void main(String[] args) {
        String[] arr = {"deef", "ddabee", "eebhk"};
        List<Character> res = commonCharacters(arr);
        System.out.println(res);
    }

    /**
     * In this problem, we use hashing algo for this problem.
     * This solution is kind of brute force solution.
     * 1. Start with the first string and increase the counter of each characters in the string
     * 2. Go on to the next string and increase their occurrences of the characters in the string
     * 3. Find the minimum count btn previous hash and current hash.
     * 4. Go through the previous hash and print out the characters common to the words.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static List<Character> commonCharacters(String[] arr) {
        int n = arr.length;
        List<Character> ans = new ArrayList<>();
        if (n == 0){
            return ans;
        }

        int[] prev = new int[26];
        for (int i = 0; i < arr[0].length(); i++) {
            prev[arr[0].charAt(i)-'a']++;
        }

        for (int i = 1; i < n; i++) {

            int[] cur = new int[26];
            String s = arr[i];
            int m = s.length();
            for (int j = 0; j < m; j++) {
                int index = s.charAt(j)-'a';
                cur[index]++;
            }

            for (int j = 0; j < 26; j++) {
                prev[j] = Math.min(prev[j],cur[j]);
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < prev[i]; j++) {
                char c = (char) (i + 'a');
                ans.add(c);
            }
        }

        return ans;
    }
}
