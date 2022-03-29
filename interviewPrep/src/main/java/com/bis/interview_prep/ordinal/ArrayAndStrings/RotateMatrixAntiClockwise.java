package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.Arrays;

/**
 *Given a square matrix, turn it by 90 degrees in anti-clockwise direction without using any extra space.
 * Examples :
 *
 *
 * Input:
 * Matrix:
 *  1  2  3
 *  4  5  6
 *  7  8  9
 * Output:
 *  3  6  9
 *  2  5  8
 *  1  4  7
 * The given matrix is rotated by 90 degree
 * in anti-clockwise direction.
 *
 * Input:
 *  1  2  3  4
 *  5  6  7  8
 *  9 10 11 12
 * 13 14 15 16
 * Output:
 *  4  8 12 16
 *  3  7 11 15
 *  2  6 10 14
 *  1  5  9 13
 * The given matrix is rotated by 90 degree
 * in anti-clockwise direction.
 *
 **/
public class RotateMatrixAntiClockwise {

    public static void main(String[] args) {
        // Test Case 1
        int mat[][] = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        };

        int[][] dup = rotateAntiInPlaceRevTranspose(mat);
        for(int[] d: dup){
            System.out.println(Arrays.toString(d));
        }
    }


    /**
     * This can be done by using auxiliary matrix
     *
     * Time Complexity = O(N^2)
     * Space Complexity = O(N^2)
     **/
    static int[][] rotateAnti(int[][] mat){
        int n = mat.length;
        int[][] dup = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dup[i][j] = mat[j][n-1-i];
            }
        }

        return dup;
    }

    /**
     * Without using extra space, we can optimize the
     * time for doing this operation.
     * We do swapping of elements anti-clockwise directions
     * temp = top;
     * top = right
     * right = bottom
     * bottom = left
     *
     * Time Complexity = O(N^2)
     * Space Complexity = (1)
     *
     **/
    static int[][] rotateAntiInPlace(int[][] mat){
        int n = mat.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n-1-i; j++) {
                int temp = mat[i][j];
                //top = right;
                mat[i][j] = mat[j][n-1-i];
                //right = bottom
                mat[j][n-1-i] = mat[n-1-i][n-1-j];
                //bottom = left
                mat[n-1-i][n-1-j] = mat[n-1-j][i];
                mat[n-1-j][i] = temp;
            }
        }

        return mat;
    }

    /**
     * Another solution is to use reflect and transpose
     * 1. Reflect the matrix on the middle point or reverse the matrix row after row
     * 2. Transpose the matrix
     *
     **/
    static int[][] rotateAntiInPlaceRevTranspose(int[][] mat){
        int n = mat.length;
        reflect(mat, n);
        transpose(mat, n);

        return mat;
    }

    private static void reflect(int[][] mat, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n /2; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[i][n -1-j];
                mat[i][n -1-j] = temp;
            }
        }
    }

    private static void transpose(int[][] mat, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

}
