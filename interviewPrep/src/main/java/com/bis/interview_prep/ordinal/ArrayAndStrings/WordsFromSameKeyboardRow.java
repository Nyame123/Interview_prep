package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Given a list of words, return all the words that require only a single row of a keyboard to type.
 * Note: You may assume that all words only contain lowercase alphabetical characters.
 *
 * Ex: Given the following list of words…
 *
 * words = ["two", "dad", "cat"], return ["two", "dad"].
 * Ex: Given the following list of words…
 *
 * words = ["ufo", "xzy", "byte"], return [].
 **/
public class WordsFromSameKeyboardRow {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "ufo", "xzy", "byte" );

        findWordsSameRow(words);
    }

    static void findWordsSameRow(List<String> words) {

        //create a hashmap of the characters with keyboard row
        // Stores row number of all possible
        // character of the strings
        Map<Character, Integer> mp = new HashMap<>();
        mp.put('q', 1);
        mp.put('w', 1);
        mp.put('e', 1);
        mp.put('r', 1);
        mp.put('t', 1);
        mp.put('y', 1);
        mp.put('u', 1);
        mp.put('i', 1);
        mp.put('o', 1);
        mp.put('p', 1);
        mp.put('a', 2);
        mp.put('s', 2);
        mp.put('d', 2);
        mp.put('f', 2);
        mp.put('g', 2);
        mp.put('h', 2);
        mp.put('j', 2);
        mp.put('k', 2);
        mp.put('l', 2);
        mp.put('z', 3);
        mp.put('x', 3);
        mp.put('c', 3);
        mp.put('v', 3);
        mp.put('b', 3);
        mp.put('n', 3);
        mp.put('m', 3);

        for(String word: words){

            int i = 0;
            char[] lword = word.toLowerCase().toCharArray();
            //find the initial character
            int numRow = mp.get(lword[i]);
            for (i = 1; i < lword.length; i++) {
                if (mp.get(lword[i]) != numRow){
                    break;
                }
            }

            if (i == word.length()){
                System.out.println(word);
            }
        }

    }
}
