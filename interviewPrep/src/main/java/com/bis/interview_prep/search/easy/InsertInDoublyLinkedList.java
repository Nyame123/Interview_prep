package com.bis.interview_prep.search.easy;

public class InsertInDoublyLinkedList {

    public static void main(String[] args) {

    }


    public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode llist, int data) {
        // Write your code here
        //base case
        if(llist == null){
            return new DoublyLinkedListNode(data);
        }
        DoublyLinkedListNode cur = llist;
        DoublyLinkedListNode prev = llist;
        while(cur != null){
            if(cur.data > data){
                //insertion occurs
                DoublyLinkedListNode node = new DoublyLinkedListNode(data);
                node.next = cur;
                node.prev = cur.prev;

                if(cur.prev != null){
                    cur.prev.next = node;
                    cur.prev = node;
                    return llist;
                }else{
                    cur.prev = node;
                    llist = node;
                    return llist;
                }


            }else{

                if(cur.next == null){
                    prev = cur;
                }
                cur = cur.next;


            }
        }

        //insertion occurs at the tail
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);
        node.prev = prev;
        prev.next = node;

        return llist;

    }


}

class DoublyLinkedListNode {
    public int data;
    public DoublyLinkedListNode next;
    public DoublyLinkedListNode prev;

    public DoublyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
        this.prev = null;
    }
}