package com.bis.interview_prep.search.easy;
/**
 * The Interpolation Search is an improvement over Binary Search for instances,
 * where the values in a sorted array are uniformly distributed. Binary Search always
 * goes to the middle element to check. On the other hand, interpolation search may go
 * to different locations according to the value of the key being searched. For example,
 * if the value of the key is closer to the last element, interpolation search is likely to start search toward the end side.
 * To find the position to be searched, it uses following formula.
 *
 * pos = lo + [ (x-arr[lo])*(hi-lo) / (arr[hi]-arr[Lo]) ]
 *
 * arr[] ==> Array where elements need to be searched
 * x     ==> Element to be searched
 * lo    ==> Starting index in arr[]
 * hi    ==> Ending index in arr[]
 **/
public class InterpolationSearch {

    public static void main(String[] args) {
        int arr[] = { 10, 12, 13, 16, 18, 19, 20, 21,
                22, 23, 24, 33, 35, 42, 47 };

        int n = arr.length;
        int x = 18;
        int index = interpolationSearch(arr, 0, n - 1, x);

        // If element was found
        if (index != -1)
            System.out.println("Element found at index "
                    + index);
        else
            System.out.println("Element not found.");
    }

    static int interpolationSearch(int[] arr,int lo, int hi, int x){
        //base case
        if(lo <= hi && arr[lo] <= x && arr[hi] >= x){

            //calc the position of x
            int pos = lo + ((x-arr[lo]) * ((hi-lo)/(arr[hi] - arr[lo])));

            if (arr[pos] == x){
                return pos;
            }else if (arr[pos] < x){
                return interpolationSearch(arr,pos+1,hi,x);
            } else if (arr[pos] > x) {
                return interpolationSearch(arr,lo,pos-1,x);
            }
        }

        return -1;
    }
}
