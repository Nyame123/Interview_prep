package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class SubStringPreprocessing {
    static int[][] count = new int[105][26];
    public static void main(String[] args) {
        String s = "geeksforgeeks";
        preprocessString(s);
        int res = findChar(0,12,'e');
        System.out.println(res);
    }

    static int findChar(int l,int r,char c){
       int sum = count[r][c-'a'] - (l != 0? count[l-1][c-'a']: 0);
        return sum;
    }

    static void preprocessString(String s){

        for (int i = 0; i < s.length(); i++) {
            count[i][s.charAt(i)-'a']++;
        }

        //add up to i
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                count[i][j] += count[i-1][j];
            }
        }
    }
}
