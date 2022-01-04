package com.bis.interview_prep.search.medium;

import java.util.List;

public class FullCountingSort {

    public static void countSort(List<List<String>> arr) {
        // Write your code here
        //grab the first half of the list and turn the s[i] to dash
        int n = arr.size() / 2;
        int max = 0;
        StringBuilder[] preArr = new StringBuilder[n];
        for(int i = 0; i < arr.size(); i++){
            int id = Integer.parseInt(arr.get(i).get(0));
            if(i < n){
                arr.get(i).set(1,"-");
            }
            String it = arr.get(i).get(1);
            if(preArr[id] == null){
                preArr[id] = new StringBuilder();
            }
            preArr[id].append(it).append(" ");
            //max = Math.max(max,id);
        }

        //StringBuilder ans = new StringBuilder();
        for(int i = 0; i < preArr.length; i++){
            if(preArr[i] != null && preArr[i].length() > 0){
                //ans.append(preArr[i]);
                System.out.print(preArr[i]);
                // System.out.print("");
            }
        }

        //System.out.println(ans.toString());

    }
}
