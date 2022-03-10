package com.bis.interview_prep.treeAndGraph.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *This problem was asked by Uber.
 *
 * A rule looks like this:
 *
 * A NE B
 *
 * This means this means point A is located northeast of point B.
 *
 * A SW C
 *
 * means that point A is southwest of C.
 *
 * Given a list of rules, check if the sum of the rules validate. For example:
 *
 * A N B
 * B NE C
 * C N A
 * does not validate, since A cannot be both north and south of C.
 *
 * A NW B
 * A N B
 * is considered valid.
 **/
public class DirectionAndPositionRules {

    final static int N = 0;
    final static int E = 1;
    final static int S = 2;
    final static int W = 3;

    static int[] dirs = {N,E,S,W};
    static Map<Character,Integer> charToDirMap = new HashMap<>();
    static {
        charToDirMap.put('N',N);
        charToDirMap.put('E',E);
        charToDirMap.put('S',S);
        charToDirMap.put('W',W);
    }
    static class VertNode{
        int val;
        List<Set<VertNode>> edges = new ArrayList<>();

        VertNode (int data){
            this.val = data;
            for (int i = 0; i < 4; i++) {
                edges.add(new HashSet<>());
            }
        }
    }
    public static void main(String[] args) {

        test1();
    }

    private static void test1() {
        String[] rules = {
                "A N B",
                "C SE B",
                "C N A"
        };
        System.out.println(validate(rules));
    }

    private static void test2() {
        String[] rules = {"A NW B",
                "A N B"};
        System.out.println(validate(rules));
    }
    private static void test3() {
        String[] rules = {"A N B",
                "C N B"};
        System.out.println(validate(rules));
    }

    private static boolean validate(String[] rules) {
        int n = rules.length;
        Map<Character,VertNode> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String rule = rules[i];
            String[] comp = rule.split(" ");
            char fromVertex = comp[2].charAt(0);
            char toVertex = comp[0].charAt(0);

            if (!graph.containsKey(toVertex)){
                graph.put(toVertex,new VertNode(toVertex));
            }

            if (!graph.containsKey(fromVertex)){
                graph.put(fromVertex,new VertNode(fromVertex));
            }
            VertNode fromNode = graph.get(fromVertex);
            VertNode toNode = graph.get(toVertex);

            for(char c: comp[1].toCharArray()){
                int dir = charToDirMap.get(c);
                if (!isRuleValid(fromNode,toNode,dir)){
                    return false;
                }

                //add the edges
                addEdges(fromNode,toNode,dir);
            }
        }
        return true;
    }

    static void addEdges(VertNode fromNode, VertNode toNode,int newDir){
        int oppositeDir = oppositeEdge(newDir);
        
        fromNode.edges.get(newDir).add(toNode);
        toNode.edges.get(oppositeDir).add(fromNode);

        for (VertNode neighbour: fromNode.edges.get(newDir)) {

            //ignore if the neighbour is the same as the toNode
            if (neighbour == toNode){
                continue;
            }

            neighbour.edges.get(newDir).add(toNode);
            toNode.edges.get(oppositeDir).add(neighbour);
        }
    }
    
    static boolean isRuleValid(VertNode fromVert,VertNode toVert,int newDir){
        int oppositeDir = oppositeEdge(newDir);

        if (fromVert.edges.get(oppositeDir).contains(toVert)){
            return false;
        }

        return true;
    }

    static int oppositeEdge(int newDir){
        return (newDir+2) % 4;
    }
}
