package com.bis.interview_prep.greedy;

public class IntegerToRomanNumerals {

    public static void main(String[] args) {
        int num = 345;
        String res = integerToNumerals(num);
        System.out.println(res);
    }

    private static String integerToNumerals(int num) {

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,1};
        String[] numerals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","I"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]){
                num -= values[i];
                stringBuilder.append(numerals[i]);
            }
        }

        return stringBuilder.toString();
    }
}
