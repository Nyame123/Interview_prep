package com.bis.interview_prep.search.medium;

import com.bis.interview_prep.codeWar.CodeWarChallenge;

/**
 *Given two linked lists that represent two numbers, return the sum of the numbers also represented as a list.
 *
 * Ex: Given the two linked lists…
 *
 * a = 1->2, b = 1->3, return a list that looks as follows: 2->5
 * Ex: Given the two linked lists…
 *
 * a = 1->9, b = 1, return a list that looks as follows: 2->0
 *
 **/
public class AddTwoList {

    public static void main(String[] args) {

        // creating first list
        Node head1 = new Node(1);
        head1.next = new Node(9);
        /*head1.next.next = new Node(9);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(6);*/
        System.out.print("First List is ");
        printNode(head1);

        // creating second list
        Node head2 = new Node(1);
        //head2.next = new Node(3);
        System.out.print("Second List is ");
        printNode(head2);

        System.out.print("Resultant List is \n");
        // add the two lists and see the result
        addTwoLists(head1, head2);
    }

    static void addTwoLists(Node h1, Node h2){
        int len1 = getHeight(h1);
        int len2 = getHeight(h2);

        if(len1 < len2){
            int dif = len2-len1;
           h1 = paddZeros(h1,dif);
        }else if(len2 < len1){
            int dif = len1-len2;
           h2 = paddZeros(h2,dif);
        }
        RemainderNode remainderNode = recurseAddList(h1,h2);
        printNode(remainderNode.node);
    }

    static Node paddZeros(Node root, int num){
        Node cur = root;
        int i = 0;
        while(i < num){
            Node n = new Node(0);
            n.next = cur;
            cur = n;
            i++;
        }

         return cur;
    }

    static int getHeight(Node root){
        if(root == null){
            return 0;
        }

        return getHeight(root.next) + 1;
    }

    static RemainderNode recurseAddList(Node h1, Node h2){
        RemainderNode remainderNode = new RemainderNode();
        if (h1 == null || h2 == null){
            return remainderNode;
        }

        int sum = 0;
        remainderNode = recurseAddList(h1.next,h2.next);
        sum = h1.data + h2.data;

        sum += remainderNode.rem;
        Node node = new Node(sum%10);
        remainderNode.rem = sum/10;
        node.next = remainderNode.node;
        remainderNode.node = node;

        return remainderNode;
    }

    static void printNode(Node root){
        System.out.println();
        Node cur = root;
        while(cur != null){
            System.out.print(cur.data +",");
            cur = cur.next;
        }
    }

    static class RemainderNode{
        Node node;
        int rem;
    }
}
