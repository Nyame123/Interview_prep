package com.bis.interview_prep.combinatoricsGameTheory.easy;

import java.util.ArrayList;
import java.util.List;

public class CountSignals {

    public static void main(String[] args) {
        List<Integer> freqs = new ArrayList<>();
        freqs.add(6);
        freqs.add(7);
        freqs.add(12);
        freqs.add(14);
        freqs.add(3);
        freqs.add(2);

        List<List<Integer>> rangde = new ArrayList<>();
        List<Integer> ra = new ArrayList<>();
        ra.add(10);
        ra.add(20);
        rangde.add(ra);

        List<Integer> ra1 = new ArrayList<>();
        ra1.add(5);
        ra1.add(15);
        rangde.add(ra1);

        List<Integer> ra2 = new ArrayList<>();
        ra2.add(5);
        ra2.add(30);
        rangde.add(ra2);

        System.out.println(countSIgnals(freqs, rangde));
    }


    static int countSIgnals(List<Integer> freq, List<List<Integer>> filters) {
        int low = filters.get(0).get(0);
        int high = filters.get(0).get(1);

        for (int i = 1; i < filters.size(); i++) {
            List<Integer> filter = filters.get(i);
            low = Math.max(low, filter.get(0));
            high = Math.min(high, filter.get(1));
        }

        int count = 0;
        for (int i = 0; i < freq.size(); i++) {
            int fre = freq.get(i);
            if (fre >= low && fre <= high) {
                count++;
            }
        }

        return count;
    }
}
