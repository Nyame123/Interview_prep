package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * You are in an infinite 2D grid where you can move in any of the 8 directions:
 * <p>
 * (x,y) to
 * (x+1, y),
 * (x - 1, y),
 * (x, y+1),
 * (x, y-1),
 * (x-1, y-1),
 * (x+1,y+1),
 * (x-1,y+1),
 * (x+1,y-1)
 * You are given a sequence of points and the order in which you need to cover the points.
 * Give the minimum number of steps in which you can achieve it. You start from the first point.
 * <p>
 * Example:
 * <p>
 * Input: [(0, 0), (1, 1), (1, 2)]
 * Output: 2
 * It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
 **/
public class MinimumStepToCoverInfiniteGrid {

    public static void main(String[] args) {
        Point arr[] = new Point[4];
        arr[0] = new Point(4, 6);
        arr[1] = new Point(1, 2);
        arr[2] = new Point(4, 5);
        arr[3] = new Point(10, 12);

        System.out.print(coverPoints(arr));
    }

    /**
     * This calculate the distance between two points
     **/
    static int shortestDistance(Point p1,Point p2){
        int x = Math.abs(p1.x-p2.x);
        int y = Math.abs(p1.y-p2.y);

        return Math.max(x,y);
    }

    /**
     * This calculate the minimum distance needed to cover the points in the
     * grid.
     * Time Complexity = O(N)
     **/
    private static int coverPoints(Point[] arr) {
        int n = arr.length;
        int minDistance = 0;
        for (int i = 0; i < n-1; i++) {
            minDistance += shortestDistance(arr[i],arr[i+1]);
        }
        return minDistance;
    }

    static class Point {
        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;

        }
    }
}
