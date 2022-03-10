package com.bis.interview_prep.ordinal.dataStructure;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Implement an LFU (Least Frequently Used) cache. It should be able to be initialized with a cache size n,
 * and contain the following methods:
 * <p>
 * set(key, value): sets key to value. If there are already n items in the cache and we are adding a
 * new item, then it should also remove the least frequently used item. If there is a tie, then the
 * least recently used key should be removed.
 * get(key): gets the value at key. If no such key exists, return null.
 * Each operation should run in O(1) time.
 **/
public class LFUCache {

    int min = -1;
    int cap;
    private HashMap<Integer, Integer> values;
    private HashMap<Integer, Integer> counts;
    private HashMap<Integer, LinkedHashSet<Integer>> list;

    public LFUCache(int capacity) {
        this.cap = capacity;
        values = new HashMap<>();
        counts = new HashMap<>();
        list = new HashMap<>();
        list.put(1,new LinkedHashSet<>());
    }


    public static void main(String[] args) {

    }

    int get(int key) {
        if (!values.containsKey(key)){
            return -1;
        }
        //get the count of the key
        int count = counts.get(key);

        //increase the count;
        counts.put(key,count+1);

        //remove the old count from the counter list
        list.get(count).remove(key);

        if (min == count && list.get(count).isEmpty()){
            min++;
        }

        if (!list.containsKey(count+1)){
            list.put(count+1,new LinkedHashSet<>());
        }

        list.get(count+1).add(key);

        return values.get(key);
    }

    void set(int key, int value){
        if (cap <= 0){
            return;
        }

        if (values.containsKey(key)){
            values.put(key,value);
            get(key);
            return;
        }

        if (values.size() >= cap){
            int removedItem = list.get(min).iterator().next();
            list.get(min).remove(removedItem);
            values.remove(removedItem);
            counts.remove(removedItem);
        }

        values.put(key,value);
        counts.put(key,1);
        min = 1;
        list.putIfAbsent(1,new LinkedHashSet<>());
        list.get(1).add(key);
    }
}
