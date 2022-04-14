package com.bis.interview_prep.greedy;

/**
 * Given a string, return whether it represents a number. Here are the different kinds of numbers:
 * <p>
 * "10", a positive integer
 * "-10", a negative integer
 * "10.1", a positive real number
 * "-10.1", a negative real number
 * "1e5", a number in scientific notation
 * And here are examples of non-numbers:
 * <p>
 * "a"
 * "x 1"
 * "a -2"
 * "-"
 **/
public class NumberPattern {

    public static void main(String[] args) {
        String number = "10e13";
        boolean res = isValidNumber(number);
        System.out.println(res);
    }

    /**
     * This problem can be solved by breaking it into characters.
     * We can use state pattern or state machine for this.
     *
     * @see State, which represent the state of the string
     * 1. Break the string into characters and per the state, then
     * follows a group of states.
     * <p>
     * Time Complexity = O(N)
     **/
    static boolean isValidNumber(String number) {
        State state = State.INIT;
        for (char c : number.toCharArray()) {
            switch (state) {
                case INIT:
                case EXP:
                    if (c == '-') {
                        state = State.MINUS;
                    } else if (c >= '0' && c <= '9') {
                        state = State.INTEGER;
                    } else {
                        return false;
                    }
                    break;
                case DOT:
                    if (c >= '0' && c <= '9') {
                        state = State.DECIMAL;
                    } else {
                        return false;
                    }
                    break;
                case MINUS:
                    if (c >= '0' && c <= '9') {
                        state = State.INTEGER;
                    } else {
                        return false;
                    }
                    break;
                case INTEGER:
                    if (c == 'e') {
                        state = State.EXP;
                    } else if (c == '.') {
                        state = State.DOT;
                    } else if (c >= '0' && c <= '9') {
                        state = State.INTEGER;
                    } else {
                        return false;
                    }
                    break;
                case DECIMAL:
                    if (c >= '0' && c <= '9') {
                        state = State.DECIMAL;
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return (state == State.INTEGER || state == State.DECIMAL);
    }

    enum State {
        INIT,
        EXP,
        DECIMAL,
        INTEGER,
        DOT,
        MINUS
    }
}
