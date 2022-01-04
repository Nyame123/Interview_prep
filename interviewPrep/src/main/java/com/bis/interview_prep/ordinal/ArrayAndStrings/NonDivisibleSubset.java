package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *  Given a set of distinct integers, print the
 *  size of a maximal subset of S where the sum of any 2 numbers in S' is not evenly divisible by K.
 **/
public class NonDivisibleSubset {

    public static void main(String[] args) {

        List<Integer> sets = Arrays.asList(1 ,2 ,3 ,4 ,5 ,6, 7 ,8 ,9,10);
        int k = 4;
        int res = nonDivisibleSubset(k,sets);
        System.out.println(res);
    }
    //Time Complexity = O(n+k)
    public static int nonDivisibleSubset(int k, List<Integer> s) {
        // Write your code here
        int n = s.size();
        int ans = 0;
        int[] results = new int[k];
        //reduce set by k
        for(int i = 0; i < n; i++){
            results[s.get(i)%k] += 1;
        }

        ans = Math.min(1,results[0]);
        for(int i = 1; i < k/2+1; i++){
            int diff = k-i;
            if(diff == i){
                ans += 1;
            }else{
                ans += Math.max(results[diff],results[i]);
            }
        }

        return ans;
    }

    public static int nonDivisibleSubset2(int k, List<Integer> s) {
        // Write your code here
        //base case
        if(k == 1 || k == 0){
            return 1;
        }

        List<Integer> resultSet = new ArrayList<>();
        int n = s.size();
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        List<Integer> visited = new ArrayList<>();

        //reduce set by k
        for(int i = 0; i < n; i++){
            resultSet.add(s.get(i)%k);
        }

        //build frequency
        for(int i = 0; i < n; i++){
            int key = resultSet.get(i);
            freqMap.putIfAbsent(key,0);
            int oldVal = freqMap.get(key);
            freqMap.put(key,oldVal+1);
        }

        int ans = 0;
        //building array count of items with no two item evenly divisble by k
        for(int key: freqMap.keySet()){
            int diff = k-key;
            visited.add(key);
            if(!visited.contains(diff)){
                //if the diff is same as the key
                if(diff == key){
                    if(diff > 0){
                        ans += 1;
                        visited.add(diff);
                    }
                }else{
                    if(freqMap.containsKey(diff)){
                        //pick the maximum frequency
                        visited.add(diff);
                        ans += Math.max(freqMap.get(key),freqMap.get(diff));
                    }else{
                        ans += freqMap.get(key);
                    }
                }

            }

            System.out.println(ans);
        }

        System.out.println(resultSet);
        System.out.println(freqMap);
        return ans;

    }

    public static int nonDivisibleSubset3(int k, List<Integer> s) {
        // Write your code here
        List<Integer> resultSet = new ArrayList<>();
        int n = s.size();
        for(int i = 0; i < n; i++){
            int m = resultSet.size();
            int item = s.get(i);
            boolean include = true;
            for(int j = 0; j < m; j++){
                if((resultSet.get(j)+item) % k == 0){
                    include = false;
                }
            }

            if(include){
                resultSet.add(item);
            }
        }

        int ans = resultSet.size();
        System.out.println(resultSet);
        return ans;

    }
}
