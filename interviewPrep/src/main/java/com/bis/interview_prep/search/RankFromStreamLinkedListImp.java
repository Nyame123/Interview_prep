package com.bis.interview_prep.search;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish
 * to be able to look up the rank of a number x (the number of values less than or equal to x).
 * Implement the data structures and algorithms to support these operations. That is, implement
 * the method track(int x), which is called when each number is generated, and the method
 * getRankOfNumber(int x), which returns the number of values less than or equal to x (not
 * including x itself).
 * EXAMPLE
 * Stream (in order of appearance): 5,1,4,4,5,9,7,13,3
 * getRankOfNumber(1) = 0
 * getRankOfNumber(3) = 1
 * getRankOfNumber(4) = 3
 **/
public class RankFromStreamLinkedListImp {

    static HashMap<Integer, Node> ranksMap;
    static Node head;

    public static void main(String[] args) {
        int[] streams = {5, 1, 4, 4, 5, 9, 7, 13, 3};

        ranksMap = new HashMap<>();
        setStreamRank(streams);
        trackNumber(8);
        trackNumber(8);
        trackNumber(8);
        trackNumber(9);

        for (Integer key : ranksMap.keySet()) {
            System.out.println(key + " = " + ranksMap.get(key).rank);
        }

    }

    //tracking number
    //Time Complexity = O(n)
    static void trackNumber(int x) {
        Node node = new Node(x);
        Node cur = head;
        Node prev = cur;
        while (cur != null) {
            if (cur.data >= x) {
                //insert the new node
                node.next = cur;
                prev.next = node;

                //update the node rank with the previous rank
                node.rank += prev.rank + 1;
                break;
            }
            prev = cur;
            cur = cur.next;
        }

        while (cur != null) {
            cur.rank++;
            cur = cur.next;
        }


        ranksMap.putIfAbsent(x, node);
    }


    //generating the ranks of the streams
    //Time Complexity = O(n)
    static void setStreamRank(int[] streams) {

        if (streams.length == 0)
            return;

        Arrays.sort(streams);
        int curItem = streams[0];
        head = new Node(curItem);
        Node cur = head;
        int i;
        for (i = 1; i < streams.length; i++) {
            int nextItem = streams[i];
            if (curItem != nextItem) {
                cur.rank = i - 1;
                ranksMap.put(curItem, cur);

                //next items
                curItem = streams[i];
                cur.next = new Node(curItem);
                cur = cur.next;
            }

        }

        //last item
        cur.rank = i - 1;
        ranksMap.put(curItem, cur);


    }

    static class Node {
        int data;
        Node next;
        int rank;

        public Node(int data) {
            this.data = data;
        }
    }
}

class RankFromStreamBinarySearchImpl {

    static TreeNode<Integer> root;
    static int counter = 0;

    public static void main(String[] args) {
        int[] streams = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        initializeTrackNumbers(streams);
        /*trackNumber(root,8);
        trackNumber(root,8);
        trackNumber(root,8);
        trackNumber(root,9);*/
        computeRank();
        // root = buildBinarySearchTree(streams);
        inorderTrav(root);
        System.out.println();
    }

    private static void initializeTrackNumbers(int[] streams) {
        root = new TreeNode<>(streams[0]);

        for (int i = 1; i < streams.length; i++) {
            trackNumber(root, streams[i]);
        }
    }

    private static TreeNode<Integer> buildBinarySearchTree(int[] streams) {
        //sort the array
        Arrays.sort(streams);

        return buildBinarySearchTree(streams, 0, streams.length - 1);
    }

    static void inorderTrav(TreeNode<Integer> root) {
        if (root == null)
            return;

        inorderTrav(root.getLeft());
        /*int count = counter++;
        root.leftSize = count;*/
        System.out.println(root.getData() + "," + root.rank);
        inorderTrav(root.getRight());

    }

    static void computeRank() {
        computeRank(root, 0, null);
    }

    static void computeRank(TreeNode<Integer> root, int rank, TreeNode<Integer> parent) {
        //base case
        if (root == null) {
            return;
        }
        //go to the left subtree
        computeRank(root.getLeft(), rank, root);
        computeRank(root.getRight(), rank + root.leftSize + 1, root);
        root.rank = rank + root.leftSize;


    }

    static void trackNumber(TreeNode<Integer> root, int x) {

        //go to the left subtree
        if (x <= root.getData()) {

            if (root.getLeft() == null) {
                root.setLeft(new TreeNode<>(x));
            } else {
                trackNumber(root.getLeft(), x);
            }
            root.leftSize++;
        } else {

            if (root.getRight() == null)
                root.setRight(new TreeNode<>(x));
            else
                trackNumber(root.getRight(), x);
        }
    }

    private static TreeNode<Integer> buildBinarySearchTree(int[] streams, int low, int high) {

        if (high < low)
            return null;

        int mid = low + (high - low) / 2;
        TreeNode<Integer> root = new TreeNode<>(streams[mid]);
        root.setLeft(buildBinarySearchTree(streams, low, mid - 1));

        root.setRight(buildBinarySearchTree(streams, mid + 1, high));

        if (root.getLeft() == null || root.getRight() == null) {
            root.leftSize = 0;
        } else {
            int leftSize = root.getLeft().leftSize;
            root.leftSize = leftSize + 1;
        }
        return root;
    }


}
