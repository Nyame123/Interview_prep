package com.bis.interview_prep.search;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array-like data structure Listy which lacks a size
 * method. It does, however, have an elementAt (i) method that returns the element at index i in
 * 0(1) time. If i is beyond the bounds of the data structure, it returns - 1. (For this reason, the data
 * structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
 * find the index at which an element x occurs. If x occurs multiple times, you may return any index
 **/

public class SortedSearchNoSize {

    public static void main(String[] args) {
        Listy listy = new Listy();
        listy.add(1);
        listy.add(3);
        listy.add(4);
        listy.add(5);
        listy.add(16);
        listy.add(19);
        listy.add(23);
        listy.add(24);
        listy.add(40);
        listy.add(50);

        int index = getIndexSortedSearch(listy, 51);
        System.out.println(index);
    }

    //using unbounded binary search
    //Time complexity = O(logn)
    private static int getIndexSortedSearch(Listy listy, int item) {
        if (listy.elementAt(0) == item) {
            return 0;
        }

        int i = 1;
        int prevIndex = i;
        while (listy.elementAt(i) != -1 && listy.elementAt(i) < item) {
            prevIndex = i;
            i *= 2;
        }

        while (prevIndex <= i) {
            int mid = prevIndex + (i - prevIndex) / 2;
            int cur = listy.elementAt(mid);
            if (cur == item) {
                return mid;
            } else if (cur > item || cur == -1) {
                i = mid - 1;
            } else {
                prevIndex = mid + 1;
            }
        }

        return -1;
    }


    static class Listy {
        List<Integer> listy;

        public Listy() {
            this.listy = new ArrayList<>();
        }

        public void add(int item) {
            listy.add(item);
        }

        public int elementAt(int i) {
            return i < listy.size() ? listy.get(i) : -1;
        }
    }
}
