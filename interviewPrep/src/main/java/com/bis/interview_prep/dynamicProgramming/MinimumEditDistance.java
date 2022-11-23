package com.bis.interview_prep.dynamicProgramming;

/**
 * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
 * <p>
 * Time complexity is O(m*n)
 * Space complexity is O(m*n)
 * <p>
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * https://en.wikipedia.org/wiki/Edit_distance
 */
public class MinimumEditDistance {
    public static void main(String args[]) {
        String str1 = "horse";
        String str2 = "ros";
        MinimumEditDistance editDistance = new MinimumEditDistance();
        int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
        //int result = editDistance.recEditDistance(str1.toCharArray(), str2.toCharArray(), 0, 0);
        System.out.print(result);
    }

    /**
     * Uses recursion to find minimum edits
     */
    public int recEditDistance(char[] str1, char str2[], int len1, int len2) {

        if (len1 == str1.length) {
            return str2.length - len2;
        }
        if (len2 == str2.length) {
            return str1.length - len1;
        }

        if (str1[len1] == str2[len2]) {
            return recEditDistance(str1, str2, len1 + 1, len2 + 1);
        } else {
            return min(recEditDistance(str1, str2, len1 + 1, len2 + 1),
                    recEditDistance(str1, str2, len1, len2 + 1),
                    recEditDistance(str1, str2, len1 + 1, len2)) + 1;
        }
    }

    /**
     * Uses bottom up DP to find the edit distance
     */
    public int dynamicEditDistance(char[] str1, char[] str2) {
        int temp[][] = new int[str1.length + 1][str2.length + 1];

        for (int i = 0; i < temp[0].length; i++) {
            temp[0][i] = i;
        }

        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = i;
        }

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    temp[i][j] = temp[i - 1][j - 1];
                } else {
                    temp[i][j] = 1 + min(temp[i - 1][j - 1], temp[i - 1][j], temp[i][j - 1]);
                }
            }
        }
        printActualEdits(temp, str1, str2);
        return temp[str1.length][str2.length];

    }

    /**
     * Prints the actual edits which needs to be done.
     */
    public void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while (true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i - 1] == str2[j - 1]) {
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i - 1]);
                i = i - 1;
            } else if (T[i][j] == T[i][j - 1] + 1) {
                System.out.println("Delete in string2 " + str2[j - 1]);
                j = j - 1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }

        }
    }

    private int min(int a, int b, int c) {
        int l = Math.min(a, b);
        return Math.min(l, c);
    }
}


/**
 * Deletion Distance
 * The deletion distance of two strings is the minimum number of characters you need to delete in
 * the two strings in order to get the same
 * string. For instance, the deletion distance between "heat" and "hit" is 3:
 *
 * By deleting 'e' and 'a' in "heat", and 'i' in "hit", we get the string "ht" in both cases.
 * We cannot get the same string from both strings by deleting 2 letters or fewer.
 * Given the strings str1 and str2, write an efficient function deletionDistance
 * that returns the deletion distance between them. Explain how your
 * function works, and analyze its time and space complexities.
 *
 * input:  str1 = "dog", str2 = "frog"
 * output: 3
 *
 * input:  str1 = "some", str2 = "some"
 * output: 0
 *
 * input:  str1 = "some", str2 = "thing"
 * output: 9
 *
 * input:  str1 = "", str2 = ""
 * output: 0
 **/
class MinimumDeletionDistance{

    public static void main(String[] args) {

        String str1 = "heat";
        String str2 = "hit";

        int minDistance = deletionDistance(str1,str2);
        System.out.println(minDistance);
    }

    static int deletionDistance(String str1, String str2) {
        // your code goes here
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n+1][m+1];

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = i;
        }

        for(int i = 0; i < dp[0].length; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){

                //same char at both location
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + 1;
                }

            }
        }


        return dp[n][m];
    }
}