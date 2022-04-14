package com.bis.interview_prep.search.hard;

/**
 * Given the head to a singly linked list, where each node also has a “random”
 * pointer that points to anywhere in the linked list, deep clone the list.
 **/
public class CloneLinkedList {

    public static void main(String[] args) {

        Node start = new Node(1);
        start.next = new Node(2);
        start.next.next = new Node(3);
        start.next.next.next = new Node(4);
        start.next.next.next.next = new Node(5);

        // 1's random points to 3
        start.random = start.next.next;

        // 2's random points to 1
        start.next.random = start;

        // 3's and 4's random points to 5
        start.next.next.random = start.next.next.next.next;
        start.next.next.next.random
                = start.next.next.next.next;

        // 5's random points to 2
        start.next.next.next.next.random = start.next;


        System.out.println("Original list : ");
        print(start);

        System.out.println("Cloned list : ");
        Node cloned_list = clone(start);
        print(cloned_list);

    }

    private static Node clone(Node start) {
        Node cur = start;
        Node temp = null;

        while (cur != null) {
            temp = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = temp;
            cur = temp;
        }

        //assign the random pointers to newly added nodes
        cur = start;
        while (cur != null){
            if (cur.next != null){
                cur.next.random = (cur.random != null)? cur.random.next : cur.random;
                cur = cur.next.next;
            }

        }

        Node original = start, copy = start.next;
        temp = copy;

        while (original != null){
            original.next = original.next.next;
            copy.next = (copy.next != null)? copy.next.next : copy.next;

            original = original.next;
            copy = copy.next;
        }

        return temp;
    }

    static void print(Node start) {
        Node ptr = start;
        while (ptr != null) {
            System.out.println("Data = " + ptr.data
                    + ", Random = "
                    + ptr.random.data);
            ptr = ptr.next;
        }
    }

    static class Node {
        int data;
        Node next, random;

        Node(int x) {
            data = x;
            next = random = null;
        }
    }
}
