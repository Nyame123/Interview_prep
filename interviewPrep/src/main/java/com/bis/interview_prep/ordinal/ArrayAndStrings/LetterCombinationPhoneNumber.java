package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a mapping of digits to letters (as in a phone number), and a digit string,
 * return all possible letters the number could represent. You can assume each valid number in the mapping is a single digit.
 * <p>
 * For example if {“2”: [“a”, “b”, “c”], 3: [“d”, “e”, “f”], …} then “23” should return
 * [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].
 **/
public class LetterCombinationPhoneNumber {

    public static void main(String[] args) {

        String s = "23";
        /*List<String> result = letterCombinations(s);
        System.out.println(result);*/

        letterCombinationsQueue(s,s.length());
    }

    static ArrayList<String> letterCombinationsUtil(String number, int n, String[] table) {
        // To store the generated letter combinations
        ArrayList<String> list = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        q.add("");

        while (!q.isEmpty()) {
            String s = q.remove();

            // If complete word is generated
            // push it in the list
            if (s.length() == n)
                list.add(s);
            else {
                String val = table[number.charAt(s.length())-'0'];
                for (int i = 0; i < val.length(); i++) {
                    q.add(s + val.charAt(i));
                }
            }
        }
        return list;
    }

    // Function that creates the mapping and
    // calls letterCombinationsUtil
    static void letterCombinationsQueue(String number, int n) {
        // table[i] stores all characters that
        // corresponds to ith digit in phone
        String[] table
                = {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        ArrayList<String> list
                = letterCombinationsUtil(number, n, table);

        // Print the contents of the list
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    static List<String> letterCombinations(String digits) {
        HashMap<Integer, List<String>> keys = new HashMap<>();
        keys.put(2, Arrays.asList("a", "b", "c"));
        keys.put(3, Arrays.asList("d", "e", "f"));
        keys.put(4, Arrays.asList("g", "h", "i"));
        keys.put(5, Arrays.asList("j", "k", "l"));
        keys.put(6, Arrays.asList("m", "n", "o"));
        keys.put(7, Arrays.asList("p", "q", "r", "s"));
        keys.put(8, Arrays.asList("t", "u", "v"));
        keys.put(9, Arrays.asList("w", "x", "y", "z"));

        char[] digs = digits.toCharArray();
        int n = digs.length;
        List<List<String>> arrays = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int it = digs[i] - '0';
            List<String> letters = keys.get(it);
            arrays.add(letters);
        }

        if (n == 0) {
            return null;
        }

        return merge(arrays, 0, arrays.size() - 1);
    }

    static List<String> merge(List<List<String>> arrays, int l, int r) {
        if (l == r) {
            return arrays.get(l);
        }

        if (l < r) {
            int mid = l + (r - l) / 2;
            List<String> leftComb = merge(arrays, l, mid);
            List<String> rightComb = merge(arrays, mid + 1, r);

            return combine(leftComb, rightComb);
        }

        return null;
    }

    static List<String> combine(List<String> l1, List<String> l2) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l2.size(); j++) {
                String c = l1.get(i) + l2.get(j);
                ans.add(c);
            }
        }

        return ans;
    }


}
