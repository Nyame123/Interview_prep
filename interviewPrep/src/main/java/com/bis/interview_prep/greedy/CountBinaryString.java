package com.bis.interview_prep.greedy;
/**
 * Given a binary string s, return the number of contiguous substrings that contains the same number of zeroes and ones.
 * Note: Each substring’s zeroes and ones must be grouped together.
 *
 * Ex: Given the following string s…
 *
 * s = "101", return 2 ("10" and "01").
 * Ex: Given the following string s…
 *
 * s = "1011101", return 4 ("10", "01", "10", "01". "101" does not count since the zeroes and ones are not grouped together).
 * */
public class CountBinaryString {

    public static void main(String[] args) {
        String s = "1011101";
        int count = countBinaryString(s);
        System.out.println(count);
    }

    private static int countBinaryString(String s) {
        int n = s.length();
        int ans = 0, prev = 0, cur = 1;

        for(int i = 1; i < n; i++){
            if(s.charAt(i-1) != s.charAt(i)){
                ans += Math.min(prev,cur);
                prev = cur;
                cur = 1;
            }else{
                cur++;
            }
        }

        return ans + Math.min(prev,cur);
    }
}
