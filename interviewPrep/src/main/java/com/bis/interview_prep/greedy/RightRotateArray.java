package com.bis.interview_prep.greedy;

import java.util.Arrays;

public class RightRotateArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 2;
        int[] res = rightRotateArrGcd(arr,k);
        System.out.println(Arrays.toString(res));
    }


    /**
     * One method is using auxiliary array
     * 1. Copy first the last k items.
     * 2. Copy the rest of the array into the auxiliary arr
     * 3. In the right clockwise rotation, k = n-k;
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     * */
    private static int[] rightRotate(int[] arr,int k) {
        int n = arr.length;
        int[] aux = new int[n];
        k = n - k;
        int j = 0;
        for (int i = k; i < n; i++) {
            aux[j++] = arr[i];
        }

        for (int i = 0; i < k; i++) {
            aux[j++] = arr[i];
        }

        return aux;
    }

    private static int[] rightRotateOnePass(int[] arr,int k) {
        int n = arr.length;
        int[] aux = new int[n];

        for (int i = 0; i < n; i++) {
            int j = (i + k) % n;
            aux[j] = arr[i];
        }

        return aux;
    }

    /**
     * Another solution is to divide the array into blocks
     * 1. We use GCD as a block for division.
     * 2. We copy to next position by length of gcd + k;
     *
     * Time Complexity = O(N)
     * */

    static int[] rightRotateArrGcd(int[] arr,int k){
        int n = arr.length;
        k = n-k;
        int gcd = gcd(n,k);

        int l,j,temp;
        for (int i = 0; i < gcd; i++) {
            j = i;
            temp = arr[i];
            while (true){
                l = j + k;
                if (l >= n){
                    l = l-n;
                }
                if (l == i){
                    break;
                }

                arr[j] = arr[l];
                j = l;
            }

            arr[j] = temp;
        }

        return arr;
    }
    static int gcd(int a,int b){
        return a % b == 0? Math.abs(b): gcd(b,a%b);
    }

    /**
     * We can improve the above solution by using reverse method.
     * 1. We reverse the last k item.
     * 2. We reverse the first n-k item
     * 3. We reverse the whole array
     *
     *Time Complexity = O(N)
     **/

    static int[] rightRotateReverse(int[] arr,int k){
        int n = arr.length;
        //We reverse the last k item.
        reverseUtil(arr,n-k,n-1);

        //We reverse the first n-k item
        reverseUtil(arr,0,n-k-1);

        //We reverse the whole array
        reverseUtil(arr,0,n-1);

        return arr;
    }

    static void reverseUtil(int[] arr,int low, int high){

        for (int i = low,j = high; i < j; i++,j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

class LeftRotateArray{
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 2;
        int[] res = leftRotateArrGcd(arr,k);
        System.out.println(Arrays.toString(res));
    }

    /**
     * One method is using auxiliary array
     * 1. Copy first the last k items.
     * 2. Copy the rest of the array into the auxiliary arr
     * 3. In the right clockwise rotation, k = n-k;
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     * */
    private static int[] leftRotate(int[] arr,int k) {
        int n = arr.length;
        int[] aux = new int[n];
        int j = 0;
        for (int i = k; i < n; i++) {
            aux[j++] = arr[i];
        }

        for (int i = 0; i < k; i++) {
            aux[j++] = arr[i];
        }

        return aux;
    }

    private static int[] leftRotateOnePass(int[] arr,int k) {
        int n = arr.length;
        int[] aux = new int[n];

        for (int i = 0; i < n; i++) {
            int j = (i + n-k) % n;
            aux[j] = arr[i];
        }

        return aux;
    }

    /**
     * We can improve the above solution by using reverse method.
     * 1. We reverse the last k item.
     * 2. We reverse the first n-k item
     * 3. We reverse the whole array
     *
     *Time Complexity = O(N)
     **/

    static int[] leftRotateReverse(int[] arr,int k){
        int n = arr.length;
        k = n-k;
        //We reverse the last k item.
        reverseUtil(arr,n-k,n-1);

        //We reverse the first n-k item
        reverseUtil(arr,0,n-k-1);

        //We reverse the whole array
        reverseUtil(arr,0,n-1);

        return arr;
    }
    static void reverseUtil(int[] arr,int low, int high){

        for (int i = low,j = high; i < j; i++,j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * Another solution is to divide the array into blocks
     * 1. We use GCD as a block for division.
     * 2. We copy to next position by length of gcd + k;
     *
     * Time Complexity = O(N)
     * */

    static int[] leftRotateArrGcd(int[] arr,int k){
        int n = arr.length;
        int gcd = gcd(n,k);

        int l,j,temp;
        for (int i = 0; i < gcd; i++) {
            j = i;
            temp = arr[i];
            while (true){
                l = j + k;
                if (l >= n){
                    l = l-n;
                }
                if (l == i){
                    break;
                }

                arr[j] = arr[l];
                j = l;
            }

            arr[j] = temp;
        }

        return arr;
    }
    static int gcd(int a,int b){
        return a % b == 0? Math.abs(b): gcd(b,a%b);
    }
}
