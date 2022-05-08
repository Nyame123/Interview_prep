package com.bis.interview_prep.greedy;

import java.util.HashMap;

/**
 * David has several containers, each with a number of balls in it. He has
 * just enough containers to sort each type of ball he has into its own container.
 * David wants to sort the balls using his sort method.
 *
 * David wants to perform some number of swap operations such that:
 *
 * Each container contains only balls of the same type.
 * No two balls of the same type are located in different containers.
 **/
public class ContainerContainingSameType {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 2, 1},
                {1, 1, 1},
                {2, 0, 0}
        };

        String result = organizingContainers(matrix);
        System.out.println(result);
    }

    /**
     * This problem is to swap all the items in the containers to make
     * sure that each container contains the same type of item.
     *
     * 1. Smart way was to get the capacity of each container
     * 2. Get the number of each item type.
     * 3. If the individual capacities of the container correspond to the number
     *  of the individual item type, then it is possible to swap else impossible
     *
     *  Time Complexity = O(N^2)
     **/
    private static String organizingContainers(int[][] container) {
        int n = container.length;
        int m = container[0].length;
        HashMap<Integer,Integer> row = new HashMap<>();
        int[] col = new int[m];

        int rCount;
        for (int i = 0; i < n; i++) {
            rCount = 0;
            for (int j = 0; j < m; j++) {
                rCount += container[i][j];
                col[j] += container[i][j];

            }
            row.put(rCount,row.getOrDefault(rCount, 0)+1);
        }

        int len = col.length;
        for (int i = 0; i < len; i++) {
            int key = col[i];
            if(row.containsKey(key)){
                row.put(key, row.getOrDefault(key, 0)-1);

                if(row.get(key) == 0){
                    row.remove(key);
                }
            }else{
                return "Impossible";
            }
        }

        return row.isEmpty()? "Possible" : "Impossible";
    }
}
