package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Reverse Words in a String
 * Medium
 *
 * Given a string s, reverse the words.
 * Note: You may assume that there are no leading or trailing
 * whitespaces and each word within s is only separated by a single whitespace.
 * <p>
 * Ex: Given the following string s…
 * <p>
 * s = "The Daily Byte", return "Byte Daily The".
 **/
public class ReverseWords {

    public static void main(String[] args) {
        String word = "a good   example";
        System.out.println(reverseWord(word));
    }

    static String reverseWord(String s) {
        s = s.trim().replaceAll("\\s+", " ");
        int n = s.length();
        char[] characters = s.toCharArray();

        //reverse the whole string
        for (int i = 0, j = n - 1; i < n / 2; j--, i++) {
            char c = characters[i];
            characters[i] = characters[j];
            characters[j] = c;
        }

        //reverse the individual words
        for (int i = 0, j = 0; i < n; ) {
            while (i < n && characters[i] != ' ') {
                i++;
            }

            i--;
            for (int l = j, k = i; l < (i + j) / 2 + 1; k--, l++) {
                char c = characters[l];
                characters[l] = characters[k];
                characters[k] = c;
            }

            j = i + 2;
            i = j;

        }

        return String.valueOf(characters);
    }

    static String reverseWordNoExtraSpace(String s) {
        s = s.replaceAll("\\s+", " ");
        String[] characters = s.split(" ");
        int n = characters.length;
        for (int i = 0, j = n - 1; i < n / 2; j--, i++) {
            String c = characters[i];
            characters[i] = characters[j];
            characters[j] = c;
        }

        return String.join(" ", characters);
    }

    static String reverseWordUsingStringBuilder(String s) {
        s = s.replaceAll("\\s+", " ");
        String[] characters = s.trim().split(" ");
        int n = characters.length;

        StringBuilder stringBuilder = new StringBuilder(n);
        for (int i = n - 1; i >= 0; i--) {
            if (i > 0) {
                stringBuilder.append(characters[i]).append(" ");
            } else {
                stringBuilder.append(characters[i]);
            }
        }

        return stringBuilder.toString();
    }
}
