package com.bis.interview_prep.greedy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ClosestNumber {

    public static void main(String[] args) throws IOException {
        File file = new File(System.getProperty("user.home"), "/Desktop/testCases/closest_number.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int numbersCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> numbers = IntStream.range(0, numbersCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        closestNumbers(numbers);

        bufferedReader.close();
    }

    public static void closestNumbers(List<Integer> numbers) {
        // Write your code here
        int n = numbers.size();

        //Collections.sort()
        mergeSort(numbers,0,n-1);
        System.out.println(numbers);

        int min = numbers.get(1) - numbers.get(0);
        for(int i = 2; i < n; i++){
            min = Math.min(min,numbers.get(i) - numbers.get(i-1));
        }

        for(int i = 1; i < n; i++){
            if((numbers.get(i) - numbers.get(i-1)) == min){
                System.out.print(numbers.get(i-1)+ " " + numbers.get(i));
                System.out.println();
            }
        }
    }

    static void sort(List<Integer> arr, int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r - m;
        //System.out.println(n1);
        //System.out.printf("l %d r %d mid %d\n",l,r,m);
        int left[] = new int[n1];
        int right[] = new int[n2];
        for(int i = 0; i < n1; i++){
            left[i] = arr.get(l+i);
        }

        for(int i = 0; i < n2; i++){
            right[i] = arr.get(m+i+1);
        }

        int i = 0; int j = 0;
        int k = l;
        while(i < n1 && j < n2){
            if(left[i] <= right[j]){
                arr.set(k,left[i]);
                i++;
            }else{
                arr.set(k,right[j]);
                j++;
            }
            k++;
        }

        while(i < n1){
            arr.set(k,left[i]);
            i++;
            k++;
        }

        while(j < n2){
            arr.set(k,right[j]);
            j++;
            k++;
        }
    }

    static void mergeSort(List<Integer> arr, int l, int h){
        if(l < h){
            int m = l + (h-l)/2;

            mergeSort(arr, l, m);
            mergeSort(arr,m+1,h);
            sort(arr,l,m,h);
        }
    }
}


class Results {

    /*
     * Complete the 'closestNumbers' function below.
     *
     * The function accepts INTEGER_ARRAY numbers as parameter.
     */





}
 class Solution11 {

}

