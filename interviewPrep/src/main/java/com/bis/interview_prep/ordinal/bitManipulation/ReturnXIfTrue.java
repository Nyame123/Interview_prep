package com.bis.interview_prep.ordinal.bitManipulation;

public class ReturnXIfTrue {

    public static void main(String[] args) {
        int x = 3, y = 4, b = 0;
        int res = isX(x,y,b);
        System.out.println(res);
    }

    static int isX(int x, int y,int b){
        b = -b; // turn into full bit mask, either all 1 or all 0
        return ((x & b) | (y & ~b));
    }
}