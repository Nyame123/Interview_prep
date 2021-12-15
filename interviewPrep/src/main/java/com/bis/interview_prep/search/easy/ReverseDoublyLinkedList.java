package com.bis.interview_prep.search.easy;

public class ReverseDoublyLinkedList {

    public static void main(String[] args) {

    }

    public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
        // Write your code here

        DoublyLinkedListNode prev = null;
        DoublyLinkedListNode cur = llist;

        while(cur != null){
            DoublyLinkedListNode next = cur.next;
            //DoublyLinkedListNode prevNode = cur.prev;

            cur.next = prev;
            if(prev != null){
                prev.prev = cur;
            }

            prev = cur;

            cur = next;
        }

        return prev;

    }
}
