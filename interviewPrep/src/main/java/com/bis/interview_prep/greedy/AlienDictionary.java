package com.bis.interview_prep.greedy;

import java.util.HashMap;

/**
 *Your friend has created a secret encrypted language by shifting letters around in the English alphabet.
 *  Given the new order of the letters and a list of words, return whether or not your friend has sorted the words lexicographically.
 *
 * Ex: Given the following order and words...
 *
 * order = "worldabcefghijkmnpqstuvxyz"
 * words = ["word","world","row"]
 * return false since ‘d’ comes before ‘l’ in the new language’s ordering and
 * therefore the first and second word are not sorted correctly.
 **/
public class AlienDictionary {

    public static void main(String[] args) {
        String order = "ngxlkthsjuoqcpavbfdermiywz";
        String[] words = {"kuvp","q"};


        boolean res = isAlienSorted(words,order);
        System.out.println(res);
    }

    public static boolean isAlienSorted(String[] words, String order) {
        int n = order.length();
        HashMap<Character,Integer> map = new HashMap<>(n);
        for(int i = 0; i < n; i++){
            char c = order.charAt(i);
            map.put(c,i);
        }


        int m = words.length;
        for(int i = 0; i < m-1; i++){
            String w1 = words[i];
            String w2 = words[i+1];

            int len1 = w1.length();
            int len2 = w2.length();

            int len = Math.min(len1,len2);
            int j = 0;
            for(j = 0; j < len; j++){
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2){
                    if(map.get(c1) > map.get(c2)){
                        return false;
                    }
                    break;
                }

            }

            if(j == len && len1 > len2){
                return false;
            }
        }

        return true;
    }
}
