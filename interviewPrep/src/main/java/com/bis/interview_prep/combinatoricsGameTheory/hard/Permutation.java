package com.bis.interview_prep.combinatoricsGameTheory.hard;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
        Permutation per = new Permutation();
        //per.permutation("abis");
        per.permutationRec("abc","");
    }
    void permutation(String str) {
         permutation(str, "");
    }

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring ( i + 1);
                permutation(rem, prefix + str.charAt( i));
            }
        }
    }

    void permutationRec(String str,String sb){

        if (str.length() == 0){
            System.out.println(sb);
        }else {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                String s = str.substring(0, i) + str.substring(i + 1);
                permutationRec(s, sb+(c));
            }
        }
    }
}

class SquareRoot{

    public static void main(String[] args) {
        System.out.println(squareRootBinarySearch(2147395599));

    }

    static void squareRootWithRec(int n){
       System.out.println(squareRootWithRec(n,1,n));
    }

    static int squareRootWithRec(int n, int min, int max){
        if (max < min){
            return -1; //Not perfect square
        }

        int guess = (min+max) / 2;
        if (guess*guess == n){
            return guess;
        }else if (guess*guess < n){ //too low
           return squareRootWithRec(n,min+1,max);
        }else{  //too high
            return squareRootWithRec(n,min,max-1);
        }

    }

    static int squareRootBinarySearch(int n){
        if (n == 0){
            return 0;
        }
        int left = 1, right = n;

        while (true){
            int mid = left + (right-left)/2;

            if (mid > (n/mid)){
                right = mid-1;
            }else {
                if (mid+1 < n/(mid+1)){
                    return mid;
                }

                left = mid+1;
            }
        }
    }


    static void squareRootIterative(int n){
        int sum = 0;
        for (int i = 0; i*i <= n; i++){
            if (i*i == n){

                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}
class PermutationNumbersArray {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        PermutationNumbersArray solution = new PermutationNumbersArray();
        List<List<Integer>> res = solution.permute(arr);
        for(List<Integer> ans: res){
            System.out.println(ans);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        int n  = nums.length;
        List<Integer> ans = new ArrayList<>(n);
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        permute(nums,ans,results,visited);
        return results;
    }

    void permute(int[] nums,List<Integer> sol, List<List<Integer>> list,boolean[] visited){
        //base case
        if(nums.length == sol.size()){
            list.add(new ArrayList<>(sol));
        }else{
            for(int i = 0; i < nums.length; i++){
                if (visited[i]){
                    continue;
                }
                int offset = nums[i];
                sol.add(offset);
                visited[i] = true;
                permute(nums,sol,list,visited);
                sol.remove(sol.size()-1);
                visited[i] = false;
            }
        }
    }
}