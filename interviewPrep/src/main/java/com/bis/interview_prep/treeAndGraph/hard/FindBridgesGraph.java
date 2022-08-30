package com.bis.interview_prep.treeAndGraph.hard;

import java.util.LinkedList;

/**
 *  A bridge in a connected (undirected) graph is an edge that, if removed,
 *  causes the graph to become disconnected. Find all the bridges in a graph.
 *
 **/
public class FindBridgesGraph {

    public static void main(String[] args) {
        // Create graphs given in above diagrams
        System.out.println("Bridges in first graph ");
        FindBridgesGraph g1 = new FindBridgesGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.bridge();
        System.out.println();

        System.out.println("Bridges in Second graph");
        FindBridgesGraph g2 = new FindBridgesGraph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.bridge();
        System.out.println();

        System.out.println("Bridges in Third graph ");
        FindBridgesGraph g3 = new FindBridgesGraph(7);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        g3.bridge();
    }

    int vertices;
    LinkedList<Integer>[] adjList;
    int[] discoverTime;
    int[] lowTime;
    int[] parent;
    int time;

    public FindBridgesGraph(int vertices){
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        discoverTime = new int[vertices];
        lowTime = new int[vertices];
        parent = new int[vertices];
        time = 0;

        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
            discoverTime[i] = -1;
        }


    }

    private void addEdge(int u, int v){
        adjList[u].add(v);
        adjList[v].add(u);
    }

    /**
     * To find the edges in the graph that form a bridge, we could use Articulation Point or Tarjan Algorithm.
     *
     * Time Complexity = (V+E)
     **/
    private void bridge(){
        for (int i = 0; i < vertices; i++) {
            if (discoverTime[i] == -1){
                dfsScc(i, discoverTime, lowTime, parent);
            }
        }
    }

    private void dfsScc(int u, int[] discoverTime, int[] lowTime, int[] parent){
        discoverTime[u] = lowTime[u] = ++time;

        for (int v : adjList[u]) {
            if (discoverTime[v] == -1){
                parent[v] = u;
                dfsScc(v, discoverTime, lowTime, parent);

                lowTime[u] = Math.min(lowTime[u], lowTime[v]);

                if (lowTime[v] > discoverTime[u]){
                    System.out.println(u+" "+v);
                }
            }else if (v != parent[u]){
                lowTime[u] = Math.min(lowTime[u], discoverTime[v]);
            }
        }
    }
}
