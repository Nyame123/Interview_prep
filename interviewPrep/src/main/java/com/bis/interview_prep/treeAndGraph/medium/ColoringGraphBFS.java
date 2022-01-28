package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ColoringGraphBFS {

    public static void main(String[] args) {
        int n = 4;
        int[][] graph = {{0, 1, 1, 1}, {1, 0, 1, 0},
                {1, 1, 0, 1}, {1, 0, 1, 0}};
        int m = 3; // Number of colors

        // Create a vector of n+1
        // nodes of type "node"
        // The zeroth position is just
        // dummy (1 to n to be used)
        List<Node> nodes = new ArrayList<Node>();

        for (int i = 0; i < n + 1; i++) {
            nodes.add(new Node());
        }

        // Add edges to each node as per given input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] > 0) {
                    // Connect the undirected graph
                    nodes.get(i).edges.add(i);
                    nodes.get(j).edges.add(j);
                }
            }
        }

        // Display final answer
        System.out.println(canColor(nodes, n, m)? 1 : 0);
    }

    static boolean canColor(List<Node> graph,int n, int m){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int maxColor = 1;
        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()){
            int cur = queue.poll();
            visited[cur] = true;

            //visit the neighbouring vertices
            for (int vertex: graph.get(cur).edges) {

                if (!visited[vertex]) {
                    if (graph.get(vertex).color == graph.get(cur).color) {
                        graph.get(vertex).color = graph.get(cur).color + 1;
                    }

                    maxColor = Math.max(maxColor, graph.get(vertex).color);

                    if (maxColor > m) {
                        return false;
                    }

                    queue.add(vertex);
                }
            }

        }

        return true;
    }
}
