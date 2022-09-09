package com.bis.interview_prep.greedy;
/**
 * The "look and say" sequence is defined as follows: beginning with the term 1,
 * each subsequent term visually describes the digits appearing in the previous term. The first few terms are as follows:
 *
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * As an example, the fourth term is 1211, since the third term consists of one 2 and one 1.
 *
 * Given an integer N, print the Nth term of this sequence.
 *
 * Upgrade to premium and get in-depth solutions to every problem, including this one.
 **/
public class LookAndSaySequence {

    public static void main(String[] args) {
        int n = 5;
        String result = lookNSaySequence(n);
        System.out.println(result);
    }

    /**
     * To get the pattern in this problem, follow these steps
     * 1. Get the first char, c of the value.
     * 2. Check if the first char, c is the same as the subsequent characters and count the matching characters.
     * 3. Add the count to the string and the char c, itself.
     * 4. Take the next char and repeat the steps from 1.
     *
     * Time Complexity = (N^2)
     **/
    private static String lookNSaySequence(int n) {
        String val = "1";

        for (int i = 0; i < n - 1; i++) {

            char c = val.charAt(0);
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < val.length(); j++) {

                if (c != val.charAt(j)){
                    sb.append(count);
                    sb.append(c);

                    count = 0;
                    c = val.charAt(j);
                }

                count++;
            }

            sb.append(count);
            sb.append(c);
            val = sb.toString();
        }

        return val;
    }
}
