package com.bis.interview_prep.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Samantha and Sam are playing a numbers game.
 * Given a number as a string, no leading zeros, determine the sum of all integer values of substrings of the string.
 * <p>
 * Given an integer as a string, sum all of its substrings cast as integers. As the number may become large,
 * return the value modulo 10^9 + 7.
 **/
public class SamNSubstring {
    static int mod = 1000000007;

    public static void main(String[] args) {
        String s = "2222222233333333333333333333333";
        int res = substringUsingDp(s);
        System.out.println(res);
    }

    //using dynamic programming
    public static int substringUsingDp(String s) {

        List<Integer> dp = new ArrayList<>();
        int first = s.charAt(0) - '0';
        dp.add(first);
        int n = s.length();
        int ans = first;
        for (int i = 1; i < n; i++) {
            int c = s.charAt(i) - '0';
            for (int j = 0; j < dp.size(); j++) {
                int it = (mul(dp.get(j), 10) + c) % mod;
                dp.set(j, it);
            }
            dp.add(c);
            ans += accumulate(dp);
        }

        return ans;
    }

    static int accumulate(List<Integer> item) {
        int sum = 0;
        for (int i = 0; i < item.size(); i++) {
            sum += item.get(i) % mod;
        }

        return sum;
    }

    public static int mul(int a, int b) {
        return (int) ((long) a * b % mod);
    }

    public static int substrings(String n) {
        // Write your code here
        int mod = 1000000007;
        int sum = 0;
        int len = n.length();
        int f = 1;
        for (int i = len - 1; i >= 0; i--) {
            sum = (sum + mul(mul(n.charAt(i) - '0', f), i + 1)) % mod;
            f = (mul(f, 10) + 1) % mod;
        }

        return sum;

    }
}
