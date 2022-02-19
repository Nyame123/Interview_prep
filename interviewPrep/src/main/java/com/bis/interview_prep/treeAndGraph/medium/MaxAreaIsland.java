package com.bis.interview_prep.treeAndGraph.medium;

/**
 * You are given two-dimensional matrix that represents a plot of land. Within the matrix there exist two values:
 * ones which represent land and zeroes which represent water within a pond. Given that parts of a pond can be
 * connected both horizontally and vertically (but not diagonally), return the largest pond size.
 * Note: You may assume that each zero within a given pond contributes a value of one to the total size of the pond.
 *
 * Ex: Given the following plot of land…
 *
 * land = [
 *     [1,1,1],
 *     [1,0,1],
 *     [1,1,1]
 * ], return 1.
 * Ex: Given the following plot of land…
 *
 * land = [
 *     [1,0,1],
 *     [0,0,0],
 *     [1,0,1]
 * ], return 5.
 **/
public class MaxAreaIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {1,0,1},
                {0,0,0},
                {1,0,1}
        };

        int res = maxAreaOfIsland(grid);
        System.out.println(res);

    }

    static int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        //right left top bottom
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int max = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    max = Math.max(max,  dfs(grid, dx, dy, i, j));
                }
            }
        }

        return max;
    }

    static int dfs(int[][] grid, int[] dx, int[] dy, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 0) {
            return 0;
        }


        int count = 0;
        grid[row][col] = -1;
        for (int i = 0; i < 4; i++) {
            count += dfs(grid, dx, dy, row + dy[i], col + dx[i]);

        }

        return ++count;
    }
}
