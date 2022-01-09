package com.bis.interview_prep.search.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MinimumSwaps {

    public static int lilysHomework(List<Integer> arr) {
        // Write your code here
        //in ascending order of arr items
        int n = arr.size();
        List<Integer> reversed = new ArrayList<>();
        for(int i = 0; i < n; i++){
            reversed.add(arr.get(n-i-1));
        }
        int asc = itemSwapped(arr);
        int desc = itemSwapped(reversed);

        //System.out.println(arr);
        return Math.min(desc,asc);
    }

    public static int itemSwapped(List<Integer> arr){
        int n = arr.size();
        HashMap<Integer,Integer> map = new HashMap<>(n);
        int[] copy = new int[n];
        for(int i = 0; i < n; i++){
            int it = arr.get(i);
            map.put(it,i);
            copy[i] = it;
        }

        //sort the copy
        Arrays.sort(copy);
        int count = 0;
        for(int i = 0; i < n; i++){
            int scv = copy[i];
            int it = arr.get(i);
            int oldIndex = map.get(scv);
            if(scv != it){
                count++;

                //swap the items
                arr.set(i,scv);
                arr.set(oldIndex,it);

                //update map
                //map.put(scv,i);
                map.put(it,oldIndex);
            }

        }

        return count;
    }

}

class LilysHomework {

    private static final Scanner scn = new Scanner(System.in);

    private static void swap(long[] array, int index1, int index2) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static int swaps(long[] unsortedValues) {
        int swaps = 0;

        Map<Long, Integer> locations = new HashMap<>();
        for (int i = 0; i < unsortedValues.length; i++) {
            locations.put(unsortedValues[i], i);
        }

        long [] sortedValue = unsortedValues.clone();
        Arrays.sort(sortedValue);

        for (int i = 0; i < sortedValue.length; i++) {
            if (sortedValue[i] != unsortedValues[i]) {
                swaps++;

                int swapIndex = locations.get(sortedValue[i]);
                locations.put(unsortedValues[i], swapIndex);

                swap(unsortedValues, i, swapIndex);
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        int numberOfElements = scn.nextInt();
        long[] values = new long[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            int value = scn.nextInt();
            values[i] = value;
        }
        // When all you have is a hammer, everything begins to look like a nail.
        long [] reverseValue = IntStream.rangeClosed(1, values.length).mapToLong(
                i -> values[values.length - i]).toArray();
        System.out.println(Math.min(swaps(values), swaps(reverseValue)));

    }
}
