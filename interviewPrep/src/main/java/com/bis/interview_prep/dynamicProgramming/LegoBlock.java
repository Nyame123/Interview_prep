package com.bis.interview_prep.dynamicProgramming;

import java.util.PriorityQueue;

import static com.bis.interview_prep.dynamicProgramming.Solution.add;

/**
 * You have an infinite number of 4 types of lego blocks of sizes given as (depth x height x width):
 * <p>
 * d	h	w
 * 1	1	1
 * 1	1	2
 * 1	1	3
 * 1	1	4
 * Using these blocks, you want to make a wall of height  and width . Features of the wall are:
 * <p>
 * - The wall should not have any holes in it.
 * - The wall you build should be one solid structure, so there should not be a straight vertical break across all rows of bricks.
 * - The bricks must be laid horizontally.
 * <p>
 * How many ways can the wall be built?
 **/
public class LegoBlock {

    public static int MOD = 1000000007;
    public static int[] row;

    public static void main(String[] args) {

        /**
         *
         * 6 7
         * 1 2 3 9 10 12
         * */
        int res = legoBlockMain(529, 190);
        System.out.println(res);
    }

    //lego main fxn
    static int legoBlockMain(int n, int m) {
        initialize(m);
        return legoBlock(n, m);
    }

    private static int legoBlock(int n, int m) {

        int[] walls = new int[m + 1];
        int[] results = new int[m + 1];

        walls[1] = 0;
        results[1] = 1;

        for (int i = 2; i <= m; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                /*sum = (sum+(results[j] * results[i - j])%MOD) % MOD;
                sum = (sum+(results[j] * walls[i - j])%MOD) % MOD;*/
                sum = add(sum, mult(results[j], results[i - j]));
                sum = add(sum, mult(results[j], walls[i - j]));
            }
            walls[i] = sum;
            results[i] = (pow(row[i], n) + MOD - walls[i]) % MOD;
        }

        return results[m];
    }

    //initialize the necessary variables
    static void initialize(int m) {
        int highM = 0;
        if (m < 4)
            highM = 4;
        else
            highM = m;
        row = new int[highM + 1];
        row[1] = 1;
        row[2] = 2;
        row[3] = 4;
        row[4] = 8;

        for (int i = 5; i <= m; i++) {
            //compute the rows from the last 4 rows
            row[i] = (row[i - 1] + row[i - 2] + row[i - 3] + row[i - 4]) % MOD;
        }
    }

    //creating a power fxn
    static int power(int a, int p) {
        //base cases
        if (p == 0)
            return 1;
        if (p == 1)
            return a;

        int num = a;
        for (int i = 2; i <= p; i++) {
            num = (num * a) % MOD;
        }
        return num;
    }


    static int mult(int x, int y) {
        long z = x;
        z *= y;
        return (int) (z % MOD);
    }

    static int pow(int x, int n) {
        int r = 1;
        while (n != 1) {
            if ((n & 1) == 1) r = mult(r, x);
            x = mult(x, x);
            n >>= 1;
        }
        return mult(x, r);
    }

    static int add(int x, int y) {
        return (x + y) % MOD;
    }
}

class Solution {
    static final int mod = 1000000007;
    static int[] f;

    static int mult(int x, int y) {
        long z = x;
        z *= y;
        return (int) (z % mod);
    }

    static int pow(int x, int n) {
        int r = 1;
        while (n != 1) {
            if ((n & 1) == 1) r = mult(r, x);
            x = mult(x, x);
            n >>= 1;
        }
        return mult(x, r);
    }

    static int add(int x, int y) {
        return (x + y) % mod;
    }

    static void computeF(int highM) {
        if (highM < 4) highM = 4;
        f = new int[highM + 1];
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        f[3] = 4;
        for (int i = 4; i <= highM; ++i) {
            int x = 0;
            for (int j = 1; j <= 4; ++j) {
                x = add(x, f[i - j]);
            }
            f[i] = x;
        }
    }

    static int solve(int n, int m) {
        int[] g = new int[m + 1], h = new int[m + 1];
        g[1] = 1;
        h[1] = 0;
        for (int i = 2; i <= m; ++i) {
            int x = 0;
            for (int j = 1; j < i; ++j) {
                x = add(x, mult(g[j], g[i - j]));
                x = add(x, mult(g[j], h[i - j]));
            }
            h[i] = x;
            g[i] = (pow(f[i], n)+ mod - h[i])%mod;
            //g[i] = add(pow(f[i], n), mod - h[i]);
        }
        return g[m];
    }

    static void input(int n, int m) {
        int highM = 0;

        if (m > highM) highM = m;
        computeF(highM);
        System.out.println(solve(n, m));
    }

    public static void main(String[] args) {
        int n = 529;
        int m = 190;
        input(n,m);
    }
}
