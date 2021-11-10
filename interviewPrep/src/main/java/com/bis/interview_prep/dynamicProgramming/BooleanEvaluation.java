package com.bis.interview_prep.dynamicProgramming;

import java.util.HashMap;

/**
 * Given a boolean expression consisting of the symbols e (false), 1 (true), &
 * (AND), I (OR), and A (XOR), and a desi red boolean result value result, implement a function to
 * count the number of ways of parenthesizing the expression such that it evaluates to result. The
 * expression should be fully parenthesized (e.g., (0) ^ (1)) but not extraneously (e.g., (((0)) ^ ((1))).
 * EXAMPLE
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 1
 **/
public class BooleanEvaluation {

    public static void main(String[] args) {

        String expression = "1^0|0|1";
        int ways = countEvaluate(expression, false,new HashMap<>());
        System.out.println(ways);
    }

    static boolean stringToBool(String s) {
        return s.equalsIgnoreCase("1");
    }


    //with memoization to optimize this
    static int countEvaluate(String expression, boolean result,HashMap<String,Integer> memo) {
        //base case
        String key = expression + result;
        if (memo.containsKey(key))
            return memo.get(key);

        if (expression.length() == 0)
            return 0;
        if (expression.length() == 1)
            return stringToBool(expression) == result ? 1 : 0;

        int ways = 0;
        //loop over the expressions
        for (int i = 1; i < expression.length(); i += 2) {
            //get the operator
            char operator = expression.charAt(i);
            String leftOperand = expression.substring(0, i);
            String rightOperand = expression.substring(i + 1);

            int leftTrue = countEvaluate(leftOperand, true);
            int leftFalse = countEvaluate(leftOperand, false);

            int rightTrue = countEvaluate(rightOperand, true);
            int rightFalse = countEvaluate(rightOperand, false);

            int total = (leftFalse + leftTrue) * (rightFalse + rightTrue);
            int totalTrue = 0;
            switch (operator) {
                case '^':
                    totalTrue = (leftTrue * rightFalse) + (rightTrue * leftFalse);

                    break;
                case '|':

                    totalTrue = (leftFalse * rightTrue) + (leftTrue * rightFalse) + (leftTrue * rightTrue);

                    break;
                case '&':
                    totalTrue = (leftTrue * rightTrue);


            }

            ways += result ? totalTrue : total - totalTrue;

        }

        memo.put(key,ways);
        return ways;
    }

    static int countEvaluate(String expression, boolean result) {
        //base case
        if (expression.length() == 0)
            return 0;
        if (expression.length() == 1)
            return stringToBool(expression) == result ? 1 : 0;

        int ways = 0;
        //loop over the expressions
        for (int i = 1; i < expression.length(); i += 2) {
            //get the operator
            char operator = expression.charAt(i);
            String leftOperand = expression.substring(0, i);
            String rightOperand = expression.substring(i + 1);

            int leftTrue = countEvaluate(leftOperand, true);
            int leftFalse = countEvaluate(leftOperand, false);

            int rightTrue = countEvaluate(rightOperand, true);
            int rightFalse = countEvaluate(rightOperand, false);

            int total = (leftFalse + leftTrue) * (rightFalse + rightTrue);
            int totalTrue = 0;
            switch (operator) {
                case '^':
                    totalTrue = (leftTrue * rightFalse) + (rightTrue * leftFalse);

                    break;
                case '|':

                    totalTrue = (leftFalse * rightTrue) + (leftTrue * rightFalse) + (leftTrue * rightTrue);

                    break;
                case '&':
                    totalTrue = (leftTrue * rightTrue);


            }

            ways += result ? totalTrue : total - totalTrue;

        }

        return ways;
    }


}
