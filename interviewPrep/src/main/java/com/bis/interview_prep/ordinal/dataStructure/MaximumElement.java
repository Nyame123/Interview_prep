package com.bis.interview_prep.ordinal.dataStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * You have an empty sequence, and you will be given N queries. Each query is one of these three types:
 *
 * 1 x  -Push the element x into the stack.
 * 2    -Delete the element present at the top of the stack.
 * 3    -Print the maximum element in the stack.
 **/
public class MaximumElement {

    public static List<Integer> getMax(List<String> operations) {
        // Write your code here
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();
        List<Integer> maxes = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(String s: operations){
            int op;
            int it=0;
            if(s.length() > 1){
                String[] arr = s.split(" ");
                op = Integer.parseInt(arr[0]);
                it = Integer.parseInt(arr[1]);
            }else{
                op = Integer.parseInt(s);
            }

            if(op == 1){

                if(maxStack.isEmpty() || max <= it){
                    maxStack.push(it);
                    max = it;
                }

                stack.push(it);
            }else if(op == 2){
                if(max == stack.pop()){
                    maxStack.pop();
                    if(maxStack.isEmpty()){
                        max = Integer.MIN_VALUE;
                    }else{
                        max = maxStack.peek();
                    }
                }
            }else{
                maxes.add(maxStack.peek());
            }
        }

        return maxes;
    }
}
