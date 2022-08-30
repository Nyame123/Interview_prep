package com.bis.interview_prep.FutureProblems;

import java.util.LinkedList;

/**
 * Given a list of words, determine whether the words can be
 * chained to form a circle. A word X can be placed in front of
 * another word Y in a circle if the last character of X is same as the first character of Y.
 * <p>
 * For example, the words ['chair', 'height', 'racket', touch', 'tunic']
 * can form the following circle: chair --> racket --> touch --> height --> tunic --> chair.
 **/
public class CanFormChain {

    public static void main(String[] args) {
        String[] words = {"abc", "efg", "cde", "ghi", "ija"};
        boolean canChain = canFormChain(words);
        System.out.println(canChain);
    }

    /**
     * 1. Group the words according to the starting and ending character.
     * 2. Go by each of the starting and ending list to search for better word to form a chain
     **/
    static boolean canFormChain(String[] words) {
        LinkedList<Word>[] starting = new LinkedList[26];

        for (String s : words) {
            char start = s.charAt(0);
            Word word = new Word(s, false, s.charAt(0), s.charAt(s.length() - 1));
            if (starting[start - 'a'] == null)
                starting[start - 'a'] = new LinkedList<>();
            starting[start - 'a'].add(word);
        }

        int end = words.length - 1, start = 0;
        Word cur = starting[words[end].charAt(start) - 'a'].iterator().next();
        return canForm(starting, cur, words[end], words.length - 1);
    }


    static boolean canForm(LinkedList<Word>[] starting, Word curWord, String startWord, int count) {
        char last = curWord.w.charAt(curWord.w.length() - 1);
        //base case
        if (count == 0) {
            return last == startWord.charAt(0);
        }

        if (starting[curWord.end - 'a'] == null) {
            return false;
        }
        curWord.visited = true;
        for (Word neigh : starting[curWord.end - 'a']) {
            if (!neigh.visited) {
                if (canForm(starting, neigh, startWord, count - 1)) {
                    return true;
                }
            }
        }

        curWord.visited = false;
        return false;
    }

    static class Word {
        String w;
        char start, end;
        boolean visited;

        Word(String word, boolean visited, char start, char end) {
            this.w = word;
            this.visited = visited;
            this.start = start;
            this.end = end;
        }
    }
}

//using Euler circuit graph theory concept
class CanFormChainUsingGraph1 {

    public static void main(String[] args) {
        //String[] words = {"abc", "efg", "cde", "ghi", "ija"};
        String[] words = {"aab", "abb"};
        boolean canChain = canFormChainGraph1(words);
        System.out.println(canChain);
    }


    static boolean canFormChainGraph1(String[] words) {
        //adjacent list
        LinkedList<Integer>[] graph = new LinkedList[26];

        int[] ins = new int[26];

        //initialize the graph
        for (int i = 0; i < 26; i++) {
            graph[i] = new LinkedList<>();
        }

        //create edges
        for (int i = 0; i < words.length; i++) {
            int start = words[i].charAt(0) - 'a';
            int end = words[i].charAt(words[i].length() - 1) - 'a';

            addEdge(graph, ins, start, end);
        }

        //check if a graph is Strongly connected components
        if (!isStronglyConnected(graph)) {
            return false;
        }

        for (int i = 0; i < 26; i++) {
            if (graph[i].size() != ins[i]) {
                return false;
            }
        }

        return true;
    }

    static void addEdge(LinkedList<Integer>[] graph, int[] ins, int u, int v) {
        graph[u].add(v);
        ins[v]++;
    }

    static boolean isStronglyConnected(LinkedList<Integer>[] graph) {
        boolean[] visited = new boolean[26];

        //find a vertex with more than at least 1 outgoing edge
        int v;
        for (v = 0; v < 26; v++) {
            if (graph[v].size() > 0) {
                break;
            }
        }

        //do DFS to help check if the graph vertices will be visited
        dfs(graph, v, visited);

        //check if all the vertices are visited
        for (int i = 0; i < 26; i++) {
            if (graph[i].size() > 0 && !visited[i]) {
                return false;
            }
        }

        //transpose the graph and do the same thing
        int[] transposeIns = new int[26];
        LinkedList<Integer>[] transposeGraph = transposeGraph(graph, transposeIns);
        //reset the visited array for transpose operation
        for (int i = 0; i < 26; i++) {
            visited[i] = false;
        }

        //find a vertex with more than at least 1 outgoing edge
        for (v = 0; v < 26; v++) {
            if (transposeGraph[v].size() > 0) {
                break;
            }
        }

        dfs(transposeGraph, v, visited);

        for (int i = 0; i < 26; i++) {
            if (transposeGraph[i].size() > 0 && !visited[i]) {
                return false;
            }
        }

        return true;
    }

    static void dfs(LinkedList<Integer>[] graph, int u, boolean[] visited) {

        visited[u] = true;

        for (int v : graph[u]) {
            if (!visited[v]) {
                dfs(graph, v, visited);
            }
        }
    }

    static LinkedList<Integer>[] transposeGraph(LinkedList<Integer>[] adj, int[] ins) {
        LinkedList<Integer>[] graph = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < 26; i++) {
            for (int v : adj[i]) {
                graph[v].add(i);
                ins[i]++;
            }
        }

        return graph;
    }
}

//Another simple approach in using Euler concept
class CanFormChainUsingGraph2 {

    public static void main(String[] args) {
        String[] words = {"abc", "efg", "cde", "ghi", "ija"};
//        String[] words = {"aab", "abb"};
        boolean canChain = canFormChainGraph2(words);
        System.out.println(canChain);
    }

    static boolean canFormChainGraph2(String[] words) {
//adjacent list
        LinkedList<Integer>[] graph = new LinkedList[26];

        int[] ins = new int[26];
        boolean[] mark = new boolean[26];

        //initialize the graph
        for (int i = 0; i < 26; i++) {
            graph[i] = new LinkedList<>();
        }

        //create edges
        for (int i = 0; i < words.length; i++) {
            int start = words[i].charAt(0) - 'a';
            int end = words[i].charAt(words[i].length() - 1) - 'a';

            addEdge(graph, ins, mark, start, end);
        }

        //check if a graph is Strongly connected components
        if (!isStronglyConnected(graph, mark)) {
            return false;
        }

        for (int i = 0; i < 26; i++) {
            if (graph[i].size() != ins[i]) {
                return false;
            }
        }

        return true;
    }

    static void addEdge(LinkedList<Integer>[] graph, int[] ins, boolean[] mark, int u, int v) {
        graph[u].add(v);
        ins[v]++;
        mark[u] = true;
        mark[v] = true;
    }

    static boolean isStronglyConnected(LinkedList<Integer>[] graph, boolean[] mark) {
        boolean[] visited = new boolean[26];

        //find a vertex with more than at least 1 outgoing edge
        int v;
        for (v = 0; v < 26; v++) {
            if (graph[v].size() > 0) {
                break;
            }
        }

        //do DFS to help check if the graph vertices will be visited
        dfs(graph, v, visited);

        for (int i = 0; i < 26; i++) {
            if (mark[i] && !visited[i]){
                return false;
            }
        }

        return true;
    }


    static void dfs(LinkedList<Integer>[] graph, int u, boolean[] visited) {

        visited[u] = true;

        for (int v : graph[u]) {
            if (!visited[v]) {
                dfs(graph, v, visited);
            }
        }
    }

}