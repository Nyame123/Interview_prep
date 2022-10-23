package com.bis.interview_prep.recursion.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string, split it into as few strings as possible such that each string is a palindrome.
 *
 * For example, given the input string racecarannakayak, return ["racecar", "anna", "kayak"].
 *
 * Given the input string abc, return ["a", "b", "c"].
 **/
public class AllPossiblePalindromicString {

    public static void main(String[] args) {
        String s = "abbac";

        List<List<String>> res = allPossiblePalindromicString(s);
        System.out.println(getMinPalindromicSubstring(res));
    }

    /**
     * This can be solved by using Backtracking algorithm
     * 1. Start cutting the string which is a palindromic and continuing from the substring
     * to the next possible palindrome until the the string ends.
     * 2. Add this result to the main result.
     *
     * Time Complexity = O(N*2^N)
     * Space Complexity = O(N^2)
     **/
    static List<List<String>> allPossiblePalindromicString(String s) {
        List<List<String>> result = new ArrayList<>();
        Deque<String> curPart = new ArrayDeque<>();
        int n = s.length();
        allPossiblePalindromicString(s.toCharArray(),result,curPart,0,n);
        return result;
    }

    static void allPossiblePalindromicString(char[] s, List<List<String>> result,
                                             Deque<String> curPart, int start, int end){
        if (start >= end){
            result.add(new ArrayList<>(curPart));
            return;
        }

        for (int i = start; i < end; i++) {
            String sub = String.copyValueOf(s,start,i-start+1);
            if (isPalindromic(sub)){
                curPart.addLast(sub);
                allPossiblePalindromicString(s,result,curPart,i+1,end);

                //backtrack
                curPart.removeLast();
            }
        }
    }

    static List<String> getMinPalindromicSubstring(List<List<String>> res){
        int n = res.size();
        int minIndex = 0;
        int prev = res.get(0).size();
        for (int i = 1; i < n; i++) {
            if (prev > res.get(i).size()) {
                minIndex = i;
            }
        }

        return res.get(minIndex);
    }

    static boolean isPalindromic(String s) {
        int right = s.length() - 1;
        int left = 0;

        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        }

        return true;
    }
}
