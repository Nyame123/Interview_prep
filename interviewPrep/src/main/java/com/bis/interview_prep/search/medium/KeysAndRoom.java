package com.bis.interview_prep.search.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *This question is asked by Amazon. Given N distinct rooms that are locked we
 *  want to know if you can unlock and visit every room. Each room has a list of
 *  keys in it that allows you to unlock and visit other rooms. We start with room 0
 *  being unlocked. Return whether or not we can visit every room.
 *
 * Ex: Given the following rooms…
 *
 * rooms = [[1], [2], []], return true
 * Ex: Given the following rooms…
 *
 * rooms = [[1, 2], [2], [0], []], return false, we can’t enter room 3.
 **/
public class KeysAndRoom {

    public static void main(String[] args) {

        List<List<Integer>> arr = new ArrayList<>();
        arr.add(Arrays.asList(1));
        arr.add(Arrays.asList(2));
        arr.add(Arrays.asList());
        
        System.out.println(canVisitAllRooms(arr));
    }

    /**
     * With this problem, we can DFS technique to solve this problem.
     * We visit a room and picks the keys from the room and use them to open
     * other rooms.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        stack.push(0);
        visited[0] = true;
        int count = 0;
        while(!stack.isEmpty()){
            int room = stack.pop();
            count++;
            for(int r: rooms.get(room)){
                if(!visited[r]){
                    stack.push(r);
                    visited[r] = true;
                }
            }
        }

        return (count == n);
    }
}
