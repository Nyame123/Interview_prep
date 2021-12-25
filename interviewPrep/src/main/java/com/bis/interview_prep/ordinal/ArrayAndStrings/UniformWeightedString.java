package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UniformWeightedString {

    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
        // Write your code here

        int n = queries.size();
        List<String> ans = new ArrayList<>(n);
        HashSet<Integer> set = new HashSet<>();
        char temp = s.charAt(0);
        int c = (int)temp-'a'+1;
        set.add(c);
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == temp){
                char sChar = s.charAt(i);
                c += (int) sChar-'a'+1;
                set.add(c);
            }else{
                temp = s.charAt(i);
                c = (int) temp-'a'+1;
                set.add(c);
            }
        }
       // System.out.println(set);
        for(int i = 0; i < n; i++){
            if(set.contains(queries.get(i))){
                ans.add("Yes");
            }else{
                ans.add("No");
            }
        }

        return ans;
    }
}
