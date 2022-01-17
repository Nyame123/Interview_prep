package com.bis.interview_prep.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Creating all the valid parenthesis given a number of
 * parenthesis
 **/
public class ValidParenthesisCreator {

    public static void main(String[] args) {
        int numOfParenthesis = 3;
        //HashSet<String> results = validParenthesis(numOfParenthesis);
        List<String> results = generateValidParen(numOfParenthesis);
        System.out.println(results + "  " + results.size());
    }

    static String validParenthesisInsertor(String parenthesis, int index) {

        String left = parenthesis.substring(0, index);
        String newPar = "()";
        String right = parenthesis.substring(index);
        return left + newPar + right;
    }

    /**
     * We can generate the parenthesis from the scratch and build up
     * 1. we get the count of the parenthesis we want to generate
     * 2. We insert a left parenthesis '(' while the leftCount > 0 and recurse over
     * remaining left count
     * 3. We insert a right parenthesis ')' while the rightCount > 0 and recurse over the
     * remaining right count
     **/
    static List<String> generateValidParen(int num){

        char[] stri = new char[num*2];
        List<String> parenthesis = new ArrayList<>();
        validParenthesis(stri,num,num,0,parenthesis);
        return parenthesis;
    }

    private static void validParenthesis(char[] stri, int leftCount, int rightCount, int index,List<String> result) {
        //base case
        if (leftCount < 0 || rightCount < leftCount) //invalid case
            return;

        if (leftCount == 0 && rightCount == 0){
            result.add(String.copyValueOf(stri));
        }else{
            stri[index] = '(';
            validParenthesis(stri,leftCount-1,rightCount,index+1,result);

            stri[index] = ')';
            validParenthesis(stri,leftCount,rightCount-1,index+1,result);
        }
    }

    /**
     * This algorithm works but generates duplicates as the number
     * of Parenthesis increases
     **/
    static HashSet<String> validParenthesis(int num) {
        HashSet<String> parenthesis = new HashSet<>();
        //base case
        if (num == 0) {
            parenthesis.add("");
            return parenthesis;
        }

        if (num == 1) {
            parenthesis.add("()");
            return parenthesis;
        }

        HashSet<String> results = validParenthesis(num - 1);

        //loop over the results and insert new parenthesis at every left parenthesis
        for (String par : results) {

            for (int i = 0; i < par.length(); i++) {
                if (par.charAt(i) == '(') {
                    String newPar = validParenthesisInsertor(par, i + 1);
                    parenthesis.add(newPar);
                }

            }

            parenthesis.add("()" + par);
        }

        return parenthesis;
    }
}
