package com.bis.interview_prep.greedy;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Minimum Cost Path with Left, Right, Bottom and Up moves allowed
 * Difficulty Level : Hard
 * Last Updated : 11 Nov, 2021
 * Given a two dimensional grid, each cell of which contains integer cost which represents a cost
 * to traverse through that cell, we need to find a path from top left cell to bottom right cell by which total cost incurred is minimum.
 * Note : It is assumed that negative cost cycles do not exist in input matrix.
 * This problem is extension of below problem.
 * <p>
 * A cost grid is given in below diagram, minimum
 * cost to reach bottom right from top left
 * is 327 (= 31 + 10 + 13 + 47 + 65 + 12 + 18 +
 * 6 + 33 + 11 + 20 + 41 + 20)
 **/
public class MinimumCostPathAllDirection {

    public static void main(String[] args) {
        int[][] grid = {{31, 100, 65, 12, 18},
                {10, 13, 47, 157, 6},
                {100, 113, 174, 11, 33},
                {88, 124, 41, 20, 140},
                {99, 32, 111, 41, 20}};

        System.out.println(shortestPath(grid, grid.length, grid[0].length));
    }

    static boolean isValid(int r, int c,int r1, int c1){
        return c1 >= 0 && c1 < c && r1 >= 0 && r1 < r;
    }

    static int shortestPath(int[][] grid,int row,int col){

        //fill the distance of each cell with infinity
        int[][] distance = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        //four direction array
        //top, left, bottom, right
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        //create a priorityQueue
        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(new DistanceComparator());

        //start from the first cell in the grid top left corner
        distance[0][0] = grid[0][0];
        priorityQueue.add(new Cell(0,0,grid[0][0]));
        while (!priorityQueue.isEmpty()){
            Cell cell = priorityQueue.poll();

            //visit the four neigbouring directions
            for (int i = 0; i < 4; i++) {
                int row1 = cell.x + dx[i];
                int col1 = cell.y + dy[i];

                if (isValid(row,col,row1,col1)){
                    Cell child = new Cell(row1,col1,distance[row1][col1]);
                    //if already computed and stored in priority queue
                    if (distance[row1][col1] != Integer.MAX_VALUE){
                        priorityQueue.remove(child);
                    }

                    //update the distance of the neighbouring cell if the new distance is smaller
                    if (distance[row1][col1] > cell.distance + grid[row1][col1]){
                        distance[row1][col1] = cell.distance + grid[row1][col1];
                        Cell cell1 = new Cell(row1,col1,distance[row1][col1]);
                        priorityQueue.add(cell1);
                    }

                }

            }
        }

        return distance[row-1][col-1];

    }

    static class DistanceComparator implements Comparator<Cell>{
        @Override
        public int compare(Cell cell, Cell t1) {
            return Integer.compare(cell.distance,t1.distance);
        }
    }

    static class Cell{
        int x;
        int y;
        int distance;

        public Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}

class GFG{

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int ROW = 5;
    static int COL = 5;

    // Custom class for representing
// row-index, column-index &
// distance of each cell
    static class Cell
    {
        int x;
        int y;
        int distance;

        Cell(int x, int y, int distance)
        {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    // Custom comparator for inserting cells
// into Priority Queue
    static class distanceComparator
            implements Comparator<Cell>
    {
        public int compare(Cell a, Cell b)
        {
            if (a.distance < b.distance)
            {
                return -1;
            }
            else if (a.distance > b.distance)
            {
                return 1;
            }
            else {return 0;}
        }
    }

    // Utility method to check whether current
// cell is inside grid or not
    static boolean isInsideGrid(int i, int j)
    {
        return (i >= 0 && i < ROW &&
                j >= 0 && j < COL);
    }

    // Method to return shortest path from
// top-corner to bottom-corner in 2D grid
    static int shortestPath(int[][] grid, int row,
                            int col)
    {
        int[][] dist = new int[row][col];

        // Initializing distance array by INT_MAX
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        // Initialized source distance as
        // initial grid position value
        dist[0][0] = grid[0][0];

        PriorityQueue<Cell> pq = new PriorityQueue<Cell>(
                row * col, new distanceComparator());

        // Insert source cell to priority queue
        pq.add(new Cell(0, 0, dist[0][0]));
        while (!pq.isEmpty())
        {
            Cell curr = pq.poll();
            for(int i = 0; i < 4; i++)
            {
                int rows = curr.x + dx[i];
                int cols = curr.y + dy[i];

                if (isInsideGrid(rows, cols))
                {
                    if (dist[rows][cols] >
                            dist[curr.x][curr.y] +
                                    grid[rows][cols])
                    {

                        // If Cell is already been reached once,
                        // remove it from priority queue
                        if (dist[rows][cols] != Integer.MAX_VALUE)
                        {
                            Cell adj = new Cell(rows, cols,
                                    dist[rows][cols]);

                            pq.remove(adj);
                        }

                        // Insert cell with updated distance
                        dist[rows][cols] = dist[curr.x][curr.y] +
                                grid[rows][cols];

                        pq.add(new Cell(rows, cols,
                                dist[rows][cols]));
                    }
                }
            }
        }
        return dist[row - 1][col - 1];
    }

    // Driver code
    public static void main(String[] args)
            throws IOException
    {
        int[][] grid = { { 31, 100, 65, 12, 18 },
                { 10, 13, 47, 157, 6 },
                { 100, 113, 174, 11, 33 },
                { 88, 124, 41, 20, 140 },
                { 99, 32, 111, 41, 20 } };

        System.out.println(shortestPath(grid, ROW, COL));
    }
}

