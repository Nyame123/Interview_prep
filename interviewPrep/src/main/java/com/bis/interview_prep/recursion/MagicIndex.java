package com.bis.interview_prep.recursion;

/**
 * Find a value which is equal to the index of the array
 * 1. when the values are sorted and distinct
 * 2. when the values are sorted and not distinct
 **/
public class MagicIndex {

    public static void main(String[] args) {
        int[] sortedArrDistinct = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        int[] sortedArrNotDistinct = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};

        int magicIndex = findMagicIndexRec(sortedArrNotDistinct);
        System.out.println(magicIndex);
    }


    private static int findMagicIndexRec(int[] sortedArrDistinct) {

        if (sortedArrDistinct.length == 0)
            return -1;

        return magicIndexInDistinct(sortedArrDistinct, 0, sortedArrDistinct.length - 1);

    }

    //Time Complexity = O(Log(n))
    //using binary search since they are sorted
    static int magicIndexInDistinct(int[] sortedArrInDistinct, int start, int end) {
        //base case
        if (end < start)
            return -1;

        int mid = start + (end - start) / 2;
        if (mid == sortedArrInDistinct[mid])
            return mid;

        //pick the min of mid-1 and midlValue
        int leftIndex = magicIndexDistinct(sortedArrInDistinct, start, Math.min(mid - 1,sortedArrInDistinct[mid]));
        if (leftIndex >= 0)
            return leftIndex;

        //pick the min of mid+1 and midlValue
        int rightIndex = magicIndexDistinct(sortedArrInDistinct, Math.max(mid + 1,sortedArrInDistinct[mid]), end);
        if (rightIndex >= 0)
            return rightIndex;


        return -1;
    }

    //Time Complexity = O(Log(n))
    //using binary search since they are sorted
    static int magicIndexDistinct(int[] sortedArrDistinct, int start, int end) {
        //base case
        if (end < start)
            return -1;

        int mid = start + (end - start) / 2;
        if (mid == sortedArrDistinct[mid])
            return mid;
        else if (mid < sortedArrDistinct[mid]) {
            int leftIndex = magicIndexDistinct(sortedArrDistinct, start, mid - 1);
            if (leftIndex >= 0)
                return leftIndex;

        } else {
            int rightIndex = magicIndexDistinct(sortedArrDistinct, mid + 1, end);
            if (rightIndex >= 0)
                return rightIndex;

        }
        return -1;
    }

    //Time Complexity = O(n)
    //Works for both distinct and indistinct values
    private static int findMagicIndexIte(int[] sortedArrDistinct) {

        for (int i = 0; i < sortedArrDistinct.length; i++) {
            if (i == sortedArrDistinct[i]) {
                return i;
            }
        }

        return -1;
    }
}
