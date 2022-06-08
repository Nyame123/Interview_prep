package com.bis.interview_prep.geometry;
/**
 * Given two rectangles on a 2D graph, return the area of their intersection. If the rectangles don't intersect, return 0.
 *
 * For example, given the following rectangles:
 *
 * {
 *     "top_left": (1, 4),
 *     "dimensions": (3, 3) # width, height
 * }
 * and
 *
 * {
 *     "top_left": (0, 5),
 *     "dimensions": (4, 3) # width, height
 * }
 * return 6.
 **/
public class AreaRectanglesIntersecting {

    public static void main(String[] args) {
        int[] topLeft1 = {1,4}, dimension1 = {3,3};
        int[] topLeft2 = {0,5}, dimension2 = {4,3};

        double areaIntersecting = areaOfRectangleIntersecting(topLeft1,dimension1,topLeft2,dimension2);
        System.out.println(areaIntersecting);
    }

    /**
     * We can use min-max to calculate the length and breadth of the intersecting
     * rectangles.
     * We have to check if the rectangles intersect.
     **/
    private static double areaOfRectangleIntersecting(int[] topLeft1, int[] dimension1, int[] topLeft2, int[] dimension2) {
        //preprocessing the data
        Rectangle rectangle1 = new Rectangle();
        rectangle1.coord1 = new int[]{topLeft1[0],topLeft1[1]-dimension1[1]};
        rectangle1.coord2 = new int[]{topLeft1[0]+dimension1[0],topLeft1[1]};

        Rectangle rectangle2 = new Rectangle();
        rectangle2.coord1 = new int[]{topLeft2[0],topLeft2[1]-dimension2[1]};
        rectangle2.coord2 = new int[]{topLeft2[0]+dimension2[0],topLeft2[1]};

        //check if the rectangles intersect on the x-axis
        if (rectangle1.coord2[0] < rectangle2.coord1[0] || rectangle1.coord1[0] > rectangle2.coord2[0]){
            return 0;
        }

        //check if the rectangles intersect on the y-axis
        if (rectangle1.coord2[1] < rectangle2.coord1[1] || rectangle1.coord1[1] > rectangle2.coord2[1]){
            return 0;
        }

        //they are intersecting so calculate the area
        int minX = Math.min(rectangle1.coord2[0],rectangle2.coord2[0]);
        int maxX = Math.max(rectangle1.coord1[0],rectangle2.coord1[0]);

        int minY = Math.min(rectangle1.coord2[1],rectangle2.coord2[1]);
        int maxY = Math.max(rectangle1.coord1[1],rectangle2.coord1[1]);

        int width = maxX - minX;
        int height = maxY - minY;

        return width*height;
    }

    static class Rectangle {
        int[] coord1;
        int[] coord2;
    }
}
