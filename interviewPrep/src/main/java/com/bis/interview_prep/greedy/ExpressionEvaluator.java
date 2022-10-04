package com.bis.interview_prep.greedy;

import java.util.ArrayDeque;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it,
 * and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function
 * which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 * <p>
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * <p>
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
public class ExpressionEvaluator {

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int result = calculate(s);
        System.out.println(result);
    }

    private static int calculate(String s) {
        char[] tokens = s.toCharArray();

        ArrayDeque<Integer> valuesStack = new ArrayDeque<>();
        ArrayDeque<Character> opStack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            //if the next tokens is a number, push to the value stack
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    sb.append(tokens[i++]);
                }

                valuesStack.push(Integer.parseInt(sb.toString()));

                //now the ith pointer is at next operator token and the for loop will
                // also increase by one, so to keep the offset, we decrease the value of i by
                // 1.
                i--;
            } else if (tokens[i] == '(') { //push to the stack
                opStack.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    int val = applyOp(opStack.pop(), valuesStack.pop(), valuesStack.pop());
                    valuesStack.push(val);
                }
                opStack.pop();
            } else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {
                while (!opStack.isEmpty() && hasPrecedence(tokens[i], opStack.peek())) {
                    int val = applyOp(opStack.pop(), valuesStack.pop(), valuesStack.pop());
                    valuesStack.push(val);
                }

                opStack.push(tokens[i]);
            }
        }

        //when the entire experession is parsed, evaluate what is left in the stacks
        while (!opStack.isEmpty()) {
            int val = applyOp(opStack.pop(), valuesStack.pop(), valuesStack.pop());
            valuesStack.push(val);
        }

        return valuesStack.pop();
    }

    /**
     * Returns true if 'op2' has higher precedence or same precedence as
     * 'op1'. otherwise return false.
     **/
    static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        else
            return (op1 != '*' && op1 != '/') ||
                    (op2 != '+' && op2 != '-');
    }

    static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("cannot divide by zero");
                }

                return a / b;
        }

        return 0;
    }
}
