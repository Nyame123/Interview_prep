package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class LengthOfLastWord {

    public static void main(String[] args) {
        String input = "Geeks For Geeks";
        System.out.println("The length of last word is " + lengthOfLastWord(input));
    }

    static int lengthOfLastWord(String input) {
        int n = input.length();

        int len = 0;
        boolean isLetter = false;
        for (int i = n-1; i >= 0; i--) {
            if (Character.isLetter(input.charAt(i))){
                len++;
                isLetter = true;
            }else{
                if (isLetter){
                    return len;
                }
                len = 0;
            }
        }
        return 0;
    }
}
