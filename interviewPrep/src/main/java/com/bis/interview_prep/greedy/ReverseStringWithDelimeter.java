package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This problem was asked by Facebook.
 * <p>
 * Given a string and a set of delimiters, reverse the words in the string while maintaining the
 * <p>
 * relative order of the delimiters. For example, given "hello/world:here", return "here/world:hello"
 * <p>
 * Follow-up: Does your solution work for the following cases: "hello/world:here/", "hello//world:here"
 **/
public class ReverseStringWithDelimeter {

    public static void main(String[] args) {
        String word = "hello//world:here";
        String res = reverseWithDelimeters(word);
        System.out.println(res);
    }

    /**
     * In this problem, we are presented with a word with some delimiters in it.
     * We are to reverse them and the thought process behind it are:
     * 1. Retrieve all the proper words separated by the delimiters.
     * 2. Reverse the individual word in the word.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static String reverseWithDelimeters(String word) {
        int n = word.length();
        char[] arr = word.toCharArray();
        List<String> separatedWords = new ArrayList<>();
        HashMap<Integer,Character> charMap = new HashMap<>();
        int end = 0;
        int delimiterCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char start = arr[i];

            if (Character.isLetter(start)) {
               sb.append(start);
               end++;
            }else{
                if (sb.length() != 0) {
                    delimiterCount++;
                    separatedWords.add(sb.toString());
                    sb.delete(0, end);
                    end = 0;
                }
                charMap.put(++delimiterCount,start);
            }
        }

        if (sb.length() != 0) {
            delimiterCount++;
            separatedWords.add(sb.toString());
            sb.delete(0, end);
        }

        int m = separatedWords.size();
        for (int i = 0,j = m-1; i < m / 2; j--,i++) {
            String temp = separatedWords.get(i);
            separatedWords.set(i,separatedWords.get(j));
            separatedWords.set(j,temp);
        }

        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < delimiterCount; i++) {
            if (charMap.containsKey(i+1)){
                result.append(charMap.get(i+1));
            }else {
                result.append(separatedWords.get(j++));
            }
        }

        return result.toString();
    }
}
