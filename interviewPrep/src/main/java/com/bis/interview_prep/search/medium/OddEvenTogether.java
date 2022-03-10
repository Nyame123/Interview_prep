package com.bis.interview_prep.search.medium;

/**
 * This question is asked by Facebook. Given a singly linked list,
 * re-order and group its nodes in such a way that the nodes in odd
 * positions come first and the nodes in even positions come last.
 * <p>
 * Ex: Given the reference to the following linked list...
 * <p>
 * 4->7->5->6->3->2->1->NULL, return 4->5->3->1->7->6->2->NULL
 * Ex: Given the reference to the following linked list...
 * <p>
 * 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL
 **/
public class OddEvenTogether {

    public static void main(String[] args) {
        Node head = newNode(1);
        head.next = newNode(2);
        head.next.next = newNode(3);
        head.next.next.next = newNode(4);
        head.next.next.next.next = newNode(5);

        System.out.println("Given Linked List");
        printlist(head);

        head = rearrangeEvenOdd(head);

        System.out.println("Modified Linked List");
        printlist(head);
    }

    private static Node rearrangeEvenOdd(Node head) {

        if (head == null){
            return null;
        }
        Node odd = head;
        Node even = head.next;
        Node evenFirst = even;
        while (true){
            if (even == null || even.next == null){
                odd.next = evenFirst;
                break;
            }

            odd.next = even.next;
            odd = odd.next;

            if (odd.next == null){
                even.next = null;
                odd.next = evenFirst;
                break;
            }

            even.next = odd.next;
            even = even.next;
        }

        return head;
    }

    static void printlist(Node node) {
        while (node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println("NULL");
    }


    static Node newNode(int key) {
        Node temp = new Node();
        temp.data = key;
        temp.next = null;
        return temp;
    }
}
