package com.bis.interview_prep.treeAndGraph.hard;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * In a directed graph, each node is assigned an uppercase letter. We define a path's
 * value as the number of most frequently-occurring letter along that path. For example,
 * if a path in the graph goes through "ABACA", the value of the path is 3, since there are 3 occurrences of 'A' on the path.
 * <p>
 * Given a graph with n nodes and m directed edges, return the largest value path of the
 * graph. If the largest value is infinite, then return null.
 * <p>
 * The graph is represented with a string and an edge list. The i-th character represents
 * the uppercase letter of the i-th node. Each tuple in the edge list (i, j) means there is
 * a directed edge from the i-th node to the j-th node. Self-edges are possible, as well as multi-edges.
 * <p>
 * For example, the following input graph:
 * <p>
 * ABACA
 * [(0, 1),
 * (0, 2),
 * (2, 3),
 * (3, 4)]
 * Would have maximum value 3 using the path of vertices [0, 2, 3, 4], (A, A, C, A).
 * <p>
 * The following input graph:
 * <p>
 * A
 * [(0, 0)]
 * Should return null, since we have an infinite loop.
 */
public class LargestValueFromPathDCG {
    private static final int UNVISITED = 0;
    private static final int VISITING = 1;
    private static final int VISITED = 2;
    public static void main(String[] args) {
        int[][] graph = {{0,0}};
        String fullPath = "A";
        int V = fullPath.length();
        int res = findFullPathValueFromPath(fullPath, graph, V);

        System.out.println(res == Integer.MAX_VALUE ? "NuLL" : res);
    }

    static int findFullPathValueFromPath(String fullPath, int[][] graph, int V) {
        int[] state = new int[V];
        LinkedList<Integer>[] buildG = buildGraph(graph, V);
        int[] freq = new int[V];
        int max = 0;
        for (int i = 0; i < V; i++) {
            if (state[i] == UNVISITED) {
                max = Math.max(max, exploreDFS(fullPath, freq, buildG, state, i));
            }
        }
        return max;
    }

    static int exploreDFS(String nodes, int[] freq, LinkedList<Integer>[] graph, int[] state, int cur) {

        if (state[cur] == VISITING){
            return Integer.MAX_VALUE;
        }

        int max = ++freq[nodes.charAt(cur) - 'A'];
        state[cur] = VISITING;
        for (int i = 0; graph[cur] != null && i < graph[cur].size(); i++) {
            max = Math.max(max, exploreDFS(nodes, freq, graph,state, graph[cur].get(i)));
        }
        //reduce the freq bound
        --freq[nodes.charAt(cur) - 'A'];
        //visit the node
        state[cur] = UNVISITED;

        return max;
    }

    static LinkedList<Integer>[] buildGraph(int[][] graph, int V) {
        LinkedList<Integer>[] adjacencyList = new LinkedList[V];

        for (int i = 0; i < graph.length; i++) {

            if (adjacencyList[graph[i][0]] == null) {
                adjacencyList[graph[i][0]] = new LinkedList<>();
            }
            adjacencyList[graph[i][0]].add(graph[i][1]);
        }

        return adjacencyList;
    }


}
