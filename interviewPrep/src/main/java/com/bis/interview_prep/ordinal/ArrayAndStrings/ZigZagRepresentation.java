package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 * Given a string and a number of lines k, print the string in zigzag form.
 * In zigzag, characters are printed out diagonally from top left to bottom
 * right until reaching the kth line, then back up to top right, and so on.
 *
 * For example, given the sentence "thisisazigzag" and k = 4, you should print:
 *
 * t     a     g
 *  h   s z   a
 *   i i   i z
 *    s     g
 *
 **/
public class ZigZagRepresentation {

    public static void main(String[] args) {
        String word = "AB";
        int k = 1;
        String zigzag = zigzagRep(word,k);
        System.out.println(zigzag);
    }

    /**
     * To move in a zigzag way, go through the word from row 0 to bottom and
     * from bottom to top
     **/
    static String zigzagRep(String word, int k) {
        int n = word.length();

        int row = 0;
        boolean bottom = true;
        StringBuilder[] arr = new StringBuilder[k];
        for (int i = 0; i < k; i++) {
            arr[i] = new StringBuilder();
        }

        for (int i = 0; i < n; i++) {

            if (row < 0){
                bottom = true;
                row++;
            }else if (row >= k){
                bottom = false;
                row--;
            }
            arr[row].append(word.charAt(i));
            if (row == 0){
                bottom = true;
            }else if (row == k-1){
                bottom = false;
            }

            if (bottom){
                row++;
            }else{
                row--;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < k; i++) {
            result.append(arr[i].toString());
        }

        return result.toString();
    }
}
