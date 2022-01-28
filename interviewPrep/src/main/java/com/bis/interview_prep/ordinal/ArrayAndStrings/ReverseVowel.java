package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashSet;
import java.util.Set;

public class ReverseVowel {

    public static void main(String[] args) {
        String word = "The Daily Byte";
        String res = reverseVowel(word);
        System.out.println(res);
    }

    static String reverseVowel(String word) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('A');
        vowels.add('e');
        vowels.add('E');
        vowels.add('i');
        vowels.add('I');
        vowels.add('o');
        vowels.add('O');
        vowels.add('u');
        vowels.add('U');

        int n = word.length();
        char[] characters = word.toCharArray();

        int left = 0;
        int right = n - 1;
        while (left < right) {

            if (!vowels.contains(characters[left])) {
                left++;
                continue;
            }

            if (!vowels.contains(characters[right])) {
                right--;
                continue;
            }


            if (characters[left] != characters[right]) {
                //swap
                char temp = characters[left];
                characters[left] = characters[right];
                characters[right] = temp;
            }

            left++;
            right--;
        }

        return String.valueOf(characters);
    }
}
