package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * One way to unlock an Android phone is through a pattern of swipes across a 1-9 keypad.
 * <p>
 * For a pattern to be valid, it must satisfy the following:
 * <p>
 * All of its keys must be distinct.
 * It must not connect two keys by jumping over a third key, unless that key has already been used.
 * For example, 4 - 2 - 1 - 7 is a valid pattern, whereas 2 - 1 - 7 is not.
 * <p>
 * Find the total number of valid unlock patterns of length N, where 1 <= N <= 9.
 **/
public class KeyboardValidPattern {

    public static void main(String[] args) {
        int min = 2, max = 2;
        int validPattern = waysOfConnect(min, max);
        System.out.println(validPattern);
    }

    /**
     * This problem can be solved by using DFS.
     * We have two basic conditions:
     * a. All of its keys must be distinct.
     * b. It must not connect two keys by jumping over a third key, unless that key.
     * 1. From the keyboard, there are 9 keys and some keys gives the same ways of valid pattern
     * 2. Keys, {1,3,7,9}, {2,4,6,8} and {5}.
     * 3. We can check the valid keyboard pattern from min to max length.
     *
     * Time Complexity = O((max-min)*10^max)
     **/
    static int waysOfConnect(int min, int max) {
        int ways = 0;
        int KEYS = 10;
        boolean[] visited = new boolean[KEYS];
        int[][] jump = new int[KEYS][KEYS];
        //2 lies between 1 and 3
        jump[1][3] = jump[3][1] = 2;
        //4 lies between 1 and 7
        jump[1][7] = jump[7][1] = 4;
        //6 lies between 3 and 9
        jump[3][9] = jump[9][3] = 6;
        //8 lies between 7 and 9
        jump[7][9] = jump[9][7] = 8;
        //5 lies between 1 and 9, 3 and 7, 2 and 8, 4 and 6
        jump[1][9] = jump[9][1] = 5;
        jump[3][7] = jump[7][3] = 5;
        jump[2][8] = jump[8][2] = 5;
        jump[4][6] = jump[6][4] = 5;
        for (int i = min; i <= max; i++) {
            //for group, {1,3,7,9}
            ways += 4 * dfs(jump,visited,1,i-1);
            //for group, {2,4,6,8}
            ways += 4 * dfs(jump,visited,2,i-1);
            //for 5
            ways += dfs(jump,visited,5,i-1);
        }
        return ways;
    }

    static int dfs(int[][] jump, boolean[] visited, int cur, int len) {
        //base case
        if (len <= 0)
            return len == 0 ? 1 : 0;

        //visited key in the current pattern
        visited[cur] = true;

        int ways = 0;
        for (int i = 1; i < 10; i++) {
            if (!visited[i] && (jump[i][cur] == 0 || visited[jump[i][cur]]))
                ways += dfs(jump, visited, i, len - 1);
        }

        visited[cur] = false;
        return ways;
    }
}
