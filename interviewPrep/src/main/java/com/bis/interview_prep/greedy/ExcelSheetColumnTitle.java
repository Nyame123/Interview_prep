package com.bis.interview_prep.greedy;
/**
 *Given a positive integer n, return its corresponding column title in an excel spreadsheet.
 *
 * Ex: Given the following values for n...
 *
 * n = 1, return “A”.
 * n = 2, return “B”.
 * n = 3, return “C”.
 * n = 26, return “Z”.
 * n = 27, return “AA”.
 * n = 28, return “AB”.
 **/
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        int n = 701;
        String res = convertToTitle(n);
        System.out.println(res);
    }


    static String convertToTitle(int columnNumber) {
        int n = columnNumber;
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            n--;
            int rem = n % 26;
            sb.append((char) (rem + 'A'));

            n /= 26;
        }

        return sb.reverse().toString();
    }
}
