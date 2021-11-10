package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;

/**
 * A child can run up a staircase with n steps, hopping at either 1 step or 2 steps
 * or 3 steps, This class is to count the number of possible ways the child can run up the
 * stairs.
 **/
public class TripleSteps {

    public static void main(String[] args) {
        int stepsSize = 6;
//        int stepCount = possibleWays(stepsSize);
        int stepCount = countWaysWithConstantSpace(stepsSize);
//        int stepCount = possibleWaysWithMemo(stepsSize,new HashMap<>());
        System.out.println(stepCount);
    }

    //Time Complexity = O(3^n)
    //Space Complexity = O(n)
    static int possibleWays(int stepsSize) {

        //base cases
        if (stepsSize < 0){
            return 0;
        }
        if(stepsSize == 0){
            return 1;
        }

        return possibleWays(stepsSize-1) +
                possibleWays(stepsSize-2) +
                possibleWays(stepsSize-3);
    }

    //from bottom up solution
    static int countWays(int stepSize){
        if (stepSize < 0){
            return 0;
        }
        if(stepSize == 0){
            return 1;
        }

        //int[] paths = {1,1,2};
        int[] paths = new int[stepSize+1];
        paths[0] = 1;
        paths[1] = 1;
        paths[2] = 2;
        for (int i = 3; i <= stepSize; i++){
            paths[i] = paths[i-1] + paths[i-2] + paths[i-3];
        }

        return paths[stepSize];
    }

    //from bottom up solution
    static int countWaysWithConstantSpace(int stepSize) {
        if (stepSize < 0) {
            return 0;
        }
        if (stepSize == 0) {
            return 1;
        }

        int[] paths = {1,1,2};

        for (int i = 3; i <= stepSize; i++) {
            int count = paths[0] + paths[1] + paths[2];
            paths[0] = paths[1];
            paths[1] = paths[2];
            paths[2] = count;
        }

        return paths[2];
    }

    //with memoization
    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static int possibleWaysWithMemo(int stepsSize, HashMap<Integer,Integer> memo){
        //base cases
        if (memo.containsKey(stepsSize))
            return memo.get(stepsSize);

        if (stepsSize < 0){
            return 0;
        }
        if(stepsSize == 0){
            return 1;
        }

        int res = possibleWaysWithMemo(stepsSize-1,memo) +
                possibleWaysWithMemo(stepsSize-2,memo) +
                possibleWaysWithMemo(stepsSize-3,memo);

        memo.put(stepsSize,res);
        return res;
    }
}
