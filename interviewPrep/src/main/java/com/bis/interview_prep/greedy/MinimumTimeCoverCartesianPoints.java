package com.bis.interview_prep.greedy;

/**
 * Given N points on a Cartesian plane, return the minimum time required to visit all points in the order that they’re given.
 * Note: You start at the first point and can move one unit vertically, horizontally, or diagonally in a single second.
 * <p>
 * Ex: Given the following points…
 * <p>
 * points = [[0, 0], [1,1], [2,2]], return 2.
 * In one second we can travel from [0, 0] to [1, 1]
 * In another second we can travel from [1, 1,] to [2, 2]
 * Ex: Given the following points…
 * <p>
 * points = [[0, 1], [2, 3], [4, 0]], return 5.
 **/
public class MinimumTimeCoverCartesianPoints {

    public static void main(String[] args) {

        Point[] arr = new Point[3];
        arr[0] = new Point(0, 1);
        arr[1] = new Point(2, 3);
        arr[2] = new Point(4, 0);

        int n = arr.length;
        System.out.print(coverPoints(arr, n));
    }

    static int calcShortestDistance(Point p1, Point p2){
        int dx = Math.abs(p1.x-p2.x);
        int dy = Math.abs(p1.y-p2.y);

        return Math.max(dx,dy);
    }
    static int coverPoints(Point[] points,int n){

        int stepCount = 0;
        for (int i = 1; i < n; i++) {
            stepCount += calcShortestDistance(points[i],points[i-1]);
        }
        return stepCount;
    }

    static class Point {
        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

}
