package com.bis.interview_prep.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You have a pile of  stones that you want to split into multiple piles, as well as a set, ,
 * of  distinct integers. We define a move as follows:
 *
 * First, choose a pile of stones. Let's say that the chosen pile contains  stones.
 * Next, look for some  such that  and  is divisible by  (i.e.,  is a factor of ); if such an
 * exists, you can split the pile into  equal smaller piles.
 * You are given  queries where each query consists of  and . For each query, calculate the maximum
 * possible number of moves you can perform and print it on a new line.
 *
 * Input Format
 *
 * The first line contains an integer, , denoting the number of queries. The  subsequent
 * lines describe each query in the following format:
 *
 * The first line contains two space-separated integers describing the respective values of
 * (the size of the initial pile in the query) and  (the size of the set in the query).
 * The second line contains  distinct space-separated integers describing the values in set .
 * Constraints
 *
 * Subtask
 *
 *  for  of the maximum score.
 * Output Format
 *
 * For each query, calculate the maximum possible number of moves you can perform and print it on a new line.
 *
 * Sample Input 0
 *
 * 1
 * 12 3
 * 2 3 4
 * Sample Output 0
 *
 * 4
 *
 **/
public class stoneDivisiion {

    public static void main(String[] args) {
        List<Long> sets = Arrays.asList(2L,3L, 4L);
        int n = 12;
        long result = stoneDivision(n, sets);
        System.out.println(result);
    }

    public static long stoneDivision(long n, List<Long> s) {
        // Write your code here
        return divRecurse(n, s, new HashMap<>());
    }

    static long divRecurse(long n, List<Long> s, Map<Long,Long> map){
        if (n == 0 || n == 1){
            return 0;
        }

        if (map.containsKey(n)){
            return map.get(n);
        }
        long max = 0;
        for(long x: s){
            if(n != x && n % x == 0){

                max = Math.max(
                        (n/x*divRecurse(x,s,map))+1,
                        max
                );
            }
        }

        map.put(n,max);
        return max;
    }

}
