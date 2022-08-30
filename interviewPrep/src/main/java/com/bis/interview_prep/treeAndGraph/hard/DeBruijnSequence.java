package com.bis.interview_prep.treeAndGraph.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given a set of characters C and an integer k, a De Bruijn sequence is a cyclic
 * sequence in which every possible k-length string of characters in C occurs exactly once.
 *
 * For example, suppose C = {0, 1} and k = 3. Then our sequence should contain the substrings
 * {'000', '001', '010', '011', '100', '101', '110', '111'}, and one possible solution would be 00010111.
 *
 * Create an algorithm that finds a De Bruijn sequence.
 **/
public class DeBruijnSequence {

    public static void main(String[] args) {
        int n = 2, k = 3;
        char[] C = {'0','1','2'};

        String deBruijnSeq = debruijnSequence(C,n,k);
        System.out.println(deBruijnSeq);
    }

    /**
     * In this problem, we want to generate sequence of length n, with items
     * from set 'c'. These n-length of item has to occur exactly once.
     * We decide to use graph theory for this case.
     **/
    static String debruijnSequence(char[] C, int n, int k) {
        String startingNode = compact(C[0],n-1);
        Set<String> visited = new HashSet<>();
        LinkedList<Integer> edges = new LinkedList<>();

        dfs(startingNode,k,C,visited,edges);

        StringBuilder sb = new StringBuilder();
        int length = (int) Math.pow(k,n);
        for (int i = 0; i < length; i++) {
            sb.append(edges.get(i));
        }

        sb.append(startingNode);
        return sb.toString();
    }

   static void dfs(String startingNode, int k, char[] C, Set<String> visited, LinkedList<Integer> edges){

        for (int i = 0; i < k; i++) {
            String s = startingNode + C[i];
            if (!visited.contains(s)){
                visited.add(s);
                dfs(s.substring(1),k,C,visited,edges);
                edges.add(i);
            }
        }
    }

    static String compact(char c, int n){
        StringBuilder sb  = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }

        return sb.toString();
    }
}
