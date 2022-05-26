package com.bis.interview_prep.geometry;
/**
 *You are given two rectangles as integer arrays and want to determine if they overlap.
 * Each rectangle will be given as two point, [x1, y1, x2, y2] where (x1, y1)
 * represents the bottom-left hand corner a rectangle and (x2, y2) represents
 * the top-right hand corner of a rectangle. Given two arrays that represent
 * rectangles, rect1 and rect2, return whether or not they overlap.
 * Note: Two rectangles overlap if the area of their intersection is positive.
 **/
public class RectanglesOverlap {

    public static void main(String[] args) {
        int[] rec1 = {0, 0, 1, 1};
        int[] rec2 = {1, 1, 4, 4};

        boolean res = isOverlapping(rec1,rec2);
        System.out.println(res);
    }

    /**
     * For rectanges not overlapping, it obeys the following rules
     * 1. one rectangle lies on the sides left,or right of the other rectangle.
     * 2. One rectangle lies on the top side or beneath of the other rectangle.
     *
     * Else true;
     **/
    private static boolean isOverlapping(int[] rec1, int[] rec2) {
        //single line
        if(rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]){
            return false;
        }

        if(rec1[0] >= rec2[2] || rec2[0] >= rec1[2]){
            return false;
        }

        if(rec1[1] >= rec2[3] || rec2[1] >= rec1[3]){
            return false;
        }

        return true;
    }
}
