package com.bis.interview_prep.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Write an algorithm to print all ways of arranging eight queens on an
 * 8x8 chessboard where none of the queens share the same row, same column and
 * same diagonal
 **/
public class EightQueens {

    public static void main(String[] args) {

        int n = 6;

        List<Queen[]> ways = waysQueen(n);
        System.out.println(ways.size());
        for (int i = 0; i < ways.size(); i++) {
            for (int j = 0; j < ways.get(i).length; j++) {
                System.out.printf("%s, ",ways.get(i)[j]);
            }
            System.out.println();
        }

    }

    static List<Queen[]> waysQueen(int n) {
        List<Queen[]> ways = new ArrayList<>();
        Queen[] columns = new Queen[n];
        waysQueen(n, 0, ways, columns);

        return ways;
    }

    private static void waysQueen(int n,int row, List<Queen[]> ways, Queen[] columns) {

        //base case
        if (row == n) {
            ways.add(columns.clone());
        } else {
            for (int i = 0; i < n; i++) {
                if (isValidPlacement(columns, row,i)) {
                    columns[row] = new Queen(row,i);;
                    waysQueen(n,row + 1, ways, columns);
                }
            }

        }
    }

    //check if the placement of the queen violate the rule of the chessboard
    static boolean isValidPlacement(Queen[] column, int r, int c) {
        int newrow = r;
        int newcol = c;

        for (int row = 0; row < newrow; row++) {

            int oldCol = column[row].col;
            if (oldCol == newcol)//checking if the column is valid
                return false;

           int colDistance = Math.abs(newcol - oldCol);
           int rowDistance = newrow - row;
           if (rowDistance == colDistance)
               return false;

        }

        return true;
    }

    static class Queen {
        int row;
        int col;

        public Queen(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Queen{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }


}
