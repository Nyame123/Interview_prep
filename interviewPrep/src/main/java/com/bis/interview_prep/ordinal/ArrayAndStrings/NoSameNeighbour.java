package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *Given a string, check if it can be modified such that no two adjacent
 *  characters are the same. If it is possible, return any string that satisfies this condition
 *  and if it is not possible return an empty string.
 *
 * Ex: Given the following string s…
 *
 * s = "abb", return "bab".
 * Ex: Given the following string s…
 *
 * s = "xxxy", return "" since it is not possible to modify s such that no two
 * adjacent characters are the same.
 **/
public class NoSameNeighbour {

    public static void main(String[] args) {
        String s = "bbbaa ";
        String res = rearrangement(s);
        System.out.println(res == null ? "No solution":res);
    }

    //Time Complexity = O(n)
    //Space Complexity = O(26) = O(1)
    //Using HashMap only
    static String rearrangement(String s) {
        s = s.trim();
        int n = s.length();

        int[] letterCount = new int[26];
        for (int i = 0; i < n; i++) {
            letterCount[s.charAt(i)-'a']++;
        }

        int max = 0;
        char maxLetter = 0;
        for (int i = 0; i < letterCount.length; i++) {
            if (max < letterCount[i]){
                max = letterCount[i];
                maxLetter = (char) (i + 'a');
            }
        }

        if (max > (n+1)/2){
            return null;
        }
        char[] res = new char[n];
        int index = 0;
        while (letterCount[maxLetter-'a'] > 0){
            res[index] = maxLetter;
            letterCount[maxLetter-'a']--;
            index += 2;
        }

        int i = 0;
        while (i < letterCount.length){
            while (letterCount[i] > 0) {
                if (index >= n) {
                    index = 1;
                }

                res[index] = (char) (i + 'a');
                letterCount[i]--;
                index += 2;
            }

            i++;
        }

        return String.valueOf(res);
    }

    //Time Complexity = O(nlogn)
    //Space Complexity = O(26) = O(1)
    //Using Priority Queue and hashmap
    static String rearrangementUsingHash(String s) {
        //using priority queue for storing all the characters
        // so that no same neighbours are following each other
        s = s.trim();
        int n = s.length();
        char[] letterCount = new char[26];
        for (int i = 0; i < n; i++) {
            letterCount[s.charAt(i)-'a']++;
        }

        //add the letters to the priority queue
        PriorityQueue<Key> priorityQueue = new PriorityQueue<>(new Key());
        for (int i = 0; i < letterCount.length; i++) {

            if (letterCount[i] > 0){
                char c = (char) (i+'a');
                priorityQueue.add(new Key(letterCount[i],c));
            }
        }

        //build the resultant string
        StringBuilder stringBuilder = new StringBuilder();
        Key prev = new Key(-1,'#'); //default initializer
        while (!priorityQueue.isEmpty()){
            Key cur = priorityQueue.poll();
            stringBuilder.append(cur.letter);

            //reduce the frequency by 1;
            cur.freq--;

            //add the prev character to priority queue if it is okay
            if (prev.freq > 0){
                priorityQueue.add(prev);
            }

            prev = cur;
        }

        if (stringBuilder.length() == n){
            return stringBuilder.toString();
        }

        return null;
    }

    static class Key implements Comparator<Key>{

        int freq;
        char letter;

        public Key() {
        }

        public Key(int freq, char letter) {
            this.freq = freq;
            this.letter = letter;
        }

        @Override
        public int compare(Key key, Key t1) {
            int res = Integer.compare(t1.freq,key.freq);
            return res == 0? Integer.compare(key.letter,t1.letter) : res;
        }
    }
}
