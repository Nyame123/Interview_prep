package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 **/
public class findAnagramsInWord {
    public static void main(String[] args) {

        String s = "abacbabc", p = "abc";
        List<Integer> res = findAnagrams(s,p);
        System.out.println(res);
    }

    /**
     * 1. To find the pattern of anagrams in the original word s, we have
     * to build a hash of the pattern.
     * 2. We use a sliding window of size len(p) to find in the original word if there is any
     * anagrams of p in s. We return the list of all indices marking the start of the anagram of p in s.
     *
     * 3. When we build a window, sliding the window add back the start character since it is out of the window.
     * 4. Adding the next character into the window reduces the count of the character if present in the window.
     * 5. When the patternMap is empty, we found a solution.
     * 6. When a character at the end pointer does not form a part of the pattern, forward the start pointer to the end pointer again
     * 7. When the character at the end pointer is more than the end character count in the pattern word, forward the start pointer to the end pointer again
     *  and start sliding window from the end character.
     *
     * Time Complexity = O(N)
     **/
    static List<Integer> findAnagram(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int len = p.length();
        char[] sChars = s.toCharArray();

        if(len > sChars.length){
            return ans;
        }
        HashMap<Character,Integer> patternMap = new HashMap<>();
        //HashMap<Character,Integer> patternCheck = new HashMap<>();
        HashSet<Character> hash = new HashSet<>();
        for(char c: p.toCharArray()){
            hash.add(c);
            patternMap.put(c,patternMap.getOrDefault(c,0)+1);
        }



        for (int end = 0,start = 0; end < sChars.length;) {
            if (patternMap.isEmpty()){
                ans.add(start);
            }
            char cStart = sChars[start];
            char cEnd = sChars[end];

            //fast forward the start pointer to end pointer to start again
            if (!hash.contains(cEnd)){
                while (start < end){
                    cStart = sChars[start];
                    memorizeMap(hash,patternMap,cStart,1);
                    start++;
                }

                //skip the end character since it is not part of the pattern word
                start++;
                end = start;
                continue;
            }


            //when the count of end character is more than necessary in the window
            if (!patternMap.containsKey(cEnd) && hash.contains(cEnd)){
                memorizeMap(hash, patternMap, cStart, 1);
                start++;
                continue;
            }

            //slide the window
            if (end-start >= len) {
                memorizeMap(hash, patternMap, cStart, 1);
                start++;
            }

            memorizeMap(hash,patternMap,cEnd,-1);
            end++;
        }

        if (patternMap.isEmpty()){
            ans.add(sChars.length-len);
        }

        return ans;
    }

    static void memorizeMap(HashSet<Character> set,HashMap<Character,Integer> map,char key,int value){
        if (set.contains(key) || map.containsKey(key)) {
            int newValue = map.getOrDefault(key,0) + value;

            if (newValue == 0) {
                map.remove(key);
            } else {
                map.put(key, newValue);
            }
        }
    }

    static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int ns = s.length();
        int np = p.length();
        if(np > ns) return res;

        int[] target = new int[26];
        int[] cur = new int[26];
        int i = 0;
        for(; i < np; i++){
            target[p.charAt(i) - 'a']++;
            cur[s.charAt(i) - 'a']++;
        }

        while(i <= ns){
            if(Arrays.equals(target, cur)){
                res.add(i - np);
            }
            int left = i - np;
            cur[s.charAt(left) - 'a']--;

            if(i < ns){
                cur[s.charAt(i) - 'a']++;
            }
            i++;
        }
        return res;

    }
}
