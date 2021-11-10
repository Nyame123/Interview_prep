package com.bis.interview_prep.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Generating a permutation of a string with unique character
 **/
public class PermutionNoDuplicates {

    public static void main(String[] args) {
        String item = "aasa";
        List<String> perms = permsWithResult(item);

        System.out.println(perms);
    }

    static List<String> perms(String s){
        if (s == null ){
            return null;
        }
        if (s.isEmpty()) {
            return new ArrayList<>();
        }

        return permsAll(s);
    }

    //Time Complexity = O(n! * n^2)
    static List<String> permsWithResult(String s){
        if (s == null ){
            return null;
        }
        if (s.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        permWithResult(s,"",result);

        return result;
    }

    //Time Complexity = O(n! * n^2)
    static void permWithResult(String s, String prefix,List<String> result){
        //base case
        if (s.length() == 1){
            result.add(prefix+s);
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            String remaining = s.substring(i+1);
            remaining += s.substring(0,i);
            permWithResult(remaining,prefix+cur,result);

        }
    }

    //Building from All n-1 characters
    //Time Complexity = O(n! * n^2)
    static List<String> permsAll(String s){
        List<String> perms = new ArrayList<>();
        //base case
        if (s.length() == 1){
            perms.add(s);
            return perms;
        }

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            String remaining = s.substring(i+1);
             remaining += s.substring(0,i);
            List<String> result = permsAll(remaining);

            //prepend the current character
            for (String si: result){
                perms.add(cur+si);
            }

        }

        return perms;
    }

    //Building from the permutation of the first  n-1 characters
    //Time Complexity = O(n! * n^2)
    static List<String> perms(String s,int index){
        //base case
        if (index == 0){
            char cur = s.charAt(index);
            List<String> strings = new ArrayList<>();
            strings.add(String.valueOf(cur));
            return strings;
        }

        char currentItem = s.charAt(index);
        //recurse over the length of string
        List<String> perms = perms(s,index-1);

        List<String> newStrings = new ArrayList<>();

        for (int i = 0; i < perms.size(); i++) {
            String item = perms.get(i);
            for (int j = 0; j <= item.length(); j++) {
                StringBuilder sb = new StringBuilder();
                String left = item.substring(0,j);
                String right = item.substring(j);
                //append the current string at the back of the old string
                sb.append(left);
                sb.append(currentItem);
                sb.append(right);
                newStrings.add(sb.toString());
            }

        }

        return newStrings;
    }
}
