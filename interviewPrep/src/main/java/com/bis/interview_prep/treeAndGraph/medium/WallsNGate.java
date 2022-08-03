package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * You are given an m x n grid rooms initialized with these three possible values.
 * <p>
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647
 * to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * Example 1:
 * <p>
 * <p>
 * Input: rooms = [[2147483647,-1,0,2147483647],
 * [2147483647,2147483647,2147483647,-1],
 * [2147483647,-1,2147483647,-1],
 * [0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * Example 2:
 * <p>
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 * Constraints:
 * <p>
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 231 - 1.
 **/
public class WallsNGate {

    /**
     * 0 -> Gate,
     * -1 -> Wall,
     * INF -> Empty Room
     * <p>
     * 1. We try to locate the GATE.
     * 2. We use BFS to fill the adjacent cells or empty rooms
     **/
    static final int GATE = 0;
    static final int WALL = -1;
    static final int EMPTY_ROOM = 2147483647;
    static final int INF = 2147483647;
    //Directions to be used
    //Left, Right, Down, and Top
    static final int[] x = {-1, 1, 0, 0};
    static final int[] y = {0, 0, 1, -1};

    public static void main(String[] args) {
        int[][] grid1 = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };
        int[][] grid = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };
        wallsAndGates(grid);
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;

        Queue<Cell> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == GATE) {
                    Cell cell = new Cell();
                    cell.col = j;
                    cell.row = i;
                    cell.distance = 0;
                    queue.add(cell);
                }
            }
        }

        bfs(rooms, queue);
    }

    static void bfs(int[][] rooms, Queue<Cell> queue) {

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            //move in all four directions or neighbours
            for (int k = 0; k < 4; k++) {
                //static final int[] x = {-1, 1, 0, 0};
                //static final int[] y = {0, 0, 1, -1};
                int newRow = current.row + y[k];
                int newCol = current.col + x[k];
                if (isValidCell(rooms, newRow, newCol)) {
                    int neighbourRoom = rooms[newRow][newCol];
                    if (neighbourRoom != WALL && neighbourRoom != GATE) {
                        if (rooms[newRow][newCol] == INF) {
                            Cell neighbour = new Cell();
                            neighbour.row = newRow;
                            neighbour.col = newCol;
                            neighbour.distance = current.distance + 1;
                            rooms[newRow][newCol] = current.distance + 1;
                            queue.add(neighbour);
                        } else {
                            int oldDistance = rooms[newRow][newCol];
                            if (oldDistance > current.distance + 1) {
                                Cell neighbour = new Cell();
                                neighbour.row = newRow;
                                neighbour.col = newCol;
                                neighbour.distance = current.distance + 1;
                                rooms[newRow][newCol] = current.distance + 1;
                                queue.add(neighbour);
                            }
                        }
                    }
                }
            }
        }

    }

    static boolean isValidCell(int[][] rooms, int row, int col) {
        return row >= 0 && row < rooms.length && col >= 0 && col < rooms[0].length;
    }

    static class Cell {
        int row, col, distance;
    }
}
