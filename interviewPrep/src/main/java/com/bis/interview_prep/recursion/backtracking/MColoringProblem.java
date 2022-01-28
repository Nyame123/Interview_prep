package com.bis.interview_prep.recursion.backtracking;

import java.util.List;

/**
 * Given an undirected graph represented as an adjacency matrix and an integer k,
 * write a function to determine whether each vertex in the graph can be colored
 * such that no two adjacent vertices share the same color using at most k colors.
 * <p>
 * Input:
 * graph = {0, 1, 1, 1},
 * {1, 0, 1, 0},
 * {1, 1, 0, 1},
 * {1, 0, 1, 0}
 * Output:
 * Solution Exists:
 * Following are the assigned colors
 * 1  2  3  2
 * Explanation: By coloring the vertices
 * with following colors, adjacent
 * vertices does not have same colors
 * <p>
 * Input:
 * graph = {1, 1, 1, 1},
 * {1, 1, 1, 1},
 * {1, 1, 1, 1},
 * {1, 1, 1, 1}
 * Output: Solution does not exist.
 * Explanation: No solution exits.
 **/
public class MColoringProblem {

    public static void main(String[] args) {

        /* Create following graph and
           test whether it is
           3 colorable
          (3)---(2)
           |   / |
           |  /  |
           | /   |
          (0)---(1)
        */
        int graph[][] = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };
        int m = 3; // Number of colors
        int v = 4;
        graphColoring(graph, m,v);
    }

    static boolean graphColoring(int[][] graph, int m, int V) {
        int[] colors = new int[V];

        if (!coloringProb(graph, m, V, 0, colors)) {
            System.out.println(
                    "Solution does not exist");
            return false;
        }else{
            printSolution(colors,V);
        }
        return true;
    }

    static boolean coloringProb(int[][] graph, int m, int vertices, int v, int[] colors) {

        //base case
        if (v == vertices)
            return true;

        for (int i = 1; i <= m; i++) {
            if (isSafe(graph, i, v, colors, vertices)) {

                colors[v] = i;
                if (coloringProb(graph, m, vertices, v + 1, colors))
                    return true;

                colors[v] = 0;
            }


        }

        return false;
    }

    static boolean isSafe(int[][] graph, int c, int v, int[] colors, int vertices) {

        //check all the adjacent vertex to check if has same color as c
        for (int i = 0; i < vertices; i++) {

            if (graph[v][i] == 1 && colors[i] == c) {
                return false;
            }
        }

        return true;
    }

    static void printSolution(int color[],int V) {
        System.out.println(
                "Solution Exists: Following"
                        + " are the assigned colors");
        for (int i = 0; i < V; i++)
            System.out.print(" " + color[i] + " ");
        System.out.println();
    }
}

class MColouringPossibility{

    public static void main(String[] args) {
        int m = 3;

    }

    public static boolean graphColoring(List<Integer>[] G, int[] color, int v, int m)
    {
        // Your code here
        if(v == color.length){
            return true;
        }

        for(int i = 1; i <= m; i++){

            if(isSafe(G,i,v,color.length,color)){

                color[v] = i;
                if(graphColoring(G,color,v+1,m)){
                    return true;
                }

                color[v] = 0;
            }
        }

        return false;
    }



    static boolean isSafe(List<Integer>[] G, int c, int v, int noV,int[] colors){

        List<Integer> neigh = G[v];
        for(int i = 0; i < neigh.size(); i++){
            if(colors[neigh.get(i)] == c){
                return false;
            }
        }

        return true;
    }
}
