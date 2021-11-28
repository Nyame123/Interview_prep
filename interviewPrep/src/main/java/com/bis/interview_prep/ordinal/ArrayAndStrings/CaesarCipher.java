package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

public class CaesarCipher {

    public static void main(String[] args) {

        String s = "Always-Look-on-the-Bright-Side-of-Life";
        String res = caesarCipher(s,5);
        System.out.println(res);
    }

    //Time complexity = O(n)
    //Space Complexity = O(1)
    public static String caesarCipher1(String s, int k){

        int n = s.length();
        StringBuilder rotatedSb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <='z'){
                char newChar = (char) (((c+k-'a')%26)+'a');
                rotatedSb.append(newChar);
            }else if (c >= 'A' && c <='Z'){
                char newChar = (char) (((c+k-'A')%26)+'A');
                rotatedSb.append(newChar);
            }else{
                rotatedSb.append(c);
            }
        }
        return rotatedSb.toString();
    }

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    public static String caesarCipher(String s, int k) {
        // Write your code here
        //since the string is a valid ascii, it means we have 28 alphabelts
        //abcdefghijklmnopqrstuvwxyz
        //after rotation by a factor k
        String origAlp = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder rotatedSb = new StringBuilder(26);
        k = k%26;
        for (int i = k; i < origAlp.length(); i++) {
            rotatedSb.append(origAlp.charAt(i));
        }

        for (int i = 0; i < k; i++) {
            rotatedSb.append(origAlp.charAt(i));
        }

        String rotatedAlp = rotatedSb.toString();
        HashMap<Integer,Character> map = new HashMap<>(26);

        //map the rotated characters
        for(int i = 0; i < rotatedAlp.length(); i++){
            char c = rotatedAlp.charAt(i);
            map.put(i,c);
        }

        String format = s.toLowerCase();
        StringBuilder cipherSb = new StringBuilder();
        for(int i = 0; i < format.length(); i++){
            char c = format.charAt(i);
            boolean isCap = Character.isUpperCase(s.charAt(i));
            if(c >= 'a' && c <= 'z'){
                int key = c-'a';
                char rotated = map.get(key);

                cipherSb.append((isCap)? Character.toUpperCase(rotated):rotated);
            }else{
                cipherSb.append(c);
            }

        }

        return cipherSb.toString();
    }
}
