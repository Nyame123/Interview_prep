package com.bis.interview_prep.greedy;

/**
 * A simple sentence if syntactically correct if it fulfills given rules. The following are given rules.
 * <p>
 * Sentence must start with a Uppercase character (e.g. Noun/ I/ We/ He etc.)
 * Then lowercase character follows.
 * There must be spaces between words.
 * Then the sentence must end with a full stop(.) after a word.
 * Two continuous spaces are not allowed.
 * Two continuous upper case characters are not allowed. However, the sentence can end after an upper case character.
 **/
public class ValidSentence {

    public static void main(String[] args) {
        String[] str = {"I love cinema.", "The vertex is S.",
                "I am single.", "My name is KG.",
                "I lovE cinema.", "GeeksQuiz. is a quiz site.",
                "I love Geeksquiz and Geeksforgeeks.",
                " You are my friend.", "I love cinema"};

        for (int i = 0; i < str.length; i++) {
            if (checkSentence(str[i]))
                System.out.println("\"" + str[i] + "\"" + " is correct");
            else
                System.out.println("\"" + str[i] + "\"" + " is incorrect");
        }
    }

    static boolean checkSentence(String s) {
        int len = s.length();
        char[] sChars = s.toCharArray();
        if (sChars[0] < 'A' || sChars[0] > 'Z') {
            return false;
        }

        if (sChars[len - 1] != '.')
            return false;

        int prevState = 0, currentState = 0;
        int index = 1;
        while (index <= len) {

            if (sChars[index] >= 'A' && sChars[index] <= 'Z') {
                currentState = 0;
            } else if (sChars[index] == ' ') {
                currentState = 1;
            } else if (sChars[index] >= 'a' && sChars[index] <= 'z') {
                currentState = 2;
            } else if (sChars[index] == '.') {
                currentState = 3;
            }

            if (currentState == prevState && currentState != 2) {
                return false;
            }

            if (prevState == 2 && currentState == 0) {
                return false;
            }

            if (currentState == 3 && prevState != 1) {
                return (index + 1 == len);
            }

            prevState = currentState;

            index++;
        }
        return false;
    }
}
