package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Search a Word in a 2D Grid of characters
 * Difficulty Level : Medium
 * <p>
 * Last Updated : 22 Jul, 2021
 * Given a 2D grid of characters and a word, find all occurrences of the given word in the grid. A word can
 * be matched in all 8 directions at any point. Word is said to be found in a direction if all characters match
 * in this direction (not in zig-zag form).
 * The 8 directions are, Horizontally Left, Horizontally Right, Vertically Up, Vertically Down and 4 Diagonal directions.
 * Example:
 * <p>
 * Input:  grid[][] = {"GEEKSFORGEEKS",
 * "GEEKSQUIZGEEK",
 * "IDEQAPRACTICE"};
 * word = "GEEKS"
 * <p>
 * Output: pattern found at 0, 0
 * pattern found at 0, 8
 * pattern found at 1, 0
 * Explanation: 'GEEKS' can be found as prefix of
 * 1st 2 rows and suffix of first row
 * <p>
 * Input:  grid[][] = {"GEEKSFORGEEKS",
 * "GEEKSQUIZGEEK",
 * "IDEQAPRACTICE"};
 * word = "EEE"
 * <p>
 * Output: pattern found at 0, 2
 * pattern found at 0, 10
 * pattern found at 2, 2
 * pattern found at 2, 12
 * Explanation: EEE can be found in first row
 * twice at index 2 and index 10
 * and in second row at 2 and 12
 **/
public class SearchAllWordInGrid {

    public static void main(String[] args) {

        char[][] grid = {{'G', 'E', 'E', 'K', 'S', 'F', 'O', 'R', 'G', 'E', 'E', 'K', 'S'},
                {'G', 'E', 'E', 'K', 'S', 'Q', 'U', 'I', 'Z', 'G', 'E', 'E', 'K'},
                {'I', 'D', 'E', 'Q', 'A', 'P', 'R', 'A', 'C', 'T', 'I', 'C', 'E'}};
        patternSearch(grid, "GEEKS");
        System.out.println();
        patternSearch(grid, "EEE");
    }

    static void patternSearch(char[][] grid, String word) {
        int R = grid.length;
        int C = grid[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (search2D(grid, i, j, word)) {
                    System.out.printf("%s starts from %d, %d\n", word, i, j);
                }
            }
        }
    }

    static boolean search2D(char[][] grid, int r, int c, String word) {
        //L R U D LU LD RU RD
        int[] x = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] y = {0, 0, -1, 1, -1, 1, -1, 1};

        if (grid[r][c] != word.charAt(0)) {
            return false;
        }

        int len = word.length();

        int i = 1;
        for (int dir = 0; dir < 8; dir++) {
            int row = r;
            int col = c;
            for (i = 1; i < len; i++) {
                row = row + y[dir];
                col = col + x[dir];

                if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
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
