package com.bis.interview_prep.treeAndGraph.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D array each cells can have one of three values.
 * Zero represents an empty cell, one represents a healthy person,
 * and two represents a sick person. Each minute that passes,
 * any healthy person who is adjacent to a sick person becomes sick.
 * Return the total number of minutes that must elapse until every person is sick.
 * Note: If it is not possible for each person to become sick, return -1.
 * <p>
 * Ex: Given the following 2D array grid…
 * <p>
 * grid = [
 * [1,1,1],
 * [1,1,0],
 * [0,1,2]
 * ], return 4.
 * [2, 1] becomes sick at minute 1.
 * [1, 1] becomes sick at minute 2.
 * [1, 0] and [0, 1] become sick at minute 3.
 * [0, 0] and [0, 2] become sick at minute 4.
 * Ex: Given the following 2D array grid…
 * <p>
 * grid = [
 * [1,1,1],
 * [0,0,0],
 * [2,0,1]
 * ], return -1.
 **/
public class Infections {

    static int totalInfected = 0;

    public static void main(String[] args) {
        int[][] community = {
                new int[]{1, 1, 1},
                new int[]{1, 1, 0},
                new int[]{0, 1, 2},
        };

        int minutesTaken = minutesTaken(community);
        System.out.println(minutesTaken);
    }

    /**
     * This function count the number of healthy person in the
     * community. This takes time complexity of O(N^2)
     **/
    static int countHealthyPerson(int[][] community) {
        int n = community.length, m = community[0].length;
        int countHealty = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (community[i][j] == 1) {
                    countHealty++;
                }
            }
        }

        return countHealty;
    }

    static int minutesTaken(int[][] community) {
        int n = community.length;
        int m = community[0].length;

        int countHealthy = countHealthyPerson(community);
        //Right, Left, Top, Down
        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, -1, 1};
        int minutes = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //if it is a sick person
                if (community[i][j] == 2) {
                    minutes += bfs(community, i, j, dirX, dirY);
                }
            }
        }

        if (countHealthy == totalInfected) {
            return minutes;
        } else {
            return -1;
        }
    }

    static boolean isSafe(int[][] community, int r, int c) {
        return (r >= 0 && r < community.length && c >= 0 && c < community[0].length && community[r][c] == 1);
    }

    static int bfs(int[][] community, int r, int c, int[] dirX, int[] dirY) {
        int count = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            boolean infected = false;
            for (int i = 0; i < 4; i++) {
                int newR = cur[0] + dirX[i];
                int newC = cur[1] + dirY[i];
                if (isSafe(community, newR, newC)) {
                    totalInfected++;
                    infected = true;
                    //infected
                    community[newR][newC] = 2;
                    queue.add(new int[]{newR, newC});
                }
            }

            if (infected)
                count++;
        }

        return count;
    }
}
