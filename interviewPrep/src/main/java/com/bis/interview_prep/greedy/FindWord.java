package com.bis.interview_prep.greedy;

import java.util.HashMap;
import java.util.HashSet;

public class FindWord {

    public static void main(String[] args) {

        String[] arr = {"W>I", "R>L", "T>Z", "Z>E", "S>W", "E>R", "L>A", "A>N", "N>D", "I>T"};

        String res = findWord(arr);
        System.out.println(res);
    }

    static String findWord(String[] arr){
        HashMap<String,String> map = new HashMap<>();
        int n = arr.length;
        HashSet<String> keys = new HashSet<>();
        HashSet<String> values = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String rule = arr[i];
            String[] rules = rule.split(">");
            keys.add(rules[0]);
            values.add(rules[1]);
            map.put(rules[0],rules[1]);
        }

        String start  = null;
        for(String s: keys){
            if (!values.contains(s)){
                start = s;
            }
        }

        StringBuilder sb =  new StringBuilder();
        sb.append(start);
        while (map.containsKey(start)){
            sb.append(map.get(start));
            start = map.get(start);
        }


        return sb.toString();
    }
}
