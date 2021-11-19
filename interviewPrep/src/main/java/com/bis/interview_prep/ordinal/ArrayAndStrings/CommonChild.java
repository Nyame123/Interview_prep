package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A string is said to be a child of a another string if it can
 * be formed by deleting 0 or more characters from the other string.
 * Letters cannot be rearranged. Given two strings of equal length,
 * what's the longest string that can be constructed such that it is a child of both?
 * <p>
 * Example
 * These strings have two children with maximum length 3, ABC and ABD.
 * They can be formed by eliminating either the D or C from both strings. Return .
 **/
public class CommonChild {

    public static void main(String[] args) {
        String s1 = "SHINCHAN";
        String s2 = "NOHARAAA";
        int count = maxCommonChildLen(s1, s2);
        System.out.println(count);
    }

    private static int maxCommonChildLen(String s1, String s2) {

        //convert them into the same case;
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        HashMap<Character, Integer> map = new HashMap<>();
        //build the hashtable for the second string
        for (int i = 0; i < s2.length(); i++) {
            Character key = s2.charAt(i);
           /* map.putIfAbsent(key, new ArrayList<>());
            List<Integer> indices = map.get(key);
            indices.add(i);*/
            map.put(key,i);
        }

        int maxCount = 0;
        List<ResultCon> resultCons = new ArrayList<>();
        //iterate over the first string to compare
        for (int i = 0; i < s1.length(); i++) {
            Character key = s1.charAt(i);
            if (map.containsKey(key)){

               /* //get the appropriate index
                List<Integer> indices = map.get(key);
                for (int j = 0; j < indices.size(); j++) {
                    if ()
                }*/
                int nextIndex = map.get(key);
                //go over the results
                for (int j = 0; j < resultCons.size(); j++) {

                    ResultCon lastResultCon = resultCons.get(j);
                    //check if the next character is in front of this character
                    if (lastResultCon.lastIndex < nextIndex){
                        lastResultCon.data.append(key);
                        lastResultCon.lastIndex = nextIndex;
                        lastResultCon.count++;
                        maxCount = Math.max(maxCount, lastResultCon.count);
                    }
                }

                ResultCon resultCon = new ResultCon();
                resultCon.data.append(key);
                resultCon.lastIndex = nextIndex;
                resultCon.count++;
                resultCons.add(resultCon);
                maxCount = Math.max(maxCount, resultCon.count);
            }
        }

        return maxCount;
    }

    static class ResultCon {
        int lastIndex;
        StringBuilder data = new StringBuilder();
        int count;
    }
}
