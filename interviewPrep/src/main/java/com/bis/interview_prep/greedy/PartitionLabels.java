package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s containing only lowercase characters, return a list of integers
 * representing the size of each substring you can create such that each character in s only appears in one substring.
 * <p>
 * Ex: Given the following string s…
 * <p>
 * s = "abacdddecn", return [3, 6, 1]
 * Ex: Given the following string s…
 * <p>
 * s = "aba", return [3]
 **/
public class PartitionLabels {

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> res = partitionSubstring(s);
        System.out.println(res);
    }

    /**
     * In this problem, we are tasks to partition or cut the string into different
     * compartment where each character appear once in a substring.
     * <p>
     * 1. In this problem, we have to track all the ending positions of the characters.
     * 2. After getting the ending positions, we aggregate the count until the character is
     * having the index as the maximum index of that substrings. Then we start a new aggregate.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(1)
     **/
    static List<Integer> partitionSubstring(String s) {
        int n = s.length();
        if (n == 0)
            return new ArrayList<>();

        //assuming lowercase letters only
        int[] endingIndex = new int[26];
        Arrays.fill(endingIndex, -1);
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (endingIndex[c - 'a'] == -1) {
                endingIndex[c - 'a'] = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        int maxIndexLp = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int lastIndex = endingIndex[c - 'a'];
            maxIndexLp = Math.max(maxIndexLp, lastIndex);
            count++;
            if (i == maxIndexLp) {
                ans.add(count);

                //reset for new substring
                count = 0;
                maxIndexLp = -1;
            }
        }

        if (count > 0)
            ans.add(count);

        return ans;
    }
}
