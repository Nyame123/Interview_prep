package com.bis.interview_prep.recursion.backtracking;
/**
 * Sudoku is a puzzle where you're given a partially-filled 9 by 9 grid with digits.
 * The objective is to fill the grid with the constraint that every row, column, and box
 * (3 by 3 subgrid) must contain all of the digits from 1 to 9
 *
 *
 **/
public class SudoKuPuzzle {

    public static void main(String[] args) {
        int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        if (solveSudoku(grid))
            print(grid);
        else
            System.out.println("No Solution exists");
    }

    static void print(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]+ " ");
            }
            System.out.println();
        }
    }

    //Time Complexity = O(9^(n*n))
    //Space Complexity = O(n*n)
    static boolean solveSudoku(int[][] grid){

        int r = -1, c = -1;
        boolean isEmpty = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0){
                    r = i;
                    c = j;
                    isEmpty = false;
                    break;
                }
            }

            if (!isEmpty){
                break;
            }
        }

        if (isEmpty)
            return true;

        //try all numbers from 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafePosition(grid,r,c,num)){
                //add the num to the position
                grid[r][c] = num;

                //recurse on the solution
                if(solveSudoku(grid)){
                    return true;
                }
                grid[r][c] = 0;
            }

        }

        return false;
    }


    static boolean isSafePosition(int[][] grid, int row,int col, int num){
        //check if the row can be filled
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == num){
                return false;
            }
        }

        //check if the col can be filled
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[row][i] == num){
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(grid.length);
        int startRowSubGrid = row - row % sqrt;
        int startColSubGrid = col - col % sqrt;

        for (int i = startRowSubGrid; i < startRowSubGrid+3; i++) {
            for (int j = startColSubGrid; j < startColSubGrid+3; j++) {
                if (grid[i][j] == num)
                    return false;
            }
        }

        return true;
    }
}
