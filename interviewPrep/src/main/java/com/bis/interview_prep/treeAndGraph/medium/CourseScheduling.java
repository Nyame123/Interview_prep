package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses -
 * 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you
 * must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0
 * you should also have finished course 1. So it is impossible.
 **/
public class CourseScheduling {

    final Integer UNVISITED = 0;
    final Integer VISITING = 1;
    final Integer VISITED = 2;

    public static void main(String[] args) {
        int[][] coursedIds = {
                //new int[]{1, 0},
                new int[]{0, 1}
        };

        CourseScheduling scheduling = new CourseScheduling();
        System.out.println(scheduling.canFinish(coursedIds.length, coursedIds));
    }


    //using Topological sorting
    //Time Complexity = O(V+E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        HashMap<Integer, List<Integer>> vertices = graphBuild(prerequisites);
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == UNVISITED) {
                if (!dfs(i, vertices, visited)) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean dfs(int u, HashMap<Integer, List<Integer>> vertices, int[] visited) {
        //process the course
        visited[u] = VISITING;


        if (vertices.containsKey(u)) {
            //check the dependants
            for (int v : vertices.get(u)) {
                if (visited[v] == UNVISITED) {
                    if (!dfs(v, vertices, visited)) {
                        return false;
                    }
                }

                if (visited[v] == VISITING) {
                    return false;
                }
            }
        }


        visited[u] = VISITED;
        //after processing all the dependents
        return true;
    }

    HashMap<Integer, List<Integer>> graphBuild(int[][] prerequisites) {

        int n = prerequisites.length;
        HashMap<Integer, List<Integer>> vertices = new HashMap<>();

        for (int[] arr : prerequisites) {
            int u = arr[1];
            int v = arr[0];
            vertices.putIfAbsent(u, new ArrayList<>());
            List<Integer> neighbours = vertices.get(u);
            neighbours.add(v);
        }

        return vertices;
    }
}
