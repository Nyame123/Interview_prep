package com.bis.interview_prep.recursion;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * <p>
 * Given the head of a singly linked list, reverse the list and return the reversed list.
 */
public class ReverseSinglyLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        reverseList(head);
        printList(head);
    }

    /**
     * 1. Find the midPoint of the Singly Linked List
     * 2. We find the midpoint of the Singly Linked list by using Slow and Fast pointer
     * .3. We traverse over the singly linked list until we get to the mid point.
     * 4. We traverse back with the swap List Node.
     * <p>
     * Time Complexity = O(N)
     **/
    static void reverseList(ListNode head) {
        //1-> 2-> 3-> 4->5
        if (head == null){
            return;
        }
        ListNode slow = head, fast = head, current = head;
        int length = length(current);
        reverseRec(slow, fast, (length % 2 == 0));
    }

    //O(N)
    static int length(ListNode head) {
        //base case
        if (head == null) {
            return 0;
        }
        return length(head.next) + 1;
    }

    //O(N/2)
    static ListNode reverseRec(ListNode slow, ListNode fast, boolean isEven) {
        //base case
        if (fast.next == null || fast.next.next == null) {
            if (isEven) {
                int temp = slow.val;
                slow.val = slow.next.val;
                slow.next.val = temp;

                return slow.next.next;
            }

            return slow.next;
        }

        ListNode swapNode = reverseRec(slow.next, fast.next.next, isEven);

        if (swapNode != null) {
            int temp = slow.val;
            slow.val = swapNode.val;
            swapNode.val = temp;

            return swapNode.next;
        }

        return null;
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

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
