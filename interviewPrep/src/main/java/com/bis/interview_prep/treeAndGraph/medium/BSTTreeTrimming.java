package com.bis.interview_prep.treeAndGraph.medium;

/**
 * Given the reference to the root of a binary search tree and
 * two values, low and high, update the tree such that any nodes
 * containing values that are strictly outside low and high are removed.
 * <p>
 * Ex: Given the following root, low, and high...
 * <p>
 * root = 1
 * / \
 * 2   3, low = 1, high = 2, return the tree updated as follows...
 * 1
 * /
 * 2
 **/
public class BSTTreeTrimming {

    public static void main(String[] args) {
        Node root = null;
        root = insert(root, 6);
        root = insert(root, -13);
        root = insert(root, 14);
        root = insert(root, -8);
        root = insert(root, 15);
        root = insert(root, 13);
        root = insert(root, 7);

        int low = -10, high = 13;
        inorderTraversal(root);
        Node result = removeOutsideRange(root,low,high);
        System.out.println();
        inorderTraversal(result);
    }

    /**
     * We do tree traversal here, checking the root node if
     * they are in the range of low to high.
     * We remove the node which are not in the range.
     * Time Complexity = O(N)
     **/
    static Node removeOutsideRange(Node root, int low, int high) {
        //base case
        if (root == null){
            return null;
        }

        root.left = removeOutsideRange(root.left,low,high);
        root.right = removeOutsideRange(root.right,low,high);

        if (root.key < low){
            return root.right;
        }

        if (root.key > high){
            return root.left;
        }

        return root;
    }

    public static Node newNode(int num) {
        Node temp = new Node();
        temp.key = num;
        temp.left = null;
        temp.right = null;
        return temp;
    }

    public static Node insert(Node root,
                              int key) {
        if (root == null) {
            return newNode(key);
        }
        if (root.key > key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    private static void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.key + " ");
            inorderTraversal(root.right);
        }
    }

    static class Node {
        int key;
        Node left;
        Node right;
    }
}

