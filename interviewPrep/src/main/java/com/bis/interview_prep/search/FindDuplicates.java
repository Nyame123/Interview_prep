package com.bis.interview_prep.search;
/**
 * You have an array with all the numbers from 1 to N, where N is at most 32,000. The
 * array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory
 * available, how would you print all duplicate elements in the array?
 **/
public class FindDuplicates {

    public static void main(String[] args) {
        /**
         * Integers from 1 to 32000 with a memory of size 4KB.
         * 4KB = 4*8*2^10 = 32768. It means that we can use 4KB to
         * store integers from 1 t0 32000.
         *
         * We can divide the integers into a block of 32.ie (N >> 5)
         **/

        int[] arr = {1,1,2,2,3,12,2,1,3,4,5,2,3,4};
        findDuplicates(arr,3);

    }

    static void findDuplicates(int[] arr,int blockSize){

        byte[] bytes = new byte[(arr.length >> blockSize) + 1];

        for (int i = 0; i < arr.length; i++) {
            int block = arr[i] >> blockSize;
            int mod = arr[i] & (1 << blockSize);

            if ((bytes[block] & (1 << mod)) == 0){
                bytes[block] |= (1 << mod);
            }else{
                System.out.println(arr[i]);
            }

        }
    }
}
