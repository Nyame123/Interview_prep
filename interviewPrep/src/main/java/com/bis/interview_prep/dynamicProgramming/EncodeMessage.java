package com.bis.interview_prep.dynamicProgramming;

/**
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 * <p>
 * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
 * <p>
 * You can assume that the messages are decodable. For example, '001' is not allowed.
 **/
public class EncodeMessage {

    public static void main(String[] args) {
        String message = "1134";
        int count = encodeMessageCount(message);
        System.out.println(count);
    }


    /**
     * In this problem, we are to count the ways to encode a message. We are given a mapping to
     * encode the message.
     * Here we use Dynamic Programming strategy to number of ways.
     * 1. here, we can see that only a substring of len 1 or 2 can be encoded from the mapping
     * because, only these length falls within the mapping.
     * <p>
     * <p>
     * Time Complexity =  O(N)
     * Space Complexity =  O(N)
     **/
    private static int encodeMessageCount(String message) {
        int[] memo = new int[message.length() + 1];
        int k = message.length();
        char[] messageChars = message.toCharArray();
        if (messageChars[0] == '0') {
            return 0;
        }
        return encodeMessageHelper(message.toCharArray(), k, memo);
    }

    static int encodeMessageHelper(char[] message, int k, int[] memo) {
        //base case
        if (k == 0) {
            return 1;
        }

        if (memo[k] > 0) {
            return memo[k];
        }

        int start = message.length - k;
        int res = 0;
        if (message[start] != '0')
            res = encodeMessageHelper(message, k - 1, memo);

        if (k > 1) {
            int digit = Integer.parseInt(String.valueOf(message, start, 2));
            if (digit >= 1 && digit <= 26) {
                res += encodeMessageHelper(message, k - 2, memo);
            }
        }
        memo[k] = res;

        return res;
    }
}
