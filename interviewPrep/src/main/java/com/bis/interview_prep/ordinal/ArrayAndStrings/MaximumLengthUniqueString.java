package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given an array of words, return the length of the longest phrase, containing only unique characters,
 * that you can form by combining the given words together.
 * <p>
 * Ex: Given the following words…
 * <p>
 * words = ["the","dog","ran"], return 9 because you can combine all the words, i.e.
 * "the dog ran" since all the characters in the phrase are unique.
 * Ex: Given the following words…
 * <p>
 * words = ["the","eagle","flew"], return 4 because "flew" is the longest phrase you can
 * create without using duplicate characters.
 **/

public class MaximumLengthUniqueString {

    public static void main(String[] args) {
        String[] words = {"the","dog","ran"};
        System.out.println(maxLength(Arrays.asList(words)));
    }

    static int maxLength(List<String> arr) {
        return maxLengthBacktrackTwoOption(0, arr, "");
    }

    static int maxLengthBacktrack(int index, List<String> arr, String s) {

        int n = arr.size();

        if (!isValid(s)) {
            return 0;
        }

        int len = s.length();

        for (int i = index; i < n; i++) {

            len = Math.max(len, maxLengthBacktrack(i + 1, arr, s + arr.get(i)));
        }

        return len;
    }

    static int maxLengthBacktrackTwoOption(int index, List<String> arr, String s) {

        int n = arr.size();
        if (!isValid(s)) {
            return 0;
        }

        if (index >= n){
            return s.length();
        }

        int len;
        //add this string
        len = Math.max(
                maxLengthBacktrackTwoOption(index+1,arr,s+arr.get(index)),
                maxLengthBacktrackTwoOption(index+1,arr,s));

        //len = Math.max(len,s.length());
        return len;
    }

    static boolean isValid(String s) {
        HashSet<Character> set = new HashSet<>();

        char[] stringCharArray = s.toCharArray();
        int n = stringCharArray.length;

        for (char c : stringCharArray) {
            if (set.contains(c)) {
                return false;
            }

            set.add(c);
        }

        return true;
    }

}

class Solution {

    public static void main(String[] args) {
        String[] words = {"the","dog","ran"};
        System.out.println(maxLength(Arrays.asList(words)));
    }
    static int maxLength(List<String> arr) {
        int m = arr.size();
        int[] masks = new int[m];
        int index = 0;
        for (String s : arr) {
            int bits = 0;
            for (char c : s.toCharArray()) {
                if ((bits & (1 << (c - 'a'))) > 0) {
                    bits = -1;
                    break;
                } else
                    bits |= 1 << (c - 'a');
            }
            masks[index++] = bits;
        }

        return dfs(0, masks, arr, 0);
    }

    static int dfs(int index, int[] masks, List<String> arr, int cur) {
        if (index == masks.length)
            return 0;
        int tmp = 0;
        tmp = Math.max(tmp, dfs(index + 1, masks, arr, cur));
        if ((cur & masks[index]) == 0 && masks[index] >= 0) {
            tmp = Math.max(tmp, arr.get(index).length() + dfs(index + 1, masks, arr, cur | masks[index]));
        }
        return tmp;
    }
}
