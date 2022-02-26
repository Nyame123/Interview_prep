package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.math.BigInteger;

public class MaximumPalindromes {

    public static int MOD = 1000000007;
    public static int[][] count;
    public static int[] fact;
    public static int[] MMI;

    public static void main(String[] args) {
        String s = "abab";
        int l = 1,r = 4;
        initialize(s);
        int result = answerQuery(l,r);
        System.out.println(result);
    }

    static int mul(int a,int b){
        return (int) ((long) a * b%MOD);
    }

    public static long power(long a, long prime,long mod){
        if(prime == 0){
            return 1;
        }

        long p = power(a,prime/2,mod) % mod;
        p = (p * p) % mod;
        if(a % 2 == 0){
            return p;
        }

        return (a*p) % mod;
    }

    public static void initialize(String s) {
        // This function is called once before all queries.
        int n = s.length();
        count = new int[n+1][26];
        fact = new int[n+1];
        MMI = new int[n+1];

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = mul(MOD - MOD/i,dp[MOD%i]);
        }
        //calculate the modular multiplicative inverse
        fact[0] = MMI[0] = 1;
        for(int i = 1; i <= n; i++){
            fact[i] = mul(fact[i-1] , i);
            //MMI[i] = power(fact[i],MOD-2,MOD);
            MMI[i] = mul(MMI[i-1],dp[i]);
        }

        //store the occurences of the characters
        for(int i = 1; i <= n; i++){
            count[i][s.charAt(i-1)-'a']++;
        }

        //precompute the storage of the characters
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 26; j++){
                count[i][j] += count[i-1][j];
            }
        }
    }

    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    public static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.

        //group all the occurences of the characters
        int[] array = new int[26];
        for(int i = 0; i < 26; i++){
            array[i] = count[r][i] - count[l-1][i];
        }

        int odd = 0;
        int even = 0;
        int num = 0,den = 1;
        for(int i = 0; i < 26; i++){
            int value = array[i];
            if(value % 2 != 0){
                odd++;
            }
            int div = value/2;
            even += div;
            den = mul(den, MMI[div]);
        }

        num = fact[even];
        int ans = mul(num,den);

        if(odd != 0){
            ans = mul(ans,odd);
        }

        return ans;
    }

}

class MaximumPalindromes1{
    public static int MOD = 1000000007;
    public static int[][] count;
    public static BigInteger[] fact;
    public static BigInteger[] MMI;

    public static void main(String[] args) {
        String s = "madamimadam";
        int l = 4,r = 7;
        initialize(s);
        int result = answerQuery(l,r);
        System.out.println(result);
    }

    public static long power(long a, long prime,long mod){
        if(prime == 0){
            return 1;
        }

        long p = power(a,prime/2,mod) % mod;
        p = (p * p) % mod;
        if(a % 2 == 0){
            return p;
        }

        return (a*p) % mod;
    }

    public static void initialize(String s) {
        // This function is called once before all queries.
        int n = s.length();
        count = new int[n+1][26];
        fact = new BigInteger[n+1];
        MMI = new BigInteger[n+1];

        //calculate the modular multiplicative inverse
        fact[0] = MMI[0] = BigInteger.valueOf(1);
        for(int i = 1; i <= n; i++){
            BigInteger x = BigInteger.valueOf(i%MOD);
            BigInteger y = BigInteger.valueOf(MOD-2);
            BigInteger z = BigInteger.valueOf(MOD);
            fact[i] = (fact[i-1].multiply(x));
            MMI[i] = fact[i].modPow(y,z);
        }

        //store the occurences of the characters
        for(int i = 1; i <= n; i++){
            count[i][s.charAt(i-1)-'a']++;
        }

        //precompute the storage of the characters
        for(int i  = 1; i <= n; i++){
            for(int j = 0; j < 26; j++){
                count[i][j] += count[i-1][j];
            }
        }
    }

    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    public static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.

        //group all the occurences of the characters
        int[] array = new int[26];
        for(int i = 0; i < 26; i++){
            array[i] = count[r][i] - count[l-1][i];
        }

        int odd = 0;
        int even = 0;
        BigInteger num = BigInteger.valueOf(0);
        BigInteger den = fact[0];
        for(int i = 0; i < 26; i++){
            int value = array[i];
            if(value % 2 != 0){
                odd++;
            }
            int div = value/2;
            even += div;
            den = den.multiply(MMI[div]);
            den = den.mod(BigInteger.valueOf(MOD));
        }

        odd = odd > 0 ? odd : 1;
        num = fact[even];
        BigInteger ans = (num.multiply(den).multiply(BigInteger.valueOf(odd)));
        ans = ans.mod(BigInteger.valueOf(MOD));


        return ans.intValueExact();
    }
}
