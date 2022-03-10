package com.bis.interview_prep.ordinal.dataStructure;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 *Write a map implementation with a get function that lets you retrieve the value of a key at a particular time.
 *
 * It should contain the following methods:
 *
 * set(key, value, time): sets key to value for t = time.
 * get(key, time): gets the key at t = time.
 * The map should work like this. If we set a key at a particular time,
 * it will maintain that value forever or until it gets set at a later time. In other words,
 * when we get a key at a time, it should return the value that was set for that key set at the most recent time.
 *
 * Consider the following examples:
 *
 * d.set(1, 1, 0) # set key 1 to value 1 at time 0
 * d.set(1, 2, 2) # set key 1 to value 2 at time 2
 * d.get(1, 1) # get key 1 at time 1 should be 1
 * d.get(1, 3) # get key 1 at time 3 should be 2
 * d.set(1, 1, 5) # set key 1 to value 1 at time 5
 * d.get(1, 0) # get key 1 at time 0 should be null
 * d.get(1, 10) # get key 1 at time 10 should be 1
 * d.set(1, 1, 0) # set key 1 to value 1 at time 0
 * d.set(1, 2, 0) # set key 1 to value 2 at time 0
 * d.get(1, 0) # get key 1 at time 0 should be 2
 **/
public class TimeBasedKeyValue {

    public static void main(String[] args) {

        TimeBasedKeyValue timeBasedKeyValue = new TimeBasedKeyValue();
        /*timeBasedKeyValue.set(1,1,0);
        timeBasedKeyValue.set(1,2,2);
        System.out.println(timeBasedKeyValue.get(1,1));
        System.out.println(timeBasedKeyValue.get(1,3));*/
        timeBasedKeyValue.set(1,1,5);
        System.out.println(timeBasedKeyValue.get(1,0));
        System.out.println(timeBasedKeyValue.get(1,10));
    }

    private HashMap<Integer, List<TimeValue>> map;
    /**
     * Since the various value can be set for a particular key at a
     * particular time, we can decide to use a hashMap with ADT containing
     * a value and the time it was set
     **/
    public TimeBasedKeyValue(){
        map = new HashMap<>();
    }

    String get(int key, int time){
        if (!map.containsKey(key)){
            return null;
        }

        int found = binarySearch(map.get(key), time);
        return  found == -1? null : String.valueOf(found);
    }

    void set(int key, int value, int time){
        TimeValue timeValue = new TimeValue();
        timeValue.time = time;
        timeValue.value = value;
        map.putIfAbsent(key,new ArrayList<>());
        map.get(key).add(timeValue);
    }

    int binarySearch(List<TimeValue> items,int time){
        int low = 0, high = items.size()-1;
        while (low < high){
            int mid = low + (high-low) /2;

            if (items.get(mid).time == time){
                return items.get(mid).value;
            }else if (items.get(mid).time < time){
                if (items.get(mid+1).time > time){
                    return items.get(mid).value;
                }

                low = mid+1;
            }else{
                high = mid-1;
            }
        }

        return items.get(low).time <= time? items.get(low).value: -1;
    }

    static class TimeValue{
        int value;
        int time;
    }
}

class TimeBasedKeyValueUsingTreeMap{
    public static void main(String[] args) {

        TimeBasedKeyValueUsingTreeMap timeBasedKeyValue = new TimeBasedKeyValueUsingTreeMap();
        /*timeBasedKeyValue.set(1,1,0);
        timeBasedKeyValue.set(1,2,2);
        System.out.println(timeBasedKeyValue.get(1,1));
        System.out.println(timeBasedKeyValue.get(1,3));*/
        timeBasedKeyValue.set(1,1,5);
        System.out.println(timeBasedKeyValue.get(1,0));
        System.out.println(timeBasedKeyValue.get(1,10));
    }

    private HashMap<Integer, TreeMap<Integer,Integer>> map;
    /**
     * Since the various value can be set for a particular key at a
     * particular time, we can decide to use a hashMap with ADT containing
     * a value and the time it was set
     **/
    public TimeBasedKeyValueUsingTreeMap(){
        map = new HashMap<>();
    }

    String get(int key, int time){
        TreeMap<Integer,Integer> treeMap = map.get(key);
        if (treeMap == null){
            return null;
        }

        Integer treeMapTime = treeMap.floorKey(time);
        if (treeMapTime == null){
            return null;
        }

        return String.valueOf(treeMap.get(treeMapTime));
    }

    void set(int key, int value, int time){

        map.putIfAbsent(key,new TreeMap<>());
        map.get(key).put(time,value);
    }
}
