package com.bis.interview_prep.ordinal.dataStructure;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * LRU Cache Implementation
 * Difficulty Level : Hard
 * How to implement LRU caching scheme? What data structures should be used?
 * We are given total possible page numbers that can be referred. We are also given cache
 * (or memory) size (Number of page frames that cache can hold at a time).
 * The LRU caching scheme is to remove the least recently used frame when the cache is
 * full and a new page is referenced which is not there in cache. Please see the Galvin book for more details
 * (see the LRU page replacement slide here)
 **/
public class LRUCache {

    public static void main(String[] args) {

        LRUCacheImp<Integer> ca = new LRUCacheImp<>(4);
        ca.offerPage(1);
        ca.offerPage(2);
        ca.offerPage(3);
        ca.offerPage(1);
        ca.offerPage(4);
        ca.offerPage(5);
        ca.display();
    }


}

/**
 * We need two data structures, i.e. Queue with doubly linked List and
 * a Hash.
 * We use two data structures to implement an LRU Cache.
 * <p>
 * The maximum size of the queue will be equal to the total number of
 * frames available (cache size). The most recently used pages will be near front
 * end and least recently pages will be near the rear end.
 * A Hash with page number as key and address of the corresponding queu
 **/
class LRUCacheImp<T> {

    //queue linkedlist
    Deque<T> queue;
    HashSet<T> hash;
    int capacity = 4;

    LRUCacheImp(int size) {
        queue = new LinkedList<>();
        hash = new HashSet<>(size);
        capacity = size;
    }

    public void offerPage(T data) {

        if (hash.size() >= capacity) {
            if (hash.contains(data)) {
                queue.remove(data);
                queue.push(data);
            } else {
                T removedItem = queue.removeLast();
                hash.remove(removedItem);

                queue.push(data);
                hash.add(data);
            }
        } else {
            if (hash.contains(data)) {
                queue.remove(data);
                queue.push(data);
            } else {
                queue.push(data);
                hash.add(data);
            }
        }
    }

    public void offerPage1(T page) {
        if (!hash.contains(page)) {
            if (queue.size() == capacity) {
                T last = queue.removeLast();
                hash.remove(last);
            }
        } else {/* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            queue.remove(page);
        }
        queue.push(page);
        hash.add(page);
    }

    public void display() {
        for (T t : queue) {
            System.out.print(t + " ");

        }
    }
}

class LRUCache1 {

    // store keys of cache
    private Deque<Integer> doublyQueue;

    // store references of key in cache
    private HashSet<Integer> hashSet;

    // maximum capacity of cache
    private final int CACHE_SIZE;

    LRUCache1(int capacity) {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    /* Refer the page within the LRU cache */
    public void refer(int page) {
        if (!hashSet.contains(page)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                int last = doublyQueue.removeLast();
                hashSet.remove(last);
            }
        }
        else {/* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            doublyQueue.remove(page);
        }
        doublyQueue.push(page);
        hashSet.add(page);
    }

    // display contents of cache
    public void display() {
        Iterator<Integer> itr = doublyQueue.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }

    public static void main(String[] args) {
        LRUCache1 ca = new LRUCache1(4);
        ca.refer(1);
        ca.refer(2);
        ca.refer(3);
        ca.refer(1);
        ca.refer(4);
        ca.refer(5);
        ca.display();
    }
}
