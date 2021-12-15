package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ValidateSubsequence {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        List<Integer> sequence = Arrays.asList(1, 6, -1, 10);
        System.out.println(isValidSubsequence(array,sequence)?"True":"False");
    }

    public boolean isSubsequenceSmart(String s, String t){
        int n = s.length();
        int m = t.length();
        int index1 = 0;

        //base case
        if(n == 0){
            return true;
        }

        for(int i = 0; i < m; i++){
            if(s.charAt(index1) == t.charAt(i)){
                index1++;
            }

            if(index1 == n){
                return true;
            }
        }

        return false;
    }

    public boolean isSubsequence(String s, String t) {


        String sequence=s,array=t;

        int n = array.length();
        int m = sequence.length();
        //put the array in the hashMap with their occurring positions
        HashMap<Character,List<Integer>> map = new HashMap<>(n);
        for(int i = 0; i < n; i++){
            char key = array.charAt(i);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(i);
        }

        int index = -1;
        for(int i = 0; i < m; i++){
            char item = sequence.charAt(i);
            int[] indexAt = {index};

            //System.out.println("map "+ map.get(item));
            boolean exist = isContains(index,map.get(item),indexAt);
            if(!exist || indexAt[0] == -1){
                return false;
            }
            //System.out.println("map "+map.get(item));
            if(!map.containsKey(item) || map.get(item).isEmpty() ){
                return false;
            }else{

                index = indexAt[0];

                //map.get(item).remove(index);
            }
        }
        return true;
    }

    static boolean isContains(int index,List<Integer> list,int[] indexAt){
        if(list == null){
            indexAt[0] = -1;
            return false;
        }
        for(int i = 0; i < list.size(); i++){
            if(index == -1 || list.get(i) > index){
                indexAt[0] = list.get(i);
                //System.out.println("comiing "+index);
                return true;
            }
        }

        indexAt[0] = -1;
        return false;
    }

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Write your code here.
        //put the array in the hashMap
        int n = array.size();
        int m = sequence.size();
        HashMap<Integer,List<Integer>> map = new HashMap<>(n);
        for(int i = 0; i < n; i++){
            int key = array.get(i);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(i);
        }

        int index = -1;
        for(int i = 0; i < m; i++){
            int item = sequence.get(i);
            if(!map.containsKey(item) || map.get(item).isEmpty() ||  map.get(item).get(0) <= index){
                return false;
            }else{
                index = i;
                map.get(item).remove(0);
            }
        }
        return true;
    }
}
