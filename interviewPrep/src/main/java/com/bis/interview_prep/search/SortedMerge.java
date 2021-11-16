package com.bis.interview_prep.search;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order
 **/
public class SortedMerge {

    public static void main(String[] args) {

        int n = 8;
        int[] A = new int[n];
        A[0] = 6;
        A[1] = 3;
        A[2] = 2;
        int[] B = {8, 7, 5, 4, 0};

        /**
         A[0] = 1;
         A[1] = 2;
         A[2] = 3;
         int[] B = {0,4,5,7,9};
         * */

        int[] result = sortedMergeFromEnd(A, B);

        for (int a : result) {
            System.out.print(a + ",");
        }
    }

    //without additional data structure but putting
    // the data at the end array A
    //Time Complexity = O(n)
    private static int[] sortedMergeFromEnd(int[] a, int[] b) {
        //go over the elements in the A array
        int diff = a.length - b.length;
        int i = diff-1, j = b.length - 1;
        int mergedIndex = a.length-1;
        while (j >= 0) {
            //if array A is sorted in increasing order
            if (a.length > 1 && a[0] < a[1]) {
                if (i >= 0 && a[i] > b[j]) {
                    a[mergedIndex--] = a[i--];
                } else {
                    a[mergedIndex--] = b[j--];
                }
            } else { //sorted in decreasing order
                if (i >= 0 && a[i] < b[j]) {
                    a[mergedIndex--] = a[i--];
                } else {
                    a[mergedIndex--] = b[j--];
                }

            }
        }

        return a;
    }

    //without additional data structure but putting
    // the data at the immediate back of last item of array A
    //Time Complexity = O(n^2)
    private static int[] sortedMerge(int[] a, int[] b) {
        //go over the elements in the A array
        int diff = a.length - b.length;
        int i = 0, j = 0;
        int n2 = b.length;
        int mergedIndex = a.length;
        while (i < diff) {
            //if array A is sorted in increasing order
            if (a.length > 1 && a[0] < a[1]) {
                if (a[i] > b[j]) {
                    //swap elements
                    j = 0;
                    int temp = a[i];
                    a[i] = b[j];
                    b[j] = temp;
                    //find the right position for swapped element in Array A in B
                    while (j + 1 < n2 && temp > b[j + 1]) {
                        b[j] = b[j + 1];
                        j++;
                    }
                    b[j] = temp;

                }
            } else { //sorted in decreasing order
                if (a[i] < b[j]) {
                    //swap elements
                    j = 0;
                    int temp = a[i];
                    a[i] = b[j];
                    b[j] = temp;
                    //find the right position for swapped element in Array A in B
                    while (j + 1 < n2 && temp < b[j + 1]) {
                        b[j] = b[j + 1];
                        j++;
                    }
                    b[j] = temp;
                }

            }
            i++;
        }

        //merge the resultant arrays i.e (A and B)
        for (int value : b) {
            a[diff++] = value;
        }
        return a;
    }

    //we can apply merge sorting where we merge together the two arrays
    static int[] mergeTwoArrays(int[] a, int[] b) {

        //apply merge sort
        mergeArrays(a, b);

        return a;
    }

    //using additional space or data structure
    private static void mergeArrays(int[] a, int[] b) {

        int k = 0;
        int i = 0, j = 0;
        int n1 = a.length - b.length;
        int n2 = b.length;
        int[] arr = new int[n1];


        for (int in = 0; in < n1; in++) {
            arr[in] = a[in];
        }

        while (i < n1 && j < n2) {
            //if the arrays are sorted in increasing order
            if (a[0] < a[1]) {
                if (arr[i] > b[j]) {
                    a[k++] = b[j++];
                } else {
                    a[k++] = arr[i++];
                }
            } else {
                if (arr[i] < b[j]) {
                    a[k++] = b[j++];
                } else {
                    a[k++] = arr[i++];
                }
            }
        }

        while (i < n1) {
            a[k++] = arr[i++];
        }

        while (j < n2) {
            a[k++] = b[j++];
        }
    }
}
