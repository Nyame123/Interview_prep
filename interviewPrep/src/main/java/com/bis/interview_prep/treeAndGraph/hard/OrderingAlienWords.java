package com.bis.interview_prep.treeAndGraph.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You come across a dictionary of sorted words in a language you've never seen before.
 * Write a program that returns the correct order of letters in this language.
 * <p>
 * For example, given ['xww', 'wxyz', 'wxyw', 'ywx', 'ywz'],
 * you should return ['x', 'z', 'w', 'y'].
 **/
public class OrderingAlienWords {

    /**
     * We use topological sort for this problem.
     * 1. We build a graph using Adjacency-List
     * 2. Apply topoligical sort on this to get the order.
     * <p>
     * Time Complexity = O(S*N)
     **/
    static final int UNVISITED = 0;
    static final int VISITING = 1;
    static final int VISITED = 2;

    public static void main(String[] args) {
        //String[] words = {"xww", "wxyz", "wxyw", "ywx", "ywz"};
        String[] words = {"aba", "bba", "aaa"};
        List<Character> order = findOrdering(words);
        System.out.println(order);
    }

    static List<Character> findOrdering(String[] words) {

        Set<Character> existingChars = new HashSet<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                existingChars.add(words[i].charAt(j));
            }
        }

        if (existingChars.size() > words.length) {
            return new ArrayList<>();
        }

        HashMap<Character, List<Character>> graph = buildGraph(words);

        return topSort(existingChars, graph);
    }

    static HashMap<Character, List<Character>> buildGraph(String[] words) {
        HashMap<Character, List<Character>> adjGraph = new HashMap<>();
        int n = words.length;

        for (int i = 0; i < n - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int j = 0, k = 0;
            while (j < word1.length() && k < word2.length()) {
                if (word1.charAt(j) == word2.charAt(k)) {
                    j++;
                    k++;
                } else {
                    adjGraph.putIfAbsent(word1.charAt(j), new ArrayList<>());
                    adjGraph.get(word1.charAt(j)).add(word2.charAt(k));
                    break;
                }
            }
        }

        return adjGraph;
    }

    static List<Character> topSort(Set<Character> vertices, HashMap<Character, List<Character>> graph) {

        Deque<Character> stack = new ArrayDeque<>();
        HashMap<Character, Integer> verticeState = new HashMap<>();
        for (Character c : vertices) {
            verticeState.put(c, UNVISITED);
        }

        for (Character c : vertices) {
            if (verticeState.get(c) != VISITED)
                if (!dfs(verticeState, c, graph, stack)) {
                    return new ArrayList<>();
                }
        }

        return new ArrayList<>(stack);
    }

    private static boolean dfs(HashMap<Character, Integer> verticeState, Character c,
                               HashMap<Character, List<Character>> graph, Deque<Character> stack) {
        verticeState.put(c, VISITING);
        List<Character> neighbors = graph.get(c);
        if (neighbors != null) {
            for (char neigh : neighbors) {
                if (verticeState.get(neigh) == VISITING) {
                    return false;
                }

                if (verticeState.get(neigh) != VISITED)
                    if (!dfs(verticeState, neigh, graph, stack)){
                        return false;
                    }
            }
        }

        verticeState.put(c, VISITED);
        stack.push(c);

        return true;
    }


}
