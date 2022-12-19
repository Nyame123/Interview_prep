package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ColoringGraphBFS {

    public static void main(String[] args) {
        int n = 4;
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}};
        int m = 4; // Number of colors

        // Create a vector of n+1
        // nodes of type "node"
        // The zeroth position is just
        // dummy (1 to n to be used)


        colorGraphMain(graph, n, m);
        backtrackingMain(graph, m, n);
        // Display final answer
        System.out.println(canColor(graph, n, m) ? 1 : 0);
    }

    //Using Backtracking
    static void backtrackingMain(int[][] graph, int color, int vertice) {
        int[] colors = new int[vertice];
        boolean results = recurse(graph, colors, 0, 1, color, vertice);
        System.out.println(Arrays.toString(colors));
        System.out.println(results);
    }

    static boolean recurse(int[][] graph, int[] colors, int v, int color, int totalColor, int total) {
        if (v == total - 1) {
            colors[v] = color;
            return true;
        }

        if (color > totalColor) {
            return false;
        }

        colors[v] = color;
        for (int i = 1; i <= totalColor; i++) {
            if (isSafe(graph, colors, v + 1, i)) {
                if (recurse(graph, colors, v + 1, i, totalColor, total)) {
                    return true;
                }
            }
        }

        colors[v] = 0;
        return false;
    }

    static boolean isSafe(int[][] graph, int[] colors, int u, int color) {
        for (int i = 0; i < graph[u].length; i++) {
            if (i != u) {
                if (graph[u][i] == 1 && colors[i] == color)
                    return false;
            }
        }

        return true;
    }

    static void colorGraphMain(int[][] graph, int vertice, int color) {
        System.out.println(colorGraph(graph, vertice, color) ? 1 : 0);
    }

    //can color graph
    static boolean colorGraph(int[][] graph, int vertice, int color) {
        List<CNode>[] adjList = new LinkedList[vertice];
        Map<Integer, CNode> nodeMap = new HashMap<>();

        for (int i = 0; i < vertice; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] > 0) {
                    addEdge(adjList, nodeMap, i, j);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        //random vertex
        int startNode = nodeMap.values().iterator().next().key;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        int maxColor = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited.add(cur);
            CNode curNode = nodeMap.get(cur);
            for (int i = 0; i < adjList[cur].size(); i++) {
                CNode neighbour = adjList[cur].get(i);
                if (!visited.contains(neighbour.key)) {
                    queue.add(neighbour.key);
                }

                if (neighbour.color == curNode.color) {
                    neighbour.color = curNode.color + 1;
                    nodeMap.get(neighbour.key).color = neighbour.color;
                }

                maxColor = Math.max(maxColor, neighbour.color);
                if (maxColor > color - 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void addEdge(List<CNode>[] graph, Map<Integer, CNode> map, int u, int v) {

        if (!map.containsKey(u)) {
            CNode n = new CNode();
            n.key = u;
            map.put(u, n);
        }


        if (!map.containsKey(v)) {
            CNode n = new CNode();
            n.key = v;
            map.put(v, n);
            graph[u].add(n);
        } else {
            graph[u].add(map.get(v));
        }

    }

    static boolean canColor(int[][] matrix, int n, int m) {
        List<Node> graph = new ArrayList<Node>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new Node());
        }

        // Add edges to each node as per given input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    // Connect the undirected graph
                    graph.get(i).edges.add(i);
                    graph.get(j).edges.add(j);
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int maxColor = 1;
        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;

            //visit the neighbouring vertices
            for (int vertex : graph.get(cur).edges) {

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

    static class CNode {
        int key;
        int color;
    }
}
