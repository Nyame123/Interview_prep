package com.bis.interview_prep.treeAndGraph.medium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BFSShortestDistance {

    public static void main(String[] args) throws IOException {
        runner();
    }

    static void runner() throws IOException {
        File file = new File(System.getProperty("user.home"), "/Desktop/graph_list.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = BFSShortestDistance.bfs(n, m, edges, s);

                System.out.println(result);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();

    }

    public static List<Integer> bfs(int n, int m, List<List<Integer>> adj, int s) {
        LinkedList<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>(adj.size());



        for(int a1 = 0; a1 < adj.size(); a1++){
            int u = adj.get(a1).get(0);
            int v = adj.get(a1).get(1);
            // First
            List<Integer> tmp = adj.get(u);
            tmp.add(v);
            adj.set(u, tmp);

            // Second
            tmp = adj.get(v);
            tmp.add(u);
            adj.set(v, tmp);
        }

        for (int i = 0; i < adj.size(); i++) {
            result.add(0);
        }
        q.addFirst(s);

        while (q.size() > 0) {
            int current = q.pollLast();
            List<Integer> tmp = adj.get(current);
            for (int i = 0; i < tmp.size(); i++) {
                int v = tmp.get(i);
                if (result.get(v) == 0) {
                    q.addFirst(v);
                    result.set(v, result.get(current) + 6);
                }
            }
        }

        return result;
    }

    public static List<Integer> bfs2(int n, int m, List<List<Integer>> edges, int s) {
        //graph initialization
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();

        //build graph from the nodes
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<>());
        }

        //add the edges to the graph
        for (int i = 0; i < edges.size(); i++) {
            List<Integer> edge = edges.get(i);
            int e1 = edge.get(0);
            int e2 = edge.get(1);
            graph.get(e1).add(e2);
            graph.get(e2).add(e1);
        }

        //Reached nodes
        HashMap<Integer, Integer> reached = new HashMap<>();

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        //put the starting node
        queue.addFirst(s);
        visited[s] = true;
        reached.put(s, 0);

        //explore the graph
        while (!queue.isEmpty()) {
            //src node
            int srcNode = queue.poll();

            //explore the neighbours
            for (int des : graph.get(srcNode)) {
                //check if not visited
                if (!visited[des]) {
                    //visit the destination node
                    visited[des] = true;

                    //write the distance of the destination from src node
                    int srcDistance = reached.get(srcNode);
                    reached.put(des, srcDistance + 6);

                    //add to the queue
                    queue.addFirst(des);
                }
            }
        }

        List<Integer> distance = new ArrayList<>();
        //retrieve the distances
        for (int i = 1; i <= n; i++) {
            if (s != i) {
                distance.add(reached.getOrDefault(i, -1));
            }
        }

        return distance;
    }

    public static List<Integer> bfs1(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here

        //System.out.println("EdgesList "+edges);
        //System.out.println("StartNode "+s);

        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        //creating the graph of nodes
        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }


        for (int i = 0; i < edges.size(); i++) {
            int key = edges.get(i).get(0);
            int nei = edges.get(i).get(1);
            // System.out.println("start "+key+" End "+nei);
            //System.out.println("Another start "+nei+" Another End "+key);

            List<Integer> neighbours = adjList.get(key);
            if (!neighbours.contains(nei))
                neighbours.add(nei);
            else
                System.out.println("Edge exist between " + key + " and " + nei);

            List<Integer> neighbours1 = adjList.get(nei);
            if (!neighbours1.contains(key))
                neighbours1.add(key);
            else
                System.out.println("Edge exist between " + nei + " and " + key);
        }

        //System.out.println("Graph " + adjList);

        List<Integer> distance = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i == s) {
                distance.add(0);
            } else {
                distance.add(Integer.MAX_VALUE);
            }

        }

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        visited[s] = true;
        //put the starting node
        queue.addFirst(s);


        while (!queue.isEmpty()) {
            int startNode = queue.poll();
            //visit the neighbouring nodes
            List<Integer> neighs = adjList.get(startNode);

            for (int des : neighs) {
                if (!visited[des]) {
                    visited[startNode] = true;
                    int newDis = distance.get(startNode - 1) + 6;
                    distance.set(des - 1, newDis);
                    queue.addFirst(des);
                }

            }

        }

        //remove the starting node from the distance
        distance.remove(s - 1);
        for (int i = 0; i < distance.size(); i++) {
            if (distance.get(i) == Integer.MAX_VALUE) {
                distance.set(i, -1);
            }

        }

        return distance;

    }
}

class Solution{

    static boolean[] visited;
    static int[] dist;
    static LinkedList<Integer> q = new LinkedList<Integer>();
    static class Graph {
        int n, m;
        HashSet<Integer>[] adj;
        public Graph(int n, int m) {
            this.n = n;
            this.m = m;
            adj = new HashSet[n];
            for (int i = 0; i < n; i ++) {
                adj[i] = new HashSet<>();
            }
        }
        public void addEdge(int i, int j) {
            adj[i].add(j);
            adj[j].add(i);
        }
    }
    static void bfs(int s, Graph g) {
        visited = new boolean[g.n];
        visited[s] = true;
        dist[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int cur = q.poll();
            Iterator<Integer> iter = g.adj[cur].iterator();
            while (iter.hasNext()) {
                int next = iter.next();
                if (!visited[next]) {
                    dist[next] = dist[cur] + 6;
                    visited[next] = true;
                    q.add(next);
                }

            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Graph g = new Graph(n, m);
            while (m--  > 0) {
                int i = sc.nextInt() - 1;
                int j = sc.nextInt() - 1;
                g.addEdge(i, j);
            }
            int s = sc.nextInt() - 1;
            visited = new boolean[n];
            dist = new int[n];
            for (int i = 0; i < n; i ++) {
                dist[i] = -1;
            }
            bfs(s, g);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if(i != s) {
                    sb.append(dist[i]).append(" ");
                }
            }
            System.out.println(sb.deleteCharAt(sb.length() - 1));
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
