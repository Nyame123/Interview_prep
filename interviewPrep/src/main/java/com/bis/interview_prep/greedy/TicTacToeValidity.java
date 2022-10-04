package com.bis.interview_prep.greedy;

/**
 * A Tic-Tac-Toe board is given after some moves are played.
 * Find out if the given board is valid, i.e., is it possible to
 * reach this board position after some moves or not.
 * Note that every arbitrary filled grid of 9 spaces isn’t valid e.g.
 * a grid filled with 3 X and 6 O isn’t valid situation because each
 * player needs to take alternate turns.
 * <p>
 * Input is given as a 1D array of size 9.
 * <p>
 * Examples:
 * <p>
 * Input: board[] =  {'X', 'X', 'O',
 * 'O', 'O', 'X',
 * 'X', 'O', 'X'};
 * Output: Valid
 * <p>
 * Input: board[] =  {'O', 'X', 'X',
 * 'O', 'X', 'X',
 * 'O', 'O', 'X'};
 * Output: Invalid
 * (Both X and O cannot win)
 * <p>
 * Input: board[] =  {'O', 'X', ' ',
 * ' ', ' ', ' ',
 * ' ', ' ', ' '};
 * Output: Valid
 * (Valid board with only two moves played)
 * <p>
 * https://www.geeksforgeeks.org/validity-of-a-given-tic-tac-toe-board-configuration/
 **/
public class TicTacToeValidity {

    public static void main(String[] args) {
        int[] board = {'X', 'X', 'O',
                'O', 'O', 'X',
                'X', 'O', 'X'};

        boolean isValid = isValidTicTacToe(board);
        if (isValid) {
            System.out.println("Given board is valid");
        } else {
            System.out.println("Given board is not valid");
        }
    }

    private static boolean isValidTicTacToe(int[] board) {
        //cheat board
        int[][] win = {
                {0, 1, 2}, //first row
                {3, 4, 5}, //second row
                {6, 7, 8}, //third row
                {0, 3, 6}, //first column
                {1, 4, 7}, //second column
                {2, 5, 8}, //third column
                {0, 4, 8}, //first diagonal
                {2, 4, 6} //second diagonal
        };

        int xCount = 0, oCount = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 'X')
                xCount++;
            if (board[i] == 'O')
                oCount++;
        }

        //if the board is valid
        if (xCount == oCount || xCount == oCount + 1) {
            if (isWin(board, win, 'O')) { // o is a winner
                if (isWin(board, win, 'X')) { //x is a winner
                    return false;
                }

                return xCount == oCount;
            }

            if (isWin(board, win, 'X') && xCount != oCount + 1) {
                return false;
            }

            return true;
        }

        return false;
    }

    static boolean isWin(int[] board, int[][] win, char x) {
        for (int i = 0; i < 8; i++) {
            if (board[win[i][0]] == x &&
                    board[win[i][1]] == x &&
                    board[win[i][2]] == x
            ) {
                return true;
            }
        }

        return false;
    }
}
