package com.bis.interview_prep.search.easy;

/**
 * This problem was asked by Google.
 * <p>
 * Determine whether a doubly linked list is a palindrome. What if it’s singly linked?
 * <p>
 * For example, 1 -> 4 -> 3 -> 4 -> 1 returns True while 1 -> 4 returns False.
 **/
public class PalindromeDoublyLinkedList {

    public static void main(String[] args) {
        Node head = null;
        head = push(head, 'l');
        head = push(head, 'e');
        head = push(head, 'v');
        head = push(head, 'e');
        head = push(head, 'l');

        if (isPalindrome(head))
            System.out.printf("It is Palindrome");
        else
            System.out.printf("Not Palindrome");
    }

    /**
     *We can solve this problem by using two pointers where
     * one is on the left of the node and other one is on the right of
     * the node.
     *
     * We check if the two nodes are the same from each other while left going forward
     * and right going backward
     *
     * Time Complexity = O(N)
     **/
    private static boolean isPalindrome(Node left) {

        Node right = left;
        while (right.next != null){
            right = right.next;
        }

        while (left != right){
            if (left.data != right.data){
                return false;
            }

            left = left.next;
            right = right.prev;
        }

        return true;
    }

    static Node push(Node head_ref, char new_data) {
        Node new_node = new Node();
        new_node.data = new_data;
        new_node.next = head_ref;
        new_node.prev = null;
        if (head_ref != null)
            head_ref.prev = new_node;
        head_ref = new_node;
        return head_ref;
    }

    static class Node {
        char data;
        Node next;
        Node prev;
    }
}
