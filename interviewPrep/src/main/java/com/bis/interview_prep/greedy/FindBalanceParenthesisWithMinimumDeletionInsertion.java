package com.bis.interview_prep.greedy;
/**
 * Given a string of parentheses, find the balanced string that can be
 * produced from it using the minimum number of insertions and deletions.
 * If there are multiple solutions, return any of them.
 *
 * For example, given "(()", you could return "(())". Given "))()(", you could return "()()()()".
 **/
public class FindBalanceParenthesisWithMinimumDeletionInsertion {

    public static void main(String[] args) {
        String s = "))()(";
        String res = findBalancedParenthesis(s);
        System.out.println(res);
    }

    /**
     * Using a greedy algorithm
     * 1. Maintain a counter which increases when an opening parenthesis is found and
     * decreases when a closing parenthesis is found.
     * 2. When the counter is zero and there is an opening parenthesis, then fix the broken
     * structure by inserting a closing parenthesis.
     * 3. When the counter is more than zero, then insert an opening parenthesis at the end
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     *
     **/
    static String findBalancedParenthesis(String s) {
        int counter = 0;

        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if (c == '('){
                counter++;
                sb.append('(');
            }else{
                if (counter == 0){
                    sb.append('(');
                }else{
                   counter--;
                }
                sb.append(')');
            }
        }

        while (counter > 0){
            sb.append(')');
            counter--;
        }
        return sb.toString();
    }
}
