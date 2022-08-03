package com.bis.interview_prep.treeAndGraph.hard;


/**
 * Typically, an implementation of in-order traversal of
 * a binary tree has O(h) space complexity, where h is the height
 * of the tree. Write a program to compute the in-order traversal
 * of a binary tree using O(1) space
 **/
public class InOrderTraversalMorrisTraversal {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.right = new Node(5);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(6);
        root.right.right.left.right = new Node(4);

        inOrderIterative(root);
    }

    /**
     * In this problem, we are tasked to do tree traversal with
     * space complexity of O(1).
     * Time Complexity = O(N)
     * We intend to use Morris Traversal.
     **/
    static void inOrderIterative(Node root) {
        Node current = root;
        while (current != null){

            if (current.left == null){
                System.out.print(current.data+",");
                current = current.right;
            }else{
                //find the predecessor of the current node
                Node predecessor = current.left;
                while (predecessor.right != current && predecessor.right != null){
                    predecessor = predecessor.right;
                }

                //when the rightmost node of the current is found, then link it for
                //future reference since no stack is being used.
                if (predecessor.right == null){
                    predecessor.right = current;
                    current = current.left;
                }else{
                    //restore the tree and cancel predecessor link
                    predecessor.right = null;
                    System.out.print(current.data+",");
                    current = current.right;
                }
            }
        }
    }

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
