package com.bis.interview_prep.search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an M x N matrix in which each row and each column is sorted in
 * ascending order, write a method to find an element.
 **/
public class SortedMatrixSearch {

    public static void main(String[] args) {
        int[][] sortedMatrix = {
                new int[]{1, 2, 3, 4, 5},
                new int[]{6, 9, 13, 14, 15},
                new int[]{18, 21, 31, 41, 45},
                new int[]{45, 52, 53, 54, 55}
        };

        int[] indices = findElem(sortedMatrix, 14);
        System.out.println(indices[0] + "," + indices[1]);
    }

    static int[] findElem(int[][] sortedMatrix, int element) {
        int row = 0;
        int col = sortedMatrix[0].length - 1;
        while (row < sortedMatrix.length && col >= 0) {

            if (sortedMatrix[row][col] == element) {
                return new int[]{row, col};
            } else if (sortedMatrix[row][col] > element) {
                col--;
            } else {
                row++;
            }
        }

        return new int[]{-1, -1};
    }

    //since the matrix is sorted, we can use binary search
    static int[] findElement(int[][] sortedMatrix, int element) {


        int[] indices = binarySearch(sortedMatrix, 0, sortedMatrix.length - 1, element);
        return indices;
    }

    private static int[] binarySearch(int[][] sortedMatrix, int low, int high, int element) {
        int[] indices = {-1, -1};

        while (low <= high) {
            int midRow = low + (high - low) / 2;
            //check the possible row for the element
            int[] row = sortedMatrix[midRow];
            if (row[0] <= element && element <= row[row.length - 1]) {
                //another binary search here
                return binarySearchRow(row, 0, row.length - 1, element, midRow);
            } else if (element < row[0]) {
                high = midRow - 1;
            } else {
                low = midRow + 1;
            }
        }

        return indices;
    }

    static int[] binarySearchRow(int[] row, int low, int high, int element, int midRow) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //check the possible row for the element
            if (row[mid] == element) {
                return new int[]{midRow, mid};
            } else if (element < row[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{midRow, -1};
    }
}

class SortedMatrixSearchQuadrant {

    public static void main(String[] args) {
        int[][] sortedMatrix = {
                new int[]{15, 20, 40, 85},
                new int[]{20, 35, 80, 95},
                new int[]{30, 55, 95, 105},
                new int[]{40, 80, 100, 120},
                new int[]{56, 91, 116, 168},
                new int[]{81, 110, 120, 179},
        };

        int[] indices = findElement(sortedMatrix, 85);
        System.out.println(indices[0] + "," + indices[1]);
    }

    //Time Complexity = O(logn)
    static int[] binarySearch(int[][] mat, Quadrant sledge, int searched) {


        if (sledge.maxCol - sledge.minCol == 0) {
            int low = sledge.minRow;
            int high = sledge.maxRow;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (searched == mat[mid][sledge.minCol])
                    return new int[]{mid, sledge.minCol};
                else if (searched < mat[mid][sledge.minCol]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        } else {
            int low = sledge.minCol;
            int high = sledge.maxCol;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (searched == mat[sledge.minRow][mid])
                    return new int[]{sledge.minRow, mid};
                else if (searched < mat[sledge.minRow][mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return new int[]{-1, -1};
    }

    //Time Complexity = O(logn)
    //Space Complexity = O(logn)
    private static int[] findElement(int[][] sortedMatrix, int searched) {

        //create a stack to keep the quadrants
        Deque<Quadrant> quadrants = new ArrayDeque<>();
        //create the first quadrant
        Quadrant root = new Quadrant(0, sortedMatrix.length - 1, 0, sortedMatrix[0].length - 1);
        quadrants.addFirst(root);

        while (!quadrants.isEmpty()) {
            Quadrant mainQuadrant = quadrants.pop();
            //get the max length of col and row and slash into two
            int rowDistance = mainQuadrant.maxRow - mainQuadrant.minRow;
            int colDistance = mainQuadrant.maxCol - mainQuadrant.minCol;
            int maxLength = Math.max(rowDistance, colDistance);
            int pivot = maxLength / 2;
            int colPivot = pivot + mainQuadrant.minCol;
            int rowPivot = pivot + mainQuadrant.minRow;

            //return if the row or col distance is zero
            if (rowDistance == 0 || colDistance == 0) {
                if (searched >= sortedMatrix[mainQuadrant.minRow][mainQuadrant.minCol]
                        && (searched <= sortedMatrix[mainQuadrant.maxRow][mainQuadrant.maxCol])) {
                    int[] result = binarySearch(sortedMatrix, mainQuadrant, searched);
                    if (result[0] == -1)
                        continue;
                    else
                        return result;
                } else
                    continue;
            }


            if ((colPivot >= 0 && colPivot < sortedMatrix[0].length) &&
                    (rowPivot >= 0 && rowPivot < sortedMatrix.length)) {

                //compare the pivot element with searched element
                if (searched == sortedMatrix[rowPivot][colPivot]) {
                    return new int[]{rowPivot, colPivot};
                } else if (searched < sortedMatrix[rowPivot][colPivot]) {
                    //discard all the quadrants and add the first half
                    Quadrant childQuadrant = new Quadrant(mainQuadrant.minRow, rowPivot, mainQuadrant.minCol, colPivot);
                    quadrants.addFirst(childQuadrant);

                    //upper right quadrant
                    Quadrant upperRightQuadrant = new Quadrant(mainQuadrant.minRow, rowPivot, colPivot + 1, mainQuadrant.maxCol);
                    if (sortedMatrix[upperRightQuadrant.minRow][upperRightQuadrant.minCol] <= searched)
                        quadrants.addFirst(upperRightQuadrant);
                } else {
                    //discard the first half and add all the other three valid quadrants
                    //upper right quadrant
                    Quadrant upperRightQuadrant = new Quadrant(mainQuadrant.minRow, rowPivot, colPivot + 1, mainQuadrant.maxCol);
                    if (sortedMatrix[upperRightQuadrant.minRow][upperRightQuadrant.minCol] <= searched)
                        quadrants.addFirst(upperRightQuadrant);

                    //bottom left quadrants
                    Quadrant bottomLeftQuadrant = new Quadrant(rowPivot + 1, mainQuadrant.maxRow, mainQuadrant.minCol, colPivot);
                    if (sortedMatrix[bottomLeftQuadrant.minRow][bottomLeftQuadrant.minCol] <= searched)
                        quadrants.addFirst(bottomLeftQuadrant);

                    //bottom right quadrants
                    Quadrant bottomRightQuadrant = new Quadrant(rowPivot + 1, mainQuadrant.maxRow, colPivot + 1, mainQuadrant.maxCol);
                    if (sortedMatrix[bottomRightQuadrant.minRow][bottomRightQuadrant.minCol] <= searched)
                        quadrants.addFirst(bottomRightQuadrant);

                }
            }
        }

        return new int[]{-1, -1};
    }

    static class Quadrant {
        int minRow;
        int maxRow;
        int minCol;
        int maxCol;

        public Quadrant(int minRow, int maxRow, int minCol, int maxCol) {
            this.minRow = minRow;
            this.maxRow = maxRow;
            this.minCol = minCol;
            this.maxCol = maxCol;
        }
    }
}
