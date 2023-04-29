package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches
 * the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix
 * heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north,
 * south, east, and west if the neighboring cell's height is less than or equal to the current cell's
 * height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can
 * flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 **/
public class PacificAtlanticOcean {

    public static void main(String[] args) {
        int[][] heights = {
                {1,2,2,3,5}
                ,{3,2,3,4,4}
                ,{2,4,5,3,1}
                ,{6,7,1,4,5}
                ,{5,1,1,2,4}
        };

        List<List<Integer>> res = pacificAtlantic(heights);
        System.out.println(res);
    }


    static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        Set<String> pacific = new HashSet<>();
        Set<String> atlantic = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();

        //visit from first and last row for both pacific and atlantic ocean
        for(int r = 0; r < n; r++){
            dfs(heights, r, 0, pacific, heights[r][0]);
            dfs(heights, r, m-1, atlantic, heights[r][m-1]);
        }

        //visit from first and last row for both pacific and atlantic ocean
        for(int c = 0; c < m; c++){
            dfs(heights, 0, c, pacific, heights[0][c]);
            dfs(heights, n-1, c, atlantic, heights[n-1][c]);
        }

        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                String key = String.format("%s,%s",r,c);
                if(pacific.contains(key) && atlantic.contains(key)){
                    result.add(List.of(r,c));
                }
            }
        }

        return result;
    }

    static void dfs(int[][] heights, int r, int c, Set<String> visited, int prevHeight){
        String key = String.format("%s,%s",r,c);
        if(visited.contains(key) || r < 0 || c < 0 || r == heights.length || c == heights[0].length
                || prevHeight > heights[r][c]){
            return;
        }

        visited.add(key);

        dfs(heights, r-1, c, visited, heights[r][c]);
        dfs(heights, r+1, c, visited, heights[r][c]);
        dfs(heights, r, c-1, visited, heights[r][c]);
        dfs(heights, r, c+1, visited, heights[r][c]);
    }
}
