package com.bis.interview_prep.greedy;

/**
 * This problem was asked by Square.
 * <p>
 * Given a string and a set of characters, return the shortest substring containing all the characters in the set.
 * <p>
 * For example, given the string "figehaeci" and the set of characters {a, e, i}, you should return "aeci".
 * <p>
 * If there is no substring containing all the characters in the set, return null.
 **/
public class SmallestWindowContainingAllChars {

    public static void main(String[] args) {

        String str = "ADOBECODEBANC";
        String pat = "ABC";

        System.out.print("Smallest window is :\n "
                + findSubStringSlidingWindow(str, pat));
    }

    /**
     * We will use a pointer-algorithm to achieve this solution
     * 1. We will have a start and end pointer
     * 2. We store the occurences of all the characters in the pattern
     * 3. We store the occurences of all the characters in the string using 256 length
     * 4. when there is a valid block we check if it is the minimum length that can contain
     * all the pattern
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static String findSubString(String str, String pat) {
        int len1 = str.length();
        int len2 = pat.length();
        if (len1 < len2) {
            return null;
        }
        int[] hashWord = new int[256];
        int[] hashPat = new int[256];

        //store the occurences of the pattern
        for (int i = 0; i < len2; i++) {
            char c = pat.charAt(i);
            hashPat[c]++;
        }


        int start = 0, startIndex = -1;
        int count = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len1; i++) {
            char c = str.charAt(i);
            //store the word
            hashWord[c]++;

            if (hashWord[c] <= hashPat[c]) {
                count++;
            }

            if (count == len2) {

                //contract the start pointer
                while (hashWord[str.charAt(start)] != hashPat[str.charAt(start)] || hashPat[str.charAt(start)]
                        == 0) {
                    if (hashWord[str.charAt(start)] > hashPat[str.charAt(start)]) {
                        hashWord[str.charAt(start)]--;
                    }
                    start++;
                }


                int minLen = i - start + 1;
                if (minLen < ans) {
                    startIndex = start;
                    ans = minLen;
                }

            }

        }

        if (startIndex == -1) {
            return null;
        }

        return str.substring(startIndex, startIndex + ans);

    }

    /**
     * We can also use a sliding window algorithm
     * 1. We start from index zero with the two edges of the windows and expand
     * 2. We can use a hash to store all the occurrences of the pattern string to know the unique
     * count of the characters of the pattern.
     * 3. We calculate the min distance when the number of characters in the pattern string is zero
     * 4. When the number of characters in the pattern is zero, we iteratively increase the count of the
     * starting Edge of the sliding window until the count of the start is more than zero
     *
     * Time Complexity = O(|S|)
     **/
    static String findSubStringSlidingWindow(String str, String pat) {
        int len1 = pat.length();
        int len2 = str.length();

        if (len1 > len2){
            return null;
        }

        int[] patChar = new int[256];

        int count = 0;
        int startIndex = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len1; i++) {
            if (patChar[pat.charAt(i)] == 0)
                count++;
            patChar[pat.charAt(i)]++;
        }

        int start = 0, end = 0;
        while (end < len2){
            char c = str.charAt(end);
            patChar[c]--;

            if (patChar[c] == 0){
                count--;
            }

            if (count == 0){
                while (count == 0){
                    int minLen = end - start + 1;
                    if (minLen < ans){
                        ans = minLen;
                        startIndex = start;
                    }

                    patChar[str.charAt(start)]++;
                    if (patChar[str.charAt(start)] > 0){
                        count++;
                    }

                    start++;
                }
            }

            end++;
        }

        if (startIndex == -1){
            return null;
        }

        return str.substring(startIndex,startIndex+ans);
    }

}
