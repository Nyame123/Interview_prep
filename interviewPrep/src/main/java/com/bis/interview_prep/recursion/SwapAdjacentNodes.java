package com.bis.interview_prep.recursion;

import java.util.List;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1]
 * Output: [1]
 **/

public class SwapAdjacentNodes {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        ListNode root = swapPairs(head);
        printList(root);
    }

    /**
     * Recurse over the linked list and swap every odd position
     * node with the even position node.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/

    static ListNode swapPairs(ListNode cur) {
        ListNode head = new ListNode(0);
        if (cur == null || cur.next == null){
            return cur;
        }
        swapPairsRec(head, cur);
        return head.next;
    }

    //works by swapping the nodes
    static void swapPairsRec(ListNode head,ListNode current){
        //1->2->3->4->5
        //base case
        if (current == null || current.next == null){
            return;
        }

        ListNode positionNode = current.next.next;
        ListNode temp = current.next;
        current.next = positionNode;
        temp.next = current;
        head.next = temp;
        head = head.next.next;
        swapPairsRec(head, temp.next.next);
    }

    static ListNode swapPairs1(ListNode cur) {
        if (cur == null || cur.next == null){
           return cur;
        }
        swapPairsRec1(cur);
        return cur;
    }

    //works by swapping the values of the nodes
    static void swapPairsRec1(ListNode current){
        //1->2->3->4->5
        //base case
        if (current == null || current.next == null){
            return;
        }

        int temp = current.val;
        current.val = current.next.val;
        current.next.val = temp;

        swapPairsRec1(current.next.next);
    }

    //O(N)
    static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + ",");
            cur = cur.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() { }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
