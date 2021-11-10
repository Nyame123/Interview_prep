package com.bis.interview_prep.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Generating a power set of a number
 * 0 => {}
 * 1 => {},{1}
 * 1,2 => {},{1},{2},{1,2}
 **/
public class PowerSet {

    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>();
        set.add(1);
        set.add(2);
        set.add(3);
        List<List<Integer>> powerSet = powerSetRec(set,3);
        for (List<Integer> list: powerSet){
            System.out.println(list);
        }
    }

    //Time Complexity = O(n*2^n)
    //Space Complexity = O(n*2^n)
    static List<List<Integer>> powerSetRec(List<Integer> set,int num){
        List<List<Integer>> allSubsets;
        //base case
        if (num == 0){
            //initialize the container set
            allSubsets = new ArrayList<>();
            //add the empty set
            allSubsets.add(new ArrayList<>());
            return allSubsets;
        }

        //recurse on the set number
        allSubsets = powerSetRec(set,num-1);

        //loop through all the subsets generated and add the current item in the list
        Integer item = set.get(num-1);
        List<List<Integer>> moreSubsets = new ArrayList<>();
        for (List<Integer> sets: allSubsets){
            //copy the old sets into the new one
            List<Integer> newSubsets = new ArrayList<>(sets);
            //add the current item to the old sets
            newSubsets.add(item);
            moreSubsets.add(newSubsets);

        }

        allSubsets.addAll(moreSubsets);
        return allSubsets;
    }
}
