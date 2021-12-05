package com.bis.interview_prep.ordinal.heaps;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Jesse loves cookies and wants the sweetness of some cookies to be greater than value . To do this,
 * two cookies with the least sweetness are repeatedly mixed. This creates a special combined cookie with:
 * sweetness = Least sweet cookie + 2nd least sweet cookie).
 * This occurs until all the cookies have a sweetness .
 * Given the sweetness of a number of cookies, determine the minimum number of operations required. If it is not possible, return -1.
 **/
public class JesseCookies {

    public static void main(String[] args) {

    }

    public static int cookies2(int k, List<Integer> A) {
        // Write your code here
        int n = A.size();
        //using a priority queue to order the items in increasing order
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);
        //load the items in the priority queue
        queue.addAll(A);


        int iteration = 0;
        while(queue.peek() < k){

            if(queue.size() == 1)
                return -1;

            //remove the least two numbers
            int leastOne = queue.poll();
            int leastTwo = queue.poll();
            int newItem = leastOne + (2*leastTwo);
            queue.add(newItem);
            iteration++;
            //System.out.println(queue.poll());
        }
        return iteration;
    }


    public static int cookies1(int k, List<Integer> A) {
        // Write your code here
        int n = A.size();
        //using a priority queue to order the items in increasing order
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);
        int sum = 0;
        //load the items in the priority queue
        for(int i = 0; i < n; i++){
            sum += A.get(i);
            queue.add(A.get(i));
        }

        //if the sume is less than k then it is not possible
        if(sum < k){
            return -1;
        }

        int iteration = 0;
        while(!queue.isEmpty()){
            //remove the least two numbers
            int leastOne = queue.poll();
            if(leastOne >= k){
                return iteration;
            }
            int leastTwo = queue.poll();
            int newItem = leastOne + (2*leastTwo);
            queue.add(newItem);
            iteration++;
            //System.out.println(queue.poll());
        }
        return -1;
    }

    public static int cookies(int k, List<Integer> A) {
        PriorityQueue<Integer> cookies = new PriorityQueue<>();

        for (int element : A) {
            cookies.offer(element);
        }

        int operations = 0;

        while (cookies.size() > 1 && cookies.peek() < k) {
            int leastSweetCookie = cookies.poll();
            int nextLeastSweetCookie = cookies.poll();
            int combinedCookie = leastSweetCookie + 2 * nextLeastSweetCookie;
            cookies.offer(combinedCookie);
            operations++;
        }

        if (cookies.peek() >= k) {
            return operations;
        }

        return -1;
    }
}
