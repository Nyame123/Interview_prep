package com.bis.interview_prep.greedy;

import java.util.Arrays;
import java.util.HashSet;

/**
 *  You are given two integer arrays top and bottom that represent the top and bottom halves of dominoes respectively.
 *  Return the minimum number of rotations (i.e. the top value becomes the bottom value of the domino
 *  and the bottom value becomes the top value of the domino) you need to perform to make all the
 *  values in top the same or all the values in bottom the same.
 *  Note: If it is not possible to make all the values in top or bottom the same, return -1.
 *
 * Ex: Given the following top and bottom...
 *
 * top = [2,1,2,4,2,2], bottom = [5,2,6,2,3,2], return 2.
 * We can rotate the second and fourth dominoes to make all values in the top row of our dominoes equal.
 **/
public class MinimumRotationDominoes {

    public static void main(String[] args) {
        int[] top = {2,1,2,4,2,2}, bottom = {5,2,6,2,3,2};
        int minRotation = minRotateUsingHash(top,bottom);
        System.out.println(minRotation);
    }


    /**
     * 1. Generate a count of elements in the top and bottom dominoes that are different
     * 2. Generate a count of elements that are the same in the same position
     * 3. if the countA[i] + countB[i] + same[i] == n, then it is the answer and pick the minimum number;
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static int minimumDominoestoRotate(int[] tops, int[] bottoms) {
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];
        int n = tops.length;
        for(int i = 0; i < n; i++){


            if(tops[i] == bottoms[i]){
                same[tops[i]]++;
            }else{
                countA[tops[i]]++;
                countB[bottoms[i]]++;
            }
        }


        for(int i = 1; i < 7; i++){
            if(countA[i] + countB[i] + same[i] == n){
                System.out.println(countA[i]+"="+countB[i]);
                return Math.min(countA[i],countB[i]);
            }
        }

        return -1;
    }


    private static int minimumDominoestoRotate1(int[] tops, int[] bottoms) {
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];
        int n = tops.length;
        for(int i = 0; i < n; i++){

            countA[tops[i]]++;
            countB[bottoms[i]]++;
            if(tops[i] == bottoms[i]){
                same[tops[i]]++;
            }
        }


        for(int i = 1; i < 7; i++){
            if(countA[i] + countB[i] - same[i] == n){
                System.out.println(countA[i]+"="+countB[i]);
                return n - Math.max(countA[i],countB[i]);
            }
        }

        return -1;
    }

    /**
     * 1. We can try to compare top[0] and top[i] and bottom[i] and top[0]
     * 2. We can try to compare bottom[0] and top[i] and bottom[i] and bottom[0]
     * 3. if (tops[0] != tops[i]), we move A pointer forward
     * 4. if (tops[0] != bottoms[i]), we move B pointer forward
     * 5. if this occurs up to the nth time, we pick the min of A and B.
     *
     * Time Complexity = O(N)
     **/

    static int minRotate(int[] tops,int[] bottoms){
        int n = tops.length;
        for (int i = 0,a = 0, b = 0; i < n && (tops[0] == tops[i] || tops[0] == bottoms[i]); i++) {
            if (tops[0] != tops[i]) a++;
            if (tops[0] != bottoms[i]) b++;

            if (i == n-1)
                return Math.min(a,b);
        }

        for (int i = 0,a = 0, b = 0; i < n && (bottoms[0] == tops[i] || bottoms[0] == bottoms[i]); i++) {
            if (bottoms[0] != tops[i]) a++;
            if (bottoms[0] != bottoms[i]) b++;

            if (i == n-1)
                return Math.min(a,b);
        }

        return -1;
    }

    /**
     * 1. solution 3 is using intersections
     **/
    static int minRotateUsingHash(int[] tops, int[] bottoms){
        HashSet<Integer> sets = new HashSet<>(Arrays.asList(1,2,3,4,5,6));
        int[] countA = new int[7];
        int[] countB = new int[7];
        int n = tops.length;
        for (int i = 0; i < n; i++) {
            sets.retainAll(new HashSet<>(Arrays.asList(tops[i],bottoms[i])));
            countA[tops[i]]++;
            countB[bottoms[i]]++;
        }

        for(int s: sets){
            return n - Math.max(countA[s],countB[s]);
        }

        return -1;
    }
}
