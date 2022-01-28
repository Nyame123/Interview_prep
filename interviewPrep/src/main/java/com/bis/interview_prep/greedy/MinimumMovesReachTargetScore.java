package com.bis.interview_prep.greedy;
/**
 * You are playing a game with integers. You start with the integer 1 and you want to reach the integer target.
 *
 * In one move, you can either:
 *
 * Increment the current integer by one (i.e., x = x + 1).
 * Double the current integer (i.e., x = 2 * x).
 * You can use the increment operation any number of times, however, you can only use the double operation at most maxDoubles times.
 *
 * Given the two integers target and maxDoubles, return the minimum number of moves needed to reach target starting with 1.
 **/
public class MinimumMovesReachTargetScore {

    public static void main(String[] args) {
        int target = 233, maxD = 2;
        int res = minMoves(target,maxD);
        System.out.println(res);
    }

    public static int minMoves(int target, int maxDouble) {

        int count = 0;
        while(target > 1){
            if(target % 2 == 0 && maxDouble > 0){
                target /= 2;
                maxDouble--;
            }else if(maxDouble == 0){
                count += target-1;
                break;
            }else{
                target--;
            }

            count++;
        }

        return count;
    }
}
