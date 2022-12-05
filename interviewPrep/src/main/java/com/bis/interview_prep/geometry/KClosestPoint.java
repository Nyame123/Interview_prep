package com.bis.interview_prep.geometry;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * Given a list of points, return the k closest points to the origin (0, 0).
 * <p>
 * Ex: Given the following points and value of k…
 * <p>
 * points = [[1,1],[-2,-2]], k = 1, return [[1, 1,]].
 **/
public class KClosestPoint {

    public static void main(String[] args) {
        int[][] arr = {
                {3, 3},
                {5, -1},
                {-2, 4}
        };
        int K = 2;

        closestPoints(arr, K);
    }

    static void closestPoints(int[][] pts, int k) {
        int n = pts.length;
        int[] distance = new int[n];
        for (int i = 0; i < n; i++) {
            int x = pts[i][0], y = pts[i][1];
            distance[i] = x * x + y * y;
        }

        Arrays.sort(distance);

        int closest = distance[k - 1];

        for (int i = 0; i < n; i++) {
            int x = pts[i][0], y = pts[i][1];
            int dis = x * x + y * y;
            if (dis <= closest) {
                System.out.println(x + "," + y);
            }
        }
    }

    static void closestUsingPriorityQueue(int[][] pts, int k) {
        PriorityQueue<Points> pairPriorityQueue = new PriorityQueue<>();
        int n = pts.length;
        for (int i = 0; i < n; i++) {
            pairPriorityQueue.add(new Points(pts[i][0], pts[i][1]));
        }

        for (int i = 0; i < k; i++) {
            Points point = pairPriorityQueue.poll();
            int x = point.x, y = point.y;
            System.out.println(x + "," + y);
        }
    }


    static class Points implements Comparable<Points> {

        int x, y;

        Points(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Points points) {
            int dis1 = x * x + y * y;
            int dis2 = points.x * points.x + points.y * points.y;
            return Integer.compare(dis1, dis2);
        }
    }


}
