package com.bis.interview_prep.greedy;

import java.math.BigInteger;
import java.util.Arrays;

public class RotateString {

    public static void main(String[] args) {
        String s = "bbbacddceeb";
        String pat = "ceebbbbacdd";
        System.out.println(rotateString1(s, pat));
    }

    /**
     * This checks all the possible shifts in the string to get
     * the pattern.
     * First, it checks from shift [0 ... n) to see where the shift starts from.
     * When the shift is wrong, it uses Java goto construct to move to the next shift.
     * This is done until a shift is h
     *
     * Time Complexity = O(N^2)
     * */
    static boolean rotateString1(String A, String B) {
        if (A.length() != B.length())
            return false;
        if (A.length() == 0)
            return true;

        nextShift:
        for (int s = 0; s < A.length(); ++s) {
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt((s + i) % A.length()) != B.charAt(i))
                    continue nextShift;
            }
            return true;
        }
        return false;
    }

    /**
     * Using a concatenation approach
     * duplicate the original String and check if the combined
     * String contains the String pat
     * <p>
     * Time Complexity = O(N^2)
     **/
    static boolean rotateString(String s, String s2) {
        return (s.length() == s2.length()) && (s + s).contains(s2);
    }


    /**
     *
     *Approach #3: Rolling Hash [Accepted]
     * Intuition
     *
     * Our approach comes down to quickly checking whether want to check whether B is a substring of A2 = A+A. Specifically,
     * (if N = A.length) we should check whether B = A2[0:N], or B = A2[1:N+1], or B = A2[2:N+2] and so on. To check this,
     * we can use a rolling hash.
     *
     * Algorithm
     *
     * For a string S, say hash(S) = (S[0] * P**0 + S[1] * P**1 + S[2] * P**2 + ...) % MOD, where X**Y represents
     * exponentiation, and S[i] is the ASCII character code of the string at that index.
     *
     * The idea is that hash(S) has output that is approximately uniformly distributed between [0, 1, 2, ..., MOD-1],
     * and so if hash(S) == hash(T) it is very likely that S == T.
     *
     * Now say we have a hash hash(A), and we want the hash of A[1], A[2], ..., A[N-1], A[0]. We can subtract A[0] from
     * the hash, divide by P, and add A[0] * P**(N-1). (Our division is under the finite field \mathbb{F}_\text{MOD}F
     * MOD
     * ​
     *   - done by multiplying by the modular inverse Pinv = pow(P, MOD-2, MOD).)
     *
     *   Time Complexity: O(N), where N is the length of A.
     *
     * Space Complexity: O(N), to perform the final check A_rotation == B.
     **/
    public boolean rotateStringRollingHash(String A, String B) {
        if (A.equals(B)) return true;

        int MOD = 1_000_000_007;
        int P = 113;
        int Pinv = BigInteger.valueOf(P).modInverse(BigInteger.valueOf(MOD)).intValue();

        long hb = 0, power = 1;
        for (char x: B.toCharArray()) {
            hb = (hb + power * x) % MOD;
            power = power * P % MOD;
        }

        long ha = 0; power = 1;
        char[] ca = A.toCharArray();
        for (char x: ca) {
            ha = (ha + power * x) % MOD;
            power = power * P % MOD;
        }

        for (int i = 0; i < ca.length; ++i) {
            char x = ca[i];
            ha += power * x - x;
            ha %= MOD;
            ha *= Pinv;
            ha %= MOD;
            if (ha == hb && (A.substring(i+1) + A.substring(0, i+1)).equals(B))
                return true;

        }
        return false;
    }

    /**
     *Approach #4: KMP (Knuth-Morris-Pratt) [Accepted]
     * Intuition
     *
     * As before, we want to find whether B exists in A+A. The KMP algorithm is a textbook algorithm that does string
     * matching in linear time, which is faster than brute force.
     *
     * Algorithm
     *
     * The algorithm is broken up into two steps, building the shifts table (or failure table), and using it
     * to find whether a match exists.
     *
     * The shift table tells us about the largest prefix of B that ends here. More specifically, B[:shifts[i+1]] == B[i - shifts[i+1] : i]
     * is the largest possible prefix of B ending before B[i].
     *
     * To build the shift table, we use a dynamic programming approach, where all previously calculated values of shifts are correct.
     * Then, left will be the end of the candidate prefix of B, and right will be the end of the candidate section that should match the prefix B[0], B[1], ..., B[left]. Call positions (left, right) "matching" if the prefix ending at B[left] matches the same length string ending at B[right]. The invariant in our loop will be that (left - 1, right - 1) is matching by the end of each for-block.
     *
     * In a new for-block, if (left, right) is matching (ie. (left - 1, right - 1) is matching from before, plus B[left] == B[right]),
     * then we know the shift (right - left) is the same number as before. Otherwise, when (left, right) is not matching, we need to find a shorter prefix.
     *
     * Our strategy is to find a matching of (left2, right) where left2 < left, by finding matchings (left2 - 1, right - 1) plus checking
     * B[left2] == B[right]. Since (left - 1, right - 1) is a matching, by transitivity we want to find matchings (left2 - 1, left - 1). The largest such left2 is left2 = left - shifts[left]. We repeatedly check these left2's in greedy order from largest to smallest.
     *
     * To find a match of B in A+A with such a shift table ready, we employ a similar strategy. We maintain a matching
     * (match_len - 1, i - 1), where these positions correspond to strings of length match_len that end at B[match_len - 1]
     * and (A+A)[i-1] respectively.
     *
     * Now when trying to find the largest length matching for (A+A) at position i, it must be at most (match_len - 1) + 1,
     * where the quantity in brackets is the largest length matching to position i-1.
     *
     * Again, our strategy is to find a matching (match_len2 - 1, i - 1) plus check that B[match_len2] == (A+A)[i].
     * Similar to before, if B[match_len] != (A+A)[i], then because (match_len - 1, i - 1) was a matching,
     * by transitivity (match_len2 - 1, match_len - 1) must be a matching, of which the largest is
     * found by match_len2 = match_len - shifts[match_len]. We also repeatedly check these match_len's in order from largest to smallest.
     *
     * If at any point in this algorithm our match length is N, we've found B in A+A successfully.
     *
     *Time Complexity: O(N), where N is the length of A.
     *
     * Space Complexity: O(N), to create the shift table shifts.
     **/

    public boolean rotateStringKMP(String A, String B) {
        int N = A.length();
        if (N != B.length()) return false;
        if (N == 0) return true;

        //Compute shift table
        int[] shifts = new int[N+1];
        Arrays.fill(shifts, 1);
        int left = -1;
        for (int right = 0; right < N; ++right) {
            while (left >= 0 && (B.charAt(left) != B.charAt(right)))
                left -= shifts[left];
            shifts[right + 1] = right - left++;
        }

        //Find match of B in A+A
        int matchLen = 0;
        for (char c: (A+A).toCharArray()) {
            while (matchLen >= 0 && B.charAt(matchLen) != c)
                matchLen -= shifts[matchLen];
            if (++matchLen == N) return true;
        }

        return false;
    }
}
