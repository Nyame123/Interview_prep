package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * Given a string s, check if it can be constructed by taking a substring of it and appending
 * multiple copies of the substring together.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 * <p>
 * Input: s = "aba"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 **/
public class RepeatedSubstring {

    public static void main(String[] args) {
        String s = "abcabcabcabc";
        boolean res = repeatedSubstringPattern(s);
        System.out.println(res);
    }

    static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        String temp;
        int count;
        for (int i = 1; i <= n; i++) {
            temp = s.substring(0, i);
            count = i;
            int c = 1;
            for (int j = i + i; j <= n; j += i) {
                if (!temp.equals(s.substring(j - i, j))) {
                    break;
                }
                count = j;
                c++;
            }

            if (count == n && c > 1) {
                return true;
            }
        }

        return false;
    }

    public boolean repeatedSubstringPattern1(String str) {

        int n = str.length();
        for (int len = n / 2; len > 0; len--) {
            if (n % len == 0) {
                int maxLen = n / len;
                String sub = str.substring(0, len);
                int batch = 0;
                for (batch = 1; batch < maxLen; batch++) {
                    int offset = len * batch;
                    if (!sub.equals(str.substring(offset, offset + len))) {
                        break;
                    }
                }

                if (batch == maxLen) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String str) {
        int l = str.length();
        for (int i = l / 2; i >= 1; i--) {
            if (l % i == 0) {
                int m = l / i;
                String subS = str.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(subS);
                }
                if (sb.toString().equals(str)) return true;
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern3(String str) {
        String s = str + str;
        return s.substring(1,s.length()-1).contains(str);
    }
}
