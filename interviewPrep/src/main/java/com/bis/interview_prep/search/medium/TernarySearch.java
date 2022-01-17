package com.bis.interview_prep.search.medium;

public class TernarySearch {

    public static void main(String[] args) {
        int arr[] = { 10, 12, 13, 16, 18, 19, 20, 21,
                22, 23, 24, 33, 35, 42, 47 };

        int n = arr.length;
        int x = 18;
        int index = ternarySearch(arr, 0, n - 1, x);

        // If element was found
        if (index != -1)
            System.out.println("Element found at index "
                    + index);
        else
            System.out.println("Element not found.");
    }

    static int ternarySearch(int[] arr, int l,int h, int x){
        if (l <= h){
            int mid1 = l + (h-l)/3;
            int mid2 = mid1 + (h-l)/3;

            if (arr[mid1] == x){
                return mid1;
            }else if (arr[mid2] == x){
                return mid2;
            }else if (arr[mid1] > x){
                return ternarySearch(arr,0,mid1-1,x);
            }else if (arr[mid2] < x){
                return ternarySearch(arr,mid2+1,h,x);
            }

            return ternarySearch(arr,mid1+1,mid2-1,x);
        }

        return -1;
    }
}
