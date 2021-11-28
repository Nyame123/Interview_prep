package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SherlockValidString {

    public static void main(String[] args) {
        String test = "aabbc";
        if (solve(test)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

       //System.out.println(isValid(test));
    }


    public static String isValid(String s) {
        int[] arr = new int[26];
        List<Integer> freq = new ArrayList<>();
        //generating the frequencies
        for(int i = 0; i < s.length(); i++){
            int c = s.charAt(i)-'a';
            arr[c]++;
        }


        //generating frequecy groups
        HashMap<Integer,Integer> frGroup = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 0){
                int freqKey = arr[i];
                System.out.println((char)(i+'a')+", F "+freqKey);
                frGroup.putIfAbsent(freqKey,0);
                frGroup.put(freqKey,frGroup.get(freqKey)+1);
            }

        }

        //check the Frequency Group size and it should be just two to be
        //closed to valid
        if(frGroup.size() > 2){
            return "NO";
        }else{
            //check if all the frequency Group > 2
            int grth1 = 0;
            int[] set = new int[2];
            int[] fGSet = new int[2];
            int index = 0;

            for(int fGK: frGroup.keySet()){
                set[index] = fGK;
                fGSet[index] = frGroup.get(fGK);
                index++;
                System.out.println(fGK +"== "+frGroup.get(fGK));
                if(frGroup.get(fGK) > 1){
                    grth1++;
                }
            }

            //check if all the frequency group > 1
            if(grth1 == 2){
                return "NO";
            }


            //check if the frequency difference of the two group > 1
            // if(set[0] > 1 && set[1] > 1 && Math.abs(set[0]-set[1]) > 1){
            //     return "NO";
            // }

            //check if the bigger frequency group has frequency greater
            //than the smaller frequency group
            if(fGSet[0] > fGSet[1]){
                //check if removing the character from the less occuring character
                //group could make the string valid
                if(set[0] != set[1]-1 && set[1]-1 > 0){
                    return "NO";
                }
            }else{
                if(set[1] != set[0]-1 && set[0]-1 > 0){
                    return "NO";
                }
            }

        }


        return "YES";
    }

    static boolean solve(String str){
        int m = 256, n = str.length() + 1;
        int[] cnt = new int[m];
        for (char c : str.toCharArray()) {
            ++cnt[c];
        }

        int[] f = new int[n];

        for (int val : cnt) {
            ++f[val];
        }

        int x = 0;
        for (int i = 1; i < n; i++) {
            if (f[i] > 0) {
                ++x;
            }
        }

        if (x == 1) {
            return true;
        }

        if (x > 2) {
            return false;
        }

        int y = 0;

        for (int i = 2; i < n; i++) {
            if (f[i] > 0) {
                ++y;
            }
        }

        if (y == 1 && f[1] == 1) {
            return true;
        }

        int z = 0;

        for (int i = 2; i < n; i++) {
            if (f[i] == 1 && f[i - 1] > 0) {
                ++z;
            }
        }

        return z == 1;
    }
}
