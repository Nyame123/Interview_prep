package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings, source and target, return the minimum number of subsequences of source that can be used to form target.
 * Note: If it is impossible to use subsequences of source to form target, return -1.
 * Ex: Given the following source and target…
 * <p>
 * source = "abc", target = "def", return -1.
 * Ex: Given the following source and target…
 * <p>
 * source = "abcdef", target = "abcadaef", return 3.
 **/
public class FormFromSubsequence {

    public static void main(String[] args) {
        String source = "geekofthemonth", target = "geeks";
        int minSubsequence = minimumSubsequence(source, target);
        System.out.println(minSubsequence);
    }

    /**
     * This problem can be solved with a map and character traversal and this works for unique characters in a source
     * 1. Put all characters in the source into a map with item as a key and
     * the index as the value.
     * 2. Traverse over the target character by character and checking for their existence in the
     * map.
     * 3. If the character is not in the map, then we cannot form a target from the source, we return -1.
     * 4. If the index of the previous character is greater than index of the current character, we start a new subsequence.
     * <p>
     * Time Complexity = O(N) N = len(target)
     * Space Complexity = O(M) M = len(source)
     **/
    private static int minimumSubsequence(String source, String target) {
        int n = source.length(), m = target.length();
        Map<Character, List<Integer>> map = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            char c = source.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }

        char[] tartetArr = target.toCharArray();
        int prevIndex = -1;
        int count = 0;
        for (int i = 0; i < m; i++) {
            char c = tartetArr[i];
            if (map.containsKey(c)) {
                int len = map.get(c).size();
                int index = -1;
                if (len == 1) {
                    index = map.get(c).get(0);
                } else {
                    int size = map.get(c).size();
                    List<Integer> charIndices = map.get(c);
                    int j;
                    for (j = 0; j < size; j++) {
                        if (charIndices.get(j) > prevIndex) {
                            index = charIndices.get(j);
                            break;
                        }
                    }

                }

                if (prevIndex == -1) {
                    prevIndex = index;
                } else {
                    if (index < prevIndex) {
                        count++;
                    }

                    prevIndex = index;
                }

            } else {
                return -1;
            }
        }

        return count + 1;
    }
}
