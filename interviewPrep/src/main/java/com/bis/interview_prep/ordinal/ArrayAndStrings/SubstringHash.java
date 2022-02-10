package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * The hash of a 0-indexed string s of length k, given integers p and m, is computed using the following function:
 * <p>
 * hash(s, p, m) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) mod m.
 * Where val(s[i]) represents the index of s[i] in the alphabet from val('a') = 1 to val('z') = 26.
 * <p>
 * You are given a string s and the integers power, modulo, k, and hashValue. Return sub, the first substring of s
 * of length k such that hash(sub, power, modulo) == hashValue.
 * <p>
 * The test cases will be generated such that an answer always exists.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0
 * Output: "ee"
 * Explanation: The hash of "ee" can be computed to be hash("ee", 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0.
 * "ee" is the first substring of length 2 with hashValue 0. Hence, we return "ee".
 * Example 2:
 * <p>
 * Input: s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32
 * Output: "fbx"
 * Explanation: The hash of "fbx" can be computed to be hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 312)
 * mod 100 = 23132 mod 100 = 32.
 * The hash of "bxz" can be computed to be hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 312) mod 100 = 25732 mod 100 = 32.
 * "fbx" is the first substring of length 3 with hashValue 32. Hence, we return "fbx".
 * Note that "bxz" also has a hash of 32 but it appears later than "fbx".
 **/
public class SubstringHash {
    public static void main(String[] args) {
        String s = "leetcode";
        int power = 7, k = 2, mod = 20, hashValue = 0;

        String res = subStrHash(s, power, k, mod, hashValue);
        System.out.println(res);
    }

    static String substringHash(String s, int p, int k, int mod, int hash) {
        int res = 0, n = s.length();
        long cur = 0, pk = 1;

        //using rolling hash method
        for (int i = n - 1; i >= 0; i--) {
            cur = (cur * p + s.charAt(i) - 'a' + 1) % mod;

            if (i + k >= n) {
                pk = pk * p % mod;
            } else {
                cur = (cur - (s.charAt(i + k) - 'a' + 1) * pk % mod + mod) % mod;
            }
            if (cur == hash) {
                res = i;
            }
        }

        return s.substring(res, res + k);
    }

    static String subStrHash(String s, int power,int k,int modulo, int hashValue) {
        long[] p = new long[k]; //to store all the power values beforehand to avoid redundant calculations
        for (int i = 0; i < k; i++) {
            p[i] = binpow(power, i, modulo);
        }

        p = powerk(k,power,modulo);

        for (int j = 0; j < s.length() - k + 1; j++) {
            long hash = 0;
            for (int i = j; i < j + k; i++) {
                hash += (s.charAt(i) - 'a' + 1) * p[i - j];
            }
            hash %= modulo;
            if (hash == hashValue)
                return s.substring(j, j + k);
        }
        return "";
    }

    static long[] powerk(int k,int a,int mod){
        long[] power = new long[k];
        power[0] = 1;
        for (int i = 1; i < k; i++) {
            power[i] = ((power[i-1]%mod)*a)%mod;
        }

        return power;
    }

    // function to calculate (a^n)mod(n) in O(log(n)) time
    static long binpow(long a, long b, long m) {
        a %= m;
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1)
                res = res * a % m;
            a = a * a % m;
            b >>= 1;
        }
        return res;
    }

}
