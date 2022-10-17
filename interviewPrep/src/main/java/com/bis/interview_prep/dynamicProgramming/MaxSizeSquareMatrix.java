package com.bis.interview_prep.dynamicProgramming;

/**
 * Given a binary matrix, find out the maximum size square sub-matrix with all 1s.
 * For the given M[R][C] in the above example, constructed S[R][C] would be:
 * <p>
 * 0  1  1  0  1
 * 1  1  0  1  0
 * 0  1  1  1  0
 * 1  1  2  2  0
 * 1  2  2  3  1
 * 0  0  0  0  0
 * The value of the maximum entry in the above matrix is 3 and the coordinates of the entry are (4, 3).
 * Using the maximum value and its coordinates, we can find out the required sub-matrix.
 * <p>
 * https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
 **/
public class MaxSizeSquareMatrix {

    public static void main(String[] args) {
        int[][] mat = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        maxSizeSquared(mat);
        int maxSize = maxSizeSquareOptimizedSpace(mat);
        System.out.println(maxSize);
    }

    //longest max size of a square
    static void maxSizeSquared(int[][] mat){
        System.out.println();
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = mat[i][0];
        }

        for (int i = 0; i < m; i++) {
            dp[0][i] = mat[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 1){
                    dp[i][j] = Math.min(dp[i][j-1],Math.min(dp[i-1][j],dp[i-1][j-1])) + 1;
                }else{
                    dp[i][j] = 0;
                }
            }
        }

        int maxI = 0, maxJ = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] > max){
                    max = Math.max(max,dp[i][j]);
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        System.out.println(max);

        for (int i = maxI-max+1; i <= maxI; i++) {
            for (int j = maxJ-max+1; j <= max; j++) {

                System.out.print(mat[i][j]+",");
            }
            System.out.println();
        }

        System.out.println();
    }























    /**
     * Finding the maximum size of a matrix with all ones can be solved
     * by using dynamic programming.
     * 1. we have memory space, of size (m*n)
     * 2. if the entry is one, then we pick min(mat[r][j-1],mat[r-1][j-1],mat[r-1][j])
     * 3. Else the entry is zero.
     * <p>
     * Time Complexity = O(M*N)
     * Space Complexity = O(M*N)
     **/
    static int maxSizeSquare(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[n][m];

        //fill the first column and row entry
        for (int i = 0; i < n; i++) {
            dp[i][0] = mat[i][0];
        }

        for (int i = 0; i < m; i++) {
            dp[0][i] = mat[0][i];
        }

        int maxSize = 0;
        int maxI = 0, maxJ = 0;
        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    if (maxSize < dp[i][j]) {
                        maxI = i;
                        maxJ = j;
                        maxSize = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        //print the result
        for (int i = maxI - maxSize + 1; i <= maxI; i++) {

            for (int j = maxJ - maxSize + 1; j <= maxJ; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        return maxSize;
    }

    /**
     * We can optimize the space by using less space
     * An improved space version of the above solution
     **/
    static int maxSizeSquareOptimizedSpace(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[2][m];

        int maxSize = 0;
        int maxI = 0, maxJ = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                int temp = mat[i][j];
                if (temp == 1) {
                    if (j != 0) {
                        temp = Math.min(dp[0][j - 1], Math.min(dp[1][j - 1], dp[1][j])) + 1;
                        if (maxSize < temp) {
                            maxI = i;
                            maxJ = j;
                            maxSize = temp;
                        }
                    }
                }

                dp[0][j] = dp[1][j];
                dp[1][j] = temp;

            }
        }

        //print the result
        for (int i = maxI - maxSize + 1; i <= maxI; i++) {

            for (int j = maxJ - maxSize + 1; j <= maxJ; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        return maxSize;
    }
}
