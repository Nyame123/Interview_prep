package com.bis.interview_prep.ordinal.ArrayAndStrings;

import com.bis.interview_prep.search.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedList {

    public static void main(String[] args) {
        int k = 3; // Number of linked lists
        int n = 4; // Number of elements in each list

        // an array of pointers storing the head nodes
        // of the linked lists
        Node arr[] = new Node[k];

        arr[0] = new Node(1);
        arr[0].next = new Node(3);
        arr[0].next.next = new Node(5);
        arr[0].next.next.next = new Node(7);

        arr[1] = new Node(2);
        arr[1].next = new Node(4);
        arr[1].next.next = new Node(6);
        arr[1].next.next.next = new Node(8);

        arr[2] = new Node(0);
        arr[2].next = new Node(9);
        arr[2].next.next = new Node(10);
        arr[2].next.next.next = new Node(11);

        // Merge all lists
        Node head = mergeKListsUsingDivideAndConquer(arr);

        Node.printList(head);
    }

    static Node mergeKListUsingMinHeap(Node[] nodes) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return Integer.compare(node.data, t1.data);
            }
        });

        int n = nodes.length;
        for (int i = 0; i < n; i++) {
            priorityQueue.add(nodes[i]);
        }

        Node dummy = new Node(0);
        Node res = dummy;
        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            res.next = cur;
            res = res.next;
            cur = cur.next;
            if (cur != null) {
                priorityQueue.add(cur);
            }
        }

        return dummy.next;
    }

    //Time Complexity = O(klog(n))
    public static Node mergeKListsUsingDivideAndConquer(Node[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }

        return divideLists(lists, 0, n - 1);
    }

    static Node divideLists(Node[] lists, int left, int right) {

        if (left == right) {
            return lists[left];
        }
        if (left < right) {

            int mid = left + (right - left) / 2;
            Node lNode = divideLists(lists, left, mid);
            Node rNode = divideLists(lists, mid + 1, right);

            return mergeList(lNode, rNode);
        }
        return null;
    }

    //Time Complexity = O(nlogk)
    public static Node mergeKLists(Node[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }

        if (n == 1) {
            return mergeList(lists[0], null);
        }

        Node head = mergeList(lists[0], lists[1]);
        for (int i = 2; i < n; i++) {
            head = mergeList(head, lists[i]);
        }

        return head;
    }

    static Node mergeList(Node n1, Node n2) {

        Node dummy = new Node();
        Node cur = dummy;
        while (n1 != null && n2 != null) {
            if (n1.data <= n2.data) {
                Node next = n1.next;
                n1.next = null;
                cur.next = n1;
                cur = n1;
                n1 = next;
            } else {
                Node next = n2.next;
                n2.next = null;
                cur.next = n2;
                cur = n2;
                n2 = next;
            }
        }

        if (n1 != null)
            cur.next = n1;

        if (n2 != null)
            cur.next = n2;

        return dummy.next;
    }
}
