package com.bis.interview_prep.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.SplittableRandom;

/**
 * Write a method to sort an array of strings so that all the anagrams are next to
 * each other.
 **/
public class GroupAnagram {

    public static void main(String[] args) {

        String[] sequence = {"acer","tag","sat","gat","race","cat","god","atc","tac","dog"};
        //Arrays.sort(sequence,new GroupAnagramComparator());
        usingHashMap(sequence);
        for (String s : sequence) {
            System.out.print(s+", ");
        }

        List<List<String>> res = groupAnagram(sequence);
        for(List<String> s: res){
            System.out.println(s);
        }

    }

    /**
     * 1. we can use anagram comparison to check if two strings are the same
     * 2. We combine if they are anagram else we keep a separate List for that
     *
     * Time Complexity = O(N^2*S)
     **/
    static List<List<String>> groupAnagram(String[] words){
        List<List<String>> ans = new ArrayList<>();

        next:
        for(String word: words){

            for(List<String> res: ans){
                if (isAnagram(res.get(0),word)){
                    res.add(word);
                    continue next;
                }
            }

            List<String> anagram = new ArrayList<>();
            anagram.add(word);
            ans.add(anagram);

        }

        return ans;
    }

    static boolean isAnagram(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2){
            return false;
        }
        int[] firstSet = new int[26];
        int[] secondSet = new int[26];
        for (int i = 0; i < len1; i++) {
            firstSet[s1.charAt(i)-'a']++;
            secondSet[s2.charAt(i)-'a']++;
        }

        return Arrays.equals(firstSet,secondSet);
    }

    //using hashMap
    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static void usingHashMap(String[] sequence){
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String s : sequence) {
            String sorted = sortUtil(s);
            List<String> strings = hashMap.getOrDefault(sorted,new ArrayList<>());
            strings.add(s);
            hashMap.put(sorted,strings);
        }

        int k = 0;
        for (String key: hashMap.keySet()){
            for (int i = 0; i < hashMap.get(key).size(); i++) {
                sequence[k++] = hashMap.get(key).get(i);
            }
        }

    }


    static class GroupAnagramComparator implements Comparator<String> {

        public String sortChars(String s) {
             char[] content = s.toCharArray();
             Arrays.sort(content);
             return new String(content);
        }
        @Override
        public int compare(String s, String t1) {
            return sortUtil(s).compareTo(sortUtil(t1));
        }
    }

    //Time Complexity = O(nlogn)
    static String sortUtil(String s){
        char[] chArray = s.toCharArray();
        mergeSort(chArray,0,s.length()-1);
      return String.valueOf(chArray);
    }

    //Merge Sort Util
    static void mergeSort(char[] s, int low, int high){
        if (low < high){

            int mid = low + (high - low) / 2;
            mergeSort(s,low,mid);
            mergeSort(s,mid+1,high);
            mergeArr(s,low,mid,high);
        }
    }

    private static void mergeArr(char[] s, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        char[] left = new char[n1];
        char[] right = new char[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = s[low+i];
        }

        for (int i = 0; i < n2; i++) {
            right[i] = s[mid+1+i];
        }

        int i = 0,j = 0;
        int k = low;
        while (i < n1 && j < n2){
            if (left[i] > right[j]){
                s[k++] = right[j++];
            }else{
                s[k++] = left[i++];
            }
        }

        while (i < n1){
            s[k++] = left[i++];
        }

        while (j < n2){
            s[k++] = right[j++];
        }
    }



    //Quick Sort Util
    static void quickSort(char[] s,int low, int high){
        if (low < high){
            //get the partition point
            int partition = partition(s,low,high);
            quickSort(s,low,partition-1);
            quickSort(s,partition+1,high);
        }
    }

    private static int partition(char[] s,int low, int high) {
        int pivot = s[high];
        int previousPivot = low-1;
        for (int i = low; i < high; i++) {

            if (s[i] < pivot){
                previousPivot++;
                swap(s,previousPivot,i);
            }
        }

        swap(s,previousPivot+1,high);
        return previousPivot+1;
    }

    static void swap(char[] s, int prev, int cur){
        char temp = s[prev];
        s[prev] = s[cur];
        s[cur] = temp;
    }
}

