package com.bis.interview_prep.dynamicProgramming;

import java.util.Map;

/**
 * There is an N by M matrix of zeroes. Given N and M, write a function to count the number of ways
 * of starting at the top-left corner and getting to the bottom-right corner. You can only move right or down.
 * <p>
 * For example, given a 2 by 2 matrix, you should return 2, since there are two ways to get to the bottom-right:
 * <p>
 * Right, then down
 * Down, then right
 * Given a 5 by 5 matrix, there are 70 ways to get to the bottom-right.
 **/
public class UniquePathInGrid {

    public static void main(String[] args) {
        int n = 12, m = 23;
        int res = uniquePaths(n, m);
        System.out.print(res);
    }

    static int uniquePaths(int m, int n) {
        return uniquePathsFilter(m, n);
    }

    static int uniquePathsFilter(int m, int n) {
        return uniquePathsUsingCombination(m + n - 2, n - 1);
    }

    static int gcd(int n1, int n2) {
        /*long gcd = 1;

        for (int i = 1; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;*/
        return (n1 % n2 == 0) ? Math.abs(n2) : gcd(n2, n1 % n2);
    }

    static int uniquePathsUsingCombination(int n, int r) {
        // p holds the value of n*(n-1)*(n-2),
        // k holds the value of r*(r-1)...
        int p = 1, k = 1;

        // C(n, r) == C(n, n-r),
        // choosing the smaller value
        if (n - r < r) {
            r = n - r;
        }

        if (r != 0) {
            while (r > 0) {
                p *= n;
                k *= r;

                // gcd of p, k
                long m = gcd(p, k);

                // dividing by gcd, to simplify
                // product division by their gcd
                // saves from the overflow
                p /= m;
                k /= m;

                n--;
                r--;
            }

            // k should be simplified to 1
            // as C(n, r) is a natural number
            // (denominator should be 1 ) .
        } else {
            p = 1;
        }

        return p;
    }

    static int countDp(int m, int n) {
        int[] dp = new int[n];

        dp[0] = 1;
        for (int i = 0; i < m; i++) {

            for (int j = 1; j < n; j++) {
                if (j > 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }

            }
        }

        return dp[n - 1];
    }

    static int countRec(int m, int n, Map<String, Integer> map) {
        String key = m + "," + n;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (m == 1 && n == 1) {
            return 1;
        }

        if (m < 0 || n < 0) {
            return 0;
        }

        int res = countRec(m - 1, n, map) + countRec(m, n - 1, map);
        map.put(key, res);
        return res;
    }
}
