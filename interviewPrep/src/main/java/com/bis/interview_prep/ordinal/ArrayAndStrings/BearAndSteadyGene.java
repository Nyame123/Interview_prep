package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

/**
 * A gene is represented as a string of length n (where  is divisible by 4), composed of the letters A, C, T, and G.
 * It is considered to be steady if each of the four letters occurs exactly N/4 times. For example, GCAT and AAGTGCCT are
 * both steady genes.
 * Bear Limak is a famous biotechnology scientist who specializes in modifying bear DNA to
 * make it steady. Right now, he is examining a gene represented as a string gene.
 * It is not necessarily steady. Fortunately, Limak can choose one (maybe empty) substring of gene
 * and replace it with any string of the same length.
 * <p>
 * Modifying a large substring of bear genes can be dangerous. Given a string gene,
 * can you help Limak find the length of the smallest possible substring that he can replace to make gene a steady gene?
 * Note: A substring of a string  is a subsequence made up of zero or more contiguous characters of s.
 * <p>
 * As an example, consider . The substring  just before or after  can be replaced with  or . One selection would create .
 **/
public class BearAndSteadyGene {

    public static void main(String[] args) {
        String gene = "ACTGAAAG";
        int result = steadyGene(gene);

        System.out.println(result);
    }

    //checking for the validity of the genes
    static boolean isValid(int n, HashMap<Character, Integer> map) {
        if (map.get('C') <= n && map.get('T') <= n && map.get('G') <= n && map.get('A') <= n) {
            return true;
        }

        return false;
    }

    public static int steadyGene(String gene) {
        // Write your code here
        //Compute the frequencies of the C,T,G,A
        HashMap<Character, Integer> freq = new HashMap<>(4);
        freq.put('C', 0);
        freq.put('T', 0);
        freq.put('G', 0);
        freq.put('A', 0);
        int n = gene.length();
        for (int i = 0; i < n; i++) {
            char c = gene.charAt(i);
            freq.put(c, freq.get(c) + 1);
        }

        //valid gene number
        int validNumber = n / 4;
        int min = Integer.MAX_VALUE;
        //we traverse the gene in search for a replacement
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (!isValid(validNumber, freq)) {
                char c = gene.charAt(j);
                freq.put(c, freq.get(c) - 1);
                j++;
                //System.out.println(freq);
            } else {
                min = Math.min(min, j - i);
                char c = gene.charAt(i);
                freq.put(c, freq.get(c) + 1);
                //System.out.println(freq);
                i++;
            }
        }

        return min;
    }
}
