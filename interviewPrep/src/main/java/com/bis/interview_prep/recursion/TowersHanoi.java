package com.bis.interview_prep.recursion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * There are 3 towers where placing of disk are constraints
 * 1. the disk cannot lie on a smaller size
 * 2. Only one disk can move at a time
 * 3. A disk is slid off from one top to another tower
 **/
public class TowersHanoi {

    public static void main(String[] args) {
        int n = 2;
        Tower[] towers = new Tower[3];
        for (int i = 0; i < 3; i++) {
            towers[i] = new Tower(i);
        }

        //fill the first tower
        for (int i = n-1; i >= 0; i--) {
            towers[0].add(i);
        }

        towers[0].moveDisk(n,towers[2],towers[1]);
        System.out.println(towers[2].disks);

    }

    static class Tower{
        Deque<Integer> disks = new ArrayDeque<>();

        int index;

        public Tower(int index) {
            this.index = index;
        }

        public void add(int d){
            if (!disks.isEmpty() && disks.peek() <= d){
                System.out.println("Error placing the disk "+d);
            }else {
                disks.addFirst(d);
            }
        }

        public void moveTopTo(Tower destination){
            int d = disks.pop();
            System.out.printf("%d->%d\n",index+1,destination.index+1);
            destination.add(d);
        }

        public void moveDisk(int n, Tower destination, Tower buffer){
            //base case
            if (n <= 0){
                return;
            }

            //move top, n-1, disks from origin to the buffer, using destination as a buffer
            moveDisk(n-1,buffer,destination);

            //move top from origin to destination
            moveTopTo(destination);

            //move top, n-1, disks from buffer to the destination, using origin as a buffer
            buffer.moveDisk(n-1,destination,this);
        }

    }
}
