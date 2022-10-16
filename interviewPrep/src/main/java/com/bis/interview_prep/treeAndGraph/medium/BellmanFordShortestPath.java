package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.ordinal.graphs.Edge;
import com.bis.interview_prep.ordinal.graphs.Graph;
import com.bis.interview_prep.ordinal.graphs.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bellman–Ford Algorithm | DP-23
 * Difficulty Level : Medium
 * Last Updated : 22 Jul, 2021
 * Given a graph and a source vertex src in graph, find shortest paths from src to all
 * vertices in the given graph. The graph may contain negative weight edges.
 * We have discussed Dijkstra’s algorithm for this problem. Dijkstra’s algorithm
 * is a Greedy algorithm and time complexity is O(V+E LogV) (with the use of Fibonacci heap).
 * Dijkstra doesn’t work for Graphs with negative weight edges, Bellman-Ford works for such graphs.
 * Bellman-Ford is also simpler than Dijkstra and suites well for distributed systems.
 * But time complexity
 * of Bellman-Ford is O(VE), which is more than Dijkstra.
 * <p>
 * https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 **/
public class BellmanFordShortestPath {

    //some random big number is treated as infinity. I m not taking INTEGER_MAX as infinity because
    //doing any addition on that causes integer overflow
    private static int INFINITY = 10000000;

    public static void main(String args[]) {

        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(0, 3, 8);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 3, 1);

        BellmanFordShortestPath shortestPath = new BellmanFordShortestPath();
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();
        Map<Vertex<Integer>, Integer> distance = shortestPath.getShortestPath(graph, startVertex);
        System.out.println(distance);

        usingGraphMatrix();
    }


    static void usingGraphMatrix() {
        int graph1[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};

        int vertice = 9;

        shortestPathUsingBellmanFord(graph1, vertice, 0);
    }

    //using BellmanFord algorithm
    static class EdgeNode{
        int src;
        int des;
        int weight;
    }

    static void shortestPathUsingBellmanFord(int[][] graph, int v, int src){
        List<EdgeNode> edgeList = new ArrayList<>();
        HashMap<Integer,Integer> distance = new HashMap<>();

        for (int i = 0; i < v; i++) {
            distance.put(i,Integer.MAX_VALUE);
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] != 0) {
                    EdgeNode edgeNode = new EdgeNode();
                    edgeNode.src = i;
                    edgeNode.des = j;
                    edgeNode.weight = graph[i][j];
                    edgeList.add(edgeNode);
                }
            }
        }
        //[0, 4, 12, 19, 21, 11, 9, 8, 14]
        distance.put(src,0);
        for (int i = 0; i < v - 1; i++) {

            for (int j = 0; j < edgeList.size(); j++) {
                EdgeNode edgeNode = edgeList.get(j);

                if (distance.get(edgeNode.src) != Integer.MAX_VALUE &&
                        distance.get(edgeNode.src) + edgeNode.weight < distance.get(edgeNode.des)){
                    distance.put(edgeNode.des,distance.get(edgeNode.src) + edgeNode.weight);
                }
            }
        }

        for (int j = 0; j < edgeList.size(); j++) {
            EdgeNode edgeNode = edgeList.get(j);
            if (distance.get(edgeNode.src) != Integer.MAX_VALUE &&
                    distance.get(edgeNode.src) + edgeNode.weight < distance.get(edgeNode.des)){
                System.out.println("Graph contains Negative weight");
                return;
            }
        }


        System.out.println(distance);
    }


































    //Time complexity - O(EV)
    //Space complexity - O(V)
    public Map<Vertex<Integer>, Integer> getShortestPath(Graph<Integer> graph,
                                                         Vertex<Integer> sourceVertex) {

        Map<Vertex<Integer>, Integer> distance = new HashMap<>();
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();

        //set distance of every vertex to be infinity initially
        for (Vertex<Integer> v : graph.getAllVertex()) {
            distance.put(v, INFINITY);
            parent.put(v, null);
        }

        //set distance of source vertex to be 0
        distance.put(sourceVertex, 0);

        int V = graph.getAllVertex().size();

        //relax edges repeatedly V - 1 times
        for (int i = 0; i < V - 1; i++) {
            for (Edge<Integer> edge : graph.getAllEdges()) {
                Vertex<Integer> u = edge.getVertex1();
                Vertex<Integer> v = edge.getVertex2();
                //relax the edge
                //if we get better distance to v via u then use this distance
                //and set u as parent of v.
                if (distance.get(u) + edge.getWeight() < distance.get(v)) {
                    distance.put(v, distance.get(u) + edge.getWeight());
                    parent.put(v, u);
                }
            }
        }

        //relax all edges again. If we still get lesser distance it means
        //there is negative weight cycle in the graph. Throw exception in that
        //case
        for (Edge<Integer> edge : graph.getAllEdges()) {
            Vertex<Integer> u = edge.getVertex1();
            Vertex<Integer> v = edge.getVertex2();
            if (distance.get(u) + edge.getWeight() < distance.get(v)) {
                throw new NegativeWeightCycleException();
            }
        }
        return distance;
    }

    class NegativeWeightCycleException extends RuntimeException {
    }
}
