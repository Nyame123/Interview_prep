package com.bis.interview_prep.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Find the largest rectangular area possible in a given histogram where the largest
 * rectangle can be made of a number of contiguous bars. For simplicity, assume
 * that all bars have same width and the width is 1 unit.
 * For example, consider the following histogram with 7 bars of heights {6, 2, 5, 4, 5, 1, 6}.
 * The largest possible rectangle possible is 12 (see the below figure, the max area rectangle is highlighted in red)
 **/
public class LargestRectangularAreaHistogram {

    public static void main(String[] args) {
        int[] histogram = {6, 2, 5, 4, 5, 1, 6};
        //int[] histogram = {6, 2};
        largestRectangularArea(histogram);
        int maxArea = largestAreaHistogram(histogram);
        System.out.println(maxArea);
    }

    //largest area in the histogram
    static void largestRectangularArea(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        int i = 0;
        for (i = 0; i < n;) {
            if (stack.isEmpty() || arr[i] >= arr[stack.peek()]){
                stack.push(i++);
            }else{
                int size = stack.pop();
                int area = (stack.isEmpty())? arr[size]*i : (i - stack.peek()-1)*arr[size];
                max = Math.max(area, max);
            }
        }

        while (!stack.isEmpty()){
            int size = stack.pop();
            int area = (stack.isEmpty())? arr[size]*i : (i - stack.peek())*size;
            max = Math.max(area, max);
        }

        System.out.println(max);
    }















    /**
     * In this solution, we intend to use Stacks for this problem.
     * 1. Push into the stack when the current bar value is greater than or equal to the stack top value.
     * 2. Else, we pop from the stack and calculate the max area from there.
     * Area = bar[top] * (len - stackTop - 1) when the stack is not empty
     * Else Area = bar[top] * len;
     * <p>
     * Time Complexity =O(N)
     * Space Complexity =O(N)
     **/
    static int largestAreaHistogram(int[] histogram) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = histogram.length;
        int len = 0;
        while (len < n) {
            if (stack.isEmpty() || histogram[stack.peek()] <= histogram[len]) {
                stack.push(len++);
            } else {
                int top = stack.pop();
                int area = histogram[top] * (stack.isEmpty() ? len : (len - stack.peek() - 1));
                maxArea = Math.max(area, maxArea);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = histogram[top] * (stack.isEmpty() ? len : (len - stack.peek() - 1));
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }
}
