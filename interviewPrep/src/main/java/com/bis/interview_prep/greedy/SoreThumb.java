package com.bis.interview_prep.greedy;
/**
 *Students in a class are lining up in ascending height order, but are having some trouble doing so.
 * Because of this, it’s possible that some students might be out of order. In particular, a student that
 * is taller than both their neighboring students (i.e. the person to both their left and right) sticks
 * out like a sore thumb. Given an integer array that represents each students height, return the index of a “sore thumb”.
 * Note: If there are multiple sore thumbs you may return the index of any of them. All numbers in the array
 * will be unique. You may assume that to the left and right bounds of the array negative infinity values exist.
 *
 * Ex: Given the following students...
 *
 * students = [1, 2, 3, 7, 5], return 3.
 **/
public class SoreThumb {

    public static void main(String[] args) {
        int[] students = {1, 2, 3,4, 7, 5};
        int stickOutPos = stickOutPosition(students);
        System.out.println(stickOutPos);
    }

    private static int stickOutPosition(int[] students) {
        int n = students.length;
        for (int i = 1; i < n; i++) {
            if (students[i] < students[i-1]){
                return i-1;
            }
        }
        return -1;
    }
}
