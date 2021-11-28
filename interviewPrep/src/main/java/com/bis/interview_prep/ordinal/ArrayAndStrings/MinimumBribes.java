package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

public class MinimumBribes {

    public static void main(String[] args) {

        //int[] arr = {1,2,5,3,7,8,6,4};
        int[] arr = {5, 1, 2, 3, 7, 8, 6, 4};
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(7);
        list.add(8);
        list.add(6);
        minimumBribes(list);
    }

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int n = q.size();
        int sum = 0;
        for (int i = n - 1; i > 0; i--) {
            //swapped one pos
            if (q.get(i - 1) == i + 1) {
                q.set(i - 1, q.get(i));
                sum++;
            } else if (i >= 2 && q.get(i - 2) == i + 1) { //swapped two pos
                q.set(i - 2, q.get(i - 1));
                q.set(i - 1, q.get(i));
                sum += 2;
            } else if (q.get(i) != i + 1) {
                System.out.println("Too chaotic");
                return;
            }
        }

        System.out.println(sum);
    }

    public static void minimumSwaps(int[] a) {
        //int[] a = arr.clone();
        int bribes = 0;
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i - 1] == i + 1) {
                a[i - 1] = a[i];
                bribes++;
            } else if (i >= 2 && a[i - 2] == i + 1) {
                a[i - 2] = a[i - 1];
                a[i - 1] = a[i];
                bribes += 2;
            } else if (a[i] != i + 1) {
                System.out.println("Too chaotic");
                return;
            }
        }
        System.out.println(bribes);
    }
}
