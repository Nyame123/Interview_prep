package com.bis.interview_prep.ordinal.easy;

import java.util.Arrays;

/**
 * Given a 2D matrix nums, return the matrix transposed.
 * Note: The transpose of a matrix is an operation that flips each value in the matrix across its main diagonal.
 *
 * Ex: Given the following matrix nums…
 *
 * nums = [
 *   [1, 2],
 *   [3, 4]
 * ]
 * return a matrix that looks as follows...
 * [
 *   [1,3],
 *   [2,4]
 * ]
 *
 **/
public class TransposeMatrix {

    public static void main(String[] args) {

        int[][] matrix = {
                new int[]{1,3},
                new int[]{2,4}
        };
        for(int[] arr: transposeSquaredMatrix(matrix)){
            System.out.println(Arrays.toString(arr));
        }

    }

    static int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] mat = new int[m][n];
        for(int i = 0; i < n; i++){

            for(int j = 0; j < m; j++){
                mat[j][i] = matrix[i][j];
            }
        }

        return mat;
    }

    static int[][] transposeSquaredMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < m; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

        return matrix;
    }
}
