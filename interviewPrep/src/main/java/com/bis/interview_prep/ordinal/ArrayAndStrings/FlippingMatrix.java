package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class FlippingMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
               new int[]{112,42,83,119},
                new int[]{56,125,56,49},
                new int[]{15,78,101,43},
                new int[]{62,98,114,108}
        };

        int maxSum = flippingMatrix(matrix);
        System.out.println(maxSum);
    }

    private static int flippingMatrix(int[][] matrix) {

        int n = matrix.length/2;
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int c1 = matrix[i][j];
                int c2 = matrix[i][2*n-1-j];
                int c3 = matrix[2*n-1-i][j];
                int c4 = matrix[2*n-1-i][2*n-1-j];
                totalSum += Math.max(c1,Math.max(c2,Math.max(c3,c4)));
            }
        }
        return totalSum;
    }
}
