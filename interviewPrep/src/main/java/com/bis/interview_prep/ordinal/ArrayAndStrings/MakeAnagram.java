package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class MakeAnagram {

    public static void main(String[] args) {
        int count = makeAnagram("fcrxzwscanmligyxyvym","jxwtrhvujlmrpdoqbisbwhmgpmeoke");
        System.out.println(count);
    }
    public static int makeAnagram(String a, String b) {
        // Write your code here
        int[] alph = new int[26];
        for (char c : a.toCharArray()) {
            int fc = c-'a';
            alph[fc]++;
        }

        for (char c : b.toCharArray()) {
            int fc = c-'a';
            alph[fc]--;
        }

        int counter = 0;
        for (int i = 0; i < alph.length; i++) {
            counter += Math.abs(alph[i]);
        }
        return counter;
    }
}
