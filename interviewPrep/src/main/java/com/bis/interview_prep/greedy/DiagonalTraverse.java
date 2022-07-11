package com.bis.interview_prep.greedy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 *
 * Example 2:
 *
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 * https://assets.leetcode.com/uploads/2021/04/10/diag1-grid.jpg
 **/
public class DiagonalTraverse {

    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12},
            {13,14,15}
        };

        int[] results = diagonalTraverse(matrix);
        System.out.println(Arrays.toString(results));

    }

    /**
     * 1. Go through the rows of the matrix
     * 2. Gather all the elements in the diagonals
     * 3. Arrange the diagonals in the array results
     *
     * Time Complexity = O(N*M)
     * Space Complexity = O(N*M)
     **/
    static int[] diagonalTraverse(int[][] matrix) {
        List<List<Integer>> diagonals = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;

        /**
         *  {1,2,3},
         *  {4,5,6}, // 1,0, 0,1
         *  {7,8,9}  //2,0 ; 1,1 ; 0,2
         **/
        for (int i = 0; i < n; i++) {
            List<Integer> diagonal = new ArrayList<>();
            int col = 0;
            int row = i;
            //row--, col++
            while (col < m && col >= 0 && row < n && row >= 0){
                diagonal.add(matrix[row][col]);
                row--;
                col++;
            }

            diagonals.add(diagonal);
        }

        //rest of the last columns

        for (int j = 1; j < m; j++) {
            int col = j;
            int row = n-1;
            List<Integer> diagonal = new ArrayList<>();
            while (col < m && col >= 0 && row < n && row >= 0){
                diagonal.add(matrix[row][col]);
                row--;
                col++;
            }
            diagonals.add(diagonal);
        }

        int[] results = new int[n*m];
        int index = 0;
        for (int i = 0; i < diagonals.size(); i++) {
            if (i % 2 == 0){ //if even position, read from left
                List<Integer> diagonal = diagonals.get(i);
                int j = 0;
                while (j < diagonal.size()){
                    results[index++] = diagonal.get(j++);
                }
            }else{ // odd,read from right
                List<Integer> diagonal = diagonals.get(i);
                int j = diagonal.size()-1;
                while (j >= 0){
                    results[index++] = diagonal.get(j--);
                }
            }
        }

        return results;
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int row = 0;
        int col = 0;
        int n = mat.length;
        int m = mat[0].length;
        int[] res = new int[n * m];

        for (int i = 0; i < n * m; i++) {
            res[i] = mat[row][col];
            if ((row + col) % 2 == 0) {
                if (col == m - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == n - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }

        return res;
    }
}
