package com.bis.interview_prep.recursion.backtracking;

/**
 * A knight's tour is a sequence of moves by a knight on a chessboard such that all squares are visited once.
 * <p>
 * Given N, write a function to return the number of knight's tours on an N by N chessboard.
 * <p>
 * Input :
 * N = 8
 * Output:
 * 0  59  38  33  30  17   8  63
 * 37  34  31  60   9  62  29  16
 * 58   1  36  39  32  27  18   7
 * 35  48  41  26  61  10  15  28
 * 42  57   2  49  40  23   6  19
 * 47  50  45  54  25  20  11  14
 * 56  43  52   3  22  13  24   5
 * 51  46  55  44  53   4  21  12
 **/
public class KnightTour {
    static int N = 8;

    public static void main(String[] args) {

        int sol[][] = new int[8][8];

        /* Initialization of solution matrix */
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;

        solveKightTour(sol);

    }

    static void solveKightTour(int[][] sol) {
        /* xMove[] and yMove[] define next move of Knight.
           xMove[] is for next value of x coordinate
           yMove[] is for next value of y coordinate */
        int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

        // Since the Knight is initially at the first block
        sol[0][0] = 0;

        /* Start from 0,0 and explore all tours using
           solveKTUtil() */
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
        } else
            printSolution(sol);
    }

    static boolean solveKTUtil(int i, int j, int imoves, int[][] sol, int[] xMove, int[] yMove) {

        //base case
        if (imoves == N * N) {
            return true;
        }

        for (int k = 0; k < 8; k++) {
            int row = i + xMove[k];
            int col = j + yMove[k];
            if (isSafe(sol, row, col)) {
                sol[row][col] = imoves;
                if (solveKTUtil(row, col, imoves + 1, sol, xMove, yMove)) {
                    return true;
                }

                sol[row][col] = -1;
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] sol, int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N || sol[row][col] != -1) {
            return false;
        }

        return true;
    }

    static void printSolution(int[][] sol) {

        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[0].length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }
}
