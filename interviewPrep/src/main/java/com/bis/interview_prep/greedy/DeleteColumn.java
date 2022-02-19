package com.bis.interview_prep.greedy;

/**
 * This problem was asked by Google.
 * <p>
 * You are given an N by M 2D matrix of lowercase letters. Determine the minimum number of columns
 * that can be removed to ensure that each row is ordered from top to bottom lexicographically.
 * That is, the letter at each column is lexicographically later as you go down each row.
 * It does not matter whether each row itself is ordered lexicographically.
 * <p>
 * For example, given the following table:
 * <p>
 * cba
 * daf
 * ghi
 * This is not ordered because of the a in the center. We can remove the second column to make it ordered:
 * <p>
 * ca
 * df
 * gi
 * So your function should return 1, since we only needed to remove 1 column.
 * <p>
 * As another example, given the following table:
 * <p>
 * abcdef
 * Your function should return 0, since the rows are already ordered (there's only one row).
 * <p>
 * As another example, given the following table:
 * <p>
 * zyx
 * wvu
 * tsr
 * Your function should return 3, since we would need to remove all the columns to order it.
 **/
public class DeleteColumn {
    public static void main(String[] args) {
        String[] strings = {"xc", "yb", "za"};
        System.out.print(minDeletionSize(strings));
    }


    static int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int count = 0;
        boolean[] rank = new boolean[n];
        for (int col = 0; col < m; col++) {
            int row = 0;
            for (row = 0; row < n - 1; row++) {
                if (rank[row])
                    continue;

                if (strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                    count++;
                    break;
                }
            }

            if (row < n - 1)
                continue;

            for (int i = 0; i < n - 1; i++) {
                if (!rank[i] && strs[i].charAt(col) < strs[i + 1].charAt(col)) {
                    rank[i] = true;
                }
            }
        }

        return count;
    }

}
