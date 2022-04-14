package com.bis.interview_prep.ordinal.dataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * This question was asked by Riot Games.
 * <p>
 * Design and implement a HitCounter class that keeps track of requests (or hits). It should support the following operations:
 * <p>
 * record(timestamp): records a hit that happened at timestamp
 * total(): returns the total number of hits recorded
 * range(lower, upper): returns the number of hits that occurred between timestamps lower and upper (inclusive)
 * Follow-up: What if our system has limited memory?
 **/
public class HitCounterUsingArray {

    /**
     * Method1
     * Using array data structure
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static List<Integer> times = new ArrayList<>();

    //Time Complexity = O(N)
    static void hit(int timestamp) {
        times.add(timestamp);
    }

    static int getHits(int timestamp) {
        int i;
        for (i = 0; i < times.size(); i++) {
            if (times.get(i) > timestamp - 300) {
                break;
            }
        }

        return times.size() - i;
    }

}

class HitCounterOptimizedWithConcurrency {
    //300 seconds or 5 mins
    static int[] times = new int[300];
    static int[] hits = new int[300];

    //Time Complexity = O(N)
    static void hit(int timestamp) {
        int mod = timestamp % 300;
        if (times[mod] != timestamp) {
            times[mod] = timestamp;
            hits[mod] = 1;
        } else {
            hits[mod]++;
        }
    }

    static int getHits(int timestamp) {
        int i;
        int total = 0;
        for (i = 0; i < 300; i++) {
            if (times[i] - timestamp < 300)
                total += hits[i];
        }

        return total;
    }
}
