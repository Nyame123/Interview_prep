package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Print a given matrix in spiral form
 * Difficulty Level : Medium
 * Last Updated : 01 Feb, 2022
 * Given a 2D array, print it in spiral form. See the following examples.
 * <p>
 * Examples:
 * <p>
 * Input:  1    2   3   4
 * 5    6   7   8
 * 9   10  11  12
 * 13  14  15  16
 * Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 * Explanation: The output is matrix in spiral format.
 * <p>
 * Input:  1   2   3   4  5   6
 * 7   8   9  10  11  12
 * 13  14  15 16  17  18
 * Output: 1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11
 * Explanation :The output is matrix in spiral format.
 **/
public class ClockwiseSpiralMatrixPrint {

    public static void main(String[] args) {
        int a[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        System.out.println(spiralOrder(a));
    }

    static List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<Integer> ans = new ArrayList<>();

        if (n == 0) {
            return ans;
        }

        boolean[][] seen = new boolean[n][m];
        int total = n * m;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int row = 0, col = 0, di = 0;
        for (int i = 0; i < total; i++) {
            ans.add(matrix[row][col]);
            seen[row][col] = true;
            int cr = row + dy[di];
            int cc = col + dx[di];
            if (0 <= cr && cr < n && 0 <= cc && cc < m && !seen[cr][cc]) {
                row = cr;
                col = cc;
            } else {
                di = (di + 1) % 4;
                row += dy[di];
                col += dx[di];
            }
        }

        return ans;

    }


    static void spiralPrint(int m, int n, int a[][]) {
        int i, k = 0, l = 0;

        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */

        while (k < m && l < n) {
            // Print the first row from the remaining rows
            for (i = l; i < n; ++i) {
                System.out.print(a[k][i] + " ");
            }
            k++;

            // Print the last column from the remaining
            // columns
            for (i = k; i < m; ++i) {
                System.out.print(a[i][n - 1] + " ");
            }
            n--;

            // Print the last row from the remaining rows */
            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    System.out.print(a[m - 1][i] + " ");
                }
                m--;
            }

            // Print the first column from the remaining
            // columns */
            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }
    }
}
