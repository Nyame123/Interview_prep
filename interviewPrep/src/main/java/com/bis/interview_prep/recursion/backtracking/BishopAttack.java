package com.bis.interview_prep.recursion.backtracking;
/**
 *On our special chessboard, two bishops attack each other if they share the same diagonal.
 * This includes bishops that have another bishop located between them, i.e. bishops can attack through pieces.
 *
 * You are given N bishops, represented as (row, column) tuples on a M by M chessboard.
 * Write a function to count the number of pairs of bishops that attack each other.
 * The ordering of the pair doesn't matter: (1, 2) is considered the same as (2, 1).
 *
 * For example, given M = 5 and the list of bishops:
 *
 * (0, 0)
 * (1, 2)
 * (2, 2)
 * (4, 0)
 * The board would look like this:
 *
 * [b 0 0 0 0]
 * [0 0 b 0 0]
 * [0 0 b 0 0]
 * [0 0 0 0 0]
 * [b 0 0 0 0]
 * You should return 2, since bishops 1 and 3 attack each other, as well as bishops 3 and 4.
 **/
public class BishopAttack {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'b', '0', '0', '0', '0'},
                {'0', '0', 'b', 'b', '0'},
                {'0', '0', 'b', '0', 'b'},
                {'b', '0', '0', '0', '0'},
                {'b', '0', 'b', '0', '0'}
               /* {'b', '0', '0', '0', '0'},
                {'0', '0', 'b', '0', '0'},
                {'0', '0', 'b', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'b', '0', 'b', '0', '0'}*/
        };
        int[][] positions = new int[][]{
                {0, 0},
                {1, 2},
                {1, 3},
                {2, 2},
                {2, 4},
                {3, 0},
                {4, 0},
                {4, 2}
                /*{0, 0},
                {1, 2},
                {2, 2},
                {4, 0}*/
        };
        int m = board.length;
        System.out.println(findTotalAttackUsingGradient(board, positions, m));
    }

    static int findTotalAttackUsingGradient(char[][] board, int[][] positions, int m){
        int sum = 0;
        for (int i = 0; i < positions.length; i++) {
            int row1 = positions[i][0], col1 = positions[i][1];
            for (int j = i+1; (j < positions.length) ; j++) {

                int row2 = positions[j][0], col2 = positions[j][1];

                if (Math.abs(row1-row2) == Math.abs(col1-col2)){
                    sum++;
                }
            }
        }

        return sum;
    }

    //Time Complexity = O(N*M)
    static int findTotalAttack(char[][] board, int[][] positions, int m) {

        int sum = 0;
        //topLeft topRight downLeft downRight
        int[] dx = {-1,1,-1,1};
        int[] dy = {-1,-1,1,1};
        //loop over the positions of the bishops
        for (int i = 0; i < positions.length; i++) {
            int r = positions[i][0];
            int c = positions[i][1];

            int topLeft = moveDir(board,r,c,dx,dy,0);
            int topRight = moveDir(board,r,c,dx,dy,1);
            int downLeft = moveDir(board,r,c,dx,dy,2);
            int downRight = moveDir(board,r,c,dx,dy,3);

            sum += (topLeft+topRight+downLeft+downRight);

        }

        return sum;
    }

    static int moveDir(char[][] board,int r,int c,int[] dx,int[] dy,int i){
        int attack = 0;
        int row = r+dy[i], col = c+dx[i];
        int m = board.length;
        int n = board[0].length;

        while (row >= 0 && row < m && col >= 0 && col < n && board[row][col] != '1'){

            if (board[row][col] == '1')
                break;

            if (board[row][col] == 'b') {
                attack++;
            } else {
                board[row][col] = '1';
            }
            row += dy[i];
            col += dx[i];
        }

        return attack;
    }
}
