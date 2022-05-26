package com.bis.interview_prep.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This problem was asked by Jane Street.
 *
 * Given an arithmetic expression in Reverse Polish Notation, write a program to evaluate it.
 *
 * The expression is given as a list of numbers and operands. For example: [5, 3, '+'] should return 5 + 3 = 8.
 *
 * For example, [15, 7, 1, 1, '+', '-', '/', 3, '*', 2, 1, 1, '+', '+', '-'] should return 5,
 * since it is equivalent to ((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1)) = 5.
 *
 * You can assume the given expression is always valid.
 **/
public class ReversePolishNotation {

    public static void main(String[] args) {
        String[] arr = {"2","1","+","3","*"};
        int res = reversePostFixNotation(arr);
        System.out.println(res);
    }

    /**
     * In this problem, we will use Stack data structure for this.
     * 1. Push all numbers.
     * 2. Pop last two numbers when the element is operator.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static int reversePostFixNotation(String[] arr){
        int n = arr.length;
        Deque<String> stack = new ArrayDeque<>();
        int i = 0;
        while (i < n){
            String it = arr[i];
            if (!it.equals("*") && !it.equals("/") && !it.equals("-") && !it.equals("+")){
                stack.push(it);
            }else {
               int num2 = Integer.parseInt(stack.pop());
               int num1 = Integer.parseInt(stack.pop());
               int res = 0;
               switch (arr[i]){
                   case "*":
                       res = num1*num2;
                       break;
                   case "/":
                       res = num1/num2;
                       break;
                   case "+":
                       res = num1+num2;
                       break;
                   case "-":
                       res = num1-num2;
                       break;
                   default:
                       res = 0;
                       break;
               }

               stack.push(res+"");
            }
            i++;
        }

        return Integer.parseInt(stack.peek());
    }
}
