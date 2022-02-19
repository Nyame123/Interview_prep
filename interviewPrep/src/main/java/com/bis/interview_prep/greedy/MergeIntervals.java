package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * This problem was asked by Snapchat.
 * <p>
 * Given a list of possibly overlapping intervals, return a new list of intervals where all overlapping intervals have been merged.
 * <p>
 * The input list is not necessarily ordered in any way.
 * <p>
 * For example, given [(1, 3), (5, 8), (4, 10), (20, 25)], you should return [(1, 3), (4, 10), (20, 25)].
 **/
public class MergeIntervals {

    static Set<int[]> visited;
    static Map<Integer,LinkedList<int[]>> connectedComp;
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1, 3}, {5, 8}, {4, 10}, {20, 25}
        };

        int[][] res = runGraph(intervals);

        for (int i = 0; i < res.length; i++) {
            System.out.printf("(%d %d), ",res[i][0],res[i][1]);
        }
    }

    //Time Complexity = O(n^2)
    static int[][] runGraph(int[][] intervals){
        visited = new HashSet<>();
        connectedComp = new HashMap<>();
       return mergeUsingGraph(intervals);
    }

    static int[][] mergeUsingGraph(int[][] intervals){
        buildComponents(intervals,buildGraph(intervals));

        LinkedList<int[]> res = new LinkedList<>();
        for (int a: connectedComp.keySet()) {
            res.add(mergeConnectedComp(a));
        }

        return res.toArray(new int[res.size()][]);
    }

    static void buildComponents(int[][] intervals,HashMap<int[],List<int[]>> graph){
        int count = 0;

        for(int[] inter: intervals){
            if (!visited.contains(inter)){
                generateConnectedComponents(inter,graph,count);
                count++;
            }
        }
    }

    static void generateConnectedComponents(int[] start, HashMap<int[],List<int[]>> graph,int key){
        Deque<int[]> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()){
            int[] item = stack.pop();

            if (!visited.contains(item)) {
                visited.add(item);
                connectedComp.putIfAbsent(key, new LinkedList<>());
                connectedComp.get(key).add(item);


                for (int[] adj : graph.get(item)) {
                    stack.push(adj);
                }
            }
        }
    }

    static int[] mergeConnectedComp(int key){
        int[] merge = new int[2];
        LinkedList<int[]> connected = connectedComp.get(key);
        merge[0] = connected.get(0)[0];
        for(int[] a: connected){
            merge[0] = Math.min(merge[0],a[0]);
        }

        for(int[] a: connected){
            merge[1] = Math.max(merge[1],a[1]);
        }

        return merge;
    }

    static HashMap<int[],List<int[]>> buildGraph(int[][] intervals){

        HashMap<int[],List<int[]>> graph = new HashMap<>();

        for(int[] interval: intervals){
           for(int[] inter: intervals){
               if (canMerge(inter,interval)){
                   graph.putIfAbsent(inter,new ArrayList<>());
                   graph.putIfAbsent(interval,new ArrayList<>());
                   graph.get(inter).add(interval);
                   graph.get(interval).add(inter);
               }

           }

        }

        return graph;
    }

    static boolean canMerge(int[] a, int[] b){
        return a[0] <= b[1] && b[0] <= a[1];
    }

    //Time Complexity = O(nLogn)
    //Space Complexity = O(n)
    static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        List<int[]> mergeArr = new ArrayList<>();
        mergeArr.add(intervals[0]);
        int n = intervals.length;
        int len = 0;
        for (int i = 1; i < n; i++) {
            int[] slot = intervals[i];
            len = mergeArr.size();
            int[] prev = mergeArr.get(len - 1);
            //check if current interval can stand on it own
            if (prev[1] < slot[0]) {
                mergeArr.add(slot);
            } else if (prev[1] >= slot[1]) {
                continue;
            } else {
                prev[1] = slot[1];
                //mergeArr.add(prev)
            }

        }


        int[][] res = new int[mergeArr.size()][2];
        for (int i = 0; i < mergeArr.size(); i++) {
            res[i] = mergeArr.get(i);
        }

        return res;
    }
}
