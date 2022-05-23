package com.bis.interview_prep.greedy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a string, s, which consists of only lowercase alphabetical characters,
 * return the length of the longest palindrome you can form using its letters.
 *
 * Ex: Given the following s...
 *
 * s = “aa”, return 2.
 * Ex: Given the following s...
 *
 * s = “abbcaadabac”, return 9.
 **/
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "abbcaadabac";
        int longestPalindrome = findLongestPalindrome2(s);
        System.out.println(longestPalindrome);
    }

    /**
     * We can use Hashing to store all the characters that can form a palindrome
     * 1. Create a hashset and use count the characters that can form a palindrome.
     * 2. Store the characters in the hashset if not already stored.
     * 3. If already stored, then remove the character and count.
     * 4. If the hashset is empty then the res = 2*count;
     * 5. Else 2*count + 1;
     *
     * Time Complexity = O(N)
     * Space Complexity = O(1)
     **/
    static int findLongestPalindrome(String s) {

        int n = s.length();
        int count = 0;
        HashSet<Character> set = new HashSet<>(26);
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (set.contains(chars[i])){
                set.remove(chars[i]);
                count++;
            }else{
                set.add(chars[i]);
            }
        }

        if (set.isEmpty()){
            return 2*count;
        }

        return 2*count +1;
    }

    /**
     * Count all the even occuring elements.
     * 1. count the frequency of characters of the elements.
     * 2. Count the double occuring elements by dividing count of each element by 2;
     * 3. If there is odd count, the res = 2*Count + 1 else res = 2*Count
     *
     * Time Complexity = O(N)
     * Space Complexity = O(1)
     **/
    static int findLongestPalindrome2(String s) {

        int n = s.length();
        int count = 0;
        HashMap<Character,Integer> map = new HashMap<>(26);
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            map.put(chars[i],map.getOrDefault(chars[i],0)+1);
        }

        boolean isOdd = false;
        for (Character c: map.keySet()) {
            int freq = map.get(c);
            if (freq % 2 == 1)
                isOdd = true;
           count += freq / 2;
        }

        if (!isOdd){
            return 2*count;
        }

        return 2*count +1;
    }
}
