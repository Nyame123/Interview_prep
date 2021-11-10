package com.bis.interview_prep.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @see PermutionNoDuplicates
 * There is an already similar class that computes all permutations of a
 * string where there is no duplicates.
 * We will tweak this algorithm to compute all permutations when there are
 * duplicates in the characters
 * <p>
 * Soln
 * ===========
 * <p>
 * Permutations(dda) = [dda, dad, dad, dda, add, add]
 * We could see duplicates in the permutation results and how do we solve it?
 * We will can use hashmap to remember whenever a permutation has been computed so that we
 * do not add the other same permutations.
 * <p>
 * The solution proposed can work but not efficient when the duplicates of the same kind are many.
 * <p>
 * For example, with Permutation(eeeeeeeeeeeeee) = [eeeeeeeeeeeeee]
 * but a lot of computation will be done even though we will get only one solution.
 * <p>
 * Lets optimize this solution.
 **/
public class PermutationWithDuplicates {
    public static void main(String[] args) {
        String item = "ddaaassssssssssssss";
        List<String> perms = permsWithResult(item);

        System.out.println(perms);
    }

    //Time Complexity = O(n! * n^2)
    static List<String> permsWithResult(String s) {
        if (s == null) {
            return null;
        }
        if (s.isEmpty()) {
            return new ArrayList<>();
        }

        HashMap<String, Integer> table = buildTable(s);
        List<String> result = new ArrayList<>();
        permWithResult("",s.length(), result, table);
//        permWithResult("", result, table);

        return result;
    }

    static HashMap<String, Integer> buildTable(String s) {

        HashMap<String, Integer> table = new HashMap<>();
        for (char c : s.toCharArray()) {
            String key = String.valueOf(c);
            table.putIfAbsent(key, 0);
            table.put(key, table.get(key) + 1);
        }

        return table;
    }

    //Time Complexity = O(n!)
    static void permWithResult(String prefix,int remaining, List<String> result, HashMap<String, Integer> table) {
        //base case
        if (remaining == 0) {
            result.add(prefix);
            return;
        }

        for (String key: table.keySet()) {
            //reduce the count of this current character from the table
            int keyCount = table.getOrDefault(key, 0);
            if (keyCount > 0) {
                keyCount = keyCount - 1;
                table.put(key, keyCount);

                permWithResult(prefix + key,remaining-1, result, table);

                //put the key back for the other thread permutation to use
                table.put(key, keyCount + 1);
            }
        }
    }


    //Time Complexity = O(n!)
    static void permWithResult(String prefix, List<String> result, HashMap<String, Integer> table) {
        //base case
        int tableSize = keyCount(table);
        if (tableSize == 0) {
            return;
        }

        if (tableSize == 1) {
            String keyString = keyString(table);
            result.add(prefix + keyString);
            return;
        }

        for (String key: table.keySet()) {
            //reduce the count of this current character from the table
            int keyCount = table.getOrDefault(key, 0);
            if (keyCount > 0) {
                keyCount = keyCount - 1;
                table.put(key, keyCount);

                permWithResult(prefix + key, result, table);

                //put the key back for the other thread permutation to use
                table.put(key, keyCount + 1);
            }
        }
    }

    static int keyCount(HashMap<String, Integer> table) {
        int count = 0;
        for (String s : table.keySet()) {
            if (table.get(s) > 0) {
                count++;
            }
        }

        return count;
    }

    static String keyString(HashMap<String, Integer> table) {
        String key = "";
        int count = 0;

        for (String s : table.keySet()) {
            if (table.get(s) > 0) {
                key = s;
                count += table.get(s);
                break;
            }
        }

        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(key);
        }

        return sb.toString();
    }

}
