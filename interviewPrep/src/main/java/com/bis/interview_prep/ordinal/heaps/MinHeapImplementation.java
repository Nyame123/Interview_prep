package com.bis.interview_prep.ordinal.heaps;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This question is designed to help you get a better understanding of basic heap operations.
 *
 * There are 3 types of query:
 *
 * "1 v " - Add an element v to the heap.
 * "2 v " - Delete the element v from the heap.
 * "3" - Print the minimum of all the elements in the heap.
 * NOTE: It is guaranteed that the element to be deleted will
 * be there in the heap. Also, at any instant, only distinct elements will be in the heap.
 **/
public class MinHeapImplementation {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        while(queries > 0){

            int op = scanner.nextInt();

            if(op == 1){
                int val = scanner.nextInt();
                pQueue.add(val);
            }else if(op == 2){
                int val = scanner.nextInt();
                pQueue.remove(val);
            }else{
                sb.append(pQueue.peek()).append("\n");
            }

            queries--;
        }

        System.out.print(sb.toString());
    }
}
