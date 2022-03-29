package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 **/
public class TopKFrequencyWords {

    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k = 4;
        List<String> res = topKFrequencyWords(words,k);
        System.out.println(res);
    }

    /**
     * In this problem, we can keep track of the occurrences of the words
     * and we can achieve this by using a hashMap to do that.
     *
     * Since the problem is finding maximum K occurring word and if the words with K
     * occurring is more than one, we sort these lexicographically.
     * We can achieve this sub-problem by making use of a MaxHeap.
     * when the values are the same, we sort by the keys
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static List<String> topKFrequencyWords(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();
        for(String s: words){
            map.put(s,map.getOrDefault(s,0)+1);
        }

        PriorityQueue<Map.Entry<String,Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> prev, Map.Entry<String, Integer> cur) {
               return  (prev.getValue().equals(cur.getValue()))? prev.getKey().compareTo(cur.getKey())
                       : cur.getValue().compareTo(prev.getValue());
            }
        });

        for(Map.Entry<String,Integer> key: map.entrySet()){
            priorityQueue.add(key);
        }
        int count = k;
        List<String> ans = new ArrayList<>();
        while (!priorityQueue.isEmpty()){
            if (count == 0){
                break;
            }
            ans.add(priorityQueue.poll().getKey());
            count--;
        }

        return ans;
    }
}
