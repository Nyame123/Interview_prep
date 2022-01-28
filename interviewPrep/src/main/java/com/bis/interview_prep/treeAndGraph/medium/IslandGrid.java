package com.bis.interview_prep.treeAndGraph.medium;
/**
 * Find the number of islands | Set 1 (Using DFS)
 * Difficulty Level : Medium
 * Last Updated : 17 Jan, 2022
 *
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island.
 * For example, the below matrix contains 5 islands
 * Example:
 * Input : mat[][] = {{1, 1, 0, 0, 0},
 *                    {0, 1, 0, 0, 1},
 *                    {1, 0, 0, 1, 1},
 *                    {0, 0, 0, 0, 0},
 *                    {1, 0, 1, 0, 1}}
 * Output : 5
 **/
public class IslandGrid {

    public static void main(String[] args) {
        int[][] M = {{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}};

        int res = countIsland(M);
        System.out.printf("Count of the Island = %d",res);
    }

    static boolean isSafe(int[][] mat,int r,int c,boolean[][] vis){
        int n = mat.length;
        int m = mat[0].length;
        return ((r >= 0) && (r < n) && (c >= 0) && (c < m) && mat[r][c] == 1 && !vis[r][c]);
    }

    static void dfs(int[][] matrix,int r,int c,boolean[][] visited){
        //directions = 8
        //up,down,left,right,upRight,upLeft,downLeft,downRight
        int[] rowD = {-1,1,0,0,-1,-1,1,1};
        int[] colD = {0,0,-1,1,1,-1,-1,-1};


        visited[r][c] = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isSafe(matrix,r+rowD[i],c+colD[j],visited)){
                    dfs(matrix,r+rowD[i],c+colD[j],visited);
                }
            }
        }
    }

    static int countIsland(int[][] matrix){
        int count = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && matrix[i][j] == 1){
                    dfs(matrix,i,j,visited);
                    count++;
                }
            }
        }

        return count;
    }
}
