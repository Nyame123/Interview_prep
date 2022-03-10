package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

/**
 *High school students are voting for their class president and you’re tasked with counting the votes.
 * Each presidential candidates is represented by a unique integer and the candidate that should win
 * the election is the candidate that has received more than half the votes. Given a list of integers,
 * return the candidate that should become the class president.
 * Note: You may assume there is always a candidate that has received more than half the votes.
 *
 * Ex: Given the following votes…
 *
 * votes = [1, 1, 2, 2, 1], return 1.
 * Ex: Given the following votes…
 *
 * votes = [1, 3, 2, 3, 1, 2, 3, 3, 3], return 3.
 **/
public class PresidentialClass {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 3, 1, 2, 3, 3, 3};
        System.out.println(presidentToWin(arr));
    }

    /**
     * We can use count of frequency for this
     * We store in hashmap and count the votes of each unique integer
     *
     * We can iterate through the keys to know highest frequency
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/

    static int presidentToWin(int[] arr){
        int maxVotes = 0;
        int president = -1;
        HashMap<Integer,Integer> votes = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            votes.putIfAbsent(arr[i],0);
            votes.put(arr[i],votes.get(arr[i])+1);
            if (maxVotes < votes.get(arr[i])){
                maxVotes = votes.get(arr[i]);
                president = arr[i];
            }
        }

        return president;
    }
}
