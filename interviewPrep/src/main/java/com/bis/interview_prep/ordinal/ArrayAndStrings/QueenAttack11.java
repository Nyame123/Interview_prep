package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.List;

public class QueenAttack11 {

    public static void main(String[] args) {

    }

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // Write your code here
        int left = c_q - 1;
        int right = n - c_q;
        int top = n - r_q;
        int bottom = r_q - 1;
        int topRight = Math.min(top,right);
        int topLeft = Math.min(top,left);
        int bottomRight = Math.min(bottom,right);
        int bottomLeft = Math.min(bottom,left);

        //go over the obstacles to select the closest one
        for(int i = 0; i < k; i++){
            int row = obstacles.get(i).get(0);
            int col = obstacles.get(i).get(1);

            if(c_q == col){  //check in same vertical
                if(row > r_q){
                    top = Math.min(top,row-r_q-1);
                }else{
                    bottom = Math.min(bottom,r_q-row-1);
                }
            }else if(r_q == row){ //check in same horizontal
                if(col > c_q){
                    right = Math.min(right,col-c_q-1);
                }else{
                    left = Math.min(left,c_q-col-1);
                }
            }else if(inDiagonal(row,col,r_q,c_q)){
                //int diagonal = inDiagonal(row,col,r_q,c_q);
                if(col < c_q){ //negative slope
                    if(row > r_q){
                        topLeft = Math.min(topLeft,row - r_q - 1);
                    }else{
                        bottomLeft = Math.min(bottomLeft,r_q - row - 1);
                    }
                }else { //positive slope
                    if(row > r_q){
                        topRight = Math.min(topRight,row - r_q - 1);
                        //topRight = Math.min(topRight,col-c_q-1)
                    }else{
                        bottomRight = Math.min(bottomRight,r_q - row - 1);

                    }
                }
            }
        }

        int ans = top + bottom + left + right + topRight + topLeft + bottomRight + bottomLeft;
        return ans;

    }

    //check if it is the diagonal
    static boolean inDiagonal(int r, int c,int r_q,int c_q){
        int deltaY = r_q - r;
        int deltaX = c_q - c;

        return Math.abs(deltaY) == Math.abs(deltaX);
    }
}
