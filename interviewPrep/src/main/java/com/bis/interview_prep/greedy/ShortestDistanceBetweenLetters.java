package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *This question is asked by Apple. Given a string and a character,
 * return an array of integers where each index is the shortest distance from the character.
 * Ex: Given the following string s and character s...
 *
 * s = "dailybyte", c = 'y', return [4, 3, 2, 1, 0, 1, 0, 1, 2]
 **/
public class ShortestDistanceBetweenLetters {
    public static void main(String[] args) {
        String S = "dailybyte";
        char X = 'y';
        minDistanceFromCharacter(S, X);
    }

    /**
     * In this problem, we can make two traversals.
     * That is left and right running over the array
     * and you update the minimum distance
     * <p>
     * We will have a prev pointer pointing to the last char as a mark
     * and we use this pointer to update the minimum distance.
     * <p>
     * Time Complexity = O(N)
     **/
    private static void shortestDistance(String s, char x) {
        char[] characters = s.toCharArray();
        int n = characters.length;
        int[] dist = new int[n];
        int prev = Integer.MAX_VALUE;


        //left to right traversal
        for (int i = 0; i < n; i++) {

            if (characters[i] == x) {
                prev = i;
            }
            if (prev == Integer.MAX_VALUE) {
                dist[i] = prev;
            } else {
                dist[i] = i - prev;
            }
        }

        //right to left traversal
        prev = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {

            if (characters[i] == x) {
                prev = i;
            }
            if (prev != Integer.MAX_VALUE) {
                dist[i] = Math.min(dist[i], prev - i);
            }
        }

        System.out.println(Arrays.toString(dist));
    }

    /**
     * We can also do this operation by listing out all the
     * points of the searched characters.
     * <p>
     * then from the positions of the searched character, you can
     * update the minimum distance.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static void minDistanceFromCharacter(String s, char x) {
        char[] characters = s.toCharArray();
        int n = characters.length;
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        List<Integer> searchedChar = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (characters[i] == x) {
                searchedChar.add(i);
            }
        }
        List<Integer> allChars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allChars.add(i);
        }

        for (int i = 0; i < searchedChar.size(); i++) {
            int p1 = 0, p2 = n-1;
            int p = searchedChar.get(i);
            if (i > 0) {
                p1 = searchedChar.get(i - 1);
            }

            if (i < searchedChar.size() - 1) {
                p2 = searchedChar.get(i + 1);
            }

            //right traversal
            int k = p;
            while (p <= p2) {
                int minDis = allChars.get(p) - k;
                dist[p] = Math.min(dist[p], minDis);
                p++;
            }

            //left traversal
            p = k;
            while (p >= p1) {
                int minDis = k - allChars.get(p);
                dist[p] = Math.min(dist[p], minDis);
                p--;
            }

        }

        System.out.println(Arrays.toString(dist));
    }
}
