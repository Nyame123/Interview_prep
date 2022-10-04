package com.bis.interview_prep.dynamicProgramming;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindromeExpandAroundCenter(s));
    }

    public static String longestPalindromeManacher(String s) {
        //get the modified String
        //01010101
        String newString = getModifiedString(s);

        //get the center and right boundary from center
        int center = 0;
        int rightBoundary = 0;
        int len = newString.length();
        int maxLen = 0;
        int maxIndex = 0;
        int[] LPS = new int[len];
        //go over the characters in the string
        for (int i = 0; i < len; i++) {
            //mirror index of the character at position i
            int mirrorIndex = 2 * center - i;

            //get the LPS[i]
            if (i < rightBoundary) {
                LPS[i] = Math.min(rightBoundary - i, LPS[mirrorIndex]);
            }

            //check palindrome length from ith position
            int right = i + (LPS[i] + 1);
            int left = i - (LPS[i] + 1);

            while (right < len && left >= 0 && newString.charAt(right) == newString.charAt(left)) {
                LPS[i]++;
                right++;
                left--;
            }

            //check if the (i+LPS[i])th > right boundary, then recalculate the center and right boundary
            if (LPS[i] + i > rightBoundary) {
                center = i;
                rightBoundary = i + LPS[i];
            }

            //check for the max length so far
            if (maxLen < LPS[i]) {
                maxLen = LPS[i];
                maxIndex = i;
            }
        }

        return getProperSubString(newString, maxLen, maxIndex);

    }

    private static String getProperSubString(String newString, int maxLen, int maxIndex) {
        String maxPalindromeSubstring = newString.substring(maxIndex - maxLen + 1, maxIndex + maxLen);
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : maxPalindromeSubstring.toCharArray()) {
            if (c != '#')
                stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static String getModifiedString(String s) {
        StringBuilder stringBuilder = new StringBuilder(2 * s.length() + 1);
        stringBuilder.append("#");
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append(s.charAt(i));
            stringBuilder.append("#");
        }

        return stringBuilder.toString();
    }

    public static String longestPalindromeDP(String s) {
        int n = s.length();
        int[][] sArray = new int[n][n];

        String ans = s.charAt(0) + "";
        //initialize the length of 1
        for (int i = 0; i < n; i++) {
            sArray[i][i] = 1;
        }

        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n; i++) {
                int j = i + k;
                if (j < n) {
                    if (i != j) {
                        boolean b = s.charAt(i) == s.charAt(j);
                        if (k == 1) {
                            if (b) {
                                int len = j - i + 1;
                                if (len > ans.length()) {
                                    ans = s.substring(i, j + 1);
                                }
                                sArray[i][j] = 1;
                            } else {
                                sArray[i][j] = 0;
                            }
                        } else {
                            if (b && sArray[i + 1][j - 1] == 1) {
                                int len = j - i + 1;
                                if (len > ans.length()) {
                                    ans = s.substring(i, j + 1);
                                }
                                sArray[i][j] = 1;
                            } else {
                                sArray[i][j] = 0;
                            }
                        }
                    }

                }

            }
        }

        return ans;
    }

    public static String longestPalindromeExpandAroundCenter(String s) {

        //every character is a palindrome
        int maxPalindrome = 0;
        String answer = "" + s.charAt(0);

        //go over the string and check one by one for the longest palindromic substring
        for (int i = 0; i < s.length(); i++) {
            int prevIndex = i - 1;
            int forIndex = i + 1;

            //check if the first case;
            while (prevIndex >= 0 && forIndex < s.length()) {
                if (s.charAt(prevIndex) == s.charAt(forIndex)) {
                    int len = forIndex - prevIndex + 1;
                    if (len > maxPalindrome) {
                        maxPalindrome = len;
                        answer = s.substring(prevIndex, forIndex + 1);
                    }

                    prevIndex--;
                    forIndex++;
                } else {
                    break;
                }

            }

            //check the second case
            forIndex = i + 1;
            prevIndex = i;
            while (prevIndex >= 0 && forIndex < s.length()) {

                if (s.charAt(forIndex) == s.charAt(prevIndex)) {
                    int len = forIndex - prevIndex + 1;
                    if (len > maxPalindrome) {
                        maxPalindrome = len;
                        answer = s.substring(prevIndex, forIndex + 1);
                    }
                    prevIndex--;
                    forIndex++;
                } else {
                    break;
                }
            }
        }

        return answer;

    }
}

class User {
    //getting the age of the user
    // int age = 23;
    long age = 24;
    String firstName = "Nyame";

}
