package com.bis.interview_prep.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 *Given an N by M matrix consisting only of 1's and 0's, find the largest rectangle containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * [[1, 0, 0, 0],
 *  [1, 0, 1, 1],
 *  [1, 0, 1, 1],
 *  [0, 1, 0, 0]]
 *
 *  Return 4.
 **/
public class LargestRectangleWithOnes {
    public static void main(String[] args) {
        int[][] mat = {
                new int[]{1, 0, 0, 0},
                new int[]{1, 0, 1, 1},
                new int[]{1, 0, 1, 1},
                new int[]{0, 1, 0, 0},
        };

        maxSquareSize(mat);
        int largestRec = largestRecWithOnes(mat);
        System.out.println(largestRec);

    }

    //maximum sized square matrix
    static void maxSquareSize(int[][] mat) {
        int n = mat.length;
        int max = largestSize(mat[0]);
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] += mat[i - 1][j];
            }

            int area = largestSize(mat[i]);
            max = Math.max(area, max);
        }

        System.out.println(max);
    }

    static int largestSize(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        int max = 0;
        while (i < n) {
            if (stack.isEmpty() || arr[i] >= arr[stack.peek()]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                int area = arr[top] * ((stack.isEmpty()) ? i : i - stack.peek()-1);
                max = Math.max(area, max);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = arr[top] * ((stack.isEmpty()) ? i : i - stack.peek()-1);
            max = Math.max(area, max);
        }

        return max;
    }



    /**
     * In this solution, we intend to use Stacks for this problem.
     * 1. Push into the stack when the current bar value is greater than or equal to the stack top value.
     * 2. Else, we pop from the stack and calculate the max area from there.
     *  Area = bar[top] * (len - stackTop - 1) when the stack is not empty
     *  Else Area = bar[top] * len;
     *
     *  Time Complexity =O(N)
     *  Space Complexity =O(N)
     **/
    static int largestAreaHistogram(int[] histogram) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = histogram.length;
        int len = 0;
        while (len < n){
            if (stack.isEmpty() || histogram[stack.peek()] <= histogram[len]){
                stack.push(len++);
            }else{
                int top = stack.pop();
                int area = histogram[top] * (stack.isEmpty()? len : (len - stack.peek() - 1));
                maxArea = Math.max(area,maxArea);
            }
        }

        while (!stack.isEmpty()){
            int top = stack.pop();
            int area = histogram[top] * (stack.isEmpty()? len : (len - stack.peek() - 1));
            maxArea = Math.max(area,maxArea);
        }

        return maxArea;
    }

    /**
     * We can use the idea of calculating max area under histogram.
     * 1. We use histogram area algo for each prefix sum row.
     *
     * Time Complexity = O(R*C)
     **/
    static int largestRecWithOnes(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        //for the first row
        int max = largestAreaHistogram(mat[0]);
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1){
                    mat[i][j] +=  mat[i-1][j];
                }
            }

            max = Math.max(max,largestAreaHistogram(mat[i]));
        }

        return max;
    }
}
