package com.bis.interview_prep.ordinal.dataStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  Moving Average from Data Stream
 *
 * Solution
 * Given a stream of integers and a window size, calculate the moving average of all
 * integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 *
 * Constraints:
 *
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * At most 104 calls will be made to
 **/
public class MovingAverageMain {
    public static void main(String[] args) {
        int[] arr = {1,10,3,5};

        List<Double> ans = new ArrayList<>();
        MovingAverage movingAverage = new MovingAverage(3);
        for (int i = 0; i < arr.length; i++) {
           double res = movingAverage.next(arr[i]);
           ans.add(res);
        }

        System.out.println(ans);
    }
}

class MovingAverage {
    int mSize;
    Queue<Integer> queue;
    double sum = 0;
    public MovingAverage(int size) {
        this.mSize = size;
        queue = new ArrayDeque<>(size);
    }

    public double next(int val) {
        if (!queue.isEmpty() && queue.size() >= mSize){
            sum -= queue.poll();
        }
        sum += val;
        queue.add(val);

        return sum / queue.size();
    }
}