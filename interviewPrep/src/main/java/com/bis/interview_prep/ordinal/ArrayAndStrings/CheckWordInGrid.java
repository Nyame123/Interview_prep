package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Check if a word exists in a grid or not
 * Difficulty Level : Medium
 * Last Updated : 20 Jan, 2022
 * Given a 2D grid of characters and a word, the task is to check if that word exists
 * in the grid or not. A word can be matched in 4 directions at any point.
 * The 4 directions are, Horizontally Left and Right, Vertically Up and Down.
 * Examples:
 * <p>
 * <p>
 * Input:  grid[][] = {"axmy",
 * "bgdf",
 * "xeet",
 * "raks"};
 * Output: Yes
 * <p>
 * a x m y
 * b g d f
 * x e e t
 * r a k s
 * <p>
 * Input: grid[][] = {"axmy",
 * "brdf",
 * "xeet",
 * "rass"};
 * Output : No
 **/
public class CheckWordInGrid {

    public static void main(String[] args) {
        char grid[][] = {"axmy".toCharArray(),
                "bgdf".toCharArray(),
                "xeet".toCharArray(),
                "raks".toCharArray()};

        // Function to check if word exists or not
        if (checkMatch(grid, "geeks"))
            System.out.print("Yes");
        else
            System.out.print("No");
    }

    static boolean checkMatch(char[][] grid, String word) {
        int R = grid.length;
        int C = grid[0].length;
        int l = word.length();

        if (l > R * C)
            return false;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    if (searchRec(grid, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean searchRec(char[][] grid, int r, int c, String word, int level) {

        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return false;
        }
        int len = word.length();
        if (level == len) {
            return true;
        }
        if (grid[r][c] == word.charAt(level)) {

            char temp = grid[r][c];
            grid[r][c] = '#';
            //L R U D
            int[] x = {-1, 1, 0, 0};
            int[] y = {0, 0, -1, 1};

            boolean res = false;
            for (int dir = 0; dir < 4; dir++) {
                res |= searchRec(grid, r + y[dir], c + x[dir], word, level + 1);
            }

            grid[r][c] = temp;
            return res;
        }

        return false;
    }

    static boolean search2D(char[][] grid, int r, int c, String word) {
        //L R U D
        int[] x = {-1, 1, 0, 0};
        int[] y = {0, 0, -1, 1};

        if (grid[r][c] != word.charAt(0)) {
            return false;
        }

        int len = word.length();

        int i = 1;
        for (int dir = 0; dir < 4; dir++) {
            int row = r;
            int col = c;
            for (i = 1; i < len; i++) {
                row = row + y[dir];
                col = col + x[dir];

                if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                    break;
                }

                if (grid[row][col] != word.charAt(i)) {
                    break;
                }
            }

            if (i == len) {
                return true;
            }
        }

        return false;
    }
}
