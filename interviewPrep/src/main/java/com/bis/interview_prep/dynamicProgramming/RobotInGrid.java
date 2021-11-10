package com.bis.interview_prep.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A robot is sitting at the upper left corner of a grid with r rows and columns c
 * and can move in only two directions. The directions are down and right only.
 * Find the paths of the robot from the left top corner to the bottom right corner.
 * The constraint is that there are some off-limits in the grid that the robot cannot pass.
 **/
public class RobotInGrid {

    public static void main(String[] args) {

        boolean[][] maze = {
                new boolean[]{true, true, true,false,true,false},
                new boolean[]{false, true, true,true,false,false},
                new boolean[]{false, true, true,false,false,true},
                new boolean[]{false, true, true,false,true,false},
                new boolean[]{false, true, true,false,true,false},
                new boolean[]{false, true, true,true,false,true},
                new boolean[]{false, false, true,true,true,true},
                new boolean[]{false, true, true,false,true,true},
                new boolean[]{false, false, true,false,true,true},
                new boolean[]{false, true, true,false,true,true},
        };

        int row = maze.length;
        int col = maze[0].length;
        Point start = new Point(0, 0);
        Point end = new Point(row-1 , col-1);

        List<Point> points = getPath(maze, start, end,new HashMap<>());
//        List<Point> points = getPath(maze, start, end);

        for (Point p : points) {
            System.out.println(p);
        }

    }

    //with memoization
    //Time Complexity = O(r+c) where r = row and c = col
    //Space Complexity = O(r+c)
    private static List<Point> getPath(boolean[][] maze, Point start, Point end, HashMap<String,List<Point>> memo) {
        //base case
        if (maze == null || maze.length == 0){
            return null;
        }
        if (memo.containsKey(start.toString())){
            return memo.get(start.toString());
        }

        if (start.col == end.col && start.row == end.row) {
            List<Point> points = new ArrayList<>();
            points.add(end);
            return points;
        }

        //when the coordinates are out of bounds
        if (start.row >= maze.length || start.col >= maze[0].length || !maze[start.row][start.col]) {
            memo.put(start.toString(),null);
            return null;
        }


        List<Point> pointRight = getPath(maze, new Point(start.row, start.col + 1), end,memo);
        List<Point> pointDown = getPath(maze, new Point(start.row + 1, start.col), end,memo);

        if (pointRight == null && pointDown == null) {
            memo.put(start.toString(), null);
            return null;
        }else if (pointDown != null) {
            pointDown.add(start);
            memo.put(start.toString(),pointDown);
            return pointDown;
        } else {
            pointRight.add(start);
            memo.put(start.toString(),pointRight);
            return pointRight;
        }
    }


    //Time Complexity = O(2^r+c) where r = row and c = col
    //Space Complexity = O(r+c)
    private static List<Point> getPath(boolean[][] maze, Point start, Point end) {
        //base case
        if (maze == null || maze.length == 0){
            return null;
        }
        if (start.col == end.col && start.row == end.row) {
            List<Point> points = new ArrayList<>();
            points.add(end);
            return points;
        }

        //when the coordinates are out of bounds
        if (start.row >= maze.length || start.col >= maze[0].length || !maze[start.row][start.col]) {
            return null;
        }


        List<Point> pointRight = getPath(maze, new Point(start.row, start.col + 1), end);
        List<Point> pointDown = getPath(maze, new Point(start.row + 1, start.col), end);

        if (pointRight == null && pointDown == null)
            return null;
        else if (pointDown != null) {
            pointDown.add(start);
            return pointDown;
        } else {
            pointRight.add(start);
            return pointRight;
        }
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
}
