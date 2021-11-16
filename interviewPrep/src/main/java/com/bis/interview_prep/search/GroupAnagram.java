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

