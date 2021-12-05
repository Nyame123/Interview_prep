package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(generate(n));
    }
    public static List<List<Integer>> generate(int numRows) {

        //base case: fill the first two rows
        List<List<Integer>> list = new ArrayList<>();
        //add first one
        if(numRows >= 1){
            List<Integer> first = new ArrayList<>();
            first.add(1);
            list.add(first);
        }

        //add the second row
        if(numRows >= 2){
            List<Integer> second = new ArrayList<>();
            second.add(1);
            second.add(1);
            list.add(second);
        }

        //go over the rest of the row
        for(int i = 2; i < numRows; i++){
            List<Integer> prev = list.get(i-1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            //go over the previous list
            for(int j = 1; j < prev.size(); j++){
                int sum = prev.get(j-1) + prev.get(j);
                row.add(sum);
            }

            row.add(1);
            list.add(row);
        }

        return list;
    }
}
