package com.bis.interview_prep.greedy;
/**
 * 1217. Minimum Cost to Move Chips to The Same Position
 * Easy
 * We have n chips, where the position of the ith chip is position[i].
 *
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 *
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 **/
public class MinimumCostChip {

    public static void main(String[] args) {

        int[] arr = {2,2,2,3,3};
        System.out.println(minCostToMoveChips(arr));
    }

    static int minCostToMoveChips(int[] arr){
        int evenCount = 0, oddCount = 0;

        for(int pos: arr){
            if (pos % 2 == 0){
                evenCount++;
            }
        }

        oddCount = arr.length-evenCount;
        return Math.min(oddCount,evenCount);
    }
}
