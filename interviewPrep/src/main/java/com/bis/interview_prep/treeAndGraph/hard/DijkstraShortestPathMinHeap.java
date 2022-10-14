package com.bis.interview_prep.treeAndGraph.hard;

import com.bis.interview_prep.ordinal.graphs.BinaryMinHeap;
import com.bis.interview_prep.ordinal.graphs.Edge;
import com.bis.interview_prep.ordinal.graphs.Graph;
import com.bis.interview_prep.ordinal.graphs.Vertex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Find single source shortest path using Dijkstra's algorithm
 * <p>
 * Space complexity - O(E + V)
 * Time complexity - O(ElogV)
 * <p>
 * References
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * CLRS book
 */
public class DijkstraShortestPathMinHeap {

    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(false);
        /*graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(2, 5, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(0, 7, 8);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 7);*/

        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 4, 9);
        graph.addEdge(1, 5, 3);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 4, 2);
        graph.addEdge(3, 4, 3);

        DijkstraShortestPathMinHeap dsp = new DijkstraShortestPathMinHeap();
        Vertex<Integer> sourceVertex = graph.getVertex(1);
        Map<Vertex<Integer>, Integer> distance = dsp.shortestPath(graph, sourceVertex);
        System.out.print(distance);

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

        shortestPath(graph1,vertice,0);
    }

    static void shortestPath(int[][] graph, int vertice, int src) {
        HashMap<Integer, List<NodeVert>> adjList = new HashMap<>();
        HashMap<Integer, Integer> distance = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        PriorityQueue<NodeVert> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.distance));

        for (int i = 0; i < vertice; i++) {
            adjList.put(i,new LinkedList<>());
            distance.put(i, Integer.MAX_VALUE);
            visited.put(i, false);
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
               if (graph[i][j] != 0){
                   NodeVert edges = new NodeVert();
                   edges.key = j;
                   edges.distance = graph[i][j];
                   adjList.get(i).add(edges);
               }
            }
        }

        NodeVert startNode = new NodeVert();
        startNode.key = src;
        startNode.distance = 0;
        minHeap.add(startNode);
        distance.put(src, 0);

        //[0, 4, 12, 19, 21, 11, 9, 8, 14]
        while (!minHeap.isEmpty()){
            NodeVert minNode =  minHeap.poll();
            distance.put(minNode.key,minNode.distance);
            visited.put(minNode.key,true);
            for(NodeVert neigh: adjList.get(minNode.key)){

                if (visited.get(neigh.key))
                    continue;

                int newDistance = distance.get(minNode.key) + neigh.distance;
                if (distance.get(neigh.key) > newDistance){
                    distance.put(neigh.key,newDistance);

                    reduceHeap(minHeap,neigh.key,newDistance);
                }
            }
        }

        System.out.println();
        System.out.println(distance);
    }

    static void reduceHeap(PriorityQueue<NodeVert> queue,int v, int distance){
        List<NodeVert> list = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek().key != v){
            list.add(queue.poll());
        }

        if (!queue.isEmpty()){
            queue.peek().distance = distance;
        }else{
            NodeVert neighNode = new NodeVert();
            neighNode.key = v;
            neighNode.distance = distance;
            queue.add(neighNode);
        }

        queue.addAll(list);
    }

    public Map<Vertex<Integer>, Integer> shortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex) {

        //heap + map data structure
        BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();

        //stores shortest distance from root to every vertex
        Map<Vertex<Integer>, Integer> distance = new HashMap<>();

        //stores parent of every vertex in shortest distance
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();

        //initialize all vertex with infinite distance from source vertex
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            minHeap.add(Integer.MAX_VALUE, vertex);
        }

        //set distance of source vertex to 0
        minHeap.decrease(sourceVertex, 0);

        //put it in map
        distance.put(sourceVertex, 0);

        //source vertex parent is null
        parent.put(sourceVertex, null);

        //iterate till heap is not empty
        while (!minHeap.empty()) {
            //get the min value from heap node which has vertex and distance of that vertex from source vertex.
            BinaryMinHeap<Vertex<Integer>>.Node heapNode = minHeap.extractMinNode();
            Vertex<Integer> current = heapNode.key;

            //update shortest distance of current vertex from source vertex
            distance.put(current, heapNode.weight);

            //iterate through all edges of current vertex
            for (Edge edge : current.getEdges()) {

                //get the adjacent vertex
                Vertex<Integer> adjacent = getVertexForEdge(current, edge);

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if (!minHeap.containsData(adjacent)) {
                    continue;
                }

                //add distance of current vertex to edge weight to get distance of adjacent vertex from source vertex
                //when it goes through current vertex
                int newDistance = distance.get(current) + edge.getWeight();

                //see if this above calculated distance is less than current distance stored for adjacent vertex from source vertex
                if (minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                    parent.put(adjacent, current);
                }
            }
        }
        return distance;
    }

    private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge e) {
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }

    //shortest distance to a vertex
    static class NodeVert {
        int key;
        int distance;
    }
}