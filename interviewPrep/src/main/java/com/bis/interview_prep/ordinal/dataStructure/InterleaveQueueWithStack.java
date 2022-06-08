package com.bis.interview_prep.ordinal.dataStructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a queue of integers of even length, rearrange the elements by
 * interleaving the first half of the queue with the second half of the queue.
 * Only a stack can be used as an auxiliary space. Examples:
 *
 * Input :  1 2 3 4
 * Output : 1 3 2 4
 *
 * Input : 11 12 13 14 15 16 17 18 19 20
 * Output : 11 16 12 17 13 18 14 19 15 20
 **/
public class InterleaveQueueWithStack {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(11);
        queue.add(12);
        queue.add(13);
        queue.add(14);
        queue.add(15);
        queue.add(16);
        queue.add(17);
        queue.add(18);
        queue.add(19);
        queue.add(20);

        interleaveQueue(queue);
        System.out.println(queue);
    }

    /**
     * To solve the problem and we need to use only a stack as
     * an auxiliary data structure and the queue should be of even length.
     * 1. Dequeue the first half of the queue into a stack.
     * 2. Enqueue the stack into the queue.
     * 3. Dequeue and Enqueue the first half number of times.
     * 4. Dequeue the first half of the queue into the stack.
     * 5. Enqueue the stack top into the queue and dequeue and enqueue the element until
     * there is no elements in the stack.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static void interleaveQueue(Queue<Integer> queue) {
        int n = queue.size();
        if (n % 2 != 0){
            System.out.println("The queue length should be even");
        }

        int half = n / 2;
        Deque<Integer> stack = new ArrayDeque<>(half);

        for (int i = 0; i < half; i++) {
            stack.push(queue.poll());
        }

        while (!stack.isEmpty()){
            queue.add(stack.pop());
        }

        for (int i = 0; i < half; i++) {
            queue.add(queue.poll());
        }

        for (int i = 0; i < half; i++) {
            stack.push(queue.poll());
        }

        while (!stack.isEmpty()){
            queue.add(stack.pop());
            queue.add(queue.poll());
        }

    }
}
